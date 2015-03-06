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
        <h1>关注回复列表</h1>
        <a href="<c:url value="/reply/subscribe/new"/> " data-role="button" data-icon="plus">新增</a>
    </div>
    <div data-role="content">
        <ul data-role="listview" data-inset="true" data-filter="true" data-filter-placeholder="搜索">
            <c:forEach items="${list}" var="item">
                <li>
                    <a href="<c:url value="/reply/subscribe/view?id=${item.id}"/> ">
                        <h2>${empty item.inputContent ? '关注操作' : item.inputContent}</h2>
                        <p>回复内容：${empty item.replyContent == true ? '回复图文消息'+item.replyContentId: item.replyContent}</p>
                        <p>创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createDate}"/></p>
                        <span class="ui-li-count">${item.enable == '1' ? '<font color="green">已启用</font>' : '未启用'}</span>
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