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
<script>
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
            +'<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">'
            +'<!-- Topbar Navbar -->'
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
            +'<ul>'
            +'<li>'
            +' <a onclick=" deleteArticles()" class="btn btn-info btn-icon-split" >'
            +'<span class="icon text-white-50" >'
            +' <i class="fas fa-trash-alt"></i>'
            +' </span>'
            +'<span class="text">삭제</span>'
            +' </a>'
            +' <a href="/admin/addPress" methods="GET"  class="btn btn-info btn-icon-split">'
            +' <span class="icon text-white-50">'
            +' <i class="fas fa-trash-alt"></i>'
            +' </span>'
            +' <span class="text">언론사 추가</span>'
            +'</a>'
            +'<a href="/admin/addArticle" methods="GET" class="btn btn-info btn-icon-split" >'
            +'  <span class="icon text-white-50" >'
            +' <i class="fas fa-trash-alt"></i>'
            +' </span>'
            +' <span class="text">기사 추가</span>'
            +' </a>'
            +'</li>'
            +'</ul>'
            +'</div>'
            +'<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">'
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
            +'<tbody>'
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
                    console.log(articles[i]);
                    var tr = $('<tr name="removeTr"></tr>').appendTo($('tbody'));
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

    var modifyArticle = function(event){
        var arCode = $(event.srcElement).val();
        console.log(arCode);

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
<body id="page-top">
</body>

</html>

