v<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>뉴스 서비스</title>
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/news4.css">
<link rel="stylesheet" href="/resources/css/mainnavi.css" media="screen">
<link rel="stylesheet" href="/resources/css/sidebar.css">
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/demo.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
<script src="/resources/js/modernizr.custom.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>

</style>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function(){
		initNews();
	});
	
	//받아온 데이터로 기사를 씀
	var addArticle = function (data, indexNum) {
		$('<div class="component">' +
				'<input type="hidden" value="' + indexNum + '" />' +
				'<div class="component_header">' +
				'<table class="head_tb">' +
				'<tr>' +
				'<h2>' + data.title + '</th>' +
				'</tr>' +
				'<tr>' +
				'<td>' + data.pressName + '</td>' +
				'</tr>' +
				'<tr>' +
				'<td>' + data.articleTime + '</td>' +
				'</tr>' +
				'</table>' +
				'</div>' +
				'<div id="arti_body" class="component_body">' +
				'<ul>' +
				'<li id="arti_img" class="articleImg"><img src="' + data.imgURL + '" class="articleImgSize" ></li>' +
				'<li>' + data.summary + '</li>' +
				'</ul>' +
				'</div>' +
				'<div id="arti_footer" class="component_footer">' +
				'<ul id="footer" class="footer">' +
				'<li><button class="btn btn-outline-info" onclick="showDistrictImg(event)" value="districtImg' + indexNum + '">' + data.districtName + '</button></li>' +
				'<li><button type="button" disabled="true" id="' + data.code +
				'" class="btn btn-outline-info" name="findid"  onclick="linkPage(event)" value="' + data.url + '">상세보기</button></li>' +
				// '<li class="districtImg"><img src=' + "cat.jpg" + ' id="districtImg' + indexNum + '" class="districtImg"></li>' +
				'<li class="districtImg"><img id="districtImg' + indexNum + '" class="districtImg"></li>' +
				'</ul>' +
				'</div>' +
				'</div>', {}).appendTo('#main_center_wrap');
	}
	
	var initNews = function(){
		$.ajax('/api/news/searchNews',{
			type:'GET',
			data:{"lastArticleCode" : '',
				"searchWord" : "${searchWord}",
				"date": "${date}",
				"district" : "${district}",
				"mode" : "init"
			}
		}).then(function(data,status){
			if(status=='success'){
				var indexNum=1;

				var datas= data;
				for(let i = 0;i < Object.keys(datas).length; i++){
					if(i != 0){
						indexNum=Number($("#main_center_wrap").children(":last").find('input[type=hidden]').val())+1;;
					}
					addArticle(datas[i], indexNum);
				}
			}
		});
	}
	
	$(window).scroll(function(){
		var scrollHeight=$(window).scrollTop()+$(window).height();
		var documentHeight=$(document).height();
	
			if(scrollHeight+1 > documentHeight){
				getMoreArticles();
			}
		});
	
	var showOption = function(){
		
		if($('#detail_search').css("display") == "none"){
			$('#detail_search').css("display","block");
		}
		else {
			$('#detail_search').css("display","none");
		}
	}
	
	var getMoreArticles = function(){
		var lastArticleCode = $("#main_center_wrap").children(":last").find('button[name=findid]').attr('id');
		
		$.ajax('/api/news/searchNews',{
			type:'GET',
			data:{"lastArticleCode" : lastArticleCode,
				"searchWord" : "${searchWord}",
				"date": "${date}",
				"district" : "${district}",
				"mode" : "init"
			}
		}).then(function(data,status){
			if(status=='success'){
				let indexNum = Number($("#main_center_wrap").children(":last").find('input[type=hidden]').val())+1;

				
				var datas= data;
				
				for(let i = 0;i < Object.keys(datas).length; i++){
					addArticle(datas[i], indexNum+i);
				}
			}
		});
	}
	
	var showDistrictImg = function(event){
		var imgId = $(event.srcElement).val();
		
		if($("#"+imgId).css("display") == "none"){
			$("#"+imgId).css("display","block");
		}
		else {
			$("#"+imgId).css("display","none");
		}
	}


	var linkPage = function(event){
		var page = $(event.srcElement).val();
		var articleId = event.srcElement.id;

		window.open(page,"","width=800,height=800");

		$.ajax('/api/news/increaseViewCount',{
			type:'GET',
			data:{articleId : articleId}
		}).then(function(data,status){
		});
	}

	String.prototype.isEmpty = function(){
		return (this.trim() == '');
	}

	var searchArticle  = function(){

		var searchWord =$("#mainSearchTextbox").val();
		var district= $("#SelectDistrict option:selected").val();
		var date= $("#SelectDate option:selected").val();

		var go = "/news/newsSearchPage?searchWord="+searchWord+"&district="+district+"&date="+date;
		location.href=go;
	}

	var clickKeyword = function	(clicked_id) {
		var searchWord =$("#"+clicked_id).val();


		var go = "/news/newsSearchPage?searchWord="+searchWord+"&district="+""+"&date="+"";
		location.href=go;

	}
	

</script>
</head>
<body bgcolor="#f3f3f3" onContextmenu = "return false" ondragstart = "return false" onSelectstart = "return false">
<div class="container" style>
			<ul id="gn-menu" class="gn-menu-main">
				<li class="gn-trigger">
					<a class="gn-icon gn-icon-menu gn-selected" style="margin-top:35px"><span>Menu</span></a>
					<nav class="gn-menu-wrapper">
						<div class="gn-scroller">
							<ul class="gn-menu">
								<li>
										<li><a href="preedu.html" class="gn-icon gn-icon-article">안전예방교육</a></li>
										<li><a href="minwon.html" class="gn-icon gn-icon-article">민원</a></li>
										<li><a href="hosin.html" class="gn-icon gn-icon-article">호신</a></li>
								</li>
							</ul>
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
					  <li><a href="/map" class="m2">지도</a></li>
					  <li><a href="/news/main"  class="m3">뉴스</a></li>
					  <li><a href="/safeguide/preedu.jsp"  class="m4">안전 가이드</a></li>
					  <li><a href="/stats" class="m5">범죄 통계</a></li>
			    	</ul>
			      </li>
			    </ul>
			    </div>
				</li>
	 		  <li style="position:absolute; left:215px; width:49%; border-right-width: 0;" >
	 		  	<div id="search_div" >
	 		  	  <input type="text" id="mainSearchTextbox" class="form-control mr-sm-2" placeHolder="키워드를 입력해 주세요.">
	       		  <button type="button" id="mainSearchButton" class="btn btn-secondary my-2 my-sm-0" onclick= "searchArticle()"></button>
	  	    	  <button type="button" id="mainSearchToogle" class="btn btn-primary dropdown-toggle" onclick="showOption()"></button>

				<div>
					<form id="detail_search" class="search_wrap" >
						<div id="dateSelect" class="up_wrap" >
							<table id="selelctTable1" class="selelctTable1">
								<tr>
									<th class="tableHead1">날짜</th>
									<td>
										<select id="SelectDate" class="form-control">
											<option value="">선택없음</option>
											<option value="1">지난 1일</option>
											<option value="3">지난 3일</option>
											<option value="7">지난 7일</option>
											<option value="30">지난 30일</option>
											<option value="365">지난 1년</option>
										</select>
									</td>
								</tr>
							</table>
						</div>
						<div id="zoneSelect" class="down_wrap">
							<table id="selelctTable2" class="selelctTable2">
								<tr>
									<th class="tableHead2">지역</th>
									<td>
										<select id="SelectDistrict" class="form-control">
											<option value="">선택없음</option>
											<option value="강남구">강남구</option>
											<option value="강동구">강동구</option>
											<option value="강북구">강북구</option>
											<option value="강서구">강서구</option>
											<option value="관악구">관악구</option>
											<option value="광진구">광진구</option>
											<option value="구로구">구로구</option>
											<option value="금천구">금천구</option>
											<option value="노원구">노원구</option>
											<option value="도봉구">도봉구</option>
											<option value="동대문구">동대문구</option>
											<option value="동작구">동작구</option>
											<option value="마포구">마포구</option>
											<option value="서대문구">서대문구</option>
											<option value="서초구">서초구</option>
											<option value="성동구">성동구</option>
											<option value="성북구">성북구</option>
											<option value="송파구">송파구</option>
											<option value="양천구">양천구</option>
											<option value="영등포구">영등포구</option>
											<option value="용산구">용산구</option>
											<option value="은평구">은평구</option>
											<option value="종로구">종로구</option>
											<option value="중구">중구</option>
											<option value="중량구">중량구</option>

										</select>
									</td>
								</tr>
							</table>
						</div>
						<div id="btnArea" class="btnArea">
							<table id="btnTable">
								<tr>
									<td>
										<button type="button" id="close" class="btn btn-outline-info">닫기</button>
										<button	type="button" id="search" class="btn btn-outline-info" onclick="searchArticle()">검색</button>
									</td>
								</tr>
							</table>
						</div>
					</form>

				</div>
	 		  	</div>
	 		  </li>



				<li><a class="" href="/index.jsp"><img src="/resources/image/logo_small.png" id="logo"><span>THINK BEE WAY</span></a></li>
			</ul>
		</div><!-- /container -->
		<script src="/resources/js/classie.js"></script>
		<script src="/resources/js/gnmenu.js"></script>
		<script>
			new gnMenu( document.getElementById( 'gn-menu' ) );
		</script>
	
	
	<div id="main_wrap" class="warp">
		<div id="main_left_wrap" class="warp" style="width:280px; height:1px"></div>
		<div id="main_center_wrap" class="wrap"></div>
		<div id="main_right_wrap" class="wrap">
			<div id="hot_topic" class="topic_wrap" style="position:fixed;">
				<div id="hot_topic_title" class="hot_topic_title">
					인기 급상승 뉴스
				</div>
				<form>
					<div id="topics" class="topics">
						<button type="button" value="남성" id="topic1" class="btn btn-outline-info" onclick="clickKeyword(this.id)" >남성</button>
						<button type="button" value="불법촬영" id="topic2" class="btn btn-outline-info" onclick="clickKeyword(this.id)">불법촬영</button>
						<button type="button" value="홍대" id="topic3" class="btn btn-outline-info " onclick="clickKeyword(this.id)">홍대</button>
						<button type="button" value="제목" id="topic4" class="btn btn-outline-info" onclick="clickKeyword(this.id)">제목</button>
						<button type="button" value="아동학대" id="topic5" class="btn btn-outline-info " onclick="clickKeyword(this.id)">아동학대</button>
						<button type="button" value="고양이" id="topic6" class="btn btn-outline-info" onclick="clickKeyword(this.id)">고양이</button>
						<button type="button" value="여성" id="topic7" class="btn btn-outline-info " onclick="clickKeyword(this.id)">여성</button>
						<button type="button" value="법원" id="topic8" class="btn btn-outline-info " onclick="clickKeyword(this.id)">법원</button>
						<button type="button" value="제목" id="topic9" class="btn btn-outline-info " onclick="clickKeyword(this.id)">제목</button>
						<button type="button" value="남성" id="topic10" class="btn btn-outline-info " onclick="clickKeyword(this.id)">남성</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>