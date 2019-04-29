<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cmfz_user_category.jsp' starting page</title>
    
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
    <table id="showAllcmfz_user_category">
		<div id="lala">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="add();">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="update();">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="removeCategory();">删除</a>
		</div>
		<thead>
		<tr>
			<th data-options="field:'ids',checkbox:true"></th>
			<th data-options="field:'id'">ID</th>
			<th data-options="field:'name'">功课名字</th>
		</tr>
		</thead>
	</table>
    <div id="addCategory" class="easyui-dialog" data-options="closed:true">
    	<form id="addCategoryInformation" method="post">
    		<br>
    			功课名：&nbsp;<input name="name"><br>
    	</form>
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitAddCategory()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseAddCategoryForm()">取消</a>
		</center>
    </div>
    <div id="updateCategory" class="easyui-dialog" data-options="closed:true">
    	<form id="updateCategoryInformation" method="post">
    		<br>
    				<input id="category_id" name="id" style="display: none">
    			功课名：&nbsp;<input id="category_name" name="name"><br>
			</form>
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitUpdateCategory()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseUpdateCategoryForm()">取消</a>
		</center>
    </div>
	<script type="text/javascript">
      $("#showAllcmfz_user_category").datagrid({
		  url:"${pageContext.request.contextPath}/category",
		  method:"get",
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
          }
	  });

      function removeCategory() {
          var arr = $("#showAllcmfz_user_category").datagrid("getChecked");
          console.log(arr);
          var ids = "";
          $.each(arr,function (index,obj) {
              ids += "&ids="+obj.id;
          });
          ids = ids.substring(1);
          console.log(ids);
          $.ajax({
              url:"${pageContext.request.contextPath}/category?"+ids,
              type:"delete",
              dataType:"json",
              success:function (data) {
                  console.log("111");
                  $("#showAllcmfz_user_category").datagrid("reload");
              }
          });
      }
      
      function add() {
          $("#addCategory").dialog("open");
      }
      
      
      function submitAddCategory() {
          $("#addCategoryInformation").form("submit",{
              url:"${pageContext.request.contextPath}/category",
              dataType:"json",
              success:function (data) {
                  $("#showAllcmfz_user_category").datagrid("reload");
                  $("#addCategory").dialog("close");
                  $("#addCategoryInformation").form("clear");
              }
          });
      }
      
      function colseAddCategoryForm() {
          $("#addCategory").dialog("close");
          $("#addCategoryInformation").form("clear");
      }
      
      
      function update() {
          var arr = $("#showAllcmfz_user_category").datagrid("getChecked");
          if(arr.length == 1){
              $("#updateCategoryInformation").form("load",arr[0]);
              $("#updateCategory").dialog("open");
          }else{
              $.messager.alert("提示","您选择了多条或未选择数据");
          }
      }
      
      function submitUpdateCategory() {
          $("#updateCategoryInformation").form("submit",{
              url:"${pageContext.request.contextPath}/category/update",
              onSubmit:function () {
                  return $("#updateCategoryInformation").form("validate");
              },
              success:function (str) {
                  //var map = JSON.parse(str);
                  $("#showAllcmfz_user_category").datagrid("reload");
                  $("#updateCategory").dialog("close");
                  $("#updateCategoryInformation").form("clear");
              }
          });
      }
      
      function colseUpdateCategoryForm() {
          $("#updateCategory").dialog("close");
          $("#updateCategoryInformation").form("clear");
      }

	</script>
  </body>
</html>
