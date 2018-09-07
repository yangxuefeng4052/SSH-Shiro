<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/stylesheets/theme.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/Valid.css">
    <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
     <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>
    
</head>
<body>
	<div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                </ul>
                <a class="brand" href="index.html"><span class="first">办公</span> <span class="second">OA系统</span></a>
        </div>
    </div>
        <div class="row-fluid">
    <div class="dialog">
        <div class="block">
            <p class="block-heading">用户登录</p>
            <div class="block-body">
                <form class="loginForm" action="loginCheck" method="post">
                    <label>用户名</label>
                    <input type="text" class="span12" name="code" datatype="m" nullmsg="请输入用户名！" errormsg="请用手机号码登录！">
                    <label>密码</label>
                    <input type="password" class="span12" name="psw" nullmsg="请输入密码！" errormsg="密码至少6个字符,最多20个字符！" datatype="*6-20">
                    <input type="submit" id="signIn" class="btn btn-primary pull-right" value="登录"></input>
                    <label class="remember-me"><input type="checkbox" name="rememberMe" value="rememberMe">记住我</label>
                    <div class="clearfix">${sessionScope.msg }</div>
                </form>
            </div>
        </div>
        
        <p><a href="resetPassword.jsp">忘记密码?</a></p>
    </div>
</div>
</body>
	 <script src="validform/Validform_v5.3.2.js"></script>
 <script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        
    $("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
    
    
    $(".loginForm").Validform();

    
        
    </script>
</html>