<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<header>
	<div class="logo" data-scroll-reveal="enter right over 0.5s">
		<a href="/"><img src="images/logo.png"> </a>
	</div>
	<nav class="topnav"
		data-scroll-reveal="enter bottom over 0.5s after 0s">
		<a href="Diary?Page=1&from=index"><span>首页</span><span class="en">Home</span> </a> 
		<a href="diary.jsp"><span>九宫格日记</span><span class="en">Diary</span></a>
		<a href="info.jsp"><span>个人信息</span><span class="en">Info</span></a>
		<a href="Diary?Page=1&from=mine"><span>我的日记</span><span class="en">Mine</span></a>
		<c:if test="${!empty sessionScope.isLogin}">
			<div style="clear:both;float:right;font-size:18px;">用户名：${sessionScope.username } &nbsp;&nbsp;<a href='Check?from=logout'>注销</a></div>
		</c:if>
		<c:if test="${empty sessionScope.isLogin}">
			<div style="clear:both;float:right;font-size:18px;"><a href='login.jsp' style="color:yellow;">登录</a></div>
		</c:if>
	</nav>
</header>