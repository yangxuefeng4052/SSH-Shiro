<%@page import="com.yxf.oa.bean.Process"%>
<%@page import="java.util.List"%>
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
	<link rel="stylesheet" type="text/css" href="css/Valid.css">
    

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
        .pagination >li{
        		display: inline-block;
        		font-size: 15px;
        }
        .pagination{
        		text-align: center;
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
	        <a href="<%=basePath %>${per.url }" onclick="window.location = '${per.url}'" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>${per.name }</a>
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
            <h1 class="page-title">报表统计</h1>
        </div>
        
        <ul class="breadcrumb">
            <li>首页<span class="divider">/</span></li>
            <li class="active">报表统计</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    <!-- 内容开始 -->
                    <div class="well">
                    		<ul class="nav nav-tabs">
				      <li class="active"><a href="" data-toggle="tab" style="font-size:16px">所有流程信息统计</a></li>
				    </ul>
				    
				    <div class="tab-content">
				    		<!-- 所有流程信息统计开始 -->
				    		<%
				    			int total = (int)session.getAttribute("count");
				    			int pageCount = total % 5 == 0 ? total/5 :total/5 +1;
				    			System.out.println(total);
				    			System.out.println(pageCount);
				    			%>
				    		<div class="tbVisibale">
				    		<table class="table list" id="all">
				    			<tr>
				    				<td>流程名称</td>
				    				<td>流程编号</td>
				    				<td>发起日期</td>
				    				<td>状态</td>
				    				<td>发起人</td>
				    			</tr>
				    			<c:forEach items="${allProcesses }" var="process" >
				    				<tr>
				    				<td>${process.processName }</td>
				    				<td>${process.processId }</td>
				    				<td>${process.createtime }</td>
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
				    				<td>${process.creater }</td>
				    			</tr>
				    			</c:forEach>
				    		</table>
				    			
				    			<nav aria-label="Page navigation">
							  <ul class="pagination">
							    <li>
							      <a href="#" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    <c:forEach begin="1" end="<%=pageCount %>" step="1" var="i">
							    		<li><a href="projectDeclare?page=${i }">${i }</a></li>
							    </c:forEach>
							    <li>
							      <a href="#" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							  </ul>
							</nav>
				    		</div>
				    		</div>
				    		<!-- 所有流程信息统计结束 -->
				    		
				    </div>
				    
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
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="validform/Validform_v5.3.2.js"></script>
    <script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        $(".well").Validform({
    		tiptype:2
    	});
	      
        
        
        
        
    </script>
</html>