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
<title>上班打卡</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="css/stylesheets/theme.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">

    <script src="js/jquery-2.2.4.min.js" type="text/javascript"></script>

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
        td{
        		width:100px;
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
            <h1 class="page-title">流程详情</h1>
        </div>
        
        <ul class="breadcrumb">
            <li>首页<span class="divider">/</span></li>
            <li>流程详情</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    <!-- 内容开始 -->
                    <div class="well">
                    		<table>
                    			<tr>
                    				<td></td>
                    				<td><h3>流程信息</h3></td>
                    				<td></td>
                    			</tr>
                    			<tr>
                    				<td>流程名称</td>
                    				<td>
                    					<select id="select" name="processName" disabled="disabled" class="input-xlarge">
                    						<option value="${processInfo.processName }">${processInfo.processName }</option>
                    					</select>
                    				</td>
                    				<td></td>
                    			</tr>
                    			<tr>
                    				<td>状态</td>
                    				<td>
                    					<select class="input-xlarge" disabled="disabled">
	                    					<c:choose>
			                   				<c:when test="${processInfo.state == 1 }">
			                   					<option value="${processInfo.state}">进行中</option>
			                   				</c:when>
			                   				<c:when test="${processInfo.state == 2 }">
			                   					<option value="${processInfo.state}">完结</option>
			                   				</c:when>
			                   				<c:when test="${processInfo.state == 3 }">
			                   					<option value="${processInfo.state}">终止</option>
			                   				</c:when>
		                   				</c:choose>
                   					</select>
                    				</td>
                    				<td></td>
                    			</tr>
                    			
                    			<c:if test="${processInfo.processName eq '病事假申请' }">
                    				<tr>
	                    				<td>请假日期</td>
	                    				<td><fmt:formatDate value="${processInfo.beginDate }"/></td>
	                    				<td>至</td>
	                    				<td><fmt:formatDate value="${processInfo.endDate }"/></td>
	                    			</tr>
                    			</c:if>
                    			
                    			<tr>
                    				<td>原因</td>
                    				<td><textarea name="content" id="content" class="form-control" rows="3" disabled="disabled" >${processInfo.content }</textarea></td>
                    				<td></td>
                    			</tr>
                    			<tr>
                    				<td>发起日期</td>
                    				<td>
                    					<input type="text" disabled="disabled" value="${processInfo.createtime }" >
                    				</td>
                    				<td></td>
                    			</tr>
                    			<tr>
                    				<td>审批人</td>
                    				<td>
                    					<select class="input-xlarge" name="next" id="next" disabled="disabled">
                    						<c:forEach items="${emp.dept.emps }" var="employee">
                    							<c:if test="${emp.role.id - 1 == employee.role.id }">
                    								<option value="${employee.empId }">${employee.username }</option>
                    							</c:if>
                    						</c:forEach>
                    					</select>
                    				</td>
                    				<td></td>
                    			</tr>
                    			<tr>
                    				<td></td>
                    				<td><h3>任务流转状态</h3></td>
                    				<td></td>
                    			</tr>
                    			<c:if test="${not empty processInfo.tasks }">
                    			<c:forEach items="${processInfo.tasks }" var="task">
                    				<c:if test="${not empty task.approver }">
		                    			<tr style="border: 1px;">
		                    				<td>审批意见</td>
		                    				<td><input type="text" value="${task.approver }" disabled="disabled" /></td>
		                    				<td></td>
		                    			</tr>
		                    			<tr style="border: 1px;">
		                    				<td>备注</td>
		                    				<td><textarea name="remark" class="form-control" rows="2" disabled="disabled" >${task.remark }</textarea></td>
		                    				<td></td>
		                    			</tr>
	                    			</c:if>
                    			</c:forEach>
                    			</c:if>
                    		</table>
                    
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

        
    </script>
</html>