<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>九宫格日记网</title>
<link href="css/base.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<link rel="stylesheet" href="css/info.css">
<!--[if lt IE 9]>
	<script src="js/modernizr.js"></script>
<![endif]-->
<script src="js/scrollReveal.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<form action="Check" method="post">
		<article>
			<div class="container">
				<c:if test="${empty sessionScope.isLogin }">
					<div
						style="text-align: center;margin-top:50px;font-size:20px;padding-bottom:50px;">
						请先<a href="login.jsp" style="color: yellow;">登录</a>！
					</div>
				</c:if>
				<c:if test="${!empty sessionScope.isLogin }">
					<table class="infos">
						<tr>
							<td>ID：</td>
							<td>${sessionScope.id }</td>
						</tr>
						<tr>
							<td>用户名：</td>
							<td>${sessionScope.username }</td>
						</tr>
						<tr>
							<td>电子邮箱：</td>
							<td>${sessionScope.email }</td>
							<td><input type="submit" value="修改邮箱"></td>
						</tr>
					</table>
				</c:if>
			</div>
		</article>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
