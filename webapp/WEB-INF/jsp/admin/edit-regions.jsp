<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Edit regions page</title>
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
        <h2>Редактирование регионов</h2>
        <h2><div id="result_line"></div></h2>
        <br>
        <table>
            <tr>
                <td class="table_title">Выбор региона</td>
                <td><select id="select_region" name="id" >
                        <option value=-1>Создать новый регион</option>
                        <c:forEach var="region" items="${regions}">
                            <option value=${region.id}>${region.regionName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="table_title">Название на русском</td>
                <td><input type="text" id="regionName" size="40" required/></td>
            </tr>
            <tr>
                <td class="table_title">Название на казахском</td>
                <td><input type="text" id="regionKzName" size="40" required/></td>
            </tr>
            <tr>
                <td class="table_title"></td>
                <td><br>
                    <div class="title_row">
                        <input type="button" class ="two_in_line" id="btn_add" style="margin: 0px;" value="Создать" />
                        <input type="hidden" class ="two_in_line" id="btn_del" value="Удалить" />
                    </div>
                </td>
            </tr>
        </table>
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