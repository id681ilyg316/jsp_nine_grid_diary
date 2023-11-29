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
<base href="<%=basePath%>">

<title>九宫格日记网</title>
<link href="css/base.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/diary.css">
<!--[if lt IE 9]>
	<script src="js/modernizr.js"></script>
<![endif]-->
<script src="js/scrollReveal.js"></script>
<script src="js/html2canvas.min.js"></script>

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<article>
		<form id="form1" action="DiarySave" method="post">
			<div class="container" id="container">
				<c:if test="${empty sessionScope.isLogin }">
					<div
						style="text-align: center;margin-top:40px;font-size:20px;padding-bottom:40px;">
						请先<a href="login.jsp" style="color: yellow;">登录</a>！
					</div>
				</c:if>
				<c:if test="${!empty sessionScope.isLogin }">
					<div class="title">
						<input type="text" name="title" placeholder="标题">
					</div>
					<div class="content" id="contentimg">
							<div>
								<div class="ctitle">开心的事</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
							<div>
								<div class="ctitle">为他人做的事</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
							<div>
								<div class="ctitle">计划/工作/备忘</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
							<div>
								<div class="ctitle">比起昨天的进步</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
							<div>
								<div class="ctitle">总结/日期：</div>
								<input type="text" name="t" placeholder="点此输入" >
							</div>
							<div>
								<div class="ctitle">心情/感悟/灵感</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
							<div>
								<div class="ctitle">关注/八卦/新闻</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
							<div>
								<div class="ctitle">健康/饮食/体重</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
							<div>
								<div class="ctitle">梦境</div>
								<input type="text" name="t" placeholder="点此输入">
							</div>
					</div>
					<div class="clear"></div>
					<input type="text" name="pic" id="pic" style="display:none;" />
					<div class="commit">
						<input type="button" value="提交" onclick="makepic()">
					</div>
				</c:if>
			</div>
		</form>
	</article>
	<script>
		function makepic() {
			html2canvas(document.querySelector("#contentimg")).then(canvas => {
				var url = canvas.toDataURL();
				document.getElementById("pic").value = url;
				document.getElementById("form1").submit();
				//document.body.appendChild(canvas);
			});
		}
	</script>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
