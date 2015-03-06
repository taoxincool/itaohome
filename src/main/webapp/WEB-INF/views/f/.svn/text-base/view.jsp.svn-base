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

        <h1>粉丝详情</h1>
    </div>
    <div data-role="content">

        <form action="" method="post" id="myform">


            <div data-role="fieldcontain" class="readonly">
                <label>微信：</label>
                <label>${returnObj.openId}</label>
            </div>

            <div data-role="fieldcontain" class="readonly">
                <label>状态：</label>
                <label>${returnObj.status == '0' ? '已取消关注': '已关注'}</label>
            </div>

            <div data-role="fieldcontain" class="readonly">
                <label>关注时间：</label>
                <label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${returnObj.createDate}"/></label>
            </div>
            <%--<input id="submit-button" name="submit" type="submit" value="认证" data-theme="b" />--%>
        </form>

    <div data-role="footer" data-theme="c" data-position="fixed">
        <div data-role="navbar" data-iconpos="left">
            <ul>
                <li><a href="#anylink" data-icon="home" data-iconpos="left"
                       class="ui-btn-active ui-state-persist">主页</a></li>
                <li><a href="#anylink" data-icon="grid" data-iconpos="left">消息</a></li>
                <li><a href="#anylink" data-icon="star" data-iconpos="left">我的</a></li>
            </ul>
        </div>
    </div>

</div>
</body>
</html>