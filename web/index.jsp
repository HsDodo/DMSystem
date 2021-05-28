<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>宿管登陆页面</title>
    <link type="text/css" href="css/signin.css" rel="stylesheet">
</head>

<body>

<%
    String nameError = (String) request.getAttribute("nameError");
    String pwdError = (String) request.getAttribute("pwdError");
    if(nameError!=null&&nameError.equals("用户名不存在!")){
%>
        <script type="text/javascript">
            alert("用户名不存在!")
        </script>
<%
    }else if(pwdError!=null&&pwdError.equals("密码不正确!")){
%>
    <script type="text/javascript">
        alert("密码不正确!")
    </script>
<%
    }
%>
<div id="login-box">
    <h1>Login</h1>
    <div class="form">
        <form method="post" name="form1">
            <div class="item">
                <i>用户名</i>
                <input type="text" placeholder="username" name="username"  >
            </div>
            <div class="item">
                <i>密&nbsp;&nbsp;码</i>
                <input type="password" placeholder="password" name="password" >
            </div>
            <input type="button" name="stu_login" id="stu_login" onclick="check(form1,1)" value="学生登录"/>
            <input type="button" name="admin_login" id="admin_login" onclick="check(form1,0)" value="管理员登录"/>
            <input type="hidden" name="login" value="1" />
        </form>
    </div>
</div>

<script language="JavaScript">
    function check(form,n){
        if(form.username.value==""){
            alert("请输入用户名");
            form.username.focus();
            return false;
        }else if(form.password.value==""){
            alert("请输入密码");
            form.password.focus();
            return false;
        }
        if(n==1){
            form.action="${pageContext.request.contextPath}/StudentLoginServlet";
            form.submit();
        }else{
            form.action="${pageContext.request.contextPath}/AdminLoginServlet";
            form.submit();
        }
    }
</script>

</body>
</html>