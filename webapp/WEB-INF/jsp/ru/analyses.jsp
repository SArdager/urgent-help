<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Analyses ru-page</title>
  <script type="text/javascript" src="../resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="../resources/js/ruAnalyseMaker.js"></script>
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
        <h1>"Послеродовые кровотечения"</h1>
        <div class="user_title">
            <a style="margin-right: 20px; " href="work-page">Вернуться</a>
        </div>
        <h2 id="scrollTo">Действия по результатам лабораторных исследований</h2>
        <h2><div id="result_line"></div></h2>
        <h3>Внесите клинико-лабораторные данные</h3>
        <table>
            <tr>
                <td class="table_wide_title">Кровопотеря, мл</td>
                <td><input type="number" id="blade" autofocus="true"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Тромбоциты, тыс/мкл</td>
                <td><input type="number" id="tro" autofocus="true"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Фибриноген, г/литр</td>
                <td><input type="number" id="fib" step="0.1" autofocus="true"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Гемоглобин, х10<sup>12</sup>/литр</td>
                <td><input type="number" id="hb" autofocus="true"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Гематокрит, %</td>
                <td><input type="number" id="ht" autofocus="true"/></td>
            </tr>
             <tr>
                 <td class="table_wide_title">ПВ, секунд</td>
                 <td><input type="number" id="pv" autofocus="true"/></td>
             </tr>
            <tr>
                <td class="table_wide_title">МНО</td>
                <td><input type="number" id="mno" step="0.1" autofocus="true"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">АЧТВ, секунд</td>
                <td><input type="number" id="atv" autofocus="true"/></td>
            </tr>
        </table>
        <p style="font-size: 0.6em;">При продолжении массового кровотечения и сохранении коагулопатии рассмотреть применение рекомбинантного активированного фактора коагуляции VII(rFVIIa)</p>
        <br>
        <h4>Примечание</h4>
        <p style="font-size: 0.6em;">Референтные значения показателей взяты согласно методик, применяемых лабораториями сети "КДЛ ОЛИМП"</p>
        <div id="main_display">
            <div id="display_body">
                <div id="display">
                    <h6 id="display_text"></h6>
                    <input type="button" id="close_display" value="Закрыть окно" />
                </div>
            </div>
        </div>
  </section>

  <script>
      $(document).ready(function(){
          $("h1").css("color", "blue");
          var resultLineValue;
          var clickNumber = 0;
          var textLength = 0;
          var currentLength = 0;
          window.addEventListener("click", function(){
              resultLineValue = $('#result_line').text();
              textLength = resultLineValue.length;
              if(textLength>0){
                  if(clickNumber == 0){
                      currentLength = textLength;
                  }
                  if(textLength == currentLength){
                      if(clickNumber<2){
                         clickNumber++;
                      } else {
                         $('#result_line').text("");
                         clickNumber = 0;
                     }
                  } else {
                      clickNumber = 0;
                  }
              }
          });
     });
  </script>

    <div class="buffer" style = "height: 5em;"></div>
</body>
</html>