<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page Title</title>
</head>
<body>
<div data-role="page">

    <div data-role="header" data-theme="b">
        <a href="<c:url value="/"/>" data-role="button" data-icon="arrow-l">返回</a>
        <h3>回复管理</h3>
    </div>
    <div data-role="content">
        <ul data-role="listview" data-inset="true">
            <li>
                <a href="<c:url value="/reply/subscribe/list"/> ">
                    <h2>关注回复</h2>
                    <p>您可以配置“关注”、“扫描”用户操作，微信自动回复的内容.注意最多只能启用一条.</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/reply/keyword/list"/> ">
                    <h2>关键字回复</h2>
                    <p>您可以配置用户输入相关关键字，微信自动回复的内容..</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/reply/news/list"/> ">
                    <h2>图文消息维护</h2>
                    <p>您可以配置图文消息内容.</p>
                </a>
            </li>
        </ul>
    </div>

    <div data-role="footer" data-theme="c" data-position="fixed" >
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