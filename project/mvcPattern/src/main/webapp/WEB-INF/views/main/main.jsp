<%--
  Created by IntelliJ IDEA.
  User: soodiamond
  Date: 2018. 7. 21.
  Time: AM 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
</head>
<body>
<%--value={login.id}--%>
<%--value={login.password}--%>
<form action="/login" method="post">
    <label>아이디</label>
    <input type="text" name="uid" placeholder="아이디를 입력해주세요."  />
    <br/>
    <label>비밀번호</label>
    <input type="text" name="password" placeholder="비밀번호를 입력해주세요."  />
    <button type="submit">로그인</button>
    <div id="app">
        {{ message }}
    </div>
</form>
<a href="/register">회원가입 하러 가기</a>
<script type="text/javascript">
    var app = new Vue({
        el: '#app',
        data: {
            message: '${message}'
        }
    });
</script>
</body>
</html>
