<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>DBC ru-page</title>
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
            <select id="select_language" style="margin-right: 20px; width: 8em">
                <option value="RU">русский</option>
                <option value="KZ">қазақ</option>
            </select>
            <a style="margin-right: 20px; margin-top: 4px;" href="../logout">Выйти</a>
        </div>
        <hr>
        <h1 style="margin-left: 20px">СПРАВОЧНЫЙ ПОРТАЛ</h1>
        <h2>"Послеродовые кровотечения"</h2>
        <div class="user_title">
            <a style="margin-right: 20px; " href="work-page">Вернуться</a>
        </div>
        <h2>ДВС синдром</h2>
        <h3><div id="result_line"></div></h3>
        <p id="text15"></p>
        <hr>
        <h3 id="seven_title" style="text-decoration: underline;">Гиперфибринолитический</h3>
        <div id="seven_area" style="display: none">
            <p id="text16"></p>
            <h3 id="analyses" style="text-decoration: underline;">Контроль параметров крови</h3>
            <p id="text17"></p>
            <hr>
        </div>
        <h3 id="eight_title" style="text-decoration: underline;">Прокоагулянтный (тромботический)</h3>
        <div id="eight_area" style="display: none">
            <p id="text18"></p>
            <hr>
        </div>
        <input type="hidden" id="language" value="RU" />
     </div>
  </section>

  <script>
      $(document).ready(function(){
          $("h1").css("color", "blue");
          $('#select_language').trigger('change');
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