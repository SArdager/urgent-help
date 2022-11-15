<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Admin start page</title>
  <script type="text/javascript" src="../resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="../resources/js/reporter.js"></script>
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
        <div class="user_title">
            <a style="margin-right: 20px; " href="work-start">Вернуться</a>
        </div>
        <h2><div id="result_line"></div></h2>
        <h2>Выбор отчета по порталу</h2>
        <h3 id="first_title">1. Количество посещений за период</h3>
        <h3 id="second_title">2. Количество посещений по регионам</h3>
        <h3 id="third_title">3. Информация по пользователю</h3>
        <div id="date_area" style="display: none">
            <form id="export_excel" action="" method="post">
                <div id="hide_all_areas" class="cut_line">Скрыть таблицу посещений</div>
                <div class="title_row" >
                    <span class="date_line">Вывести за период:</span>
                    <span class="date_line">c</span>
                    <input type="date" id="startDate" name="startDate"/>
                    <span class="date_line">по</span>
                    <input type="date" id="endDate" name="endDate"/>
                </div>
                <input type="hidden" id="totalVisits" value="0"/>
                <input type="hidden" id="regionId" name="regionId" value="0" />
                <input type="hidden" id="userId" name="userId" value="0" />
            </form>
        </div>
        <div id="first_area" style="display: none">
            <div class="title_row"">
                <span class="date_line">Вывести по региону:</span>
                <select id="select_region">
                    <option value=0>Все регионы</option>
                    <c:forEach var="region" items="${regions}">
                        <option value=${region.id}>${region.regionName}</option>
                    </c:forEach>
                </select>
                <div class="title_row" id="count_area" style="width: 40%; justify-content: right; display: block">
                    <span class="text_line" >по</span>
                    <input type="number" id="pageSize" min="2" value="20" style="width: 3em"/>
                    <span class="text_line">записей</span>
                </div>
            </div>
            <h4 id="visits">Таблица всех посещений за период</h4>
            <div id="visits_area" style="display: none">
                <div id="hide_visits_area" class="cut_line">Скрыть таблицу всех посещений</div>
                <div class="title_row" style="justify-content: space-between;">
                    <div class="title_row" style="width: 40%; justify-content: space-between; ">
                        <span id="reload_visits" class ="reload_line" style='font-weight: bold'>Обновить</span>
                        <span id="excel_visits" class ="reload_line" style='font-weight: bold'>Передать в Excel</span>
                    </div>
                    <div id="visits_pages_title"></div>
                </div>
                <div class = "scroll_table">
                    <table>
                        <thead>
                            <tr>
                              <th>Дата</th>
                              <th>Пользователь</th>
                              <th>Регион</th>
                              <th>Организация</th>
                              <th>Должность</th>
                            </tr>
                        </thead>
                        <tbody id = "visits_table_body">
                        </tbody>
                    </table>
                </div>
            </div>
            <h4 id="user_visits">Таблица количества посещений по регионам и пользователям</h4>
            <div id="user_visits_area" style="display: none">
                <div id="hide_users_area" class="cut_line">Скрыть таблицу количества посещений</div>
                <div class="title_row" style="width: 40%; justify-content: space-between; ">
                    <span id="reload_users" class ="reload_line" style='font-weight: bold'>Обновить</span>
                    <span id="excel_users" class ="reload_line" style='font-weight: bold'>Передать в Excel</span>
                </div>
                <div class = "scroll_table">
                    <table>
                        <thead>
                            <tr>
                              <th>Регион</th>
                              <th>Пользователь</th>
                              <th>Организация</th>
                              <th>Должность</th>
                              <th>Количество</th>
                            </tr>
                        </thead>
                        <tbody id = "users_visits_table_body">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="second_area" style="display: none">
            <h4>Таблица количества посещений по регионам</h4>
            <div class="title_row" style="width: 40%; justify-content: space-between; ">
                <span id="reload_regions" class ="reload_line" style='font-weight: bold'>Вывести</span>
                <span id="excel_regions" class ="reload_line" style='font-weight: bold'>Передать в Excel</span>
            </div>
            <div class = "scroll_table">
                <table>
                  <thead>
                    <tr>
                      <th>Регион</th>
                      <th>Количество</th>
                    </tr>
                  </thead>
                  <tbody id = "regions_table_body">
                  </tbody>
                </table>
            </div>
            <hr>
        </div>
        <div id="third_area" style="display: none">
            <div class="title_row"">
                <span class="date_line">Пользователь</span>
                <input type="text" id="user_name" size="40" placeholder="Первые три буквы фамилии" required/>
                <button id="btn_close" ></button>
                <div class="title_row" style="width: 40%; justify-content: right;">
                    <span class="text_line" >по</span>
                    <input type="number" id="userPageSize" min="2" value="20" style="width: 3em"/>
                    <span class="text_line">записей</span>
                </div>
            </div>
            <div class="title_row" id="show_select" style="display: none; ">
                <select id="select_user" style="margin-left: 114px;"></select>
            </div>
            <br>
            <h4>Таблица количества посещений по пользователю</h4>
            <div id="user_info_body"></div>
            <div id="load_line" style="display: none">
                <div class="title_row" id="load_line" style="justify-content: space-between;">
                    <div class="title_row" style="width: 40%; justify-content: space-between; ">
                        <span id="reload_user" class ="reload_line" style='font-weight: bold'>Вывести</span>
                        <span id="excel_user" class ="reload_line" style='font-weight: bold'>Передать в Excel</span>
                    </div>
                    <div id="user_pages_title"></div>
                </div>
            </div>
            <div class = "scroll_table">
                <table style="width: 200px; margin-left: 40px;">
                  <thead>
                    <tr >
                      <th>Дата посещения</th>
                    </tr>
                  </thead>
                  <tbody id = "user_table_body">
                  </tbody>
                </table>
            </div>
            <hr>
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