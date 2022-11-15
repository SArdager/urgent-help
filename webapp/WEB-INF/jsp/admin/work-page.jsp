<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Admin working page</title>
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
            <a style="margin-right: 20px; " href="work-start">Вернуться</a>
        </div>
        <h2><div id="result_line"></div></h2>
        <h2 id="begin_title">Редактирование начальных действий</h2>
        <div id="begin_area" style="display: none">
            <div id="hide_begin_area" class="cut_line">Скрыть поле редактирования</div>
            <h4 id="first_title">1. Первоначальные действия</h4>
            <div id="first_area" style="display: none">
                <div id="hide_first_area" class="cut_line">Скрыть поле первоначальных действий</div>
                <textarea id="text1"></textarea>
                <button id="btn_first_text" style="margin-left: 100px">Сохранить</button>
            </div>
            <h4 id="second_title">2. Манипуляции</h4>
            <div id="second_area" style="display: none">
                <div id="hide_second_area" class="cut_line">Скрыть поле манипуляций</div>
                <textarea id="text2"></textarea>
                <button id="btn_second_text" style="margin-left: 100px">Сохранить</button>
            </div>
            <h4 id="third_title">3. Назначение анализов</h4>
            <div id="third_area" style="display: none">
                <div id="hide_third_area" class="cut_line">Скрыть поле назначения анализов</div>
                <textarea id="text3"></textarea>
                <button id="btn_third_text" style="margin-left: 100px">Сохранить</button>
            </div>
            <h4 id="forth_title">4. Тромбоэластография</h4>
            <div id="forth_area" style="display: none">
                <div id="hide_forth_area" class="cut_line">Скрыть поле тромбоэластографии</div>
                <textarea id="text4"></textarea>
                <button id="btn_forth_text" style="margin-left: 100px">Сохранить</button>
            </div>
            <h4 id="fifth_title">5. Установление причины (4Т)</h4>
            <div id="fifth_area" style="display: none">
                <div id="hide_fifth_area" class="cut_line">Скрыть поле установления причины</div>
                <textarea id="text5"></textarea>
                <button id="btn_fifth_text" style="margin-left: 100px">Сохранить</button>
            </div>
            <h4 id="sixth_title">6. Продолжить инфузионно-трансфузионную терапию (ИТТ)</h4>
            <div id="sixth_area" style="display: none">
                <div id="hide_sixth_area" class="cut_line">Скрыть поле инфуззионно-трансфузионную терапии</div>
                <textarea id="text6"></textarea>
                <button id="btn_sixth_text" style="margin-left: 100px">Сохранить</button>
            </div>
        </div>
        <h2 id="no_hem">Редактирование переходного этапа</h2>
        <div id="care_area" style="display: none">
            <div id="hide_care_area" class="cut_line">Скрыть поле редактирования</div>
            <h4 >Баллонная тампонада матки</h4>
            <h4 id="first_c_title">Утеротоническая терапия</h4>
            <div id="first_c_area" style="display: none">
                <div id="hide_first_c_area" class="cut_line">Скрыть поле утеротонической терапии</div>
                <textarea id="text11"></textarea>
                <button id="btn_first_c_text" style="margin-left: 100px">Сохранить</button>
            </div>
            <h4 id="second_c_title">Продолжение ИТТ</h4>
            <div id="second_c_area" style="display: none">
                <div id="hide_second_c_area" class="cut_line">Скрыть поле продолжения ИТТ</div>
                <textarea id="text12"></textarea>
                <button id="btn_second_c_text" style="margin-left: 100px">Сохранить</button>
                <br>
                <h4 id="fifth_c_title">Редактирование ДВС синдром</h4>
                <div id="fifth_c_area" style="display: none">
                    <div id="hide_fifth_c_area" class="cut_line">Скрыть поле ДВС синдром</div>
                    <textarea id="text15"></textarea>
                    <button id="btn_fifth_c_text" style="margin-left: 100px">Сохранить</button>
                    <hr>
                    <h4 id="sixth_c_title">Гиперфибринолитический</h4>
                    <div id="sixth_c_area" style="display: none">
                        <div id="hide_sixth_c_area" class="cut_line">Скрыть поле гиперфибринолитического вида</div>
                        <textarea id="text16"></textarea>
                        <button id="btn_sixth_c_text" style="margin-left: 100px">Сохранить</button>
                        <p>Текст после ссылки на лабораторные анализы</p>
                        <textarea id="text17"></textarea>
                        <button id="btn_seven_c_text" style="margin-left: 100px">Сохранить</button>
                        <hr>
                    </div>
                    <h4 id="eight_c_title">Прокоагулянтный (тромботический)</h4>
                    <div id="eight_c_area" style="display: none">
                        <div id="hide_eight_c_area" class="cut_line">Скрыть поле прокоагулянтного вида</div>
                        <textarea id="text18"></textarea>
                        <button id="btn_eight_c_text" style="margin-left: 100px">Сохранить</button>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
     </div>
  </section>

  <script>
      $(document).ready(function(){
          $("h1").css("color", "blue");
          $('#select_lang').trigger("change");
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