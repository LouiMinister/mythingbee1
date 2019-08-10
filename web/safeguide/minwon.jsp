<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">


    <meta property="og:url" content="http://equinny.co.kr"> -->
    <title>안전 가이드 서비스</title>
    <link rel="stylesheet" href="/resources/css/p2p.css" media="screen">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/mainnavi.css" media="screen">
    <link rel="stylesheet" href="/resources/css/sidebar.css">
    <link rel="shortcut icon" href="../favicon.ico">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
    <script src="/resources/js/modernizr.custom.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">

    <script language="JavaScript" type="text/javascript">


        function layer_toggle(obj) {
            if (obj.style.display=='none') obj.style.display = 'block';
            else if (obj.style.display=='block') obj.style.display = 'none';
        }
    </script>
</head>
<body bgcolor="#f3f3f3" onContextmenu = "return false" ondragstart = "return false" onSelectstart = "return false">


<div class="container" style>
    <ul id="gn-menu" class="gn-menu-main">
        <li class="gn-trigger">
            <a class="gn-icon gn-icon-menu gn-selected"><span>Menu</span></a>
            <nav class="gn-menu-wrapper">
                <div class="gn-scroller">
                    <ul class="gn-menu">
                        <li>
                        <li><a href="preedu.jsp" class="gn-icon gn-icon-article">안전예방교육</a></li>
                        <li><a href="minwon.jsp" class="gn-icon gn-icon-article">민원</a></li>
                        <li><a href="hosin.jsp" class="gn-icon gn-icon-article">호신</a></li>
                        </li>
                    </ul>
                </div><!-- /gn-scroller -->
            </nav>
        </li>
        <li>
            <div id="mainmenu_div">
                <ul class="menu">
                    <li> <span id="mainmenu_text">메인 메뉴</span>
                        <ul>
                            <li><a href="/index.jsp" id="mainmenu-list" class="m1">메인</a></li>
                            <li><a href="/map" id="mainmenu-list" class="m2">지도</a></li>
                            <li><a href="/news/main" id="mainmenu-list" class="m3">뉴스</a></li>
                            <li><a href="/safeguide/preedu.jsp" id="mainmenu-list" class="m4">안전 가이드</a></li>
                            <li><a href="/stats" id="mainmenu-list" class="m5">범죄 통계</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </li>
        <li><a class="codrops-icon codrops-icon-prev" href="/index.jsp"><span>THINK BEE WAY</span></a></li>
    </ul>
</div><!-- /container -->
<script src="/resources/js/classie.js"></script>
<script src="/resources/js/gnmenu.js"></script>
<script>
    new gnMenu( document.getElementById( 'gn-menu' ) );
</script>

</div> -->
<div style="margin-left:0%">
    <div class="centerbox"  style="margin-top:72px;">
        <div class="listbox" onclick="window.open('https://www.epeople.go.kr/jsp/user/pc/cvreq/UPcCvreqForm.paid?flag=N')">
            <div class="listboxIMG"><img src="/resources/image/linkImg/1to1.png" class="listboxIMG-i"  alt="사이트 바로가기"></div>
            <div class="listboxTEXT"><b>국민신문고</b> <span class="greenicon">정부</span><br>
                민원 신청페이지</div>
            <span class="listboxCLICK">민원 신청 바로가기</span>
        </div>
        <div class="listbox" onclick="window.open('https://www.120dasan.or.kr/dsnc/main/contents.do?menuNo=200022')">
            <div class="listboxIMG"><img src="/resources/image/linkImg/1to1.png" class="listboxIMG-i"  alt="사이트 바로가기"></div>
            <div class="listboxTEXT"><b>다산콜센터</b> <span class="greenicon">정부</span><br>
                민원 신청페이지</div>
            <span class="listboxCLICK">민원 신청 바로가기</span>
        </div>
        <div class="listbox" onclick="window.open('http://eungdapso.seoul.go.kr/Req/Req02/Req02_not.jsp')">
            <div class="listboxIMG"><img src="/resources/image/linkImg/1to1.png" class="listboxIMG-i"  alt="사이트 바로가기"></div>
            <div class="listboxTEXT"><b>서울특별시 응답소</b> <span class="greenicon">정부</span><br>
                민원 신청 페이지</div>
            <span class="listboxCLICK">민원 신청 바로가기</span>
        </div>
        <div style="clear: both;"></div>
    </div>
    <div class="centerbox "><div style="padding:20px 10px;">
        <div style="border-top:1px solid #cccccc;text-align:right;"></div>
        <div style="display: block" id="more01a">
            <div style="display: none" id="more01b"><br>
                <!-- 전체게시물 목록 시작 { -->
            </div>
        </div></div>
    </div>
</body>
</html>


