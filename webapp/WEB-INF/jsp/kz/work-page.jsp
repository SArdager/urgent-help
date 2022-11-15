<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Working kz-page</title>
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
            <strong style="margin-top: 4px; margin-right: 20px">Пайдаланушы: ${user.userFirstname} ${user.userSurname}</strong>
            <select id="select_lang" style="margin-right: 20px; width: 8em">
                <option value="KZ">қазақ</option>
                <option value="RU">русский</option>
            </select>
            <a style="margin-right: 20px; margin-top: 4px;" href="../logout">Шығу</a>
        </div>
        <hr>
            <h1 style="margin-left: 20px">АНЫҚТАМАЛЫҚ ПОРТАЛ</h1>
            <h1>"Босанғаннан кейінгі қан жоғалту"</h1>
        <div class="user_title">
            <a style="margin-right: 20px; " href="change-password">Пароль өзгерту</a>
        </div>
        <h2 id="actions" style="text-decoration: underline">Қан кетуді тоқтатумен және емдеумен бір мезгілде орындау</h2>
        <div id="actions_area" style="display: none">
                <div id="hide_actions_area" class="cut_line">Скрыть поле начальных действий</div>
            <h4 id="first_title">1. Бастапқы әрекеттер</h4>
            <div id="first_area" style="display: none">
                <div id="hide_first_area" class="cut_line">Бастапқы әрекеттер өрісін жасыру</div>
                <p id="text1"></p>
            </div>
            <h4 id="second_title">2. Манипуляциялар</h4>
            <div id="second_area" style="display: none">
                <div id="hide_second_area" class="cut_line">Манипуляция өрісін жасыру</div>
                <p id="text2"></p>
            </div>
            <h4 id="third_title">3. Талдаудың мақсаты</h4>
            <div id="third_area" style="display: none">
                <div id="hide_third_area" class="cut_line">Талдау тағайындау өрісін жасыру</div>
                <p id="text3"></p>
            </div>
            <h4 id="forth_title">4. Тромбоэластография</h4>
            <div id="forth_area" style="display: none">
                <div id="hide_forth_area" class="cut_line">Тромбоэластография  өрісін жасыру</div>
                <p id="text4"></p>
            </div>
            <h4 id="fifth_title">5. Себебін анықтау (4Т)</h4>
            <div id="fifth_area" style="display: none">
                <div id="hide_fifth_area" class="cut_line">Себебін анықтау өрісін жасыру</div>
                <p id="text5"></p>
            </div>
            <h4 id="sixth_title">6. Инфузиялық-трансфузиялық терапияны жалғастыру (ИТТ)</h4>
            <div id="sixth_area" style="display: none">
                <div id="hide_sixth_area" class="cut_line">Инфузиялық-трансфузиялық терапия өрісін жасыру</div>
                <p id="text6"></p>
            </div>
            <input type="hidden" id="lang" value="KZ" />
        </div>
        <h2 id="analyses" style="text-decoration: underline">Зертханалық зерттеулер нәтижелері бойынша іс-әрекеттер</h2>
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