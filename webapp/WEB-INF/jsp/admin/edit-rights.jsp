<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Edit rights page</title>
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
            <a style="margin-right: 20px; margin-top: 4px;" href="../logout">Выйти</a>
        </div>
        <hr>
            <h1 style="margin-left: 20px">АДМИНИСТРИРОВАНИЕ СПРАВОЧНОГО ПОРТАЛА</h1>
            <h1>"Послеродовые кровотечения"</h1>
        <br>
        <a href="work-start">Вернуться</a>
        <br>
        <h2>Предоставление административных прав пользователю</h2>
        <h2><div id="result_line"></div></h2>
        <br>
        <div class="main_block">
           <div class="field">
               <label>Пользователь</label>
               <input type="text" id="user_name" size="40" placeholder="Первые три буквы фамилии" required/>
           </div>
           <input type="hidden" id="user_id" name="userId" value="0"/>
           <input type="hidden" id="username"  value=""/>
           <div class="field" id="show_select" style="display: none; ">
               <label style="color: blue;" >Кликните пользователя</label>
               <select id="select_user">
               </select>
           </div>
           <br>
           <u>Права доступа</u>
           <div class="field">
               <label>Права пользователя</label>
               <input type="checkbox" id="userRole" />
           </div>
           <div class="field">
               <label>Права администратора</label>
               <input type="checkbox" id="adminRole" />
           </div>
           <div class="field">
               <label></label>
               <button id="btn_role">Изменить права</button>
           </div>
        </div>
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