<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cmfz_userlog.jsp' starting page</title>
    
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

	  <script type="text/javascript">
	
	</script>
  </head>
  <body>
    <table id="showAllcmfz_userlog">
		<div id="lala">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="removeLog();">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="downloadLog();">导出日志文件到本地</a>
		</div>
		<thead>
			<tr>
				<th data-options="field:'ids',checkbox:true"></th>
				<th data-options="field:'id'">ID</th>
				<th data-options="field:'methodname'">方法名</th>
				<th data-options="field:'createdate'">操作时间</th>
				<th data-options="field:'consumetime'">耗时</th>
				<th data-options="field:'username'">操作者</th>
				<th data-options="field:'result'">操作结果</th>
			</tr>
		</thead>
	</table>
	</body>
  <script>
	  $("#showAllcmfz_userlog").datagrid({
		  url:"${pageContext.request.contextPath}/cmfzlog",
          method:"get",
          height:500,
          fitColumns:true,//让表格自适应网页大小
          checkOnSelect:false,//是否点中该行是选中复选框按钮
          pagination:true,//显示分页插件
          pageSize:15,//每页显示5条.
          pageList:[15,30,50],//页面大小列表
          toolbar:"#itemSearch",
          view: detailview,
          detailFormatter: function(rowIndex, rowData){
              return '<table><tr>' +
                  '<td rowspan=2 style="border:0"><img src="" style="height:50px;"></td>' +
                  '<td style="border:0">' +
                  '<p>Attribute: ' + rowData.dec + '</p>' +
                  '<p>Status: ' + rowData.status + '</p>' +
                  '</td>' +
                  '</tr></table>';
          },
          onLoadSuccess:function (note,data) {
			  console.log(note);
			  console.log(data);
          }
	  });



	  function removeLog() {
          var arr = $("#showAllcmfz_userlog").datagrid("getChecked");
          console.log(arr);
          var ids = "";
          $.each(arr,function (index,obj) {
              ids += "&ids="+obj.id;
          });
          ids = ids.substring(1);
          console.log(ids);
          $.ajax({
              url:"${pageContext.request.contextPath}/cmfzlog?"+ids,
              type:"delete",
              dataType:"json",
              success:function (data) {
                  console.log(data);
                  $("#showAllcmfz_userlog").datagrid("reload");
              }
          });
      }
      
      function downloadLog() {
		  $.ajax({
              url:"${pageContext.request.contextPath}/cmfzlog/out",
              type:"get"
		  });
      }
  </script>
</html>
