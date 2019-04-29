<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <table id="showAllcmfz_role">
		<div id="toolbar">
			<shiro:hasPermission name="role:create">
				<a href="javascript:void(0)" class="easyui-linkbutton" onClick="addRole();" data-options="iconCls:'icon-add'"></a>
			</shiro:hasPermission>

			<shiro:hasPermission name="role:update">
				<a href="javascript:void(0)" class="easyui-linkbutton"  onClick="uRole();" data-options="iconCls:'icon-edit'"></a>
			</shiro:hasPermission>

			<shiro:hasPermission name="role:delete">
				<a href="javascript:void(0)" class="easyui-linkbutton"  onClick="removeRole();" data-options="iconCls:'icon-remove'"></a>
			</shiro:hasPermission>


		</div>
		<thead>
			<tr>
				<th data-options="field:'ids',checkbox:true"></th>
				<th data-options="field:'id'">ID</th>
				<th data-options="field:'rolename'">角色名</th>
				<th data-options="field:'resourceList',formatter:showResouorce">资源</th>
			</tr>
		</thead>

	</table>

    
    <div id="addRole" class="easyui-dialog" data-options="closed:true">
    	<form id="addRoleform" method="post">
    		<ul id="t1" class="easyui-tree"></ul>
    		角色名字：<input id="rolenametoadd" name="rolename" type="text" class="easyui-validatebox" data-options="required:true">
    		<br/>
    		<br/>
    		<div align="center">
    		<input onClick="confirmRole();" class="easyui-linkbutton" value="确定" data-options="width:50"/>
    		<input class="easyui-linkbutton" onClick="addQuit();" value="取消" data-options="width:50"/>
    		</div>
    	</form>
    </div>
        <div id="updateRole" class="easyui-dialog" data-options="closed:true">
    	<form id="updateRoleform" method="post">
    		<ul id="t2" class="easyui-tree"></ul>
    		<input name="id" id="roleid" hidden="hidden">
            原角色名字<input name="rolename" id="oldRoleName" readonly="readonly"/><br/>
    		是否要修改角色名：<input id="roleName" name="newRoleName" type="text" class="easyui-validatebox" data-options="required:true">
    		<div align="center">
                <a onClick="upRole();" class="easyui-linkbutton" data-options="width:50">确定</a>
                <a onClick="quitRole();" class="easyui-linkbutton" data-options="width:50">取消</a>
    		</div>
    	</form>
    </div>
	<script type="text/javascript">
		$("#showAllcmfz_role").datagrid({
            url:"${pageContext.request.contextPath}/role",
            method:"get",
            height:500,
            fitColumns:true,//让表格自适应网页大小
            checkOnSelect:false,//是否点中该行是选中复选框按钮
            pagination:true,//显示分页插件
            pageSize:5,//每页显示5条.
            pageList:[5,10,20,50],//页面大小列表
		});
		
		function showResouorce(value,row,index) {
			if(value){
			    content = "<select>";
			    $.each(value,function (inde,val) {
					content+="<option>"+val.name+"</option>";
					if(val.resourceList.length!=0){
                        $.each(val.resourceList,function (ind,va){
							content+="<option>&nbsp;&nbsp;&nbsp;"+va.name+"</option>";
                            if(va.resourceList.length!=0){
                                $.each(va.resourceList,function (i,v){
                                    content+="<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+v.name+"</option>";

                                });
                            }
						});
					}
                });
                content += "</select>";
                return content;
			}

        }

        function addRole() {
		    $("#t1").tree({
                url:"${pageContext.request.contextPath}/resource/getAll",
                method:"get",
                lines:true,
                checkbox:true
			});
			$("#addRole").dialog("open");
        }
		
        function confirmRole() {
		    var nodes1 = $("#t1").tree('getChecked', 'indeterminate');
		    var nodes2 = $("#t1").tree('getChecked');
			var ids = "";
		    $.each(nodes1,function (index,value) {
				ids +="&ids="+value.id;
            });
            $.each(nodes2,function (index,value) {
                ids +="&ids="+value.id;
            });
            ids = ids.substring(1);
            var val = $("#rolenametoadd").val();
			ids+="&rolename="+val;
            $.ajax({
				url:"${pageContext.request.contextPath}/role",
                type:"post",
                data:ids,
                dataType:"json",
                success:function (data) {
                    $("#showAllcmfz_role").datagrid("reload");
                    $("#addRole").dialog("close");
                    $("#addRoleform").form("clear");
                }
			});
        }

        function addQuit() {
            $("#addRole").dialog("close");
            $("#addRoleform").form("clear");
        }
        
        function uRole() {
            var arr = $("#showAllcmfz_role").datagrid("getChecked");
            if(arr.length==1){

                $("#updateRoleform").form("load",arr[0]);

                $.ajax({
                    url:"${pageContext.request.contextPath}/resource/getAll",
                    type:"get",
                    dataType:"json",
                    success:function (data) {
                        $("#t2").tree({
                            data:data,
                            lines:true,
                            checkbox:true
                        });
                        $.each(arr[0].resourceList,function (index,value) {
                            if(value.resourceList&&value.resourceList.length!=0){
                                $.each(value.resourceList,function (inde,val) {
                                    if(val.resourceList&&val.resourceList.length!=0){
                                        $.each(val.resourceList,function (i,v) {

                                            var node = $("#t2").tree("find",v.id);
                                            if (node){
                                                $('#t2').tree('update', {
                                                    target: node.target,
                                                    checked:true
                                                });
                                            }
                                        })
                                    }else{
                                        var node = $("#t2").tree("find",val.id);
                                        if (node){
                                            $('#t2').tree('update', {
                                                target: node.target,
                                                checked:true
                                            });
                                        }
                                    }
                                })
                            }else{
                                console.log(value.id)
                            }
                        })
                    }
                });



			}
            $("#updateRole").dialog("open");
        }
        
        function upRole() {
            var nodes1 = $("#t2").tree('getChecked', 'indeterminate');
            var nodes2 = $("#t2").tree('getChecked');
            var ids = "";
            $.each(nodes1,function (index,value) {
                ids +="&ids="+value.id;
            });
            $.each(nodes2,function (index,value) {
                ids +="&ids="+value.id;
            });
            ids = ids.substring(1);
            var rolename = $("#oldRoleName").val();
            var newRoleName = $("#roleName").val();

            var id = $("#roleid").val();
            ids+="&rolename="+rolename+"&id="+id;
            if(newRoleName&&newRoleName!=""){
                ids+="&newRoleName="+newRoleName;
            }
            console.log(ids);
            $.ajax({
                url:"${pageContext.request.contextPath}/role/update",
                type:"post",
                data:ids,
                dataType:"json",
                success:function (data) {
                    $("#showAllcmfz_role").datagrid("reload");
                    $("#updateRole").dialog("close");
                    $("#updateRoleform").form("clear");
                }
            });
        }
        
        function quitRole() {
            $("#updateRole").dialog("close");
            $("#updateRoleform").form("clear");
        }


        function removeRole() {
            var arr = $("#showAllcmfz_role").datagrid("getChecked");
            console.log(arr);
            var ids = "";
            $.each(arr,function (index,obj) {
                ids += "&ids="+obj.id;
            });
            ids = ids.substring(1);
            console.log(ids);
            $.ajax({
                url:"${pageContext.request.contextPath}/role?"+ids,
                type:"delete",
                dataType:"json",
                success:function (data) {
                    $("#showAllcmfz_role").datagrid("reload");
                }
            });
        }

	</script>
	 </body>
</html>
