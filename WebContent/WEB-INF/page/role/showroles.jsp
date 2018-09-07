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
<title>角色管理</title>
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
		<c:forEach items="${permissions }" var="per">
	        <a onclick="window.location = '${per.url}'" href="${per.url }" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>${per.name }</a>
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
            <h1 class="page-title">角色管理</h1>
        </div>
        
        <ul class="breadcrumb">
            <li>首页<span class="divider">/</span></li>
            <li class="active">角色管理</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    <!-- 内容开始 -->
					<div class="btn-toolbar">
					    <a href="turnToCreateRole" class="btn btn-primary" id="addRole"><i class="icon-plus"></i> 新增岗位角色</a>
					  <div class="btn-group">
					  </div>
					</div>
					
					<div class="well">
					    <table class="table">
					      <thead>
					        <tr>
					          <th>角色编号</th>
					          <th>角色名称</th>
					          <th>查看权限</th>
					          <th style="width: 100px;">相关操作</th>
					        </tr>
					      </thead>
					      <!-- 循环写入数据 -->
					      <tbody>
						      <c:forEach items="${roles }" var="role">
							      	<tr>
							      		<td>${role.id }</td>
							      		<td>${role.name }</td>
							      		<!-- 查看权限接口，添加角色接口，修改角色接口，删除角色接口 -->
							      		<td><a href="findRoleInfo?roleId=${role.id }" class="icon-eye-open"></a></td>
							      		<td>
								              <a href="#myModal" class="del" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
								          </td>
							      	</tr>
						      </c:forEach>
					      </tbody>
					    </table>
					</div>
					<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					    <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					        <h3 id="myModalLabel">⚠注意️</h3>
					    </div>
					    <div class="modal-body">
					        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>确定要删除此岗位角色吗?</p>
					    </div>
					    <div class="modal-footer">
					        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					        <button onclick="deleteRole()" class="btn btn-danger" data-dismiss="modal">确认</button>
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
<script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        //点击删除获取角色id
        var roleId = "";
        $(".del").on("click",function(){
			console.log($(this).parent().parent().get(0).firstElementChild.innerHTML);
			roleId = $(this).parent().parent().get(0).firstElementChild.innerHTML;
		});
        
        //删除角色
        function deleteRole(){
        		$.ajax({
        			type:"post",
        			url:"deleteRole",
        			data:{
        				roleId:roleId
        			},
        			success:function(res){
        				if(res == 1){
        					location.href="roleManager";
        				}else{
        					
        				}
        			}
        		});
        		
        }
        
    </script>
</html>