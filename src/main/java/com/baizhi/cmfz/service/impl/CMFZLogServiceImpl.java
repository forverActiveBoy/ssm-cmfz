package com.baizhi.cmfz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.baizhi.cmfz.dao.CMFZLogDAO;
import com.baizhi.cmfz.entity.CMFZLog;
import com.baizhi.cmfz.service.CMFZLogService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/9
 * createTime:19:20
 * description:
 */
@Service
public class CMFZLogServiceImpl implements CMFZLogService {
    @Autowired
    private CMFZLogDAO cmfzLogDAO;
    @Override
    public Map<String,Object> getAll(Integer page, Integer rows) {
        Integer start = (page -1)*rows;
        List<CMFZLog> cmfzLogList = cmfzLogDAO.selectAll(start, rows);
        int count = cmfzLogDAO.selectCount();
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",cmfzLogList);
        return map;
    }

    @Override
    public int deletes(int[] ids) {
        int i = cmfzLogDAO.deletes(ids);
        return i;
    }

    @Override
    public int addCMFZLog(CMFZLog cmfzLog) {
        return cmfzLogDAO.insert(cmfzLog);
    }

    @Override
    public int outCMFZLog() {

        // 1. 将数据以文件的形式保存至本地
        // 负责数据与文件之间的传输
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet log = workbook.createSheet("log");

        List<CMFZLog> cmfzLogList = cmfzLogDAO.selectAllCMFZlog();

        HSSFRow row1 = log.createRow(0);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        HSSFCellStyle dateCellStyle=workbook.createCellStyle();
        short df=workbook.createDataFormat().getFormat("yyyy年MM月dd日 HH时mm分ss秒");
        dateCellStyle.setDataFormat(df);


        row1.createCell(0).setCellValue("ID");
        row1.createCell(1).setCellValue("方法名");
        row1.createCell(2).setCellValue("开始时间");
        row1.createCell(3).setCellValue("耗时");
        row1.createCell(4).setCellValue("操作人");
        row1.createCell(4).setCellValue("操作结果");
        for (int i = 1; i<cmfzLogList.size() ;i++) {
            HSSFRow row = log.createRow(i);
            CMFZLog cmfzLog = cmfzLogList.get(i);
            row.createCell(0).setCellValue(cmfzLog.getId());
            row.createCell(1).setCellValue(cmfzLog.getMethodname());

            HSSFCell cell = row.createCell(2);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(cmfzLog.getCreatedate());

            row.createCell(3).setCellValue(cmfzLog.getConsumetime());
            row.createCell(4).setCellValue(cmfzLog.getUsername());
            row.createCell(4).setCellValue(cmfzLog.getResult());
        }
        try {
            FileOutputStream fos = new FileOutputStream("D://1.xls");
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
