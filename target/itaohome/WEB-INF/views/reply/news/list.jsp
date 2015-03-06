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
        <a href="<c:url value="/reply/menu"/>" data-role="button" data-icon="arrow-l">返回</a>
        <h1>图文消息列表</h1>
        <a href="<c:url value="/reply/news/new"/> " data-role="button" data-icon="plus">新增</a>
    </div>
    <div data-role="content">
        <ul data-role="listview" data-inset="true" data-filter="true" data-filter-placeholder="搜索">
            <c:forEach items="${list}" var="item">
                <li>
                    <a href="<c:url value="/reply/news/view?id=${item.id}"/> ">
                        <h2>${item.title}</h2>
                        <p>描述内容：<c:out value="${item.description}"/></p>
                        <p>创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createDate}"/></p>
                        <c:if test="${empty item.parentId || parentId == ''}">
                            <span class="ui-li-count"><font color="green">父节点</font></span>
                        </c:if>
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