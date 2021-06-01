<%@ page import="JAVABEAN.Student" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>学生宿舍管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
    <script src="https://lib.sinaapp.com/js/jquery/2.0.2/jquery-2.0.2.min.js"></script>
    <script type="text/javascript">
        function logout(){
            if(!confirm("真的要退出吗？")){
                window["event"].returnValue = false;
            }
        }
    </script>
</head>

<body>
<%
    String dormadminname = (String) request.getSession().getAttribute("dormadminname");
    if(dormadminname == null)
    {
        response.sendRedirect("index.jsp");
    }
%>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar hidden-xs">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="admin_student_information.jsp">宿舍信息</a></li>
                <li><a href="admin_student_guarantee.jsp">维修信息</a></li>
                <li><a href="admin_student_leavereturn.jsp">学生离校与返校</a></li>
                <li><a href="admin_student_laterecord.jsp">晚归记录</a></li>
                <li><a href="admin_student_fee.jsp">水电费信息</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">宿舍信息</h2>
            <div class="table-responsive">
                <table class="table table-striped" >
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>专业</th>
                        <th>宿舍号</th>
                        <th>年龄</th>
                        <th>班级</th>
                        <th>手机号</th>
                        <th>入住时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Student> students=(List<Student>)request.getSession().getAttribute("students");
                        int size=students.size();
                        String p_index=request.getParameter("pageIndex");
                        int pageIndex = p_index==null?1:Integer.parseInt(p_index);
                        int beginIndex = (pageIndex-1)*12;
                        for(int i=beginIndex;i<beginIndex+12&&i<size;i++){
                            Student stu = students.get(i);
                            out.println("<tr>");
                            out.println("<td>"+stu.getStudentid()+"</td>");
                            out.println("<td>"+stu.getStudentname()+"</td>");
                            out.println("<td>"+stu.getGender()+"</td>");
                            out.println("<td>"+stu.getMajor()+"</td>");
                            out.println("<td>"+stu.getDormitoryid()+"</td>");
                            out.println("<td>"+stu.getAge()+"</td>");
                            out.println("<td>"+stu.getClasses()+"</td>");
                            out.println("<td>"+stu.getPhoneid()+"</td>");
                            out.println("<td>"+stu.getEntrytime()+"</td>");
                            out.println("</tr>");
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <div class="text-center">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="admin_student_information.jsp?pageIndex=<%=(pageIndex-1)<=0?pageIndex:pageIndex-1%>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <%
                            int i=1;
                            int t=size;
                            int pageSize=0;
                            while(t>0){
                                out.println("<li><a href='admin_student_information.jsp?pageIndex="+i+"'>"+i+"</a></li>");
                                t-=12;
                                pageSize+=1;
                                i++;
                            }
                        %>
                        <li>
                            <a href="admin_student_information.jsp?pageIndex=<%=(pageIndex+1)>pageSize?pageIndex:pageIndex+1%>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>



            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/AdminSearchServlet" method="post">
                <input type="hidden" name="per" value="service">
                <div class="form-group">
                    <label class="col-sm-2 control-label">学号</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" placeholder="输入学号" name="studentid">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">学生姓名</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" placeholder="输入学生姓名" name="studentname">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">专业</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" placeholder="输入学生专业" name="major">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">班级</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" placeholder="输入学生班级" name="classes">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">宿舍号</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" placeholder="输入宿舍号" name="dormitoryid">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>

