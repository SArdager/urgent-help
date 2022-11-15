<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Analyses kz-page</title>
  <script type="text/javascript" src="../resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="../resources/js/kzAnalyseMaker.js"></script>
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
        <div class="user_title">
            <a style="margin-right: 20px; " href="work-page">Қайту</a>
        </div>
        <h1>"Босанғаннан кейінгі қан жоғалту"</h1>
        <h2>Зертханалық зерттеулер нәтижелері бойынша іс-әрекеттер</h2>
        <h2><div id="result_line"></div></h2>
        <h3>Отметьте выполняемые мероприятия</h3>
        <table>
            <tr>
                <td class="table_wide_title">Проведение СЗП</td>
                <td><input type="checkbox" id="made_szp_checkbox" /></td>
            </tr>
            <tr>
                <td class="table_wide_title">Введение криопреципитата</td>
                <td><input type="checkbox" id="made_cri_checkbox" /></td>
            </tr>
            <tr>
                <td class="table_wide_title">Введение тромбоцитарного концентрата</td>
                <td><input type="checkbox" id="made_tro_checkbox" /></td>
            </tr>
            <tr>
                <td class="table_wide_title">Введение эритроцитарной массы</td>
                <td><input type="checkbox" id="made_erm_checkbox" /></td>
            </tr>
        </table>
        <table>
            <tr>
                <td class="table_wide_title">Тромбоциты, тыс/мкл</td>
                <td><input type="number" id="tro" value="0"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Фибриноген, г/литр</td>
                <td><input type="number" id="fib" step="0.1" value="0.0"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Гемоглобин, 10<sup>12</sup>/литр</td>
                <td><input type="number" id="hb" value="0"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Гематокрит, %</td>
                <td><input type="number" id="ht" value="0"/></td>
            </tr>
            <tr>
                 <td class="table_wide_title">ПВ, секунд</td>
                 <td><input type="number" id="pv" value="0"/></td>
             </tr>
            <tr>
                <td class="table_wide_title">МНО</td>
                <td><input type="number" id="mno" step="0.1" value="0.0"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">АЧТВ, секунд</td>
                <td><input type="number" id="atv" value="0"/></td>
            </tr>
            <tr>
                <td class="table_wide_title">Кровопотеря, мл</td>
                <td><input type="number" id="blade" value="0"/></td>
            </tr>
        </table>
        <span id="progress" style="text-decoration: underline">KZ Прогрессирование клинических и гемодинамических нарушений</span>
        <br>
        <span id="saving" style="text-decoration: underline">KZ Продолжение кровотечения при сохранении коагулопатии</span>
        <input type="hidden" id="lang" value="KZ" />
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