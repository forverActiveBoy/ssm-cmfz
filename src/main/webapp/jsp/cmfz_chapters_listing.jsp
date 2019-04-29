<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cmfz_chapters_listing.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/icon.css">


  </head>
  
  <body>
    <table id="showAllcmfz_chapters_listing">
		<div id="lala">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="add();">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="update();">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="removeListen();">删除</a>
		</div>
		<thead>
		<tr>
			<th data-options="field:'ids',checkbox:true"></th>
			<th data-options="field:'id'">ID</th>
			<th data-options="field:'name'">名字</th>
			<th data-options="field:'listen',formatter:showListenName">专辑名</th>
			<th data-options="field:'url'">路径名</th>
			<th data-options="field:'size'">大小</th>
			<th data-options="field:'count'">下载次数</th>
			<th data-options="field:'downloadListen',width:30,formatter:downloadOneListen">下载</th>
		</tr>
		</thead>
	</table>
    <div id="addchapters_listing" class="easyui-dialog" data-options="closed:true">
    	<form id="addchapters_listingInformation" enctype="multipart/form-data" method="post">
    		<br>
    		章节名：&nbsp;<input name="name"><br>
    		章节音频：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件'
    		"><br>
    		所属专辑：<input name="listen.id" class="easyui-combobox" data-options="
    			valueField:'id',
    			textField:'name',
    			method:'get',
    			url:'${pageContext.request.contextPath}/listen/getAll'
    			" /><br>  
    		章节大小：<input name="size" class="easyui-numberbox" data-options="min:0">M<br>
    		章节下载次数：<input name="count" class="easyui-numberbox" data-options="min:0"><br>

    		<%--章节时长：<input name="chapter_duration" class="easyui-numberbox" data-options="min:0,precision:2"><br>--%>
    	</form>
        <center>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitAddAudio()">确认</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseAddAudioForm()">取消</a>
        </center>
    </div>
    <div id="updatechapters_listing" class="easyui-dialog" data-options="closed:true">
    	<form id="updatechapters_listingInformation" enctype="multipart/form-data" method="post">
    		<br>
    				<input id="chapter_id" name="id" style="display: none">
    				<input name="url" style="display: none">
    		章节名：&nbsp;<input id="chapter_name" name="name"><br>
    		章节音频：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件'
    		"><br>
    		所属专辑：<input id="listen_id" name="listen.id" class="easyui-combobox" data-options="
    			width:100" ></input><br>
    		章节大小：<input id="chapter_size" name="size" class="easyui-numberbox" data-options="min:0">M<br>
    		章节下载次数：<input id="count" name="count" class="easyui-numberbox" data-options="min:0"><br>
    		<%--
    		章节时长：<input id="chapter_duration" name="chapter_duration" class="easyui-numberbox" data-options="min:0,precision:2"><br>
    		--%>
    	</form>
        <center>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitUpdateAudio()">确认</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseUpdateAudioForm()">取消</a>
        </center>
    </div>
	<script type="text/javascript">
        $("#showAllcmfz_chapters_listing").datagrid({
			url:"${pageContext.request.contextPath}/audio",
            method:"get",
            height:500,
            fitColumns:true,//让表格自适应网页大小
            checkOnSelect:false,//是否点中该行是选中复选框按钮
            pagination:true,//显示分页插件
            pageSize:5,//每页显示5条.
            pageList:[5,10,20,50],//页面大小列表
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
			    if(rowData.image)
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/picture' + rowData.image + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.name + '</p>' +
                    '<p>Status: ' + rowData.createDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            onLoadSuccess:function(data){//加载完成事件  在easyui加载完成之前加载该方法
			    console.log("成功");
                $(".deletePersonButton").linkbutton({
                    iconCls:"icon-ok"
                });
            }
        });
        function showListenName(value,row,index) {
			if(value)
			    return value.name;
        }

        function downloadOneListen(value,row,index) {
            // value   指的是当前单元格中的数据
            // row   指的是 包含当前单元格所在行全部数据的json对象
            // index  指的是当前单元格所在行的行号
            console.log(value);
            console.log(row);
            console.log(index);
            return "<a href=\"javascript:void(0)\" class='deletePersonButton' onclick=\"downloadListen(\'"+row.id+"\',this,\'"+row.name+"\')\">下载</a>";
        }


        function downloadListen(id,obj,name) {
            console.log(id);
            console.log(obj);
            console.log(name);
        }



        function add() {
			$("#addchapters_listing").dialog("open");
			
        }
        
        function submitAddAudio() {
            $("#addchapters_listingInformation").form("submit",{
                url:"${pageContext.request.contextPath}/audio",
                type:"post",
                dataType:"json",
                success:function (data) {
                    $("#showAllcmfz_chapters_listing").datagrid("reload");
                    $("#addchapters_listing").dialog("close");
                    $("#addchapters_listingInformation").form("clear");
                }
            });
        }
        
        function colseAddAudioForm() {
            $("#addchapters_listing").dialog("close");
            $("#addchapters_listingInformation").form("clear");
        }
        
        function update() {
			var arr = $("#showAllcmfz_chapters_listing").datagrid("getChecked");
			if(arr.length == 1){
                $("#updatechapters_listingInformation").form("load",arr[0]);
                $("#listen_id").combobox({
                    valueField:'id',
                    method:'get',
                    textField:'name',
                    url:'${pageContext.request.contextPath}/listen/getAll'
                });
                $("#listen_id").combobox("select",arr[0].listen.id);
                $("#updatechapters_listing").dialog("open");
            }else{
                $.messager.alert("提示","您选择了多条或未选择数据");
            }
        }
        
        function submitUpdateAudio() {
            $("#updatechapters_listingInformation").form("submit",{
                url:"${pageContext.request.contextPath}/audio/1",
                onSubmit:function () {
                    return $("#updatechapters_listingInformation").form("validate");
                },
                success:function (str) {
                    //var map = JSON.parse(str);
                    $("#showAllcmfz_chapters_listing").datagrid("reload");
                    $("#updatechapters_listing").dialog("close");
                    $("#updatechapters_listingInformation").form("clear");
                }
            });
        }
        
        function colseUpdateAudioForm() {
            $("#updatechapters_listing").dialog("close");
            $("#updatechapters_listingInformation").form("clear");
        }

        function removeListen() {
            var arr = $("#showAllcmfz_chapters_listing").datagrid("getChecked");
            console.log(arr);
            var ids = "";
            $.each(arr,function (index,obj) {
                ids += "&ids="+obj.id;
            });
            ids = ids.substring(1);
            console.log(ids);
            $.ajax({
                url:"${pageContext.request.contextPath}/audio?"+ids,
                type:"delete",
                dataType:"json",
                success:function (data) {
                    console.log("111");
                    $("#showAllcmfz_chapters_listing").datagrid("reload");
                }
            });
        }

	</script>
  </body>
</html>
