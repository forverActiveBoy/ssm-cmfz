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
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/area.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui.css">
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/icon.css">

  </head>
  <body>
  <!-- form表单不能放置table内部，会导致表单无效 -->
  <form id="queryUserForm"  method="post" enctype="multipart/form-data">
    <table id="showAllcmfz_user">
        <div id="lele">
           <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="add()">添加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="update()">修改</a>
             <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="remove()">删除</a>
               <a href="javascript:void(0)" class="easyui-linkbutton" plain="true"  data-options="iconCls:'icon-print'" onClick="exportUser();">导出</a>
               <input  id="fileName" class="easyui-filebox" name="fileName"  style="width:200px;vertical-align:middle;" />
                <a href="javascript:void(0)" onclick="importUser()" class="easyui-linkbutton">开始导入</a>
        </div>
        <thead>
            <tr>
                <th data-options="field:'ids',checkbox:true"></th>
                <th data-options="field:'id'">ID</th>
                <th data-options="field:'telphone'">电话号码</th>
                <th data-options="field:'password'">密码</th>
                <th data-options="field:'imageName'">头像</th>
                <th data-options="field:'nikename'">昵称</th>
                <th data-options="field:'name'">名字</th>
                <th data-options="field:'sex'">性别</th>
                <th data-options="field:'autograph'">简介</th>
                <th data-options="field:'userSheng'">省份</th>
                <th data-options="field:'userShi'">所在市</th>
                <th data-options="field:'guru',formatter:showguruname">上师名</th>
            </tr>
        </thead>
    </table>
  </form>

  <div id="addUser"  class="easyui-dialog" data-options="closed:true,width:350,height:600">
      <form id="adduserInformation" enctype="multipart/form-data" method="post">
          用户手机号：<input  name="telphone"><br>
          密码：<input  name="password"><br>
          昵称：<input name="nikename"><br>
          名字：<input name="name"><br>
          性别：男:<input type="radio" value="男" name="sex"> 女:<input type="radio" value="女" name="sex"><br>
          简介：<input name="autograph"><br>
          所属上师：<select id="userGuru" name="guru.id"></select><br/>
          用户头像：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件',
    			width:150,
    			height:20
    		"><br/>
          用户所在地：<select id="s_province" name="userSheng"></select>省份 <select id="s_city" name="userShi"></select>市
      </form>
      <center>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitAddUser()">确认</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseAddUserForm()">取消</a>
      </center>
  </div>
  <div id="updateUser"  class="easyui-dialog" data-options="closed:true,width:350,height:600">
      <form id="updateuserInformation" enctype="multipart/form-data" method="post">
          <input id="guru_id" name="id" style="display: none">
          <input name="imageName" style="display: none">
          用户手机号：<input  name="telphone"><br>
          密码：<input  name="password"><br>
          昵称：<input name="nikename"><br>
          名字：<input name="name"><br>
          所属上师：<select id="userGuru1" name="guru.id"></select><br/>
          性别：男：<input type="radio" value="男" name="sex">女：<input type="radio" value="女" name="sex"><br>
          简介：<input name="autograph"><br>
          用户头像：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件',
    			width:150,
    			height:20
    		"><br/>
          用户所在地：<select id="s_province1" name="userSheng"></select>省份 <select id="s_city1" name="userShi"></select>市
      </form>
      <p id="guru_picture"><img width="100" height="100" src="" id="userImage"> </p>

      <center>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="submitUpdateUser()">确认</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onClick="colseUpdateUserForm()">取消</a>
      </center>
  </div>

  <script type="text/javascript">
    $("#showAllcmfz_user").datagrid({
        url:"${pageContext.request.contextPath}/user",
        method:"get",
        height:500,
        fitColumns:true,//让表格自适应网页大小
        checkOnSelect:false,//是否点中该行是选中复选框按钮
        pagination:true,//显示分页插件
        pageSize:5,//每页显示5条.
        pageList:[5,10,20,50]//页面大小列表
    });

    function showguruname(value,row,index) {
        console.log(row);
        if(value)
            return value.nikename;
    }
    function add() {
        $("#userGuru").combobox({
            url:"${pageContext.request.contextPath}/guru/addArticle",
            method:"get",
            valueField:"id",
            textField:"nikename"
        });
        s=["s_province","s_city"];
        _init_area();
        $("#addUser").dialog("open");
    }
    
    function submitAddUser() {
        $("#adduserInformation").form("submit",{
            url:"${pageContext.request.contextPath}/user",
            type:"post",
            dataType:"json",
            success:function (data) {
                $("#addUser").dialog("close");
                $("#adduserInformation").form("clear");
                $("#showAllcmfz_user").datagrid("reload");
            }
        });
    }


    function colseAddUserForm() {
        $("#addUser").dialog("close");
        $("#adduserInformation").form("clear");
    }
    
    
    function update() {
        $("#userGuru1").combobox({
            url:"${pageContext.request.contextPath}/guru/addArticle",
            method:"get",
            valueField:"id",
            textField:"nikename"
        });

        s=["s_province1","s_city1"];
        _init_area();
        var arr = $("#showAllcmfz_user").datagrid("getChecked");
        if(arr.length == 1){
            $("#userGuru1").combobox("select",arr[0].guru.id);
            console.log(arr[0]);
            $("#userImage").prop("src","${pageContext.request.contextPath}/picture/userpicture/"+arr[0].imageName);
            $("#updateuserInformation").form("load",arr[0]);
            $("#updateUser").dialog("open");
        }else{
            $.messager.alert("提示","您选择了多条或未选择数据");
        }
    }
    
    function submitUpdateUser() {
        $("#updateuserInformation").form("submit",{
            url:"${pageContext.request.contextPath}/user/update",
            onSubmit:function () {
                return $("#updateuserInformation").form("validate");
            },
            success:function (str) {
                //var map = JSON.parse(str);
                $("#showAllcmfz_user").datagrid("reload");
                $("#updateUser").dialog("close");
                $("#updateuserInformation").form("clear");
            }
        });
    }


    function colseUpdateUserForm() {
        $("#updateUser").dialog("close");
        $("#updateuserInformation").form("clear");
    }
    
    function remove() {
        var arr = $("#showAllcmfz_user").datagrid("getChecked");
        console.log(arr);
        var ids = "";
        $.each(arr,function (index,obj) {
            ids += "&ids="+obj.id;
        });
        ids = ids.substring(1);
        console.log(ids);
        $.ajax({
            url:"${pageContext.request.contextPath}/user?"+ids,
            type:"delete",
            dataType:"json",
            success:function (data) {
                console.log("111");
                $("#showAllcmfz_user").datagrid("load");
            }
        });
    }
    
    function exportUser() {

        var arr = $("#showAllcmfz_user").datagrid("getChecked");
        if(arr.length != 0){
            console.log(arr);
            var ids = "";
            $.each(arr,function (index,obj) {
                ids += "&ids="+obj.id;
            });
            ids = ids.substring(1);
            console.log(ids);
            window.location.href="${pageContext.request.contextPath}/user/out?"+ids;
        }else{
            $.messager.confirm("确认提示框","您没有选中数据，是否要导出全部",function (r) {
                if(r){
                    window.location.href="${pageContext.request.contextPath}/user/out";
                }else{

                }
            });
        }

    }
    
    function importUser() {
        $("#queryUserForm").form("submit",{
            url:"${pageContext.request.contextPath}/user/import",
            onSubmit:function () {
                return $("#queryUserForm").form("validate");
            },
            success:function (str) {
                //var map = JSON.parse(str);
                $("#queryUserForm").form("clear");
                $("#showAllcmfz_user").datagrid("reload");
            }
        });
    }
  </script>
	 </body>
</html>
