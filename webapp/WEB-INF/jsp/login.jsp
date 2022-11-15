<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log-in page</title>
  <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="resources/js/main.js"></script>
  <script>
      var w = Number(window.innerWidth);
      var h = Number(window.innerHeight);
      if (h>1.5*w) {
        $('head').append('<link rel="stylesheet" type="text/css" href="resources/css/mobileStyle.css">');
        $('head').append('<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">');
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
        <sec:authorize access="isAuthenticated()">
            <% response.sendRedirect("/"); %>
        </sec:authorize>
        <form id="user_auth" method="post" action="login">
            <div id="ru_text" style="display: block">
              <h1 style="margin-left: 20px">СПРАВОЧНЫЙ ПОРТАЛ</h1>
              <h1>"Послеродовые кровотечения"</h1>
              <p>Для продолжения работы необходима авторизация пользователя</p>
              <div class="user_title">
                  <a id="reg" style="margin-right: 20px; " href="reg-ru">Зарегистрироваться</a>
              </div>
              <h2>Вход в систему</h2>
            </div>
            <div id="kz_text" style="display: none">
              <h1 style="margin-left: 20px">АНЫҚТАМАЛЫҚ ПОРТАЛ</h1>
              <h1>"Босанғаннан кейінгі қан жоғалту"</h1>
              <p>Кіру тек уәкілетті пайдаланушылар үшін</p>
              <br>
              <h2>Кіру</h2>
            </div>
            <h3><div id="result_line"></div></h3>
            <table>
                <tr>
                    <td id="ru_phone" class="table_title" style="padding-left: 5%; display: table-cell">Телефон</td>
                    <td id="kz_phone" class="table_title" style="padding-left: 5%; display: none">Телефон</td>
                    <td style="font-size: 0.85em;">(+7)<input type="tel" id="phone" name="username" autofocus="true" required/></td>
                </tr>
                <tr>
                    <td id="ru_password" class="table_title" style="padding-left: 5%; display: table-cell">Пароль</td>
                    <td id="kz_password" class="table_title" style="padding-left: 5%; display: none">KZ Пароль</td>
                    <td><input type="password" id="password" name="password" size="40" required/></td>
                </tr>
                <tr>
                    <td class="table_title"></td>
                    <td><button id="btn_title" type="submit">Войти</button></td>
                </tr>
            </table>
            <input id="lang" name="lang" type="hidden" value="RU" />
            <input type="hidden" id="count" value="0"/>
        </form>
        <br>
        <br>
        <p>
            <h3 id="forget_password" style="margin-left: 16px;">Забыли пароль?</h3>
            <div id="show_forget" style="display: none; margin-left: 16px;">
                <div id="text_forget">
                    <b>Пароль не восстанавливается!</b> <br>Если Вы забыли пароль, то его потребуется сбросить. <br>
                    Временный пароль будет отправлен на Ваш адрес электронной почты.<br>
                    После авторизации по временному паролю Вы можете поменять пароль.
                </div>
                <br>
                <button id="btn_forget_password" style="margin-left: 150px;">Сбросить пароль</button>
            </div>
        </p>
     </div>
  </section>
    <script>
        $(document).ready(function(){
            $("h1").css("color", "blue");
            var resultLineValue;
            var clickNumber = 0;
            window.addEventListener("click", function(){
                clickNumber++;
                resultLineValue = $('#result_line').text();
                if(clickNumber==0){
                    $('#result_line').text("");
                }
                if(resultLineValue.length>0){
                    clickNumber = -1;
                }
            });
       });
    </script>

    <div class="buffer" style = "height: 5em;"></div>

</body>
</html>