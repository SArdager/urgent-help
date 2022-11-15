<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>Main page</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
  <script>
      var w = Number(window.innerWidth);
      var h = Number(window.innerHeight);
      if (h>1.5*w) {
        $('head').append('<link rel="stylesheet" type="text/css" href="resources/css/mobileStyle.css">');
        $('head').append('<meta name="viewport" content="width=device-width, initial-scale=1.0">');
      } else {
        $('head').append('<link rel="stylesheet" type="text/css" href="resources/css/style.css">');
      }
  </script>
</head>
<body>
    <section>
    <div class="container">
        <div class="user_title">
            <select id="select_lang" style="margin-right: 20px; width: 8em">
                <option value="RU">русский</option>
                <option value="KZ">қазақ</option>
            </select>
        </div>
        <hr>
        <div id="ru_page" style="display: block">
            <h1 style="margin-left: 20px">СПРАВОЧНЫЙ ПОРТАЛ</h1>
            <h1>"Послеродовые кровотечения"</h1>
            <sec:authorize access="!isAuthenticated()">
                <p>Вход только для авторизованных пользователей</p>
                <h4><a href="login">Войти</a></h4>
                <h4><a href="registration?lang=RU">Зарегистрироваться</a></h4>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <h3 style="margin-left: 5%;">Login: ${pageContext.request.userPrincipal.name}</h3>
                <h4><a href="ru/work-page">На главную</a></h4>
                <h4><a href="ru/change-password">Сменить пароль</a></h4>
                <h4><a href="logout">Выйти</a></h4>
            </sec:authorize>
        </div>
        <div id="kz_page" style="display: none">
            <h1>АНЫҚТАМАЛЫҚ ПОРТАЛ</h1>
            <h1>"Босанғаннан кейінгі қан жоғалту"</h1>
            <h3 style="margin-left: 10%;">${pageContext.request.userPrincipal.name}</h3>
            <sec:authorize access="!isAuthenticated()">
                <p>Кіру тек уәкілетті пайдаланушылар үшін</p>
                <h4><a href="login">Кіру</a></h4>
                <h4><a href="registration?lang=KZ">Тіркелу</a></h4>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <h4><a href="kz/work-page">Басты бетке</a></h4>
                <h4><a href="kz/change-password">Құпиясөзді өзгерту</a></h4>
                <h4><a href="logout">Шығу</a></h4>
            </sec:authorize>
        </div>
    </div>
    </section>
    <script>
        $(document).ready(function(){
            $("h1").css("color", "blue");
            var kz_page = document.getElementById("kz_page");
            var ru_page = document.getElementById("ru_page");
            $('#select_lang').on('change', function(){
                if($('#select_lang').val()==="KZ"){
                    kz_page.style.display = "block";
                    ru_page.style.display = "none";
                } else {
                    kz_page.style.display = "none";
                    ru_page.style.display = "block";
                }
            });
       });
    </script>
</body>
</html>


