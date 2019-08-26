<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load('visualization', '1.0', {'packages':['corechart']});
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/chart.css">
    <link rel="stylesheet" href="/resources/css/mobile.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/mdemostats.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
    <script src="/resources/js/modernizr.custom.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap_1.min.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/mainnavi.css" media="screen">
    <link rel="stylesheet" href="/resources/css/mappopchart.css">
    <link rel="stylesheet" href="/resources/css/mappolygon.css">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="/resources/js/statsmapSidebar.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <link rel="stylesheet" href="/resources/css/mobileNav.css">
</head>
<body onload='selection(event)' bgcolor="#f3f3f3" onContextmenu = "return false" ondragstart = "return false" onSelectstart = "return false">
<header>
    <div class="nav">
        <ul>
            <li class="home"><a href="/stats">차트</a></li>
        </ul>
    </div>
</header>
<script type="text/javascript"src="/resources/js/chart.js"></script>


<script src="/resources/js/classie.js"></script>
<script src="/resources/js/gnmenu.js"></script>
<div>
    <div id="mapwrap">
        <div id="stats_content" style="margin-left: 15%";>
            <div id="columnchart_values" style="width: 100%; height: 400px; "></div>
            <div id="chart_div"></div>
        </div>
        <div class="container">
            <header>
                <h1>지역별 범죄발생율<br> 순위</h1>
            </header>
            <div class="wrapper">
                <table id="rankContent">
                    <thead>
                    <tr>
                        <th>순위</th>
                        <th>지역명</th>
                        <th>범죄발생률</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/mstaticCharts.js"></script>
<footer>
    <div class="navbar-mobile btn-group btn-group-justified navbar-fixed-bottom">
        <span class="btn btn-default"  onclick="selection(event)" id="ALL">합계</span>
        <span class="btn btn-default" onclick="selection(event)" id="MU">살인</span>
        <span class="btn btn-default" onclick="selection(event)" id="RO">강도</span>
        <span class="btn btn-default"  onclick="selection(event)" id="SE"> 성범죄</span>
        <span class="btn btn-default" onclick="selection(event)" id="TH">절도</span>
        <span class="btn btn-default" onclick="selection(event)" id="VI">폭력</span>
    </div>
</footer>
</body>
</html>
