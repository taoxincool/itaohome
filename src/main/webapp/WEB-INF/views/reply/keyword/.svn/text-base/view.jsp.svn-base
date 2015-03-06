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

        <h1>关键字自动回复详情</h1>
    </div>

    <div data-role="content">
        <form action="" method="post" id="myform">
            <input type="hidden" name="id" value="${returnObj.id}"/>
            <input type="hidden" name="isDelete" value="${empty returnObj.isDelete ? '0':returnObj.isDelete }"/>
            <c:if test="${optType == 'keyword'}">
                <!--关注自动回复，需要填写的字段-->
                <input type="hidden" name="type" value="1"/>

                <div data-role="fieldcontain" class="readonly">
                    <label>用户输入关键字：</label>
                    <input name="inputContent" class="required" value="${returnObj.inputContent}"/>
                </div>

                <div data-role="fieldcontain" class="readonly">
                    <label>自动回复类型：</label>
                    <select name="replyContentType" id="replyContentType" class="required">
                        <option value="text" ${returnObj.replyContentType == 'text' ?  'selected' : ''}>文本消息</option>
                        <option value="news" ${returnObj.replyContentType == 'news' ?  'selected' : ''}>图文消息</option>
                    </select>
                </div>

                <div data-role="fieldcontain" id="replyContentDiv" class="readonly"
                     style="display: ${empty returnObj.replyContentType || returnObj.replyContentType == 'text' ? 'block':'none'}">
                    <label>自动回复内容：</label>
                    <textarea name="replyContent" class="required">${returnObj.replyContent}</textarea>
                </div>

                <div data-role="fieldcontain" id="replyContentTypeDiv" class="readonly"
                     style="display: ${returnObj.replyContentType == 'news' ? 'block':'none'}">
                    <label>关联图文消息：</label>
                    <select name="replyContentId" id="replyContentId">
                    </select>
                </div>
            </c:if>
            <input id="submitBtn" type="button" value="确定" data-theme="b"/>

            <c:if test="${!empty returnObj.id}">
                <input id="delBtn" type="button" value="删除" data-theme="c" sid="${returnObj.id}"/>
            </c:if>
        </form>

        <div data-role="footer" data-theme="c" data-position="fixed">
            <div data-role="navbar" data-iconpos="left">
                <ul>
                    <li><a href="<c:url value="/" />" data-icon="home" data-iconpos="left"
                           class="ui-btn-active ui-state-persist">主页</a></li>
                    </li>
                    <li><a href="#anylink" data-icon="grid" data-iconpos="left">消息</a></li>
                    <li><a href="#anylink" data-icon="star" data-iconpos="left">我的</a></li>
                </ul>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            //图文消息时，加载所有父节点
            $.ajax({
                type: "GET",
                url: "<c:url value="/reply/queryNews"/>",
                data: {},
                cache: false,
                success: function (data) {
                    $('#parentId').html('');
                    $.each(data, function (i, e) {
                        if ("${returnObj.replyContentId}" == e.id) {
                            $('#replyContentId').append($("<option />").val(e.id).text(e.title).attr("selected", true));//选中
                        } else {
                            $('#replyContentId').append($("<option />").val(e.id).text(e.title));
                        }
                    });
                    $('#replyContentId').selectmenu('refresh', true);
                }
            });
        });

        $("#replyContentType").change(function () {
            if ($(this).val() == "text") {
                $("#replyContentDiv").show();
                $("#replyContentTypeDiv").hide();
            } else {
                $("#replyContentDiv").hide();
                $("#replyContentTypeDiv").show();
            }
        });

        //提交表单
        $("#submitBtn").click(function () {
            if ($("#myform").valid()) {
                $("#submitBtn").button("disable");
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "<c:url value="/reply/keyword/submit"/>",
                    data: $('#myform').serialize(),// 你的formid
                    async: false,
                    success: function (data) {
                        if (data == "1") {
                            //成功
                            alert("操作成功!");
                            location.href = "<c:url value="/reply/keyword/list"/> ";
                        } else {
                            //失败
                            alert("提交失败!");
                            $("#submitBtn").button("enable");
                        }
                    }
                });
            }
        });


        $("#delBtn").click(function () {
            var sid = $(this).attr("sid");
            if (confirm("确定要删除吗？")) {
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "<c:url value="/reply/keyword/delete"/>",
                    data: {id:sid},
                    async: false,
                    success: function (data) {
                        if (data == "1") {
                            //成功
                            alert("删除成功!");
                            location.href = "<c:url value="/reply/keyword/list"/> ";
                        }else {
                            //失败
                            alert("删除失败!");
                            $("#submitBtn").button("enable");
                        }
                    }
                });
            }
        });

    </script>

</div>
</body>
</html>