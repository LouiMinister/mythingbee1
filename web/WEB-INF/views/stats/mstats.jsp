<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/chart.css">
    <link rel="stylesheet" href="/resources/css/sidebar.css">
    <link rel="stylesheet" href="/resources/css/mobile.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/mdemostats.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
    <script src="/resources/js/modernizr.custom.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/mappopchart.css">
    <link rel="stylesheet" href="/resources/css/mappolygon.css">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=06e3ef01d1d218cb9e1a983b257300eb"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="/resources/js/statsareas.js"></script>
    <script src="/resources/js/statsmapSidebar.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"src="/resources/js/markerPosition.js"></script>
    <script>

        function layer_toggle(obj) {
            if (obj.style.display=='none') obj.style.display = 'block';
            else if (obj.style.display=='block') obj.style.display = 'none';
        }
        $(window).on('resize', function () {
            redrawChart();
        });
    </script>
</head>
<body onload='selection(event)' bgcolor="#f3f3f3" onContextmenu = "return false" ondragstart = "return false" onSelectstart = "return false">
    <div id="mapwrap">
        <div id="map" style="width: 100%; height: 100%; z-index: 1;"></div>
        <div id="clickLatlng"></div>
    </div>
<script src="/resources/js/mstaticMap.js"></script>
<footer>
    <div class="navbar-mobile btn-group btn-group-justified navbar-fixed-bottom" style="z-index: 1;">
        <span class="btn btn-default"  onclick="selection(event)" id="ALL">합계</span>
        <span class="btn btn-default" onclick="selection(event)" id="MU">살인</span>
        <span class="btn btn-default" onclick="selection(event)" id="RO">강도</span>
        <span class="btn btn-default"  onclick="selection(event)" id="SE"> 성범죄</span>
        <span class="btn btn-default" onclick="selection(event)" id="TH">절도</span>
        <span class="btn btn-default" onclick="selection(event)" id="VI">폭력</span>
        <a href="/stats/chart"class="btn btn-default">차트보기</a>
        </span>
    </div>
</footer>
</body>
</html>
