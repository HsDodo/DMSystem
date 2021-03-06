<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/mystyles.css">
    <link rel="stylesheet" type="text/css" href="css/all.css">
    <title>个人通讯录</title>
  </head>
  <script type="text/javascript">
    function logout(){
      if(!confirm("真的要退出吗？")){
        window["event"].returnValue = false;
      }
    }
  </script>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand text-primary" href="index.jsp">学生宿舍管理系统</a>
        </div>
        <!-- 无用户登录-->
        <c:if test="${sessionScope.studentname == null && sessionScope.dormadminname == null}">
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav justify-content-end">
              <li class="nav-item active">
                <a class="navbar-right" href="index.jsp">登陆</a>
              </li>
            </ul>
          </div>
        </c:if>
        <!-- 学生-->
        <%
          String studentname = (String)request.getSession().getAttribute("studentname");
          if(!studentname.equals("")){
        %>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav justify-content-end">
              <li class="nav-item active">
                <a class="navbar-right" href="student_information.jsp">正在登陆的用户为：${sessionScope.studentname}(学生)</a>
              </li>
              <li class="nav-item active">
                <a class="navbar-right" href="/" onclick="return logout()">退出</a>
              </li>
            </ul>
          </div>
        <%
          }else{
        %>
        <!-- 管理员-->
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav justify-content-end">
              <li class="nav-item active">
                <a class="navbar-right" href="admin_student_information.jsp">正在登陆的用户为：${sessionScope.dormadminname}(宿管)</a>
              </li>
              <li class="nav-item active">
                <a class="navbar-right" href="" onclick="return logout()">退出</a>
              </li>
            </ul>
          </div>
      <%
        }
      %>
      </div>
    </nav>
</body>
</html>