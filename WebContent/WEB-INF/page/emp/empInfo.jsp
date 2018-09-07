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
<title>标题</title>
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
        span{
        		font-size:15px;
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
                            <li><a tabindex="-1" href="<%=basePath %>turnToInfo">用户信息</a></li>
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
    </div>
    
    <div class="content">
    		<div class="header">
            <h1 class="page-title">用户基本信息</h1>
        </div>
        <ul class="breadcrumb">
            <li><a href="<%=basePath %>home.jsp">首页</a> <span class="divider">/</span></li>
            <li class="active">用户基本信息</li>
        </ul>
        
        <div class="container-fluid">
            <div class="row-fluid">
         <!-- 保存按钮 -->
         <div class="btn-toolbar">
   			 <button id="save" class="btn btn-primary"><i class="icon-save"></i> 保存</button>
  		<div class="btn-group">
  		</div>
  		
  		<div class="well">
  		
		    <ul class="nav nav-tabs">
		      <li class="active"><a href="#home" data-toggle="tab" style="font-size:16px">基本信息</a></li>
		      <li><a href="#profile" data-toggle="tab" style="font-size:16px">密码修改</a></li>
		    </ul>
		 
    <div id="myTabContent" class="tab-content">
    <!-- 员工信息 -->
      <div class="tab-pane active in" id="home">
      	<!-- e_id -->
      	<input type="hidden" value="${emp.id }" id="hiddenId"/>
        <label>姓名</label>
        <input type="text" value="${emp.username }" class="input-xlarge" disabled="disabled"/>
        <label>工号</label>
        <input type="text" value="${emp.empId }" class="input-xlarge" disabled="disabled"/>
        <label>所属部门</label>
        <!-- 下拉框 -->
        <select name="dept" id="dept" class="input-xlarge" disabled="disabled">
        		<option value="1">综合管理部</option>
        		<option value="2">财务部</option>
        		<option value="3">研发部</option>
        		<option value="4">市场部</option>
        		<option value="5">营销部</option>
        		<option value="6">人事部</option>
        		<option value="7">行政部</option>
        </select>
        <label>职位</label>
        <select name="job" id="job" class="input-xlarge" disabled="disabled">
        		<option value="2">总经理</option>
        		<option value="3">主管</option>
        		<option value="4">总监</option>
        		<option value="5">项目经理</option>
        		<option value="6">员工</option>
        </select>
        <label>年龄</label>
        <input type="text" id="age" value="${emp.age }" class="input-xlarge"/>
        <label>性别</label>
        <!-- redio -->
        <input type="radio" name="sex" value="男" class="input-xlarge" /><span>男</span>
        <input type="radio" name="sex" value="女" class="input-xlarge" /><span>女</span>
        <label>婚姻状况</label>
         <!-- redio -->
        <input type="radio" name="ismarry" value="1" class="input-xlarge" /><span>已婚</span>
        <input type="radio" name="ismarry" value="2" class="input-xlarge" /><span>未婚</span>
        
        <label>联系电话</label>
        <input type="text" id="phone" value="${emp.phone }" class="input-xlarge"/>
        <div class="alert alert-warning alert-dismissible" role="alert" style="width:500px;height:30px;font-size: 15px;">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>请注意!</strong> 修改联系电话后，您之后登录将使用修改后的电话号码登录。
		</div>
        <label>公司邮箱</label>
        <input type="text" id="email" value="${emp.email }" class="input-xlarge"/>
        
      </div>
      <!-- 修改密码 -->
      <div class="tab-pane fade" id="profile">
      
        <label>设置新的密码</label>
        <input type="password" class="input-xlarge" id="password">
        <div>
            <button class="btn btn-primary" id="update">修改</button>
        </div>
      </div>
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
    
    
    
</body>
<script src="js/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        
        /* 点击按钮修改密码 */
        document.getElementById("update").onclick=function(){
	        	$.ajax({
	    			type:"post",
	    			url:"updatePsw",
	    			data:{
	    				psw:$("#password").val(),
	    				id:$("#hiddenId").val()
	    			},
	    			success:function(res){
	    				console.log(res);
	    				if(res == 1){
	    					alert("密码修改成功！请妥善保管您的新密码");
	    				}else{
	    					alert("密码修改失败");
	    				}
	    			}
	    		});
        }
        
        //获取值判断是否结婚
        console.log("${emp.ismarry}");
        if("${emp.ismarry}" == 1){
        	 	document.getElementsByName("ismarry")[0].checked='checked';
        }else{
        		document.getElementsByName("ismarry")[1].checked='checked';
        }
        //获取值判断性别
        var sex = '男';
        <%
        Emp emp = (Emp)session.getAttribute("emp");
        		String sex = emp.getSex();
        		System.out.println(emp);
        %>
        if('<%=sex %>' == sex){
        		document.getElementsByName("sex")[0].checked='checked';
        }else{
        		document.getElementsByName("sex")[1].checked='checked';
        }
       //获取所属部门
       var sel="${emp.dept.id}";
       
		$("#dept").find("option[value="+sel+"]").attr("selected",true);
        //获取职位
        var jobNum = "${emp.role.id}";
        $("#job").find("option[value=" + jobNum + "]").attr("selectde",true);
        
        
        //点击保存提交表单
        document.getElementById("save").onclick=function(){
        		$.ajax({
        			type:"post",
        			url:"updateEmpInfo",
        			data:{
        				age:$("#age").val(),
        				sex:$("input[name='sex']:checked").val(),
        				ismarry:$("input[name='ismarry']:checked").val(),
        				phone:$("#phone").val(),
        				email:$("#email").val()
        			},
        			success:function(res){
        				location.href="empManager";
        			}
        		})
        }
        
        
    </script>
</html>