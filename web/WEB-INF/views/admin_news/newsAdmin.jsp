<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>뉴스 관리자 페이지</title>

    <!-- Custom fonts for this template -->
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/news4.css">
    <link rel="stylesheet" href="/resources/css/mainnavi.css" media="screen">
    <link rel="stylesheet" href="/resources/css/sidebar.css"><%--
<link rel="shortcut icon" href="../favicon.ico">--%>
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
    <script src="/resources/js/modernizr.custom.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet" type="text/css"/>

    <!-- Custom styles for this page -->
    <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css"/>
</head>
<script language="JavaScript" type="text/javascript"></script>
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/resources/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="/resources/js/demo/datatables-demo.js"></script>

<script src="/resources/js/classie.js"></script>
<script src="/resources/js/gnmenu.js"></script>
<script> new gnMenu( document.getElementById("gn-menu") );</script>

<script>

    function layer_toggle(obj) {
        if (obj.style.display=='none') obj.style.display = 'block';
        else if (obj.style.display=='block') obj.style.display = 'none';
    }

    $(document).ready(function(){
        showFormat();
        getNews();
    });

    var showFormat = function(){
        var form = $(''
            +'<!-- Page Wrapper -->'
            +'<div id="wrapper">'
            +'<!-- Content Wrapper -->'
            +'<div id="content-wrapper" class="d-flex flex-column">'
            +'<!-- Main Content -->'
            +'<div id="content">'
            +'<!-- Topbar -->'
            +'<nav class="navbar navbar-expand">'
            +'<!-- Topbar Navbar -->'
            +'<div class="container" style>'
            +'<ul id="gn-menu" class="gn-menu-main">'
            +'<li class="gn-trigger">'
            +'<a class="gn-icon gn-icon-menu gn-selected" style="margin-top:35px"><span>Menu</span></a>'
           +'<nav class="gn-menu-wrapper">'
         +'<div class="gn-scroller">'
         +'<ul class="gn-menu">'
         +'<li>'
          +'<li><a href="preedu.html" class="gn-icon gn-icon-article">안전예방교육</a></li>'
         +'<li><a href="minwon.html" class="gn-icon gn-icon-article">민원</a></li>'
         +'<li><a href="hosin.html" class="gn-icon gn-icon-article">호신</a></li>'
          +'</li>'
          +'</ul>'
          +'</div><!-- /gn-scroller -->'
          +'</nav>'
         +'</li>'
         +'<li>'
          +'<div id="mainmenu_div">'
           +'<ul class="menu">'
           +'<!--  <li><span id=mainmenu_btn>MAIN MENU</span> -->'
           +'<li> <span id="mainmenu_text">메인 메뉴</span>'
            +'<ul>'
            +'<li><a href="/index.jsp" id="mainmenu-list" class="m1">메인</a></li>'
           +'<li><a href="/map" id="mainmenu-list" class="m2">지도</a></li>'
            +'<li><a href="/news/main" id="mainmenu-list" class="m3">뉴스</a></li>'
            +'<li><a href="/safeguide/preedu.jsp" id="mainmenu-list" class="m4">안전 가이드</a></li>'
        +'<li><a href="/stats" id="mainmenu-list" class="m5">범죄 통계</a></li>'
        +'</ul>'
        +'</li>'
        +'</ul>'
        +'</div>'
        +'</li>'
        +'<li style="position:absolute; left:215px; width:49%; border-right-width: 0;" >'
        +'<div id="search_div" >'
        +'<input type="text" id="mainSearchTextbox" class="form-control mr-sm-2" placeHolder="키워드를 입력해 주세요.">'
        +'<button type="button" id="mainSearchButton" class="btn btn-secondary my-2 my-sm-0" onclick= "searchArticle()"></button>'
        +'<button type="button" id="mainSearchToogle" class="btn btn-primary dropdown-toggle" onclick="showOption()"></button>'
        +'</div>'
        +'</li>'
        +'<li><a class="codrops-icon codrops-icon-prev" href="/index.jsp"><span>THINK BEE WAY</span></a></li>'
        +'</ul>'
        +'</div><!-- /container -->'

        +'<div id="main_wrap" class="warp">'
         +'<div id="main_left_wrap" class="warp" style="width:280px; height:1px"></div>'
        +'<div id="main_center_wrap" class="wrap"></div>'
        +'<div id="main_right_wrap" class="wrap">'
        +'<form id="detail_search" class="search_wrap" style="background: #ffffff; margin-top: 120px" >'
            +'<div id="dateSelect" class="up_wrap" >'
                +'<table id="selelctTable1" class="selelctTable1">'
                    +'<tr>'
                        +'<th class="tableHead1">날짜</th>'
                        +'<td>'
                            +'<select id="SelectDate" class="form-control">'
                                +'<option value="">선택없음</option>'
                                +'<option value="1">지난 1일</option>'
                                +'<option value="3">지난 3일</option>'
                                +'<option value="7">지난 7일</option>'
                                +'<option value="30">지난 30일</option>'
                                +'<option value="365">지난 1년</option>'
                                +' </select>'
                            +' </td>'
                        +'</tr>'
                    +'</table>'
                +'</div>'
            +'<div id="zoneSelect" class="down_wrap">'
                +'<table id="selelctTable2" class="selelctTable2">'
                    +' <tr>'
                        +'<th class="tableHead2">지역</th>'
                        +'<td>'
                            +'<select id="SelectDistrict" class="form-control">'
                                +'<option value="">전체</option>'
                                +'<option value="강남구">강남구</option>'
                                +'<option value="강동구">강동구</option>'
                                +'<option value="강북구">강북구</option>'
                                +'<option value="강서구">강서구</option>'
                                +'<option value="관악구">관악구</option>'
                                +'<option value="광진구">광진구</option>'
                                +'<option value="구로구">구로구</option>'
                                +'<option value="금천구">금천구</option>'
                                +'<option value="노원구">노원구</option>'
                                +'<option value="도봉구">도봉구</option>'
                                +'<option value="동대문구">동대문구</option>'
                                +'<option value="동작구">동작구</option>'
                                +'<option value="마포구">마포구</option>'
                                +'<option value="서대문구">서대문구</option>'
                                +'<option value="서초구">서초구</option>'
                                +'<option value="성동구">성동구</option>'
                                +'<option value="성북구">성북구</option>'
                                +'<option value="송파구">송파구</option>'
                                +'<option value="양천구">양천구</option>'
                                +'<option value="영등포구">영등포구</option>'
                                +'<option value="용산구">용산구</option>'
                                +'<option value="은평구">은평구</option>'
                                +'<option value="종로구">종로구</option>'
                                +'<option value="중구">중구</option>'
                                +'<option value="중량구">중량구</option>'
                                +'</select>'
                            +'</td>'
                        +'</tr>'
                    +'</table>'
                +'</div>'
            +'<div id="btnArea" class="btnArea">'
                +'<table id="btnTable">'
                    +'<tr>'
                        +'<td>'
                            +'<button type="button" id="close" class="btn btn-outline-info" onclick="showOption()">닫기</button>'
                            +'<button   type="button" id="search" class="btn btn-outline-info" onclick="searchArticle()">검색</button>'
                            +'</td>'
                        +'</tr>'
                    +'</table>'
                +'</div>'
            +'</form>'
            +'</div>'
            +'</div>'
            +'<ul class="navbar-nav ml-auto">'
            +'<!-- Nav Item - Search Dropdown (Visible Only XS) -->'
            +'<li class="nav-item dropdown no-arrow d-sm-none">'
            +'<a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
            +'<i class="fas fa-search fa-fw"></i>'
            +'</a>'
            +'<div class="topbar-divider d-none d-sm-block"></div>'
            +'</li>'
            +'</ul>'
            +'</nav>'
            +'<!-- End of Topbar -->'
            +'<!-- Begin Page Content -->'
            +'<div class="container-fluid">'
            +'<!-- DataTales Example -->'
            +'<div class="card shadow mb-4">'
            +'<div class="card-header py-3">'
            +'<h6 class="m-0 font-weight-bold text-primary">뉴스 관리자</h6>'
            +'</div>'
            +'<div class="card-body">'
            +' <div class="table-responsive">'
            +'<div>'
            +'<ul style="list-style:none;padding-left: 0px;" >'
            +'<li>'
            +' <a onclick=" deleteArticles()" class="btn btn-info btn-icon-split" style="float">'
            +'<span class="icon text-white-50" >'
            +' <i class="fas fa-trash-alt"></i>'
            +' </span>'
            +'<span class="text">삭제</span>'
            +' </a>'
            +' <a href="/admin/addPress" methods="GET"  class="btn btn-info btn-icon-split" style="float:right ;margin-left: 15">'
            +' <span class="icon text-white-50">'
            +' <i class="fas fa-trash-alt"></i>'
            +' </span>'
            +' <span class="text">언론사 추가</span>'
            +'</a>'
            +'<a href="/admin/addArticle" methods="GET" class="btn btn-info btn-icon-split" style="float:right ">'
            +'  <span class="icon text-white-50" >'
            +' <i class="fas fa-trash-alt"></i>'
            +' </span>'
            +' <span class="text">기사 추가</span>'
            +' </a>'
            +'</li>'
            +'</ul>'
            +'</div>'
            +'<table style="font-size: 0.85rem;"class="table table-bordered" id="dataTable" width="100%" cellspacing="0">'
            +'<thead>'
            +'<tr>'
            +'<th></th>'
            +' <th>제목</th>'
            +'<th>언론사</th>'
            +'<th>지역구</th>'
            +'<th>키워드</th>'
            +'<th>수정</th>'
            +'</tr>'
            +'</thead>'
            +'<tbody id="addArticleTb">'
            +'</tbody>'
            +'</table>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'<!-- /.container-fluid -->'
            +'</div>'
            +'<!-- End of Main Content -->'
            +' <!-- Footer -->'
            +'<footer class="sticky-footer bg-white">'
            +'  <div class="container my-auto">'
            +'  <div class="copyright text-center my-auto"></div>'
            +' </div>'
            +' </footer>'
            +' <!-- End of Footer -->'
            +' </div>'
            +' <!-- End of Content Wrapper -->'
            +'   </div>'
            +'  <!-- End of Page Wrapper -->'
            +' <!-- Scroll to Top Button-->'
            +'<a class="scroll-to-top rounded" href="#page-top">'
            +'<i class="fas fa-angle-up"></i>'
            +' </a>'
            +' <!-- Logout Modal-->'
            +'<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'
            +' <div class="modal-dialog" role="document">'
            +' <div class="modal-content">'
            +'<div class="modal-header">'
            +' <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>'
            +' <button class="close" type="button" data-dismiss="modal" aria-label="Close">'
            +'   <span aria-hidden="true">×</span>'
            +'</button>'
            +' </div>'
            +'<div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>'
            +' <div class="modal-footer">'
            +'  <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>'
            +' <a class="btn btn-primary" href="login.html">Logout</a>'
            +' </div>'
            +' </div>'
            +' </div>'
            +' </div>').appendTo($('body'));
    }

    var getNews = function(){
        $.ajax("/admin/getNews",{
            type:'GET'
        }).then(function(data, status){

            if(status == 'success'){

                var articles = [];
                articles = data;

                for(var i = 0 ;i < articles.length ; i++){

                    var tr = $('<tr name="removeTr"></tr>').appendTo($('#addArticleTb'));
                    $('<td><input type="checkbox" class="delCheck" name="del_chk" value="'+articles[i].code+'"/></td>').appendTo(tr);
                    $('<td/>',{text:articles[i].title}).appendTo(tr);
                    $('<td/>',{text:articles[i].pressName}).appendTo(tr);
                    $('<td/>',{text:articles[i].districtName}).appendTo(tr);
                    $('<td/>',{text:articles[i].keywordName}).appendTo(tr);
                    $('<td><button type="button" value="'+articles[i].code+'" onclick="modifyArticle(event)">수정</button></td>').appendTo(tr);
                }
            }
        });
    }

    var searchArticle  = function(){

        var searchWord =$("#mainSearchTextbox").val();
        var district= $("#SelectDistrict option:selected").val();
        var date= $("#SelectDate option:selected").val();

        console.log(searchWord+", "+district+", "+date);

        $.ajax("/api/news/searchNews",{
            type:'GET',
            data:{searchWord:searchWord,district:district,date:date,lastArticleCode:""}
        }).then(function(data, status){
            if(status == 'success'){
                $('[name="removeTr"]').remove();

                var articles = [];
                articles = data;

                for(var i = 0 ;i < articles.length ; i++){

                    var tr = $('<tr name="removeTr"></tr>').appendTo($('#addArticleTb'));
                    $('<td><input type="checkbox" class="delCheck" name="del_chk" value="'+articles[i].code+'"/></td>').appendTo(tr);
                    $('<td/>',{text:articles[i].title}).appendTo(tr);
                    $('<td/>',{text:articles[i].pressName}).appendTo(tr);
                    $('<td/>',{text:articles[i].districtName}).appendTo(tr);
                    $('<td/>',{text:articles[i].keywordName}).appendTo(tr);
                    $('<td><button type="button" value="'+articles[i].code+'" onclick="modifyArticle(event)">수정</button></td>').appendTo(tr);
                }
            }
        });
    };


    var showOption = function(){
        console.log($('#detail_search').css("display"));

        if($('#detail_search').css("display") == "none"){
            $('#detail_search').css("display","block");
        }
        else {
            $('#detail_search').css("display","none");
        }
    };

    var modifyArticle = function(event){
        var arCode = $(event.srcElement).val();


        var go = "/admin/updateNews?arCode=\""+arCode+"\"";
        location.href = go;
    }

    var deleteArticles =function(){
        console.log("Delete");

        var delCheckBox = document.getElementsByName("del_chk");
        var delCodes = [];

        for(var i = 0;i<delCheckBox.length;i++){
            if(delCheckBox[i].checked){
                console.log(delCheckBox[i].value);
                delCodes.push(delCheckBox[i].value);
            }
        }

        console.log(delCodes);

        $.ajax('/admin/deleteArticles',{
            type:'GET',
            data:{delCodes:delCodes}
        }).then(function(data,status){
            console.log(data);
            $('[name="removeTr"]').remove();
            getNews();
        });
    }
</script>
<script src="/resources/js/classie.js"></script>
<script src="/resources/js/gnmenu.js"></script>
<script>
    new gnMenu( document.getElementById('gn-menu') );
</script>

<body id="page-top"></body>

</html>

