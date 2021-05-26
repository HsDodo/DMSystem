<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>宿管登陆页面</title>
    <style type="text/css">
        body{
            background: url("images/1.jpg") no-repeat;
            background-size: 100% auto;
        }

        #login-box h1{
            color: #FFFFFF;
        }

        #login-box{
            width: 30%;
            height: auto;
            border: 0;
            margin: 0 auto;
            margin-top: 15%;
            text-align: center;
            padding: 20px 50px;
            background: #00000060;
            border-radius: 15px;
        }
        #login-box .form .item{
            margin-top: 15px;
        }
        #login-box .form .item i{
            font-size: 12px;
            color: #FFFFFF;
        }

        #login-box .form .item input{
            border: 0;
            width: 180px;
            font-size: 18px;
            border-bottom: 2px solid #000000;
            padding: 5px 10px;
            background: #ffffff00;
            color: #ffffff;
        }

        #login-box #stu_login{
            margin-top: 15px;
            width: 150px;
            height: 30px;
            font-size: 20px;
            font-weight: 700;
            color: #FFFFFF;
            background: #5698c3;
            border: 0;
            border-radius: 15px;
        }
        #login-box #admin_login{
            margin-left: 20px;
            margin-top: 15px;
            width: 150px;
            height: 30px;
            font-size: 20px;
            font-weight: 700;
            color: #FFFFFF;
            background: #5698c3;
            border: 0;
            border-radius: 15px;
        }
    </style>
</head>
<body>
<div id="login-box">
    <h1>Login</h1>
    <div class="form">
        <form method="post" name="form1" action="${pageContext.request.contextPath}/StudentLoginServlet">
            <div class="item">
                <i>用户名</i>
                <input type="text" placeholder="username" name="username"  >
                <span class="msg">${requestScope.namemsg}${requestScope.nameError}</span>
            </div>
            <div class="item">
                <i>密&nbsp;&nbsp;码</i>
                <input type="password" placeholder="password" name="password" >
                <span class="msg">${requestScope.namemsg}${requestScope.nameError}</span>
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
            form.submit();
        }else{
            form.action="AdminLoginServlet";
            form.submit();
        }

    }
</script>

</body>
</html>