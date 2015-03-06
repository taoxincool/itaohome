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
        <h1>消息详情</h1>
    </div>
    <div data-role="content">

        <form action="" method="post" id="myform">

            <div data-role="fieldcontain" class="readonly">
                <label>发送者：</label>
                <label>${returnObj.fromUserName}</label>
            </div>

            <div data-role="fieldcontain" class="readonly">
                <label>接受者：</label>
                <label>${returnObj.toUserName}</label>
            </div>

            <div data-role="fieldcontain" class="readonly">
                <label>消息类型：</label>
                <label>${returnObj.msgType}</label>
            </div>

            <c:if test="${!empty returnObj.event}">
            <div data-role="fieldcontain" class="readonly">
                <label>事件：</label>
                <label>${returnObj.event}</label>
            </div>
            </c:if>

            <c:if test="${!empty returnObj.eventKey}">
            <div data-role="fieldcontain" class="readonly">
                <label>事件key：</label>
                <label>${returnObj.eventKey}</label>
            </div>
            </c:if>

            <c:if test="${!empty returnObj.ticket}">
            <div data-role="fieldcontain" class="readonly">
                <label>二维码ticket：</label>
                <label>${returnObj.ticket}</label>
            </div>
            </c:if>

            <c:if test="${!empty returnObj.content}">
            <div data-role="fieldcontain" class="readonly">
                <label>消息内容：</label>
                <label>${returnObj.content}</label>
            </div>
            </c:if>

            <div data-role="fieldcontain" class="readonly">
                <label>消息XML：</label>
                <label>${returnObj.memo}</label>
            </div>

            <c:if test="${!empty returnObj.msgId}">
                <div data-role="fieldcontain" class="readonly">
                    <label>微信消息ID：</label>
                    <label>${returnObj.msgId}</label>
                </div>
            </c:if>

            <c:if test="${!empty returnObj.picUrl}">
                <div data-role="fieldcontain" class="readonly">
                    <label>图片链接：</label>
                    <label>${returnObj.picUrl}</label>
                </div>
            </c:if>

            <div data-role="fieldcontain" class="readonly">
                <label>创建时间：</label>
                <label>${returnObj.createDate}</label>
            </div>
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