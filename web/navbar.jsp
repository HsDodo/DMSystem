<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2021/5/27
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<%--         ------------------   --%>
            <!-- 学生-->
            <%
                String stuname = (String)request.getSession().getAttribute("studentname");
                if(stuname!=null&&!stuname.equals("")){
            %>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav nav-pills">
                    <li class="nav-item active">
                        <a class="navbar-right" href="student_information.jsp">正在登陆的用户为：${sessionScope.studentname}(学生)</a>
                    </li>
                    <li class="nav-item active">
                        <a class="navbar-right" href="/LoginOutServlet" onclick="return logout()">退出</a>
                    </li>
                </ul>
            </div>
            <%
            }else{
            %>
            <!-- 管理员-->
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav nav-pills">
                    <li class="nav-item active">
                        <a class="navbar-right" href="admin_student_information.jsp">正在登陆的用户为：${sessionScope.dormadminname}(宿管)</a>
                    </li>
                    <li class="nav-item active">
                        <a class="navbar-right" href="/LoginOutServlet" onclick="return logout()">退出</a>
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
