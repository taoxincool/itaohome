<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf8" %>  
<html>
<head>
</head>
<body>
	<div data-role="dialog">
		<div data-role="header" data-theme="b">
			<h1>${title}</h1>
		</div>
		<div data-role="content">
			<p style="text-align: center;">${message}</p>
			<p>
				<a data-role="button" data-theme="b" href="<c:url value="${url}"/>">确认</a>
				<a data-rel="back" data-role="button" data-theme="c" href="">取消</a>
			</p>
		</div>
	</div>		
</body>
</html>