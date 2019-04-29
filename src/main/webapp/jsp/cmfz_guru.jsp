<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cmfz_guru.jsp' starting page</title>
    
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
    <table id="showAllcmfz_guru">
		<div id="lala">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="addGuru();">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="updateGuru();">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="removeGuru();">删除</a>
		</div>
		<thead>
			<tr>
				<th data-options="field:'ids',checkbox:true"></th>
				<th data-options="field:'id'">ID</th>
				<th data-options="field:'name'">上师名字</th>
				<th data-options="field:'image'">上师图片地址</th>
				<th data-options="field:'nikename'">上师法号</th>
			</tr>
		</thead>

	</table>
    <div id="addguru"  class="easyui-dialog" data-options="closed:true">
    	<form id="addguruInformation" enctype="multipart/form-data" method="post">
    		上师名字：<input name="name"><br>
    		上师法号：<input name="nikename"><br>
    		上师照片：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件',
    			width:150,
    			height:20
    		">
    	</form>
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitAddGuru()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseAddGuruForm()">取消</a>
		</center>
    </div>
    <div id="updateguru"  class="easyui-dialog" data-options="closed:true">
    	<form id="updateguruInformation" enctype="multipart/form-data" method="post">
    				<input id="guru_id" name="id" style="display: none">
    				<input name="image" style="display: none">
    		上师名字：<input id="guru_name" name="name"><br>
    		上师法号：<input name="nikename"><br>
    		上师照片：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件',
    			width:150,
    			height:20
    		">
    	</form>
    		<p id="guru_picture"><img src="" id="guruImage"> </p>

		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitUpdateGuru()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseUpdateGuruForm()">取消</a>
		</center>
    </div>
	<script type="text/javascript">
        $("#showAllcmfz_guru").datagrid({
            url:"${pageContext.request.contextPath}/guru",
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
                    '<p>Status: ' + rowData.nikename + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });



        function addGuru() {
			$("#addguru").dialog("open");

        }
        
        
        function submitAddGuru() {
            $("#addguruInformation").form("submit",{
                url:"${pageContext.request.contextPath}/guru",
                type:"post",
                dataType:"json",
                success:function (data) {
                    $("#addguru").dialog("close");
                    $("#addguruInformation").form("clear");
                    $("#showAllcmfz_guru").datagrid("reload");
                }
            });
        }
        
        function colseAddGuruForm() {
            $("#addguru").dialog("close");
            $("#addguruInformation").form("clear");
        }
		

        function updateGuru() {
            var arr = $("#showAllcmfz_guru").datagrid("getChecked");
            if(arr.length == 1){
                console.log(arr[0]);
                $("#guruImage").prop("src","${pageContext.request.contextPath}/picture/"+arr[0].image);
                $("#updateguruInformation").form("load",arr[0]);
                $("#updateguru").dialog("open");
			}else{
                $.messager.alert("提示","您选择了多条或未选择数据");
			}

        }

        function submitUpdateGuru() {
            $("#updateguruInformation").form("submit",{
                url:"${pageContext.request.contextPath}/guru/1",
                onSubmit:function () {
                    return $("#updateBanform").form("validate");
                },
                success:function (str) {
                    //var map = JSON.parse(str);
                    $("#showAllcmfz_guru").datagrid("reload");
                    $("#updateguru").dialog("close");
                    $("#updateguruInformation").form("clear");
                }
            });
        }

        function colseUpdateGuruForm() {
            $("#updateguru").dialog("close");
            $("#updateguruInformation").form("clear");
        }


        function removeGuru() {
            var arr = $("#showAllcmfz_guru").datagrid("getChecked");
            console.log(arr);
            var ids = "";
            $.each(arr,function (index,obj) {
                ids += "&ids="+obj.id;
            });
            ids = ids.substring(1);
            console.log(ids);
            $.ajax({
                url:"${pageContext.request.contextPath}/guru/id?"+ids,
                type:"delete",
                dataType:"json",
                success:function (data) {
                    console.log("111");
                    $("#showAllcmfz_guru").datagrid("load");
                }
            });
        }
	</script>
  </body>
</html>
