<%@page import="com.yxf.oa.bean.Emp"%>
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
            <h1 class="page-title">流程发起</h1>
        </div>
        
        <ul class="breadcrumb">
            <li>首页<span class="divider">/</span></li>
            <li class="active">流程发起</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    <!-- 内容开始 -->
                    <div class="well">
                    		<input type="hidden" id="deptId" name="deptId" value="${emp.dept.id }">
                    		<input type="hidden" id="roleId" name="roleId" value="${emp.role.id }">
                    		<input type="hidden" id="creater" name="creater" value="${emp.empId }">
                    		<table>
                    			<tr>
                    				<td>流程名称</td>
                    				<td>
                    				<%
                    					String type = (String)request.getAttribute("type");
                    					String processName="";
	                    				switch (Integer.parseInt(type)) {
	                    				case 1:
	                    					processName = "补打卡";
	                    					break;
									case 2:
										processName = "加班申请";
	                    					break;
									case 3:
										processName = "病事假申请";
                    						break;
	                    				}
                    				%>
                    					<select id="select" name="processName" disabled="disabled" class="input-xlarge">
                    						<option value="<%=processName %>"><%=processName %></option>
                    					</select>
                    				</td>
                    				<td></td>
                    			</tr>
                    			<%
                    				if(processName.equals("补打卡")){
                    			
                    			%>	
                 			<tr>
                    				<td>补签日期</td>
                    				<td><input id="clockDate" type="date" name="clockDate" nullmsg="请填写日期"></td>
                    				<td></td>
                    			</tr>
                    			<tr>
                    				<td>打卡时间</td>
                    				<td>
                    					<input type="radio" name="clock" value="am" class="input-xlarge" nullmsg="请选择时间" /><span>上午</span>
       								<input type="radio" name="clock" value="pm" class="input-xlarge" /><span>下午</span>
       							</td>
                    				<td></td>
                    			</tr>
                    			<%	
                    				}else if(processName.equals("病事假申请")){
                    			%>
                    				<tr>
	                    				<td>请假日期</td>
	                    				<td><input id="beginDate" type="date" name="beginDate" nullmsg="请填写日期"></td>
	                    				<td>至</td>
	                    				<td><input id="endDate" type="date" name="endDate" nullmsg="请填写日期"></td>
	                    			</tr>
                    			<%		
                    				}
                    			%>
                    			
                    			<tr>
                    				<td>原因</td>
                    				<td><textarea name="content" id="content" class="form-control" rows="3" nullmsg="请填写原因"></textarea></td>
                    				<td></td>
                    			</tr>
                    			<tr>
                    				<td>设置审批人</td>
                    				<td>
                    					<select class="input-xlarge" name="next" id="next" nullmsg="请选择审批人">
                    							<option value="">请选择</option>
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
                    				<td><input type="button" id="createProcess" value="流程发起" class="btn btn-primary" ></td>
                    				<td></td>
                    			</tr>
                    		
                    		</table>
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
	        document.getElementById("createProcess").onclick=function(){
	        		//取消select标签desabled属性
	        		$("#select").attr("disabled",false);
	        		$.ajax({
	        			type:"post",
	        			url:"createProcess",
	        			data:{
	        				deptId:$("#deptId").val(),
	        				roleId:$("#roleId").val(),
	        				creater:$("#creater").val(),
	        				processName:$("#select").val(),
	        				clockDate:$("#clockDate").val(),
	        				clock:$('input[name="clock"]:checked').val(),
	        				content:$("#content").val(),
	        				next:$("#next").val(),
	        				beginDate:$("#beginDate").val(),
	        				endDate:$("#endDate").val()
	        			},
	        			success:function(res){
	        				console.log(res);
	        				if(res == 1){
	        					alert("流程发起成功，请等待上级审核");
	        					location.href="turnToHome";
	        				}else{
	        					alert("流程启动失败,请查看相关内容是否填写正确");
	        				}
	        			}
	        		})
	        }
        
        
        
        
    </script>
</html>