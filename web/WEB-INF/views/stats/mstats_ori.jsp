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
    <link rel="stylesheet" href="/resources/css/sidebar.css">
<%--    <link rel="shortcut icon" href="/resources/css/favicon.ico">--%>
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/demostats.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
    <script src="/resources/js/modernizr.custom.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/mainnavi.css" media="screen">
    <link rel="stylesheet" href="/resources/css/mappopchart.css">
    <link rel="stylesheet" href="/resources/css/mappolygon.css">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=06e3ef01d1d218cb9e1a983b257300eb"></script>
    <!-- <script src="js/resize.js"></script> -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="/resources/js/statsareas.js"></script>
    <script src="/resources/js/statsmapSidebar.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"src="/resources/js/markerPosition.js"></script>

    <script language="JavaScript" type="text/javascript">


        function layer_toggle(obj) {
            if (obj.style.display=='none') obj.style.display = 'block';
            else if (obj.style.display=='block') obj.style.display = 'none';
        }
    </script>


</head>
<body onload='selection(event)' bgcolor="#f3f3f3" onContextmenu = "return false" ondragstart = "return false" onSelectstart = "return false">
<script type="text/javascript"src="/resources/js/chart.js"></script>

<div class="container">
    <ul id="gn-menu" class="gn-menu-main">
        <li class="gn-trigger">
            <a class="gn-icon gn-icon-menu gn-selected" style="margin-top:35px"><span>Menu</span></a>
            <nav class="gn-menu-wrapper">
                <div class="gn-scroller">
                    <ul class="gn-menu">
                        <li>
                        <li><a href="#" onclick="selection(event)" id="ALL" class="gn-icon gn-icon-article">합계</a></li>
                        <li><a href="#" onclick="selection(event)" id="MU" class="gn-icon gn-icon-article">살인</a></li>
                        <li><a href="#" onclick="selection(event)" id="RO" class="gn-icon gn-icon-article">강도</a></li>
                        <li><a href="#" onclick="selection(event)" id="SE" class="gn-icon gn-icon-article">성범죄</a></li>
                        <li><a href="#" onclick="selection(event)" id="TH" class="gn-icon gn-icon-article">절도</a></li>
                        <li><a href="#" onclick="selection(event)" id="VI" class="gn-icon gn-icon-article">폭력</a></li>
                        </li>
                    </ul>
                </div>
            </nav>
        </li>
        <li>

            <div id="mainmenu_div">

                <ul class="menu">

                    <li> <span id="mainmenu_text">메인 메뉴</span>
                        <ul>
                            <li><a href="/index.jsp" id="mainmenu-list_main" class="m1">메인</a></li>
                            <li><a href="/map" id="mainmenu-list_map" class="m2">지도</a></li>
                            <li><a href="/news/main" id="mainmenu-list_news" class="m3">뉴스</a></li>
                            <li><a href="/safeguide/preedu.jsp" id="mainmenu-list_guard" class="m4">안전 가이드</a></li>
                            <li><a href="/stats" id="mainmenu-list_stats" class="m5">범죄 통계</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </li>
        <li><a class="codrops-icon codrops-icon-prev" href="/index.jsp"><span>THINK BEE WAY</span></a></li>
    </ul>
</div>
<script src="/resources/js/classie.js"></script>
<script src="/resources/js/gnmenu.js"></script>
<script>
    new gnMenu( document.getElementById( 'gn-menu' ) );
</script>
<div style="margin-left:6%">
    <div id="mapwrap">
        <div id="map" style="width: 70%;height: 600px;z-index: 0;position: relative;overflow: hidden;background: url('http://t1.daumcdn.net/mapjsapi/images/2x/bg_tile.png');margin-left: 20%;"></div>
        <div id="clickLatlng"></div>
        <div id="stats_content" style="margin-left: 15%";>
            <div id="columnchart_values" style="width: 100%; height: 400px; "></div>
            <div id="chart_div"></div>
        </div>
        <div class="container">
            <header>
                <h1>지역별 범죄발생율 순위</h1>
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
<script src="/resources/js/staticMap.js"></script>
</body>
</html>
