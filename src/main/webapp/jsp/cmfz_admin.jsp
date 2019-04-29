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
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui.css">
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/icon.css">

  </head>
  <body>
    <table id="showAllcmfz_user">
    		<div id="lele"> 
               <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="add()">添加</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="updataUser();">修改</a>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'">删除</a>
            </div>
		<thead>
			<tr>
				<th data-options="field:'ids',checkbox:true"></th>
				<th data-options="field:'id'">ID</th>
				<th data-options="field:'username'">用户账号</th>
				<th data-options="field:'password'">密码</th>
				<th data-options="field:'beforepassword'">加密前密码</th>
				<th data-options="field:'passwordsalt'">密码盐值</th>
				<th data-options="field:'roleList',formatter:showRole">角色</th>
			</tr>
		</thead>
    </table>
    <div id="addUser" class="easyui-dialog" data-options="closed:true">
    	<form id="addUserInformation"  method="post" style="margin-left: 50px">
    		<br>
    		用户名：&nbsp;<input id="addusername" name="username"><br>
    		用户密码：&nbsp;<input id="addbeforepassword" name="beforepassword"><br>
    		角色分配：&nbsp;<input id="roleid" name="role.id"><br>
    		<br>
    		<br>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<a class="easyui-linkbutton" data-options="plain:true,width:60,iconCls:'icon-add',onClick:submitAddAdmin" >确定</a>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<a class="easyui-linkbutton" data-options="plain:true,width:60,iconCls:'icon-remove',onClick:cancleAddAdmin" >取消</a>
    	</form>
    </div>
    <div id="updateUser" class="easyui-dialog" data-options="closed:true">
    	<form id="updateUserInformation" enctype="multipart/form-data" method="post">
    		<br>
    		用户名：&nbsp;&nbsp;&nbsp;<input name="username" id="upUserName" readOnly><br>
    		<input name="id" id="upId" hidden="hidden">
    		<input name="passwordsalt" id="upPasswordSalt" hidden="hidden">
    		用户密码：&nbsp;<input name="beforepassword" id="upPassWord"><br>
    		角色分配：&nbsp;<input width="100" id="cc"></input>
    		<br>
    		<br>
    		<div id="lalala">
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<a class="easyui-linkbutton" data-options="plain:true,width:60,iconCls:'icon-add'" onClick="upUser();">确定</a>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<a class="easyui-linkbutton" data-options="plain:true,width:60,iconCls:'icon-remove'" onClick="quitAdmin();">取消</a>
    		</div>
    	</form>
    </div>
	<div>
	
	</div>
	<script type="text/javascript">
		$("#showAllcmfz_user").datagrid({
			url:"${pageContext.request.contextPath}/admin",
			method:"get",
			height:500,
            fitColumns:true,//让表格自适应网页大小
            checkOnSelect:false,//是否点中该行是选中复选框按钮
            pagination:true,//显示分页插件
            pageSize:5,//每页显示5条.
            pageList:[5,10,20,50],//页面大小列表
		});
		
		function add() {
		    /*$("#roleid").combotree({
				url:"${pageContext.request.contextPath}/role/getAll",
				method:"get",
				valueField:"id",
				textField:"rolename"
			})*/
            $("#roleid").combotree();
			$.ajax({
				url:"${pageContext.request.contextPath}/role/getAll",
				type:"get",
				success:function (data) {
					console.log(data);
                    var treedata = new Array();
					$.each(data,function (index,value) {
						treedata[index] = {id:value.id,text:value.rolename,};
                    });
					console.log(treedata)
                    $("#roleid").combotree("loadData",treedata);
                    $("#roleid").combotree({
                        checkbox:true,
                        multiple:true
                    });
                }
			});

			$("#addUser").dialog("open");
        }

        function submitAddAdmin() {

            var t = $('#roleid').combotree('tree');	// 获取树对象
            var nodes = t.tree('getChecked');
			var ids ="";
            $.each(nodes,function (index,value) {
				ids+="&ids="+value.id;
            });
			ids = ids.substring(1);
            var beforepassword = $("#addbeforepassword").val();
            var username = $("#addusername").val();
            ids += "&username="+username+"&beforepassword="+beforepassword;
            $.ajax({
                url:"${pageContext.request.contextPath}/admin",
                type:"post",
				data:ids,
                dataType:"json",
                success:function (data) {
                    $("#showAllcmfz_user").datagrid("reload");
                    $("#addUser").dialog("close");
                    $("#addUserInformation").form("clear");
                }
            });


            /*$("#addUserInformation").form("submit",{
                url:"${pageContext.request.contextPath}/admin",
                type:"post",
                dataType:"json",
                success:function (data) {
                    $("#showAllcmfz_user").datagrid("reload");
                    $("#addUser").dialog("close");
                    $("#addUserInformation").form("clear");
                }
            });*/
        }

        function cancleAddAdmin() {
            $("#addUser").dialog("close");
            $("#addUserInformation").form("clear");
        }
        
        
        function showRole(value,row,index) {
            var content = "<select>";
			if(value&&value.length!=0){
                console.log(value);

				$.each(value,function (i,val) {
					content+="<option value='"+val.id+"'>"+val.rolename+"</option>"
                })
                content += "</select>";

			}
            return content;
        }

        function updataUser() {

            var arr = $("#showAllcmfz_user").datagrid("getChecked");
            if(arr.length == 1){
                $("#updateUserInformation").form("load",arr[0]);
				var roleList = arr[0].roleList;

                $("#cc").combotree();
                $.ajax({
                    url:"${pageContext.request.contextPath}/role/getAll",
                    type:"get",
                    success:function (data) {
                        console.log(data);
                        var treedata = new Array();
                        $.each(data,function (index,value) {
                            treedata[index] = {id:value.id,text:value.rolename,};
                        });
                        console.log(treedata)
                        $("#cc").combotree("loadData",treedata);
                        $("#cc").combotree({
                            checkbox:true,
                            multiple:true
                        });
                        var t = $('#cc').combotree('tree');
						$.each(arr[0].roleList,function (i,v) {
                            var node = t.tree("find",v.id);
                            if (node){
                                t.tree("check",node.target);
                            }
                        });
                    }
                });





                $("#updateUser").dialog("open");
            }else{
                $.messager.alert("提示","您选择了多条或未选择数据");
            }


			$("#updateUser").dialog("open");
        }

        function upUser() {
            var t = $('#cc').combotree('tree');	// 获取树对象
            var nodes = t.tree('getChecked');
            var ids ="";
            $.each(nodes,function (index,value) {
                ids+="&ids="+value.id;
            });
            ids = ids.substring(1);

            var beforepassword = $("#upPassWord").val();
            var username = $("#upUserName").val();
            var id = $("#upId").val();
            var salt = $("#upPasswordSalt").val();
            ids += "&username="+username+"&beforepassword="+beforepassword+"&id="+id+"&passwordsalt="+salt;
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/update",
                type:"post",
                data:ids,
                dataType:"json",
                success:function (data) {
                    $("#showAllcmfz_user").datagrid("reload");
                    $("#updateUser").dialog("close");
                    $("#updateUserInformation").form("clear");
                }
            });

        }

        function quitAdmin() {
            $("#updateUserInformation").form("clear");
            $("#updateUser").dialog("close");
        }

	</script>
	 </body>
</html>
