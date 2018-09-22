<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<spring:eval var="userInfo" expression="@userInfo" />
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title>AdminLTE 3 | Starter</title>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="/resources/plugins/font-awesome/css/font-awesome.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/resources/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom">

    <!-- SEARCH FORM -->
    <form class="form-inline ml-3">
      <div class="input-group input-group-sm">
          <p>로그인: </p>${userInfo.userId}
        <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
        <div class="input-group-append">
          <button class="btn btn-navbar" type="submit">
            <i class="fa fa-search"></i>
          </button>
        </div>
      </div>
    </form>
  </nav>

    <!-- Main content -->
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="container-fluid">
                    <div class="card">
                        <div class="card-body">
                            <div>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">순서</th>
                                        <th scope="col">제목</th>
                                        <th scope="col">작성자</th>
                                        <th scope="col">등록일자</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:choose>
                                        <c:when test="${fn:length(list) > 0 }">
                                            <c:forEach items="${list}" var="boards">
                                                <tr>
                                                    <th scope="row">${boards.idx}</th>
                                                    <th scope="row"><a href="contents/${boards.idx}">${boards.title}</a></th>
                                                    <th scope="row">${boards.writer}</th>
                                                    <th scope="row">${boards.regDate}</th>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                                    </tbody>
                                </table>
                            </div>
                            <a href="contents" class="btn btn-primary">글쓰기</a>
                        </div>
                    </div>

                    <div class="card card-primary card-outline">
                        <div class="card-header">
                            <h5 class="m-0">Featured</h5>
                        </div>
                        <div class="card-body">
                            <h6 class="card-title">Special title treatment</h6>

                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
<!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="/resources/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/resources/dist/js/adminlte.min.js"></script>
</body>
</html>