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
    <title>소복소복</title>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
</head>
<style>
    h1 {
        color: #c1daff;
    }
    button {
        cursor: pointer;
    }
    .title h1 {
        text-align: center;
    }
    .loginForm {
        width: 400px;
        display: flex;
        flex-direction: column;
        padding: 40px 30px;
        margin: 0 auto;
        background-image: linear-gradient(to right, #E3FEE7 , #D9E7FE);
    }
    .loginForm > div {
        display: flex;
        margin: 15px 18px;
        height: 40px;
        align-items: center;
    }
    .loginForm div label {
        margin: 0 18px;
        width: 70px;
    }
    .loginForm div input {
        flex-grow: 1;
        padding: 0 13px;
        height: 100%;
    }
    .loginForm div button {
        flex: 1;
        margin: 0 10px;
        height: 100%;
        color: #a0c7ff;
        border: none;
        border-bottom: 2px solid #a0c7ff;
        font-size: 1.5em;
        background-color: transparent;
    }
</style>
<body>
<%--value={login.id}--%>
<%--value={login.password}--%>

    <form action="/login" method="post">
        <div class="title">
            <h1>소복"소복"</h1>
        </div>
        <div class="loginForm">
            <div>
                <label>아이디</label>
                <input type="text" name="uid" placeholder="아이디를 입력해주세요."  />
            </div>
            <div>
                <label>비밀번호</label>
                <input type="password" name="password" placeholder="비밀번호를 입력해주세요."  />
            </div>
            <div>
                <button type="submit" name="login" value="Login">로그인</button>
                <button type="submit" name="register" value="Register">회원가입</button>
            </div>
        </div>
        <%--<div id="app">--%>
        <%--{{ message }}--%>
        <%--</div>--%>
    </form>

<script type="text/javascript">
    <%--var app = new Vue({--%>
        <%--el: '#app',--%>
        <%--data: {--%>
            <%--message: '${message}'--%>
        <%--}--%>
    <%--});--%>
</script>
</body>
</html>
