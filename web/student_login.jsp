<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <style type="text/css">
        body{
            background: url(images/1.jpg) no-repeat;
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

        #login-box #login{
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
        #login-box #register{
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
        <form method="post" id="form1">
            <div class="item">
                <i>用户名</i>
                <input type="text" placeholder="username" name="username" >
                <span class="msg">${requestScope.namemsg}${requestScope.nameError}</span>
            </div>
            <div class="item">
                <i>密&nbsp;&nbsp;码</i>
                <input type="password" placeholder="password" name="password" >
                <span class="msg">${requestScope.namemsg}${requestScope.nameError}</span>
            </div>
            <input type="button" name="login" id="login" onclick="check(form1,1)" value="学生登录"/>
            <input type="button" name="register" id="register" onclick="check(form1,0)" value="管理员登录"/>
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
            form.action="${pageContext.request.contextPath}StudentLoginServlet";
            form.submit();
            return true;
        }else{
            form.action="${pageContext.request.contextPath}/AdminLoginServlet";
            form.submit();
            return true;
        }

    }
</script>


</body>
</html>
