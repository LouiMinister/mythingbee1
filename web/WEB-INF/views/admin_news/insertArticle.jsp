<%--
  Created by IntelliJ IDEA.
  User: nina2
  Date: 2019-08-03
  Time: 오후 7:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>뉴스 관리자 페이지</title>

    <!-- Custom fonts for this template -->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <style>
        table {margin:0 auto;}
        .btn{border-radius : 10px;}
        .btn-blue{background-color : #ffcccc;color : white; font-size : 18px;font-weight : bold;}
        .btn-red{background-color : #ccccff;color : white; font-size : 18px;font-weight : bold;}
        body{padding : 0;margin : 0;}
        ul, ul li {padding : 0; margin : 0; list-style-type : none;}
        ul{position : relative;display : block; width : 100%;}
        ul::before,ul::after{display : block; content ; '';clear : both;}
        ul >li {position : relative;display : block;float : left;width : 25%;}
        .wrap {positon : absolute; left : 0; top : 0; width : 100%; height : 100%;}
        .wrap > form > table, .wrap>div {
            position : relative;display : block; width : 600px; margin : 0 auto;
        }
        .caption{
            position : relative; display : block; width : 600px;
            font-size : 25px; font-weight : bold;
            text-align : center; padding : 20px 0; margin : 0 auto;
        }

        .wrap > form > table{border : none;}
        .wrap > form > table  tr > th {width : 150px;}
        .wrap > form > table  tr > td {width : 150px;}

        /*.wrap > form > table tr >td > input[type : text],*/
        /*.wrap > form > table tr >td > input[type : password],*/
        /*.wrap > form > table tr >td > input[type : email]{*/
        /*    width : 140px;*/
        /*}*/

        .wrap > div > ul > li {width : 50%;}
        .wrap > div > ul > li:first-child{text-align : right;}
        .wrap > div > ul > li button {
            width : 100px;height : 50px;margin : 20px 5px;}
    </style>

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
                <form method="post" action="/remove">
                    <!-- datatales example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            기사 추가
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class = "wrap" >
                                    <div class="caption"></div>
                                    <form name="regForm" method="POST" action="?????">
                                        <table colspan="3">
                                            <tr >
                                                <th>기사 제목</th>
                                                <td><input type = "text" id = "title" name = "title"></td>
                                            </tr>
                                            <tr>
                                                <th>기사 URL</th>
                                                <td><input type = "text" id = "ar_url" name = "ar_url"></td>
                                            </tr>
                                            <tr>
                                                <th>이미지 URL</th>
                                                <td><input type = "text" id = "img_url" name = "img_url"></td>
                                            </tr>
                                            <tr>
                                                <th>기사 요약</th>
                                                <td><input type = "text" id = "summary" name = "summary"></td>
                                            </tr>
                                            <tr>
                                                <th>언론사</th>
                                                <td >
                                                    <select id = "press" name = "press" >
                                                        <option value = '1'></option>
                                                    </select>
                                                </td>

                                                <th>지역구</th>
                                                <td >
                                                    <select id = "district" name = "district" >
                                                        <option value = '1'></option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>키워드</th>
                                                <td>
                                                    <input type="text" id="keyword" name="keyword" />
                                                    <input type="text" id="keywordCode" name="keywordCode"/>
                                                    <button onclick="addKeyword()">추가</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>

                                    <div>
                                        <ul>
                                            <li><button class = "btn btn-blue" onclick="register()">등록하기</button></li>
                                            <li><button class = "btn btn-red" onclick="cancel()">취소하기</button></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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
