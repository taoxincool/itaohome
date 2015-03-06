<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page Title</title>
</head>
<body>

<div data-role="page">
    <div data-role="header" data-theme="b">
        <a href="#" data-role="button" data-icon="arrow-l" data-rel="back">返回</a>
        <h1>日志列表</h1>
    </div>
    <div data-role="content">
        <ul data-role="listview" data-inset="true" data-filter="true" data-filter-placeholder="搜索">
            <c:forEach items="${list}" var="item">
                <li>
                    <a href="<c:url value="/m/view?id=${item.id}" />">
                        <h2>${item.content == '' ? '图文消息' : item.content}</h2>
                        <p>接收人：${item.toUserName}</p>
                        <p>发送人：${item.fromUserName}</p>
                        <p>发送时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createDate}"/></p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div data-role="footer" data-theme="c" data-position="fixed">
        <div data-role="navbar" data-iconpos="left">
            <ul>
                <li><a href="<c:url value="/" />" data-icon="home" data-iconpos="left" class="ui-btn-active ui-state-persist">主页</a></li>
                <li><a href="#anylink" data-icon="grid" data-iconpos="left">消息</a></li>
                <li><a href="#anylink" data-icon="star" data-iconpos="left">我的</a></li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>