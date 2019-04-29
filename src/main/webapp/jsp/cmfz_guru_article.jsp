<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cmfz_guru_article.jsp' starting page</title>
    
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
    <table id="showAllcmfz_guru_article">
    		<div id="lala">
               <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="addGuruArticle();">添加</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="updateGuruArticle();">修改</a>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" onClick="removeGuruArticle();">删除</a>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove'" id="resetIndexDB" onClick="resetIndexDB();">重置索引库</a>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-remove',disabled:true" id="addIndexDB" onClick="addIndexDB();">添加索引库</a>
               <input class="easyui-searchbox" id="keysword" data-options="prompt:'Please Input Value',searcher:''" style="width:130px;vertical-align:middle;"></input>
               <a href="javascript:void(0)" onClick="lucen();" class="easyui-linkbutton" plain="true">高级检索</a>
            </div>
			<thead>
				<tr>
					<th data-options="field:'ids',checkbox:true"></th>
					<th data-options="field:'id'">ID</th>
					<th data-options="field:'name'">文章名字</th>
					<th data-options="field:'image'">图片地址</th>
					<th data-options="field:'content'">文章内容</th>
					<th data-options="field:'guru',formatter:showGuruName">上师法名</th>
					<th data-options="field:'createDate'">上传日期</th>
					<th data-options="field:'count'">阅读数量</th>
				</tr>
			</thead>
    </table>

     
    <div id="addArticle"  class="easyui-dialog" data-options="closed:true">
    	<form id="addArticleInformation" enctype="multipart/form-data" method="post">
    		文章标题：<input name="name"><br>
    		所属上师：<input id="addguru_id" name="guru.id" class="easyui-combobox" ><br>
    		文章插图：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件'
    		"><br>
    		文章内容：<br>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea name="content" rows="5px" cols="30px"></textarea>
    	</form>
		<div id="addFormButton">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="submitGuruArticleForm();">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="cancleAddGuruArticleForm();">取消</a>
		</div>
    </div>
    <div id="updateguru_Article"  class="easyui-dialog" data-options="closed:true">
    	<form id="updateguru_ArticleInformation" enctype="multipart/form-data" method="post">
    				<input id="article_id" name="id" style="display: none">
    				<input name="image" style="display: none">
    				<input name="createDate" style="display: none">
    		文章标题：<input id="article_title" name="name"><br>
			所属上师：<select id="guru_id" name="guru.id" class="easyui-combobox"></select><br>
    		文章插图：<input name="uploadFile" class="easyui-filebox" data-options="
    			buttonText:'选择文件'
    		"><br>
    		阅读量：<input name="count" id="article_read_count" type="text" class="easyui-numberbox" data-options="min:0"></input>
    		文章内容：<br>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="content" name="content" rows="5px" cols="30px"></textarea>
    	</form>
    		<p id="Article_picture"><img width="100" height="150" id="articleImage" src=""> </p>
		<div id="updateFormButton">
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-add'" onClick="submitUpdateArticleForm();">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-edit'" onClick="cancleUpdateGuruArticleForm();">取消</a>
		</div>
    </div>
  </body>
  <script type="text/javascript">
     $("#showAllcmfz_guru_article").datagrid({
         url:"${pageContext.request.contextPath}/article",
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
         },
         onLoadSuccess:function (data) {
			 console.log(data);
			 if(data.code=="500"){
                 $("#showAllcmfz_guru_article").datagrid("load",{
                     keysword:""
				 });
			     $.messager.alert("警告","您还没有创建索引库");
			 }
         }
	 });
     function showGuruName(value,row,index) {
		 if(value)
		     return value.nikename;
     }
  </script>


  <script>
	  function lucen() {
		  var keysword = $("#keysword").val();
		  console.log(keysword);
          $("#showAllcmfz_guru_article").datagrid("reload",{
              keysword:keysword
		  });
      }
      
      
      function resetIndexDB() {
		  $.ajax({
			  url:"${pageContext.request.contextPath}/article/deleteLucene",
			  type:"delete",
			  dataType:"json",
			  success:function (data) {
				  console.log(data);
				  if(data.code == "200")
				  $("#addIndexDB").linkbutton({
                      disabled:false
				  })
              }
		  });
      }

      function addIndexDB() {
          $.ajax({
              url:"${pageContext.request.contextPath}/article/addLucene",
              type:"post",
              dataType:"json",
              success:function (data) {
                  console.log(data);
                  $("#addIndexDB").linkbutton({
                      disabled:true
                  })
              }
          });
      }

      //添加文章
      function addGuruArticle() {
		  $("#addArticle").dialog("open");
		  $("#addguru_id").combobox({
			  url:"${pageContext.request.contextPath}/guru/addArticle",
			  method:"get",
              valueField:"id",
              textField:"nikename"
		  });
      }
      function submitGuruArticleForm() {
		  $("#addArticleInformation").form("submit",{
		      url:"${pageContext.request.contextPath}/article",
			  onSubmit:function () {
                  /*$("#addArticleInformation").form("")*/
              },
			  success:function (str) {
				  console.log(str);
                  $("#addArticleInformation").form("clear");
                  $("#addArticle").dialog("close");
                  $("#showAllcmfz_guru_article").datagrid("reload");
              }
		  });

      }

      function cancleAddGuruArticleForm() {
          $("#addArticleInformation").form("clear");
          $("#addArticle").dialog("close");
      }


      function updateGuruArticle() {
	      var arr = $("#showAllcmfz_guru_article").datagrid("getChecked");
	      if(arr.length == 1){
              $("#guru_id").combobox({
                  url:"${pageContext.request.contextPath}/guru/addArticle",
                  method:"get",
				  width:100,
                  valueField:"id",
                  textField:"nikename"
              });
              $("#articleImage").prop("src","${pageContext.request.contextPath}/picture"+arr[0].image);
              $("#guru_id").combobox("select",arr[0].guru.id);
	          $("#updateguru_ArticleInformation").form("load","${pageContext.request.contextPath}/article/"+arr[0].id);
              $("#updateguru_Article").dialog("open");
		  }else{
              $.messager.alert("提示","您选中了多条或没有选中数据");
		  }

      }
      function submitUpdateArticleForm() {
          $("#updateguru_ArticleInformation").form("submit",{
              url:"${pageContext.request.contextPath}/article/update",
              onSubmit:function () {
                  /*$("#addArticleInformation").form("")*/
              },
              success:function (str) {
                  console.log(str);
                  $("#updateguru_ArticleInformation").form("clear");
                  $("#updateguru_Article").dialog("close");
                  $("#showAllcmfz_guru_article").datagrid("reload");
              }
          });
      }
      function cancleUpdateGuruArticleForm() {
          $("#updateguru_ArticleInformation").form("clear");
          $("#updateguru_Article").dialog("close");
      }
      
      
      function removeGuruArticle() {
          var arr = $("#showAllcmfz_guru_article").datagrid("getChecked");
          var ids = "";
          $.each(arr,function (index,obj) {
              ids += "&ids="+obj.id;
          });
          ids = ids.substring(1);
          console.log(ids);
          $.ajax({
              url:"${pageContext.request.contextPath}/article/id?"+ids,
              type:"delete",
              dataType:"json",
              success:function (data) {
                  console.log("111");
                  $("#showAllcmfz_guru_article").datagrid("load");
              }
          });
      }
  </script>
</html>
