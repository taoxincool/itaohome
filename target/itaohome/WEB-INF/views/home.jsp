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
        <h3>ITAOHOME管理平台</h3>
    </div>
    <div data-role="content">
        <ul data-role="listview" data-inset="true">
            <li>
                <a href="<c:url value="/reply/menu"/>">
                    <h2>回复管理</h2>
                    <p>您可以配置“关注”、“扫描”、“输入关键字”等用户操作，微信自动回复的内容.</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/f/list"/>">
                    <h2>粉丝统计</h2>
                    <p>您可以查看关注此微信公众号的所有人的信息.</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/m/list"/>">
                    <h2>消息管理</h2>
                    <p>您可以查看此微信公众号的所有的聊天消息记录.</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/l/list"/>">
                    <h2>日志管理</h2>
                    <p>您可以统计与查看此此管理平台运行日志.</p>
                </a>
            </li>
            <li>
                <a href="#">
                    <h2>用户管理</h2>
                    <p>您可以配置登录此后台系统的用户.</p>
                </a>
            </li>
        </ul>

    </div>

    <div data-role="footer" data-theme="c" data-position="fixed" >
        <div data-role="navbar" data-iconpos="left">
            <ul>
                <li><a href="<c:url value="/" />" data-icon="home" data-iconpos="left" class="ui-btn-active ui-state-persist">主页</a></li>
                <li><a href="#anylink" data-icon="star" data-iconpos="left">我的</a></li>
            </ul>
        </div>
    </div>

</div>
</body>
</html>