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
<link rel="stylesheet" type="text/css" href="css/lightbox.min.css">

<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<script src="js/scrollReveal.js"></script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<article>
		<div class="container">
			<c:if test="${empty sessionScope.isLogin }">
				<div
					style="text-align: center;margin-top:50px;font-size:20px;padding-bottom:50px;">
					请先<a href="login.jsp" style="color: yellow;">登录</a>！
				</div>
			</c:if>
			<c:if test="${!empty sessionScope.isLogin }">
			<script type="text/javascript">
				var h = window.location.href;
				if (h.substring(h.lastIndexOf('/')+1)=="mine.jsp")
					window.location="Diary?Page=1&from=mine";
			</script>
				<c:if test="${sessionScope.status != 'mine' }">
					<script type="text/javascript">
						window.location="Diary?Page=1&from=mine";
					</script>
				</c:if>
			<ul class="cbp_tmtimeline">
				<c:forEach items="${requestScope.diarys }" var="d">
					<li><time class="cbp_tmtime">
							<span>${d.timeBeg }</span> <span>${d.timeEnd }</span>
						</time>
						<div class="cbp_tmicon"></div>
						<div class="cbp_tmlabel" data-scroll-reveal="enter right over 0.5s">
							<p>
								<span class="blogpic"><a href="${d.address }"
									data-lightbox="image-1"><img src="${d.address }"> </a> </span>
									<div style="font-size:1.2em;line-height:2em;">
										<h2>${d.title }</h2>
										作者：${d.username} <br>
										日期：${d.timeEnd } <br>
										时间：${d.timeBeg }
									</div>
							</p>
							<a href="DiaryDelete?id=${d.id }" class="readmore">删除本条</a>
						</div></li>
				</c:forEach>
			</ul>
			<div class="page">
				${requestScope.pageinfo }
			</div>
			</c:if>
		</div>
	</article>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
