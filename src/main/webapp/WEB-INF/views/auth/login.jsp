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
    <h1>登录界面</h1>
  </div>
  <div data-role="content">
      <form name="myform" action="<c:url value="/j_spring_security_check"/>" method="POST" >

        <c:if test="${not empty loginFailedMessage}">
          <span style="color:red">${loginFailedMessage}</span>
        </c:if>

      <div data-role="fieldcontain">
        <label for="j_username">用户名：</label>
        <input type="text" name="j_username" id="j_username"/>
      </div>

      <div data-role="fieldcontain">
        <label for="j_password">密码：</label>
        <input type="password" name="j_password" id="j_password"/>
      </div>

      <input name="submit" type="submit" value="登录" data-theme="b"/>

    </form>
  </div>

</div>
</body>
</html>