<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Working ru-page</title>
  <script type="text/javascript" src="../resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="../resources/js/working.js"></script>
  <script>
      var w = Number(window.innerWidth);
      var h = Number(window.innerHeight);
      if (h>w) {
        $('head').append('<link rel="stylesheet" type="text/css" href="../resources/css/mobileStyle.css">');
        $('head').append('<meta name="viewport" content="width=device-width, initial-scale=1.0">');
      } else {
        $('head').append('<link rel="stylesheet" type="text/css" href="../resources/css/style.css">');
      }
  </script>

</head>

<body>
  <section>
     <div class="container">
        <div class="user_title">
            <strong style="margin-top: 4px; margin-right: 20px">Пользователь: ${user.userFirstname} ${user.userSurname}</strong>
            <select id="select_lang" style="margin-right: 20px; width: 8em">
                <option value="RU">русский</option>
                <option value="KZ">қазақ</option>
            </select>
            <a style="margin-right: 20px; margin-top: 4px;" href="../logout">Выйти</a>
        </div>
        <hr>
            <h1 style="margin-left: 20px">СПРАВОЧНЫЙ ПОРТАЛ</h1>
            <h2>"Послеродовые кровотечения"</h2>
        <div class="user_title">
            <a style="margin-right: 20px; " href="change-password">Сменить пароль</a>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="user_title">
                <a style="margin-right: 20px; " href="../admin/work-start">Вернуться в  административную панель</a>
            </div>
        </sec:authorize>
        <h3><div id="result_line"></div></h3>
        <h2>Первый этап.</h2>
        <h3 id="actions" style="text-decoration: underline;">Начальные действия (одновременно с остановкой кровотечения и лечением)</h3>
        <div id="actions_area" style="display: none">
            <h4 id="first_title" >1. Первоначальные действия</h4>
            <div id="first_area" style="display: none">
                <p id="text1"></p>
                <hr>
            </div>
            <h4 id="second_title" style="text-decoration: underline;">2. Манипуляции</h4>
            <div id="second_area" style="display: none">
                <p id="text2"></p>
                <hr>
            </div>
            <h4 id="third_title" style="text-decoration: underline;">3. Назначение анализов</h4>
            <div id="third_area" style="display: none">
                <p id="text3"></p>
                <hr>
            </div>
            <h4 id="forth_title" style="text-decoration: underline;">4. Тромбоэластография</h4>
            <div id="forth_area" style="display: none">
                <p id="text4"></p>
                <hr>
            </div>
            <h4 id="fifth_title" style="text-decoration: underline;">5. Установление причины (4Т)</h4>
            <div id="fifth_area" style="display: none">
                <p id="text5"></p>
                <hr>
            </div>
            <h4 id="sixth_title" style="text-decoration: underline;">6. Продолжить инфузионно-трансфузионную терапию (ИТТ)</h4>
            <div id="sixth_area" style="display: none">
                <p id="text6"></p>
            </div>
            <input type="hidden" id="lang" value="RU" />
        </div>
        <br>
        <h2 id="analyses" style="text-decoration: underline;">Действия по результатам лабораторных исследований</h2>
        <br>
        <h2>Переходный этап.</h2>
        <h3 id="no_hem" style="text-decoration: underline;">Отсутствие надежного гемостаза и продолжающееся кровотечение</h3>
        <br>
        <h2>Второй этап (хирургический).</h2>
        <h3 id="surgeon" style="text-decoration: underline;" >Лапаротомия, ангихирургия (при кровопотере более 1000 мл и продолжающемся кровотечении)</h3>
        <div id="surgeon_area" style="display: none">
            <h5>1. Утеротоническая терапия</h5>
            <h5>2. Продолжение ИТТ с учетом клинико-лабораторных данных, коррекция нарушений свертывания крови</h5>
            <h5>3. Компрессия матки (шов по В-Линчу). Перевязка маточныхсосудов по О`Лири</h5>
            <h5>4. Перевязка маточных или внутренних подвздошных артеий (или эмболизация маточных</h5>
            <h5>5. Гистерэктомия</h5>
        </div>
     </div>
  </section>

  <script>
      $(document).ready(function(){
          $("h1").css("color", "blue");
          $('#select_lang').trigger('change');
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