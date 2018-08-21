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
    <title>회원가입</title>
</head>
<body>
<%--value={login.id}--%>
<%--value={login.password}--%>
<form action="/signup" method="post">
    <label>아이디</label>
    <input type="text" name="uid" placeholder="아이디를 입력해주세요."  />
    <br/>
    <label>비밀번호</label>
    <input type="password" name="password" placeholder="비밀번호를 입력해주세요." />
    <button type="submit">회원가입</button>
</form>

</body>
</html>
