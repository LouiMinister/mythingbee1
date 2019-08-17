<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>지도 지도 시작하기</title>
<link rel="stylesheet" href="/resources/css/map.css">
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/mainnavi.css" media="screen">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="stylesheet" href="/resources/css/mapSearch.css">
<link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/demo.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
<script src="/resources/js/modernizr.custom.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=06e3ef01d1d218cb9e1a983b257300eb&libraries=services"></script>
<script src='/resources/js/jquery-3.4.1.min.js'></script>
</head>
<body>


<div style="margin-left:0%">

<div class="container" style>
			<ul id="gn-menu" class="gn-menu-main">
				<li class="gn-trigger">
					<a class="gn-icon gn-icon-menu" style="
    padding-top: 35px;"
					><span>Menu</span></a>
					<nav class="gn-menu-wrapper">
						<div class="gn-scroller" id="map-scroller">
<%--							<div id="menu_wrap">--%>
							<ul class="gn-menu">
								<li>
										<li><a class="gn-icon gn-icon-article" id="search_menu">검색</a></li>
										<li><a class="gn-icon gn-icon-article" id="security_menu">치안</a></li>
								</li>
							</ul>
							<div class="scroll_pane content" id="searchForm">
								<div id="search_title">
									<h4 id="address_search_text"></h4>
										주소 검색
										</h6>
								</div>
						<div id="address_search">
							<div id="search_form" class="option">
						<form class="form-inline my-2 my-lg-0"
							onsubmit="searchPlaces(); return false;">
							<input class="form-control mr-sm-2" type="text"
								placeholder="Search" id="keyword">
						</form>
						<div style="margin-top:5px;">
							<button class="btn btn-secondary my-2 my-sm-0"
								onclick="searchPlaces();">Search</button>
						</div>
					</div>
				</div>
				<div id="address_result">
					<ul id="placesList"></ul>
					<div id="pagination"></div>
				</div>
			</div>
<%--			</div>--%>
		</div><!-- /gn-scroller -->
					</nav>
				</li>
				<li>
					
				  <div id="mainmenu_div">
				  
			      <ul class="menu">
			      
			     <!--  <li><span id=mainmenu_btn>MAIN MENU</span> -->
			     <li> <span id="mainmenu_text">메인 메뉴</span>
			      <ul>
					  <li><a href="/index.jsp" id="mainmenu-list" class="m1">메인</a></li>
					  <li><a href="/map"  class="m2">지도</a></li>
					  <li><a href="/news/main"  class="m3">뉴스</a></li>
					  <li><a href="/safeguide/preedu.jsp"  class="m4">안전 가이드</a></li>
					  <li><a href="/stats"  class="m5">범죄 통계</a></li>
			    	</ul>
			      </li>
			    </ul>
			    </div>
				</li>
				<!-- <li><a class="codrops-icon codrops-icon-drop" href="http://tympanus.net/Development/HeaderEffects/"><span>Previous Demo</span></a></li> -->
				<li><a class="" href="/index.jsp"><img src="/resources/image/logo_small.png" id="logo"><span>Think Bee Way</span></a></li>
			</ul>
		</div><!-- /container -->
		<script src="/resources/js/classie.js"></script>
		<script src="/resources/js/gnmenu.js"></script>
		<script>
			new gnMenu( document.getElementById( 'gn-menu' ) );
		</script>
<div id="container">
	<!-- <div id="left_bar">
		<div id="nav_panel" class="panel_main nav_panel">
			<div class="panel_content nano has-scrollbar" id="menu_wrap">
				<div class="scroll_pane content" >
					<div id="search_title">
						<h4 id="address_search_text"> 주소 검색 </h6>
					</div>
					<div id="address_search">
						<div id="search_form" class="option">
							<form class="form-inline my-2 my-lg-0" onsubmit="searchPlaces(); return false;">
						      <input class="form-control mr-sm-2" type="text" placeholder="Search" id="keyword">
						      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
						    </form>
						</div>
					</div>
					<div id="address_result">
						<ul id="placesList"></ul>
     				   <div id="pagination"></div>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<div id="mapwrap"> 
		<div id="map" style="width: 100%; height: 100%"></div>
		<div id="security">
			<ul id="category">
				<li>	
					<img src="/resources/image/map/menubar.png" id="menu_bar">
					<img src="/resources/image/map/cctv_color.png" style="display:none;" name="cctv">
					<button id="cctv" onclick="cctvToggle()"><img src="/resources/image/map/cctv.png" name="cctv"></button>
				</li>
				<li>
					<img src="/resources/image/map/bell_color.png" style="display:none;" name="bell">
					<button id="bell" onclick="bellToggle()"><img src="/resources/image/map/bell.png" name="bell"></button>
				</li>
				<li>
					<img src="/resources/image/map/guard_color.png" style="display:none" name="guard">
					<button id="guard"><img src="/resources/image/map/guard.png" name="guard"></button>
				</li>
				<li>
					<img src="/resources/image/map/police_color.png" style="display:none" name="police">
					<button id="police" onclick="policeToggle()"><img src="/resources/image/map/police.png" name="police"></button>
				</li>
				<li>
					<img src="/resources/image/map/shop_color.png" style="display:none" name="shop">
					<button id="shop" onclick="shopToggle()"><img src="/resources/image/map/shop.png" name="shop"></button>
				</li>
				<li>
					<img src="/resources/image/map/security_lamp_color.png" style="display:none"  name="security_lamp">
					<button id="security_lamp" onclick="securityLampToggle()"><img src="/resources/image/map/security_lamp.png" name="security_lamp"></button>
				</li>
			</ul>
		</div>
	</div>
</div>
</div>

<script src="/resources/js/map.js"></script>
<!-- <input type="checkbox" id="menu_state" checked> --> 
</body>
</html>