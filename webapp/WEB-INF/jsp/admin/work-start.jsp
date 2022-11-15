<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Admin start page</title>
  <script type="text/javascript" src="../resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="../resources/js/administration.js"></script>
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
            <h1 style="margin-left: 20px">АДМИНИСТРИРОВАНИЕ СПРАВОЧНОГО ПОРТАЛА</h1>
            <h1>"Послеродовые кровотечения"</h1>
        <div class="user_title">
            <a style="margin-right: 20px; " href="../ru/change-password">Сменить пароль</a>
        </div>
        <h2>Выбор вида администрирования</h2>
        <h5><a href="../ru/work-page">Просмотр пользовательского интерфейса</a></h5>
        <h5><a href="work-page">Редактирование текста страниц</a></h5>
        <h5><a href="edit-regions">Редактирование регионов</a></h5>
        <h5><a href="edit-rights">Предоставление административных прав</a></h5>
        <h5><a href="report">Статистика</a></h5>
        <br>
     </div>
  </section>

  <script>
      $(document).ready(function(){
          $("h1").css("color", "blue");
     });
  </script>

    <div class="buffer" style = "height: 5em;"></div>
</body>
</html>