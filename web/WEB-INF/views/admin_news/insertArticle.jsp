<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: nina2--%>
<%--  Date: 2019-08-03--%>
<%--  Time: 오후 7:01--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>뉴스 관리자 페이지</title>--%>

<%--    <!-- Custom fonts for this template -->--%>
<%--    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">--%>
<%--    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">--%>

<%--    <!-- Custom styles for this template -->--%>
<%--    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">--%>

<%--    <!-- Custom styles for this page -->--%>
<%--    <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">--%>
<%--&lt;%&ndash;    <script src="/resources/js/insertArticle.js"/>&ndash;%&gt;--%>
<%--    <style>--%>
<%--        table {margin:0 auto;}--%>
<%--        .btn{border-radius : 10px;}--%>
<%--        .btn-blue{background-color : #ffcccc;color : white; font-size : 18px;font-weight : bold;}--%>
<%--        .btn-red{background-color : #ccccff;color : white; font-size : 18px;font-weight : bold;}--%>
<%--        body{padding : 0;margin : 0;}--%>
<%--        ul, ul li {padding : 0; margin : 0; list-style-type : none;}--%>
<%--        ul{position : relative;display : block; width : 100%;}--%>
<%--        ul::before,ul::after{display : block; content : '';clear : both;}--%>
<%--        ul >li {position : relative;display : block;float : left;width : 25%;}--%>
<%--        .wrap {positon : absolute; left : 0; top : 0; width : 100%; height : 100%;}--%>
<%--        .wrap > form > table, .wrap>div {--%>
<%--            position : relative;display : block; width : 1000px; margin : 0 auto;--%>
<%--        }--%>
<%--        .caption{--%>
<%--            position : relative; display : block; width : 1000px;--%>
<%--            font-size : 25px; font-weight : bold;--%>
<%--            text-align : center; padding : 20px 0; margin : 0 auto;--%>
<%--        }--%>

<%--        .wrap > form > table{border : none;}--%>
<%--        .wrap > form > table  tr > th {width : 150px;}--%>
<%--        .wrap > form > table  tr > td {width : 1000px;}--%>

<%--        .wrap > div > ul > li {width : 50%;}--%>
<%--        .wrap > div > ul > li:first-child{text-align : right;}--%>
<%--        .wrap > div > ul > li button {--%>
<%--            width : 100px;height : 50px;margin : 20px 5px;}--%>
<%--    </style>--%>

<%--</head>--%>
<%--<script>--%>
<%--    var keyNames = new Array();--%>

<%--    var addKeyword = function(){--%>
<%--        var keyName = $('#keyword').val();--%>
<%--        var keyCode  = $('#keywordCode').val();--%>
<%--        console.log(keyName + ", " + keyCode);--%>

<%--        for(var i = 0; i<keyNames.length ; i++){--%>
<%--            if(keyNames[i] == keyName){--%>
<%--                return ;--%>
<%--            }--%>
<%--        }--%>
<%--        keyNames.push(keyName);--%>

<%--        var td = $('<td name="keywordList" id="'+keyName+'"></td>').appendTo('#keywordAdd');--%>

<%--        $('<div class="btn-group btn-group-toggle" data-toggle="buttons">'--%>
<%--        +'<label class="btn btn-secondary active" onclick="deleteKeyword(event)" id="'+keyName+'">'--%>
<%--        +'<input type="radio" name="options" id="option2" autocomplete="off" checked>X</label>'--%>
<%--        +'<label class="btn btn-secondary" >'+keyName+'</label></div>').appendTo(td);--%>
<%--    }--%>

<%--    var deleteKeyword = function(event){--%>
<%--        var keyName = event.srcElement.id;--%>
<%--        console.log(keyName);--%>

<%--        $("#"+keyName).remove();--%>
<%--    }--%>

<%--</script>--%>
<%--<body id="page-top">--%>

<%--<!-- Page Wrapper -->--%>
<%--<div id="wrapper">--%>

<%--    <!-- Content Wrapper -->--%>
<%--    <div id="content-wrapper" class="d-flex flex-column">--%>

<%--        <!-- Main Content -->--%>
<%--        <div id="content">--%>

<%--            <!-- topbar -->--%>
<%--            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">--%>
<%--            </nav>--%>
<%--            <!-- end of topbar -->--%>

<%--            <!-- begin page content -->--%>
<%--            <div class="container-fluid">--%>
<%--                    <!-- datatales example -->--%>
<%--                    <div class="card shadow mb-4">--%>
<%--                        <div class="card-header py-3">--%>
<%--                            기사 추가--%>
<%--                        </div>--%>
<%--                        <div class="card-body">--%>
<%--                            <div class="table-responsive">--%>
<%--                                <div class = "wrap" >--%>
<%--                                    <div class="caption"></div>--%>
<%--                                    <form name="regForm" method="POST" action="/admin/addArticle">--%>
<%--                                        <table>--%>
<%--                                            <tr >--%>
<%--                                                <th>기사 제목</th>--%>
<%--                                                <td><input type = "text" id = "title" name = "title"></td>--%>
<%--                                            </tr>--%>
<%--                                            <tr>--%>
<%--                                                <th>기사 URL</th>--%>
<%--                                                <td><input type = "text" id = "ar_url" name = "ar_url"></td>--%>
<%--                                            </tr>--%>
<%--                                            <tr>--%>
<%--                                                <th>이미지 URL</th>--%>
<%--                                                <td><input type = "text" id = "img_url" name = "img_url"></td>--%>
<%--                                            </tr>--%>
<%--                                            <tr>--%>
<%--                                                <th>기사 요약</th>--%>
<%--                                                <td><input type = "text" id = "summary" name = "summary"></td>--%>
<%--                                            </tr>--%>
<%--                                            <tr>--%>
<%--                                                <th>언론사</th>--%>
<%--                                                <td >--%>
<%--                                                    <select id = "press" name = "press" >--%>
<%--                                                    <option selected disabled>언론사</option>--%>
<%--                                                    <c:forEach items="${presses}" var = "press">--%>
<%--                                                        <option value = '${press}'>${press}</option>--%>
<%--                                                    </c:forEach>--%>
<%--                                                </select>--%>
<%--                                                </td>--%>

<%--                                                <th>지역구</th>--%>
<%--                                                <td >--%>
<%--                                                    <select id = "district" name = "district" >--%>
<%--                                                            <option selected disabled>지역구</option>--%>
<%--                                                        <c:forEach items="${districts}" var = "district">--%>
<%--                                                            <option value = '${district}'>${district}</option>--%>
<%--                                                        </c:forEach>--%>
<%--                                                    </select>--%>
<%--                                                </td>--%>
<%--                                            </tr>--%>
<%--                                            <tr>--%>
<%--                                                <th>키워드</th>--%>
<%--                                                <td>--%>
<%--                                                    <input type="text" id="keyword" name="keyword" />--%>
<%--                                                    <input type="text" id="keywordCode" name="keywordCode"/>--%>
<%--                                                    <button type="button" onclick="addKeyword()">추가</button>--%>
<%--                                                </td>--%>
<%--                                            </tr>--%>
<%--                                            <tr id="keywordAdd">--%>
<%--                                            </tr>--%>
<%--                                        </table>--%>
<%--                                    </form>--%>

<%--                                    <div>--%>
<%--                                        <ul>--%>
<%--                                            <li><button class = "btn btn-blue" onclick="register()">등록하기</button></li>--%>
<%--                                            <li><button class = "btn btn-red" onclick="cancel()">취소하기</button></li>--%>
<%--                                        </ul>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--            </div>--%>
<%--            <!-- /.container-fluid -->--%>
<%--        </div>--%>
<%--        <!-- End of Main Content -->--%>

<%--        <!-- Footer -->--%>

<%--        <!-- End of Footer -->--%>

<%--    </div>--%>
<%--    <!-- End of Content Wrapper -->--%>

<%--</div>--%>
<%--<!-- End of Page Wrapper -->--%>

<%--<!-- Scroll to Top Button-->--%>
<%--<a class="scroll-to-top rounded" href="#page-top">--%>
<%--    <i class="fas fa-angle-up"></i>--%>
<%--</a>--%>
<%--<!-- Bootstrap core JavaScript-->--%>
<%--<script src="/resources/vendor/jquery/jquery.min.js"></script>--%>
<%--<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>--%>

<%--<!-- Core plugin JavaScript-->--%>
<%--<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>--%>

<%--<!-- Custom scripts for all pages-->--%>
<%--<script src="/resources/js/sb-admin-2.min.js"></script>--%>

<%--<!-- Page level plugins -->--%>
<%--<script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>--%>
<%--<script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>--%>

<%--<!-- Page level custom scripts -->--%>
<%--<script src="/resources/js/demo/datatables-demo.js"></script>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>뉴스 추가 페이지</title>

    <!-- Custom fonts for this template -->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <style>
        .go-center {padding: 0 30%;}
    </style>

    <script>
        Array.prototype.remove = function(){
            var what, a = arguments, L=a.length,ax;

            while(L&&this.length){
                what = a[--L];
                while((ax = this.indexOf(what)) !== -1){
                    this.splice(ax,1);
                }
            }
            return this;
        }

        String.prototype.isEmpty = function(){
            return (this.trim() == '');
        }

        var keyNames = new Array();
        var keyCodes = new Array();

        var addKeyword = function(){

            if(keyNames.length >= 5){
                document.getElementById("keyword").value="5개까지 가능합니다.";
                document.getElementById("keywordCode").value="5개까지 가능합니다.";

                document.getElementById("keyword").style.backgroundColor="#ffffb3";
                document.getElementById("keywordCode").style.backgroundColor="#ffffb3";

                setTimeout(function() {
                    document.getElementById("keyword").style.backgroundColor="#ffffff";
                    document.getElementById("keywordCode").style.backgroundColor="#ffffff";

                    document.getElementById("keyword").value="";
                    document.getElementById("keyword").placeholder="키워드 이름";

                    document.getElementById("keywordCode").value="";
                    document.getElementById("keywordCode").placeholder="키워드 이름";
                }, 1500);

                return ;
            }

            var keyName = $('#keyword').val();
            var keyCode  = $('#keywordCode').val();
            console.log(keyName + ", " + keyCode);

            for(var i = 0; i<keyNames.length ; i++){
                if(keyNames[i] == keyName){
                    document.getElementById("keyword").value="";
                    document.getElementById("keyword").placeholder="키워드 이름";

                    document.getElementById("keywordCode").value="";
                    document.getElementById("keywordCode").placeholder="키워드 이름";
                    return ;
                }
            }
            keyNames.push(keyName);
            keyCodes.push(keyCode);

            var td = $('<td name="keywordList" id="'+keyName+'"></td>').appendTo('#keywordAdd');

            $('<div class="btn-group btn-group-toggle" data-toggle="buttons">'
            +'<label class="btn btn-secondary active" onclick="deleteKeyword(event)" id="'+keyName+','+keyCode+'">'
            +'<input type="radio" name="options" id="option2" autocomplete="off" checked>X</label>'
            +'<label class="btn btn-secondary" >'+keyName+'</label></div>').appendTo(td);

            document.getElementById("keyword").value="";
            document.getElementById("keyword").placeholder="키워드 이름";

            document.getElementById("keywordCode").value="";
            document.getElementById("keywordCode").placeholder="키워드 이름";
        }

        var deleteKeyword = function(event){
            var id = event.srcElement.id;
            var splitString = id.split(",");

            var keyName = splitString[0];
            var keyCode = splitString[1];

            console.log(keyName + ", "+keyCode);

            $("#"+keyName).remove();

            keyNames.remove(keyName);
            keyCodes.remove(keyCode);
        }

        var addArticle  = function(){

            var form = document.addForm;

            var title = $('#title').val();
            if(title.isEmpty()){
                $('#title').focus();
                document.getElementById("title").style.backgroundColor="#ffffb3";

                setTimeout(function() {

                    document.getElementById("title").style.backgroundColor="#ffffff";
                }, 1500);

                return;
            }

            var summary = $('#summary').val();
            if(summary.isEmpty()){
                $('#summary').focus();
                document.getElementById("summary").style.backgroundColor="#ffffb3";

                setTimeout(function() {
                    document.getElementById("summary").style.backgroundColor="#ffffff";
                }, 1500);

                return;
            }

            var ar_url = $('#ar_url').val();
            console.log(ar_url);
            if(ar_url.isEmpty()){
                $('#ar_url').focus();
                document.getElementById("ar_url").style.backgroundColor="#ffffb3";

                setTimeout(function() {
                    document.getElementById("ar_url").style.backgroundColor="#ffffff";
                }, 1500);

                return;
            }

            var img_url = $('#img_url').val();
            if(img_url.isEmpty()){
                $('#img_url').focus();
                document.getElementById("img_irl").style.backgroundColor="#ffffb3";

                setTimeout(function() {
                    document.getElementById("img_irl").style.backgroundColor="#ffffff";
                }, 1500);

                return;
            }

            var district = document.getElementById('district').value;
            if(district == "지역구"){
                $('#district').focus();
                document.getElementById("district").style.backgroundColor="#ffffb3";

                setTimeout(function() {
                    document.getElementById("district").style.backgroundColor="#ffffff";
                }, 1500);
                return ;
            }

            // 구현되면 주석 풀기
            var press = document.getElementById('press').value;
            if(press == "언론사"){
                $('#press').focus();
                document.getElementById("press").style.backgroundColor="#ffffb3";

                setTimeout(function() {
                    document.getElementById("press").style.backgroundColor="#ffffff";
                }, 1500);

                return ;
            }

            if(keyNames.length == 0){
                document.getElementById("keyword").value="키워드 미입력";
                document.getElementById("keywordCode").value="키워드 코드 미입력";

                document.getElementById("keyword").style.backgroundColor="#ffffb3";
                document.getElementById("keywordCode").style.backgroundColor="#ffffb3";

                setTimeout(function() {
                    document.getElementById("keyword").style.backgroundColor="#ffffff";
                    document.getElementById("keywordCode").style.backgroundColor="#ffffff";
                }, 1500);
                return ;
            }

            var keywordNameString = keyNames.toString();
            var keywordCodeString = keyCodes.toString();
            console.log(keywordNameString);
            console.log(keywordCodeString);

            $.ajax({
                url:"/admin/addArticle",
                type:"POST",
                contentType: 'application/json',
                crossDomain:true,
                data:JSON.stringify({"title":title,
                    "summary":summary,"url":ar_url,
                    "imgUrl":img_url,"districtName":district,"pressName":press,"keywordName":keywordNameString,"keywordCode":keywordCodeString})
            }).then(function(data, status){
                if(status == "success"){
                    console.log(data);

                    location.href = "/admin/news";
                }
            });
        }
    </script>
</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            </nav>
            <!-- end of topbar -->

            <!-- begin page content -->
            <div class="container-fluid">
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            기사 추가
                        </div>

                        <div class="bs-docs-section">

                            <div class="row">
                                <div class="col-md-12 go-center">

                                    <form class="bs-component" name="addForm">

                                        <div class="form-group">
                                            <label class="col-form-label col-form-label-lg" >기사 제목</label>
                                            <input class="form-control form-control-lg" type="text" placeholder="기사 제목" id = "title" name = "title">
                                        </div>

                                        <div class="form-group">
                                            <label class="col-form-label col-form-label-lg">기사 요약</label>
                                            <input class="form-control form-control-lg" type="text" placeholder="기사 요약" id = "summary" name = "summary">
                                        </div>

                                        <div class="form-group">
                                            <label class="col-form-label col-form-label-lg" >기사
                                                URL</label>
                                            <input class="form-control form-control-lg" type="text"
                                                   placeholder="기사 URL" id = "ar_url" name = "ar_url">
                                        </div>

                                        <div class="form-group">
                                            <label class="col-form-label col-form-label-lg" >이미지
                                            URL</label>
                                            <input class="form-control form-control-lg" type="text"
                                                   placeholder="이미지 URL" id = "img_url" name = "img_url">
                                        </div>

                                        <fieldset>
                                            <div class="form-group">
                                                <select class="custom-select" id = "press" name = "press" >
                                                    <option selected disabled>언론사</option>
                                                    <c:forEach items="${presses}" var = "press">
                                                        <option value = '${press}'>${press}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="form-group">
                                                    <select class="custom-select" id = "district" name = "district" >
                                                        <option selected disabled>지역구</option>
                                                        <c:forEach items="${districts}" var = "district">
                                                            <option value = '${district}'>${district}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </fieldset>

                                            <table>
                                                <tr>
                                                    <td>
                                                        <div class="form-group">
                                                            <label class="col-form-label col-form-label-lg" >키워드 이름
                                                            <input class="form-control form-control-lg" type="text"
                                                                   placeholder="키워드 이름" id="keyword" name="keyword">
                                                            </label>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="form-group">
                                                            <label class="col-form-label col-form-label-lg" >키워드 코드
                                                            <input class="form-control form-control-lg" type="text"
                                                                   placeholder="키워드 코드" id="keywordCode" name="keywordCode">
                                                            </label>
                                                        </div>
                                                    </td>
                                                    <td >
                                                        <button type="button" onclick="addKeyword()">추가</button>
                                                    </td>
                                                </tr>
                                                <tr id="keywordAdd"><tr>
                                            </table>
                                    </form>

                                    <div>
                                        <ul style="text-align: center;list-style: none;padding:0">
                                            <li>
                                                <button type="button" class="btn btn-success"
                                                        onclick="addArticle()">추가하기</button>
                                                <button type="button" class="btn btn-secondary"
                                                        onclick="location.href='/admin/news'">취소하기</button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->

        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<!-- Bootstrap core JavaScript-->
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
</body>
</html>