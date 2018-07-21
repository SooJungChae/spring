<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="/resources/plugins/font-awesome/css/font-awesome.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/resources/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Content Wrapper. Contains page content -->
  <div>
    <!-- /.content-header -->
    <c:choose>
      <c:when test="${isRead ne true}">
        <form:form method="POST" action="${actionUrl}" modelAttribute="boardVO">
          title:<br>
          <input type="text" name="title" value="${board.title}" /><br>
          content:<br>
          <textarea name="contents">${board.contents}</textarea> <br>
          writer:<br>
          <input type="text" name="writer" value="${board.writer}" /><br>
          <button type="submit" >
            등록 / 수정
          </button>

        </form:form>
      </c:when>
      <c:when test="${isRead eq true}">
        <a href="/edit/${board.idx}">수정</a>
        <a href="/delete/${board.idx}">삭제</a>
        <form action="#">
          title:<br>
          <input type="text" name="title" value="${board.title}" readonly/><br>
          content:<br>
          <textarea name="contents" readonly>${board.contents}</textarea> <br>
          writer:<br>
          <input type="text" name="writer" value="${board.writer}" readonly /><br>
        </form>
      </c:when>
    </c:choose>

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="container-fluid">
            <div class="card">
              <div class="card-body">
                <div>
	                글쓰러 왔슈?
                    <c:if test="${not empty board}">
                      <tr>
                        <th scope="row">${board.idx}</th>
                        <th scope="row">${board.title}</th>
                        <th scope="row">${board.writer}</th>
                        <th scope="row">${board.regDate}</th>
                      </tr>
                    </c:if>
				</div>
              </div>
            </div>
          </div>
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
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