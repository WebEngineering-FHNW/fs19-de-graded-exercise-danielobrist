<%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 2019-05-09
  Time: 19:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div>
    <g:each in="${topTen}" var="p">
        <li>${p}</li>
    </g:each>
</div>
</body>
</html>