<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registration page</title>
  <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="resources/js/registration.js"></script>
  <script>
      var w = Number(window.innerWidth);
      var h = Number(window.innerHeight);
      if (h>1.5*w) {
        $('head').append('<link rel="stylesheet" type="text/css" href="resources/css/mobileStyle.css">');
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
        <h1 style="margin-left: 20px">СПРАВОЧНЫЙ ПОРТАЛ</h1>
        <h1>"Послеродовые кровотечения"</h1>
        <div class="user_title">
            <a style="margin-right: 20px; " href="login">Войти</a>
        </div>
        <h2>Регистрация нового пользователя</h2>
        <h2><div id="result_line"></div></h2>
        <br>
        <form:form id="registration" modelAttribute="userForm" action="registration/addUser" method="post">
        <form:errors path="username"></form:errors>
            ${errorRegistration}
        <table>
            <tr>
                <td class="table_title">Имя</td>
                <td><form:input type="text" id="firstname"  path="userFirstname" size="40" autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td class="table_title">Фамилия</td>
                <td><form:input type="text" id="surname" path="userSurname" size="40" autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td class="table_title" style="font-weight: bold; ">Телефон (логин)</td>
                <td style="font-size: 0.85em;">(+7)<form:input type="tel" id="phone" path="username" maxlength="15" autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td class="table_title">Электронная почта</td>
                <td><form:input type="text" id="email" path="email" size="40" autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td class="table_title">Регион</td>
                <td><form:select id="select_region" size="35" autofocus="true" path="regionId">
                       <option value="0">Выберите регион</option>
                       <c:forEach var="region" items="${regions}">
                           <option value=${region.id}>${region.regionName}</option>
                       </c:forEach>
                    <form:/select>
                    <br>
                    <form:input type="hidden" id="regionName" path="regionName" value=""></form:input>
                </td>

            </tr>
            <tr>
                <td class="table_title">Место работы</td>
                <td><form:input type="text" id="workPlace" path="workPlace" size="40" autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td class="table_title">Должность</td>
                <td><form:input type="text" id="position" path="position" size="40" autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td class="table_title">Пароль</td>
                <td><form:input type="password" id="password" path="password" size="40" autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td class="table_title">Повторите пароль</td>
                <td><input type="password" id="confirm_password" size="40" autofocus="true"/></td>
            </tr>
            <tr>
                <td class="table_title"></td>
                <td>
                <br>
                <br>
                <input type="button" id="add_user" style="width: 12em" value="Зарегистрироваться" /></td>
            </tr>
        </table>
        <form:input type="hidden" id="lang" path="lang" value="RU" />
        </form:form>
        <input type="hidden" id="count" value="0"/>
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

</body>
</html>