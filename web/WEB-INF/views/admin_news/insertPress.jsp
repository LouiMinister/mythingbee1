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
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <style>
        .go-center {padding: 0 30%;}
        .seperate-form {margin-top: 30px}
        .button-box{margin: 10px 4px}
    </style>

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
        String.prototype.isEmpty = function () {
            return (this.trim() == '');
        }

        var keyNames = new Array();
        var keyCodes = new Array();

        $(document).ready(function(){
            getPressName();
        });

        var deletePress = function(){
            var pressName = document.getElementById('press').value;
            console.log(pressName);

            $.ajax("/admin/deletePress",{
                type:"GET",
                data:{"delPressName":pressName}
            }).then(function(data, status){
                if(status == "success"){
                    console.log(data);

                    $('[name="delOp"]').remove();
                    getPressName();
                }
            });
        }

        var getPressName = function(){
            $.ajax('/admin/allPress',{
                type:'GET'
            }).then(function(data,status){
               if(status == "success"){
                   console.log(data);

                   var presses = [];

                   presses = data;

                   for(var i = 0; i<presses.length; i++){
                       var option =
                           $('<option name="delOp" value="'+presses[i]+'">'+presses[i]+'</option>').appendTo($("#press"));
                   }
               }
            });
        }

        var addPressInfo = function() {
            var pressName = document.getElementById("pressName").value;
            var pressCode = document.getElementById("pressCode").value;

            console.log(pressName + ", " + pressCode);

            if (pressCode.isEmpty()) {
                document.getElementById("pressCode").value = "필수 입력";
                document.getElementById("pressCode").style.backgroundColor = "#ffffb3";

                setTimeout(function () {
                    document.getElementById("pressCode").style.backgroundColor = "#ffffff";
                    document.getElementById("pressCode").value = "";
                    document.getElementById("pressCode").placeholder = "키워드 코드";
                }, 1500);

                return;
            }

            if (pressName.isEmpty()) {
                document.getElementById("pressName").value = "필수 입력";
                document.getElementById("pressName").style.backgroundColor = "#ffffb3";

                setTimeout(function () {
                    document.getElementById("pressName").style.backgroundColor = "#ffffff";
                    document.getElementById("pressName").value = "";
                    document.getElementById("pressName").placeholder = "키워드 이름";
                }, 1500);

                return;
            }

            $.ajax({
                url: "/admin/addPress",
                type: "POST",
                contentType: "application/json",
                crossDomain: true,
                data: JSON.stringify({"name": pressName, "code": pressCode})
            }).then(function (data, status) {
                if (status == "success") {
                    console.log(data);

                    $('[name="delOp"]').remove();
                    getPressName();

                    document.getElementById("pressCode").value = "";
                    document.getElementById("pressCode").placeholder = "키워드 코드";

                    document.getElementById("pressName").value = "";
                    document.getElementById("pressName").placeholder = "키워드 이름";
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
                    <!-- datatales example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            언론사 관리
                        </div>

                        <div class="bs-docs-section">

                            <div class="row">
                                <div class="col-md-12 go-center">
                                    <form class="seperate-form">
                                        <fieldset>
                                            <legend class="text-xl-left">언론사 삭제</legend>
                                            <div class="form-group">
                                                <label for="press">현재 언론사</label>
                                                <select class="form-control" id="press" name="press">
                                                        <option disabled selected>언론사 목록</option>
                                                </select>
                                            </div>
                                        </fieldset>
                                    </form>
                                    <button class="btn btn-primary" onclick="deletePress()">삭제
                                    </button>

                                    <form class="seperate-form">

                                        <fieldset>
                                            <legend class="text-xl-left">언론사 추가</legend>
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <label class="form-control-label" >언론사 이름</label>
                                                    <input type="text" class="form-control"
                                                           id="pressName" placeholder="언론사 이름">
                                                    <div class="valid-feedback">추가할 언론사 이름을 입력해주세요.</div>
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-control-label" >언론사 코드</label>
                                                    <input type="text" class="form-control"
                                                           id="pressCode" placeholder="언론사 코드">
                                                    <div class="valid-feedback">추가할 언론사 코드를 영문 대문자 두글자로 입력해주세요.</div>
                                                </div>
                                            </div>
                                            <div>
                                            <button type="button" class="btn btn-success button-box"
                                                onclick="addPressInfo()">추가
                                            </button>
                                            <button type="button" class="btn btn-secondary button-box"
                                                    onclick="location.href='/admin/news'">뉴스 관리자 페이지</button>
                                            </div>
                                        </fieldset>
                                    </form>
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
</body>
</html>
