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

        <h1>图文详情</h1>
    </div>

    <div data-role="content">
        <form action="" method="post" id="myform">
            <input type="hidden" name="id" value="${returnObj.id}"/>
            <input type="hidden" name="isDelete" value="${empty returnObj.isDelete ? '0':returnObj.isDelete }"/>
            <!--关注自动回复，需要填写的字段-->

            <div data-role="fieldcontain" class="readonly">
                <label>图文标题：</label>
                <input name="title" class="required" value="${returnObj.title}"/>
            </div>

            <div data-role="fieldcontain" class="readonly">
                <label>图文内容链接：</label>
                <input name="url" class="required" value="${returnObj.url}"/>
            </div>

            <div data-role="fieldcontain" class="readonly">
                <label>图文内容描述：</label>
                <textarea name="description" class="required">${returnObj.description}</textarea>
            </div>

            <div data-role="fieldcontain" class="readonly">
                <label>图片链接：</label>
                <input name="picUrl" id="picUrl" class="required" value="${returnObj.picUrl}"/>
                <input type="file" name="picUrlFile" id="picUrlFile" class="form-control"/>
            </div>


            <%--修改时，如果不是父节点则显示--%>
            <div data-role="fieldcontain" class="readonl    y" style="display:${(empty returnObj.id || (!empty returnObj && !empty returnObj.parentId && returnObj.parentId != '')) ? 'block' : 'none'}">
                <label>父图文节点：<font color="red">(不选择时为父节点)</font></label>
                <select name="parentId" id="parentId">
                </select>
            </div>

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
                    $('#parentId').append($("<option />").val("").text("--请选择--"));
                    $.each(data, function (i, e) {
                        if ("${returnObj.parentId}" == e.id) {
                            $('#parentId').append($("<option />").val(e.id).text(e.title).attr("selected", true));//选中
                        } else {
                            $('#parentId').append($("<option />").val(e.id).text(e.title));
                        }
                    });
                    $('#parentId').selectmenu('refresh', true);
                }
            });
        });

        //提交表单
        $("#submitBtn").click(function () {
            if ($("#myform").valid()) {
                $("#submitBtn").button("disable");
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "<c:url value="/reply/news/submit"/>",
                    data: $('#myform').serialize(),// 你的formid
                    async: false,
                    success: function (data) {
                        if (data == "1") {
                            //成功
                            alert("操作成功!");
                            location.href = "<c:url value="/reply/news/list"/> ";
                        }else if (data == "2") {
                            //提示
                            alert("该父节点的子图文已不能超过10个,请选择其他父节点!");
                            $("#submitBtn").button("enable");
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
            var text = "${(!empty returnObj.id && empty returnObj.parentId ) ? '删除父节点后，所有子节点一并删除，你确定要执行此操作?' : '确定要删除吗？'}";
            if (confirm(text)) {
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "<c:url value="/reply/news/delete"/>",
                    data: {id:sid},
                    async: false,
                    success: function (data) {
                        if (data == "1") {
                            //成功
                            alert("删除成功!");
                            location.href = "<c:url value="/reply/news/list"/> ";
                        }else {
                            //失败
                            alert("删除失败!");
                            $("#submitBtn").button("enable");
                        }
                    }
                });
            }
        });


        //触发上传事件
        bindUploadChangEvent();

        //绑定上传事件
        function bindUploadChangEvent(){
            /*异步ajax上传*/
            $("#picUrlFile").change(function () {
                if ($(this).val() != "") {
                    var elementIds = ["picUrlFile"]; //flag为id、name属性名
                    $.ajaxFileUpload({
                        url: '<c:url value="/fileUpload" /> ',
                        type: 'post',
                        secureuri: false, //一般设置为false
                        fileElementId: 'picUrlFile', // 上传文件的id、name属性名
                        dataType: 'text', //返回值类型，一般设置为json、application/json
                        elementIds: elementIds, //传递参数到服务器
                        success: function (data, status) {
                            $("#picUrl").val(data);
                            bindUploadChangEvent();//重新绑定，因为上传完成以后会清空原来的上传控件
                        },
                        error: function (data, status, e) {
                            alert("服务器繁忙，上传！")
                            bindUploadChangEvent();//重新绑定，因为上传完成以后会清空原来的上传控件
                        }
                    });
                }
            });
        }

    </script>

</div>
</body>
</html>