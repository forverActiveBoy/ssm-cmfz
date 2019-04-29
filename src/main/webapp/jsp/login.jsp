<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/global/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/icon.css">
    <script type="text/javascript">
      if(window.frames.length != window.parent.frames.length){
        parent.location.reload();
      }
    </script>
  </head>

  <body>
    <div id="top"> </div>
    <form id="loginform" name="login">
    <div id="center">
      <div id="center_left"></div>
      <div id="center_middle">
        <div class="user">
          <label>电   话：
          <input type="text" name="username" id="email"/>
          </label>
        </div>
        <div class="user">
          <label>密　码：
          <input type="password" name="password" id="pwd"/>
          </label>
        </div>
        <div class="chknumber">
          <label>验证码：
          <input name="code" type="text" id="chknumber" maxlength="4" class="chknumber_input" />
          </label><img width="50" height="30" src="#" id="imgVcode" onclick="document.getElementById('imgVcode').src='${pageContext.request.contextPath}/validateCode/code?time='+(new Date()).getTime();"  />
        </div>
      </div>
      <div id="center_middle_right"></div>
      <div id="center_submit">
        <div class="button"> <img src="${pageContext.request.contextPath}/admin/imgs/global/dl.gif" width="57" height="20" onclick="form_submit()"> </div>
        <div class="button"> <img src="${pageContext.request.contextPath}/admin/imgs/global/cz.gif" width="57" height="20" onclick="form_reset()"> </div>
      </div>
      </div>
      <div style="position:absolute;left:550px;bottom:100px;">
      <input name="rememberMe" type="checkbox">请记住我</div>
    </form>
  </body>
    <script type="text/javascript">
      function form_submit() {
          $("#loginform").form("submit",{
              url:"${pageContext.request.contextPath}/admin/login.do",
              method:"post",
              onSuccess:function () {

              },
              success:function (str) {
                  console.log("11111111")
                  var map = JSON.parse(str);
                  if(map.code == "200"){
                      window.location.href="${pageContext.request.contextPath}/jsp/main.jsp";
                  }else{
                      window.location.href="${pageContext.request.contextPath}/jsp/main.jsp";
                  }
              }
          });
      }
    </script>
</html>
