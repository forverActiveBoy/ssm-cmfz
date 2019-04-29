package com.baizhi.cmfz.utils;

import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author:bobo大人
 * createDate:2018/8/10
 * createTime:16:28
 * description:
 */
public class PoiUtils {
    //导出文件
    public HSSFWorkbook exportUser(List<User> userList){

        //2. 编写poi代码
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作簿
        HSSFSheet sheet = workbook.createSheet("student");


        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        HSSFCellStyle dateCellStyle=workbook.createCellStyle();
        short df=workbook.createDataFormat().getFormat("yyyy年MM月dd日 HH时mm分ss秒");
        dateCellStyle.setDataFormat(df);*/

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户ID");
        row.createCell(1).setCellValue("手机号码");
        row.createCell(2).setCellValue("用户密码");
        row.createCell(3).setCellValue("用户头像保存路径");
        row.createCell(4).setCellValue("用户昵称");
        row.createCell(5).setCellValue("用户名");
        row.createCell(6).setCellValue("性别");
        row.createCell(7).setCellValue("用户简介/签名");
        row.createCell(8).setCellValue("用户所在省");
        row.createCell(9).setCellValue("用户所在市");
        row.createCell(10).setCellValue("用户所从上师名");
        row.createCell(11).setCellValue("用户所从上师ID");

        for(int i = 0;i<userList.size();i++){
            User user = userList.get(i);
            HSSFRow row1 = sheet.createRow(i+1);
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getTelphone());
            row1.createCell(2).setCellValue(user.getPassword());
            row1.createCell(3).setCellValue(user.getImageName());
            row1.createCell(4).setCellValue(user.getNikename());
            row1.createCell(5).setCellValue(user.getName());
            row1.createCell(6).setCellValue(user.getSex());
            row1.createCell(7).setCellValue(user.getAutograph());
            row1.createCell(8).setCellValue(user.getUserSheng());
            row1.createCell(9).setCellValue(user.getUserShi());
            row1.createCell(10).setCellValue(user.getGuru().getNikename());
            row1.createCell(11).setCellValue(user.getGuru().getId());
        }
        return workbook;
    }

    public List<User> importUser(MultipartFile file){
        List<User> userList = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheetAt = workbook.getSheetAt(0);
            // 获取所有行
            int hang = sheetAt.getPhysicalNumberOfRows();

            for(int i=1;i<hang;i++){
                int id = (int)sheetAt.getRow(i).getCell(0).getNumericCellValue();
                String telphone = sheetAt.getRow(i).getCell(1).getStringCellValue();
                String password = sheetAt.getRow(i).getCell(2).getStringCellValue();
                String imageName = sheetAt.getRow(i).getCell(3).getStringCellValue();
                String nikename = sheetAt.getRow(i).getCell(4).getStringCellValue();
                String name = sheetAt.getRow(i).getCell(5).getStringCellValue();
                String sex = sheetAt.getRow(i).getCell(6).getStringCellValue();
                String autograph = sheetAt.getRow(i).getCell(7).getStringCellValue();
                String userSheng = sheetAt.getRow(i).getCell(8).getStringCellValue();
                String userShi = sheetAt.getRow(i).getCell(9).getStringCellValue();
                String guruname = sheetAt.getRow(i).getCell(10).getStringCellValue();
                String guruId = sheetAt.getRow(i).getCell(11).getStringCellValue();
                User user = new User();
                user.setId(id);
                user.setTelphone(telphone);
                user.setPassword(password);
                user.setImageName(imageName);
                user.setNikename(nikename);
                user.setName(name);
                user.setSex(sex);
                user.setAutograph(autograph);
                user.setUserSheng(userSheng);
                user.setUserShi(userShi);
                Guru guru = new Guru();
                guru.setId(guruId);
                guru.setNikename(guruname);
                user.setGuru(guru);
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
