<%--
  Created by IntelliJ IDEA.
  User: Space
  Date: 2019-08-27
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .main {

            min-height: 100%;
            min-width: 1024px;

            width: 100%;
            height: auto;

            position: fixed;
            top: 0;
            left: 0;
        }

        @media screen and (max-width: 1024px) {
            .main {
                left: 50%;
                margin-left: -512px;
            }
        }

    </style>
    <title>Error</title>
</head>
<body>
<div>
    <a href="javascript:history.back();"> <img src="/resources/image/error/404.png" class="main"> </a>
</div>
</body>
</html>
