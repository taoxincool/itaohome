<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Page Title</title>
</head>
<body>
	<div data-role="dialog">
		<div data-role="header" data-theme="b">
			<h1>${title}</h1>
		</div>
		<div data-role="content">
			<p style="text-align: center;">${message}</p>
			<p>
				<c:if test="${not empty url}">
					<a data-role="button" data-theme="b" href="<c:url value="${url}"/>">确认</a>
				</c:if>
				<c:if test="${empty url}">
					<a data-rel="back" data-role="button" data-theme="c" href="">关闭</a>
				</c:if>
			</p>
		</div>
	</div>
</body>
</html>