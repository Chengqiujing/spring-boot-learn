<%--
  Created by IntelliJ IDEA.
  User: chengqj
  Date: 2020/1/31
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    使用反向代理的时候request.getServerName()
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + ":" + request.getContextPath();
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${pageContext.request.contextPath}/
    <br/>
    在webapp目录下
<br/>
消息是：${msg}
</body>
</html>
