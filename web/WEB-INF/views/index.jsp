<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>

  <title>안전 가이드 서비스</title>
  <link rel="stylesheet" href="/resources/css/p2p.css" media="screen">
  <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/mainnavi.css" media="screen">
  <link rel="stylesheet" href="/resources/css/sidebar.css">
  <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>

<body bgcolor="#055A8A" onContextmenu = "return false" ondragstart = "return false" onSelectstart = "return false">
<!-- <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div id="mainmenu">
    <ul class="menu">
    <li><img src="./mainNaviImg/menu Button.png">
              <ul>
          <li><a href="index.html" class="m1">메인</a></li>
          <li><a href="map.jsp" class="m2">지도</a></li>
          <li><a href="news.jsp" class="m3">뉴스</a></li>
        <li><a href="preedu.html" class="m4">안전 가이드</a></li>
        <li><a href="stats.jsp" class="m5">범죄 통계</a></li>
          </ul>
    </li>
  </ul>
  </div>
     <a id="navlogo" href="#"><img src="./mainNaviImg/menu Button.png"></a>
</nav> -->

<div class="center" height:auto;">
<video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
  <source src="./image/index/indexMov.mp4" type="video/mp4">
  <source src="movie.webm" type="video/webm">
  Sorry, your browser does not support HTML5 video.
</video>

<button name="test" type="button" onclick="location.href='news.jsp'"

        style=" opacity:0; height:31%; width:12%; cursor:hand; position:absolute; top:57%; left:47%; z-index:10001">
  <button name="test" type="button" onclick="location.href='map.jsp'""

  style="opacity:0; height:28%; width:13%; cursor:hand; position:absolute; top:54%; left:59%; z-index:10001">

  </div>

</body>


<style>
  #videobcg {
    position: fixed;
    top: 0px;
    left: 0px;
    min-width: 100%;
    min-height: 100%;
    width: 100%;
    height: 80%;
    z-index: -1000;
    overflow: hidden;
    transform: translateX(0%) translateY(0%);
  body{
    background-color: black;
  }
</style>
</html>
