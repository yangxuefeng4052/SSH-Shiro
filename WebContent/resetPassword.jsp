<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码找回</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/stylesheets/theme.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">

    
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
                <a class="brand" href="#"><span class="first">办公</span> <span class="second">OA系统</span></a>
        </div>
    </div>
        <div class="row-fluid">
    <div class="dialog">
        <div class="block" style="height:225px">
            <p class="block-heading">请填写您的邮箱，稍后会将验证码发送到您的邮箱中</p>
            <div class="block-body">
                    <label>邮箱</label>
                    <input type="text" class="span12" id="email"></input>
                    <button id="sendMail" class="btn btn-primary pull-right">获取验证码</button>
                    <label>验证码</label>
                    <input type="text" class="span12" id="checkCode"></input>
                    <div class="clearfix"></div>
                    <button id="checkEmail" class="btn btn-primary pull-right">验证</button>
            </div>
        </div>
        <a href="login.jsp">返回登录页</a>
    </div>
</div>
</body>
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        /* 发送邮件 */
        document.getElementById("sendMail").onclick=function(){
        	$.ajax({
				type:"post",      			
        			url:"sendEmail",
        			data:{
        				email:$("#email").val()
        				
        			},
        			success:function(res){
        				console.log(res);
        				if(res == 1){
        					alert("邮件发送成功，请您查看邮件获取验证码");
        					setTimeout(function() {
        							$("#email").css("disabled","disabled");
								$("#sendMail").css("disabled","disabled");
								$("#sendMail").val("已发送");
							}, 1000*60);
        					$("#sendMail").css("disabled",false);
        					$("#email").css("disabled",false);
							$("#sendMail").val("获取验证码");
        				}else{
        					alert("您填写的邮箱有误，请修改后重试！");
        				}
        			}
        		});
        }
       /*  	$("#sendMail").click({
        		
        	}); */
        
        	document.getElementById("checkEmail").onclick=function(){
        		$.ajax({
        			type:"post",
        			url:"checkCode",
        			data:{
        				email:$("#email").val(),
        				checkCode:$("#checkCode").val()
        			},
        			success:function(res){
        				console.log(res);
        				if(res == 1){
        					alert("验证成功！您的密码已重置，请登录后尽快修改");
        					location.href="login.jsp";
        				}else{
        					alert("您填写的验证码有误，请修改后重试！");
        				}
        			}
        		})
        	}
        	
        	
    </script>
</html>