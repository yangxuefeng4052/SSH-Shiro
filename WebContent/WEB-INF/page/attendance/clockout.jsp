<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    String path = request.getContextPath();
    
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上班打卡</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="css/stylesheets/theme.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">

    <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

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
	<!-- 头 -->
    <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> ${sessionScope.emp.username }
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="turnToInfo">用户信息</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="logout.action">注销</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="turnToHome"><span class="first">办公</span> <span class="second">OA系统</span></a>
        </div>
    </div>
    
    <!-- 左边导航栏 -->
     <div class="sidebar-nav">
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>通用导航</a>
        <!-- <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="home.jsp">Home</a></li>
            <li ><a href="users.html">Sample List</a></li>
            <li ><a href="user.html">Sample Item</a></li>
            <li ><a href="media.html">Media</a></li>
            <li ><a href="calendar.html">Calendar</a></li>
            
        </ul> -->
        <!-- 根据权限动态获取左侧菜单 -->
		<c:forEach items="${permissions }" var="per">
	        <a href="${per.url}" onclick="window.location = '${per.url}'" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>${per.name }</a>
			<c:if test="${per.url eq '#legal-menu' }">
				<ul id="legal-menu" class="nav nav-list collapse">
		            <li ><a href="turnToClockIn">上班打卡</a></li>
		            <li ><a href="turnToClockOut">下班打卡</a></li>
		            <li ><a href="turnToProcess?type=1">未打卡登记</a></li>
		            <li ><a href="turnToProcess?type=2">加班申请</a></li>
		            <li ><a href="turnToProcess?type=3">病事假申请</a></li>
		        </ul>
			</c:if>
			<c:if test="${per.url eq '#job-plan' }">
				 <ul id="job-plan" class="nav nav-list collapse">
		            <li ><a href="report">报工</a></li>
		            <li ><a href="joblog">工作日记</a></li>
		            <c:if test="${emp.role.id == 3 }">
		            		<li ><a href="notice">部门通知</a></li>
		            </c:if>
		        </ul>
			</c:if>
		</c:forEach>
        <!-- <a href="help.html" class="nav-header" ><i class="icon-question-sign"></i>流程审批</a>
        <a href="faq.html" class="nav-header" ><i class="icon-comment"></i>日志查看</a> -->
    </div>
    
    <!-- 内容 -->
        <div class="content">
        
        <div class="header">
            <h1 class="page-title">下班打卡</h1>
        </div>
        
        <ul class="breadcrumb">
            <li>首页<span class="divider">/</span></li>
            <li>我的考勤<span class="divider">/</span></li>
            <li class="active">下班打卡</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    <!-- 内容开始 -->
                    <h2>当前时间：</h2>
                    <div id="cv" style="width: 300px;height:30px;font-size:20px"></div>
                    
                    <div class="btn-toolbar">
                    		<input type="hidden" id="empId" value="${emp.empId }" />
                    		<input type="hidden" id="deptId" value="${emp.dept.id }" />
                    		<input type="hidden" id="roleId" value="${emp.role.id }" />
			   			 <i class="icon-save"><input type="button" id="clock" class="btn btn-primary" value="打卡"></i> 
			  		</div>
                    
                    <!-- 内容结束 -->

                    <footer>
                        <hr>
                        <p class="pull-right">Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>

                        <p>&copy; 2012 <a href="#" target="_blank">Portnine</a></p>
                    </footer>
                    
            </div>
        </div>
    </div>
    
    
	
</body>
<!-- 引用 -->
    <script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        
        
         var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth()+1;
		var day = now.getDate();
		var week = now.getDay();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		document.getElementById("cv").innerHTML = addZero(hour)+":"+addZero(minute)+":"+addZero(second)+"  "+year+"/"+addZero(month)+"/"+addZero(day)+"/  星期"+change(week);
        
        setInterval(function(){
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth()+1;
			var day = now.getDate();
			var week = now.getDay();
			var hour = now.getHours();
			var minute = now.getMinutes();
			var second = now.getSeconds();
			document.getElementById("cv").innerHTML = addZero(hour)+":"+addZero(minute)+":"+addZero(second)+"  "+year+"/"+addZero(month)+"/"+addZero(day)+"/  星期"+change(week);
		},1000);
	
	function change(x){
		switch(x){
			case 0:
				return "日";
			break;
			case 1:
				return "一";
			break;
			case 2:
				return "二";
			break;
			case 3:
				return "三";
			break;
			case 4:
				return "四";
			break;
			case 5:
				return "五";
			break;
			case 6:
				return "六";
			break;
		}
	}	
	
	function addZero(x){
		return x > 9 ? x : '0' + x;
	}
	//记录打卡状态
		var isClock = false;
       //打卡
       document.getElementById("clock").onclick=function(){
    	   
    	   var nowTime = new Date();
   	  	var nowHour = now.getHours();
   	   		
   	   		
   	   		if(nowHour < 18){
   	   			alert("还没下班呢就来打卡，别着急");
   	   			return;
   	   		}
   	   		else{
    	   			if(!isClock){
        	   			$.ajax({
            	   			type:"post",
            	   			url:"clockOut",
            	   			data:{
            	   				empId:$("#empId").val(),
            	   				deptId:$("#deptId").val(),
            	   				roleId:$("#roleId").val()
            	   			},
            	   			success:function(res){
            	   				console.log(res);
            	   				if(res == 1){
            	   					alert("打卡成功！");
            	   					isClock = true;
            	   				}else{
            	   					alert("打卡失败，具体原因请联系管理员");
            	   				}
            	   			}
            	   		})
        	   		}else{
        	   			alert("您已打卡成功，不可重复打卡！");
        	   		}
    	   		}
    	   		
    	   		
    	   
    	   		
    	   		
       }
        
    </script>
</html>