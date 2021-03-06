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
<title>修改员工信息</title>
</head>
<link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="css/stylesheets/theme.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/Valid.css">
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
        <!-- 根据权限动态获取左侧菜单 -->
		<c:forEach items="${permissions }" var="per">
	        <a href="${per.url }" onclick="window.location = '${per.url}'" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>${per.name }</a>
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
            <h1 class="page-title">修改员工信息</h1>
        </div>
        
        <ul class="breadcrumb">
            <li><a href="<%=basePath %>home.jsp">首页</a> <span class="divider">/</span></li>
            <li class="active">修改员工信息</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    <!-- 内容部分开始 -->
                    	<div class="well">
  		
		    <ul class="nav nav-tabs">
		      <li class="active"><a href="#home" data-toggle="tab" style="font-size:16px">基本信息</a></li>
		    </ul>
		 
    <div id="myTabContent" class="tab-content">
    <!-- 员工信息 -->
      <div class="tab-pane active in" id="home">
      <!-- 工号根据规则生成 -->
      <form class="updateEmpForm" action="updateEmp" method="post"> 
      <input type="hidden" name="empId" value="${updateEmp.empId }" id="hiddenId"/>
      	<table border="0">
      		<tr>
      			<td><label>工号</label></td>
      			<td><input type="text" name="empId" class="input-xlarge" disabled="disabled" value="${updateEmp.empId }" /></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><label>姓名</label></td>
      			<td><input type="text" name="username" class="input-xlarge" value="${updateEmp.username }" datatype="s" nullmsg="请输入姓名！" errormsg="姓名必须为汉字字符！" /></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td> <label>所属部门</label></td>
      			<td>
      				<select name="dept" id="dept" class="input-xlarge">
			        		<option value="1">综合管理部</option>
			        		<option value="2">财务部</option>
			        		<option value="3">研发部</option>
			        		<option value="4">市场部</option>
			        		<option value="5">营销部</option>
			        		<option value="6">人事部</option>
			        		<option value="7">行政部</option>
			        </select>
      			</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><label>职位</label></td>
      			<td><select name="job" id="job" class="input-xlarge">
		        		<option value="2">总经理</option>
		        		<option value="3">主管</option>
		        		<option value="4">总监</option>
		        		<option value="5">项目经理</option>
		        		<option value="6" selected="selected">员工</option>
		        </select></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><label>年龄</label></td>
      			<td><input type="text" name="age" class="input-xlarge" value="${updateEmp.age }" datatype="n"  errormsg="请输入数字！" nullmsg="请填写年龄"/></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><label>性别</label></td>
      			<td>
      				<input type="radio" name="sex" value="男" class="input-xlarge" datatype="*" errormsg="请选择性别！" nullmsg="请选择性别" /><span>男</span>
       				<input type="radio" name="sex" value="女" class="input-xlarge" /><span>女</span>
      			</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><label>婚姻状况</label></td>
      			<td>
      				<input type="radio" name="ismarry" value="1" class="input-xlarge" datatype="*" errormsg="请选择婚姻状况！" nullmsg="请选择婚姻状况" /><span>已婚</span>
        				<input type="radio" name="ismarry" value="2" class="input-xlarge" /><span>未婚</span>
      			</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><label>联系电话</label></td>
      			<td><input type="text" name="phone" class="input-xlarge" value="${updateEmp.phone }" datatype="m" errormsg="请输入手机号码！" nullmsg="请填写手机号码！"/></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><label>公司邮箱</label></td>
      			<td><input type="text" name="email" class="input-xlarge" value="${updateEmp.email }" datatype="e" errormsg="请输入正确的邮箱格式！" nullmsg="请填写邮箱！"/></td>
      			<td></td>
      		</tr>
      		<tr>
      			<td>
      				
			         <div class="btn-toolbar">
			   			 <i class="icon-save"><input type="reset" class="btn btn-primary" value="重置"></i> 
			  		</div>
			  		<div class="btn-group">
			  		</div>
      				
      			</td>
      			<td>
			         <div class="btn-toolbar">
			   			 <i class="icon-save"><input type="submit" class="btn btn-primary" value="修改"></i> 
			  		</div>
			  		<div class="btn-group">
			  		</div>
      			</td>
      			<td></td>
      		</tr>
      	</table>
        </form>
        
      </div>
      
  </div>


  		</div>
</div>
                    <!-- 内容部分结束 -->

                    <footer>
                        <hr>
                        <p class="pull-right">Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>

                        <p>&copy; 2012 <a href="#" target="_blank">Portnine</a></p>
                    </footer>
                    
            </div>
        </div>
    </div>
    
    <!-- 引用 -->
    <script src="validform/Validform_v5.3.2.js"></script>
    <script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
    
    $(".updateEmpForm").Validform({
		tiptype:3
	});
    
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        
      //获取值判断是否结婚
        console.log("${updateEmp.ismarry}");
        if("${updateEmp.ismarry}" == 1){
        	 	document.getElementsByName("ismarry")[0].checked='checked';
        }else{
        		document.getElementsByName("ismarry")[1].checked='checked';
        }
        //获取值判断性别
        var sex = '男';
        <%
        Emp emp = (Emp)session.getAttribute("updateEmp");
        		String sex = emp.getSex();
        		System.out.println(emp);
        %>
        if('<%=sex %>' == sex){
        		document.getElementsByName("sex")[0].checked='checked';
        }else{
        		document.getElementsByName("sex")[1].checked='checked';
        }
       //获取所属部门
       var sel="${updateEmp.dept.id}";
       
		$("#dept").find("option[value="+sel+"]").attr("selected",true);
        //获取职位
        var jobNum = "${updateEmp.role.id}";
        $("#job").find("option[value=" + jobNum + "]").attr("selectde",true);
        
    </script>
    
    
</body>
</html>