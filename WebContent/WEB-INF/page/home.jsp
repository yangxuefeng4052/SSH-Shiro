<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.yxf.oa.bean.Permission"%>
<%@page import="java.util.Set"%>
<%@page import="com.yxf.oa.bean.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%
    String path = request.getContextPath();
    
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入OA系统</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="css/stylesheets/theme.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">

    <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
    		*{
			margin:0;
			padding: 0; 
		}
		#dayList{
			border-collapse: collapse;
			margin:40px auto;
			width: 70%;
		}
		#dayList td{
			height: 50px;
			line-height: 50px;
			/* border:1px solid black; */
			width: 50px;
			text-align: center;
			font-size: 20px;
		}
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
        <%
        		Set<Permission> lists =  (Set<Permission>)session.getAttribute("permissions");
        		for(Permission p : lists){
        			System.out.println(p.getName());
        		}
        %>
		<c:forEach items="${permissions }" var="per">
	        <a onclick="window.location = '${per.url}'" href="<%=basePath %>${per.url }" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>${per.name }</a>
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
    

    
    <div class="content">
        
        <div class="header">
            <h1 class="page-title">通用工作清单</h1>
        </div>
        
        <ul class="breadcrumb">
            <li><a href="turnToHome">首页</a> <span class="divider">/</span></li>
            <li class="active">通用工作清单</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    

<!-- <div class="row-fluid">

    <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <strong>Just a quick note:</strong> Hope you like the theme!
    </div>
</div> -->

<div class="row-fluid">
<!-- 部门人员列表 开始 -->
    <div class="block span6">
        <a href="#tablewidget" class="block-heading" data-toggle="collapse">部门人员列表<span class="label label-warning"></span></a>
        <div id="tablewidget" class="block-body collapse in">
            <table class="table">
              <thead>
                <tr>
                  <th>工号</th>
                  <th>姓名</th>
                  <th>职位</th>
                </tr>
              </thead>
              <tbody>
              <!-- 显示第一页 -->
              <c:forEach  items="${emp.dept.emps }" var="employee" begin="0" end="4" step="1"> 
                <tr>
                  <td>${employee.empId }</td>
                  <td>${employee.username }</td>
                  <td>${employee.role.name }</td>
                </tr>
              </c:forEach>  
              </tbody>
            </table>
            <p><a href="empManager">查看更多</a></p>
        </div>
    </div>
    <!-- 部门人员列表 结束 -->
    <div class="block span6">
        <a href="#widget1container" class="block-heading" data-toggle="collapse">部门通知</a>
        <div id="widget1container" class="block-body collapse in">
        <c:choose>
        <c:when test="${not empty notice.content }">
        		<label>标题</label>
        		<p><h3>${notice.title }</h3></p>
        		<label>内容</label>
        		<p><h3>${notice.content }</h3></p>
        		<label>发布时间</label>
        		<p><h3>${notice.createTime }</h3></p>
        	</c:when>
        	<c:otherwise><h3>今日无通知</h3></c:otherwise>
        	</c:choose>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="block span6">
        <div class="block-heading">
            <span class="block-icon pull-right">
                <a href="#" class="demo-cancel-click" rel="tooltip" title="Click to refresh"><i class="icon-refresh"></i></a>
            </span>

            <a href="#widget2container" data-toggle="collapse">我的流程列表</a>
        </div>
        <div id="widget2container" class="block-body collapse in">
            <table class="table list">
            	  <thead>
            	  	<th>流程编号</th>
            	  	<th>流程名称</th>
            	  	<th>发起人</th>
            	  	<th>状态</th>
            	  	<th>发起日期</th>
            	  	<!-- 此列用于放查看按钮 -->
            	  	<th></th>
            	  </thead>
              <tbody>
              <!-- <a href="findRoleInfo?roleId=${role.id }" class="icon-eye-open"></a> -->
                  <!-- 查询数据库获取相关内容 -->
                   <c:forEach items="${processes }" var="process" begin="0" end="4" step="1">
                   		<tr>
                   			<td>${process.processId }</td>
                   			<td>${process.processName }</td>
                   			<td>${emp.username }</td>
                   			<td>
	                   			<c:choose>
	                   				<c:when test="${process.state == 1 }">
	                   					进行中
	                   				</c:when>
	                   				<c:when test="${process.state == 2 }">
	                   					完结
	                   				</c:when>
	                   				<c:when test="${process.state == 3 }">
	                   					终止
	                   				</c:when>
	                   			</c:choose>
                   			</td>
                   			<td>
                   				<fmt:formatDate value="${process.createtime }" pattern="yyyyMMdd" />
                   			</td>
                   			<td><a href="findProcessInfo?processId=${process.processId }" class="icon-eye-open"></a></td>
                   		</tr>
                   </c:forEach> 
              </tbody>
            </table>
            <p><a href="turnToShowProcesses">查看更多</a></p>
        </div>
    </div>
    <div class="block span6">
        <a href="#dateLsit" id="title" class="block-heading" data-toggle="collapse"></a>
        
        <div class="block-body" id="dateLsit">
            <div id="cv" style="width: 360px;height:50px;font-size:20px;padding: 20px 0 0 100px;box-sizing: border-box;"></div>
				<table id="dayList" class="table table-striped">
					<tr>
						<td>日</td>
						<td>一</td>
						<td>二</td>
						<td>三</td>
						<td>四</td>
						<td>五</td>
						<td>六</td>
					</tr>
				</table>
        </div>
    </div>
</div>
                    <footer>
                        <hr>
                        <p class="pull-right">Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>

                        <p>&copy; 2012 <a href="#" target="_blank">Portnine</a></p>
                    </footer>
                    
            </div>
        </div>
    </div>
    


    <script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        
        var tds =document.getElementsByClassName("dateTd");
	    	create();
	    	function create(){
	    		var now = new Date();
	    		//当月
	    		var month = now.getMonth();
	    		//当前天
	    		var day = now.getDate();
	    		//当前月初的星期
	    		var week = new Date(2018,month,1).getDay();
	    		//当前月的天数
	    		var countDay = days(month);
	    		//当前月需要几行
	    		if(Math.floor((countDay-(7-week))%7) == 0){
	    			var x = 0;
	    		}else{
	    			var x = 1;
	    		}
	    		var row = 1+Math.floor((countDay-(7-week))/7)+x;
	    		//创建日历
	    		for (var i = 0; i < row; i++) {
	    			var tr = $("<tr></tr>");
	    			for (var j = 0; j < 7; j++) {
	    				var td = $("<td class='dateTd'></td>");
	    				tr.append(td);
	    			}
	    			$("#dayList").append(tr);
	    		}
	    		var nowCount = 1;
	    		title.innerHTML = month+1+"月";
	    		for (var i = week; i < tds.length; i++) {
	    			tds[i].innerHTML = nowCount;
	    			nowCount++;
	    			if(tds[i].innerHTML == day){
	    				tds[i].style.background = "red";
	    				tds[i].style.color = "white";
	    				tds[i].style.borderRadius = "100%";  
	    			}
	    			if(nowCount>=countDay){
	    				return;
	    			}
	    		}
	    	}
	    	function days(x){
	    		if(x<=7){
	    			if(x%2 == 0){
	    				return 30;
	    			}else{
	    				return 31;
	    			}
	    		}else{
	    			if(x%2 == 0){
	    				return 31;
	    			}else{
	    				return 30;
	    			}
	    		}
	    	}
        
	    	
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
	    	
        
    </script>
</body>
</html>