<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cmfz_banner.jsp' starting page</title>
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
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
	  <script type="text/javascript" charset="utf-8"  src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/icon.css">


  </head>
  
  <body>
 
    <table id="showAllcmfz_banner">
		<thead>
			<tr>
				<th data-options="field:'ids',checkbox:true"></th>
				<th data-options="field:'id'">ID</th>
				<th data-options="field:'image'">图片</th>
				<th data-options="field:'name'">名字</th>
				<th data-options="field:'status',formatter:showStatus">是否展示</th>
				<th data-options="field:'dec',formatter:showDec">位置</th>
			</tr>
		</thead>

	</table>
	<!-- 菜单按钮组 -->
	<div id="itemSearch">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',onClick:showAddBanner">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',onClick:showUpdateBanner">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',onClick:deleteBanners">删除</a>
		<input class="easyui-searchbox"/>
	</div>

	<%--添加Banner--%>
	<div id="addBandia" class="easyui-dialog" data-options="closed:true">
		<form id="addBanform" style="margin:30px" enctype="multipart/form-data" method="post">
			<table>
				<tr>
					<td>
						图片名字：<input class="easyui-validatebox" id="bannerPrice" name="name" data-options="required:true,missingMessage:'请输入图片片名'"/> <br/><br/>
						轮播图图片：<input class="easyui-filebox"  name="uploadFile" data-options="missingMessage:'请选择图片'"/><br/><br/>
					</td>
					<td>
						是否展示：展示：<input type="radio" name="status" value="y">
						不展示：<input type="radio" name="status" value="n">
						<br/><br/>
					</td>
				</tr>
				<tr>
					<td>
						展示位置：展示在首页：<input type="radio" name="dec" value="0">
						展示功课背景：<input type="radio" name="dec" value="1">
					</td>
				</tr>
			</table>
		</form>
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitAddBanner()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseAddBanForm()">取消</a>
		</center>
	</div>


	<%--更新Banner--%>
	<div id="updateBandia" class="easyui-dialog" data-options="closed:true">
		<form id="updateBanform" style="margin:30px" enctype="multipart/form-data" method="post">
			<table>
				<tr>
					<td>
						ID：<input class="easyui-validatebox" id="proName" name="id" readonly="readonly"/><br/><br/>
						轮播图图片：<input class="easyui-filebox"  name="uploadFile" data-options="missingMessage:'请选择图片'"/><br/><br/>
					</td>
					<td>
						图片名字：<input class="easyui-validatebox" id="proPrice" name="name" data-options="required:true,missingMessage:'请输入图片片名'"/> <br/><br/>
						是否展示：展示：<input type="radio" name="status" value="y">
                                不展示：<input type="radio" name="status" value="n">
                                    <br/><br/>
					</td>
				</tr>
				<tr>
					<td>
						展示位置：展示在首页：<input type="radio" name="dec" value="0">
						展示功课背景：<input type="radio" name="dec" value="1">
					</td>
				</tr>
				<tr><td colspan="2" align="center"><p id="image"><img id="showBannerImage" width="40" height="70">
					<input id="productImage111" name="image" style="display: none"/>
				</p></td></tr>
			</table>
		</form>
		<center>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitUpdateBanner()">确认</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseUpdateBanForm()">取消</a>
		</center>
	</div>

	<%--加载数据--%>
  	<script>
		$("#showAllcmfz_banner").datagrid({
			url:"${pageContext.request.contextPath}/banner",
			method:"get",
            height:500,
            fitColumns:true,//让表格自适应网页大小
            checkOnSelect:false,//是否点中该行是选中复选框按钮
            pagination:true,//显示分页插件
            pageSize:5,//每页显示5条.
            pageList:[5,10,20,50],//页面大小列表
			toolbar:"#itemSearch",
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.image + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.dec + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
		});
		/*function showImages(value,row,index){
		    console.log(row.image);
		    if(row.image)
		    return "<img width='50' height='100' src='${pageContext.request.contextPath}/"+row.image+"'>";
		}*/
        function showStatus(value,row,index){
		    console.log(row)
		    if(row.status)
            if(row.status == "y"){
                return "展示";
			}else
            return "不展示";
        }
        function showDec(value,row,index) {
			    if(row.dec == 0){
			        return "展示在首页上";
				}else{
			        return "展示在功课计数器上";
				}
        }
	</script>

  	<!--添加-->
	<script>
        function showAddBanner(){
            $("#addBandia").dialog("open");
		}

		function submitAddBanner() {
			$("#addBanform").form("submit",{
			    url:"${pageContext.request.contextPath}/banner",
				type:"post",
				dataType:"json",
				success:function (data) {
                    $("#addBandia").dialog("close");
                    $("#addBanform").form("clear");
                }
			});
        }



        function colseAddBanForm() {
            $("#addBandia").dialog("close");
            $("#addBanform").form("clear");
        }
	</script>




	<!--删除-->
	<script>
        function deleteBanners(){
			var arr = $("#showAllcmfz_banner").datagrid("getChecked");
			console.log(arr);
            var ids = "";
            $.each(arr,function (index,obj) {
                ids += "&ids="+obj.id;
            });
            ids = ids.substring(1);
            console.log(ids);
            $.ajax({
                url:"${pageContext.request.contextPath}/banner/id?"+ids,
                type:"delete",
                dataType:"json",
                success:function (data) {
                    console.log("111");
                    $("#showAllcmfz_banner").datagrid("load");
                }
            });
        }
	</script>




    <!--修改-->
	<script>
        /*打开修改窗口  并填充数据*/
        function showUpdateBanner() {
            var obj = $("#showAllcmfz_banner").datagrid("getChecked");
            if(obj.length == 1){
				$("#updateBanform").form("load","${pageContext.request.contextPath}/banner/"+obj[0].id);
                $("#updateBandia").dialog("open");
                $("#showBannerImage").prop("src","${pageContext.request.contextPath}/"+obj[0].image);
			}else{
                $.messager.alert("提示","您选择了多条或未选择数据");
			}

        }
        /*提交修改表单并提交数据*/
        function submitUpdateBanner() {
			$("#updateBanform").form("submit",{
			    url:"${pageContext.request.contextPath}/banner/1",
				onSubmit:function () {
                    return $("#updateBanform").form("validate");
                },
				success:function (str) {
                    //var map = JSON.parse(str);
                    $("#showAllcmfz_banner").datagrid("reload");
                    $("#updateBandia").dialog("close");
                    $("#updateBanform").form("clear");
                }
			});
        }
        function colseUpdateBanForm() {
            $("#updateBandia").dialog("close");
            $("#updateBanform").form("clear");
        }
	</script>
    <!--搜索-->
</body>
</html>
