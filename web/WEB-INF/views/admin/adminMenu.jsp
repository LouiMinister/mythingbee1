<%--
  Created by IntelliJ IDEA.
  User: nina2
  Date: 2019-08-03
  Time: 오후 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>관리자 메뉴</h3> <br>
    <button onclick="location.href='/admin/facility'">시설물 관리자</button><br>
    <button onclick="location.href='/admin/news'">뉴스 관리자</button>
</body>
</html>
