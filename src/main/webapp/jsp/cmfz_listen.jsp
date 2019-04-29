<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cmfz_listen.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/icon.css">


  </head>
  
  <body>
    <table id="showAllcmfz_listen">
		<div id="lala">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="addListen();">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="updateListen();">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="removeListen();">删除</a>
		</div>
		<thead>
		<tr>
			<th data-options="field:'ids',checkbox:true"></th>
			<th data-options="field:'id'">ID</th>
			<th data-options="field:'name'">专辑名字</th>
			<th data-options="field:'author'">作者</th>
			<th data-options="field:'teller'">播音员</th>
			<th data-options="field:'episodes'">章节数</th>
			<th data-options="field:'createDate'">上架时间</th>
			<th data-options="field:'content'">专辑详情</th>
			<th data-options="field:'image'">专辑图片路径</th>
			<th data-options="field:'star'">热度</th>
		</tr>
		</thead>
	</table>
    <div id="addListen"  class="easyui-dialog" data-options="closed:true">
    	<form id="addListenInformation" enctype="multipart/form-data" method="post">
    		<br>
    		专辑名：&nbsp;<input name="name"><br>
    		专辑封面：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件',
    			width:160,
    			height:20
    		"><br>
    		专辑作者：<input name="author"><br>
    		播音员：&nbsp;<input name="teller"><br>
    				
    		专辑评分：<input name="star" class="easyui-numberbox" data-options="min:0,width:160,height:20"><br>
    		专辑集数：<input name="episodes" class="easyui-numberbox" data-options="min:0,width:160,height:20"><br>
    		
    		出版时间：<input class="Wdate" type="text" name="createDate" onClick="WdatePicker()"><br>
    		专辑简介：<br>
    				&nbsp;&nbsp;&nbsp;
    				<textarea name="content" rows="5px" cols="20px"></textarea>
    	</form>
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitAddListen()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseAddListen()">取消</a>
		</center>
    </div>
    <div id="updateListen"  class="easyui-dialog" data-options="closed:true">
    	<form id="updateListenInformation" enctype="multipart/form-data" method="post">
    		<br>
    				<input id="listen_id" name="id" style="display: none">
    				<input  name="image" style="display: none">
    		专辑名：&nbsp;<input name="name" id="listen_name"><br>
    		专辑封面：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件'
    		"><br>
    		专辑作者：<input name="author" id="listen_author"><br>
    		播音员：&nbsp;<input name="teller" id="listen_announcer"><br>
    				
    		专辑评分：<input name="star" id="listen_star" class="easyui-numberbox" data-options="min:0"><br>
    		专辑集数：<input name="episodes" id="listen_amount" class="easyui-numberbox" data-options="min:0"><br>
    		专辑简介：<br><br>
    				      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				      <textarea name="content" id="listen_brief_introduction" rows="5px" cols="20px"></textarea>
    	</form>
    		<p id="listen_picture"><img src="" id="listenImage"></p>
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitUpdateListen()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseUpdateListenForm()">取消</a>
		</center>
    </div>
	<script type="text/javascript">
        $("#showAllcmfz_listen").datagrid({
            url:"${pageContext.request.contextPath}/listen",
            method:"get",
            height:500,
            fitColumns:true,//让表格自适应网页大小
            checkOnSelect:false,//是否点中该行是选中复选框按钮
            pagination:true,//显示分页插件
            pageSize:5,//每页显示5条.
            pageList:[5,10,20,50],//页面大小列表
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/picture' + rowData.image + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.name + '</p>' +
                    '<p>Status: ' + rowData.createDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });
        
        
        function addListen() {
			$("#addListen").dialog("open");
        }

		
        function submitAddListen() {
            $("#addListenInformation").form("submit",{
                url:"${pageContext.request.contextPath}/listen",
                dataType:"json",
                success:function (data) {
                    $("#addListen").dialog("close");
                    $("#addListenInformation").form("clear");
                    $("#showAllcmfz_listen").datagrid("reload");
                }
            });
        }
        
        function colseAddListen() {
            $("#addListen").dialog("close");
            $("#addListenInformation").form("clear");

        }


        function updateListen() {
            var arr = $("#showAllcmfz_listen").datagrid("getChecked");
            if(arr.length == 1){
                console.log(arr[0]);
                $("#listenImage").prop("src","${pageContext.request.contextPath}/picture/"+arr[0].image);
                $("#updateListenInformation").form("load",arr[0]);
                $("#updateListen").dialog("open");
            }else{
                $.messager.alert("提示","您选择了多条或未选择数据");
            }
        }
		
        
        function submitUpdateListen() {
            $("#updateListenInformation").form("submit",{
                url:"${pageContext.request.contextPath}/listen/id",
                onSubmit:function () {
                    return $("#updateListenInformation").form("validate");
                },
                success:function (str) {
                    //var map = JSON.parse(str);
                    $("#showAllcmfz_listen").datagrid("reload");
                    $("#updateListen").dialog("close");
                    $("#updateListenInformation").form("clear");
                }
            });
        }
        
        function colseUpdateListenForm() {
            $("#updateListen").dialog("close");
            $("#updateListenInformation").form("clear");
        }


        function removeListen() {
            var arr = $("#showAllcmfz_listen").datagrid("getChecked");
            console.log(arr);
            var ids = "";
            $.each(arr,function (index,obj) {
                ids += "&ids="+obj.id;
            });
            ids = ids.substring(1);
            console.log(ids);
            $.ajax({
                url:"${pageContext.request.contextPath}/listen/id?"+ids,
                type:"delete",
                dataType:"json",
                success:function (data) {
                    console.log("111");
                    $("#showAllcmfz_listen").datagrid("load");
                }
            });
        }
	</script>
  </body>
</html>
