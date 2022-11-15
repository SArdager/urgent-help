<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Change password page</title>
  <script type="text/javascript" src="../resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="../resources/js/passwordChanger.js"></script>
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
            <a style="margin-right: 20px; margin-top: 4px;" href="../logout">Выход</a>
        </div>
        <hr>
        <h1 style="margin-left: 20px">СПРАВОЧНЫЙ ПОРТАЛ</h1>
        <h1>"Послеродовые кровотечения"</h1>
        <div class="user_title">
            <a style="margin-right: 20px; " id="linkToMain" href="work-page">На главную</a>
        </div>
        <h2>Смена пароля</h2>
        <h2><div id="result_line"></div></h2>
        <br>
        <table>
            <tr>
                <td class="table_title">Новый пароль</td>
                <td><input id="password" name="password" size="40" /></td>
            </tr>
            <tr>
                <td class="table_title">Повторите пароль</td>
                <td><input id="confirm_password" size="40" /></td>
            </tr>
            <tr>
                <td class="table_title"></td>
                <td>
                <br>
                <br>
                <input type="button" id="btn_change_password" value="Поменять" /></td>
            </tr>
        </table>
        <input type="hidden" id="role" value="" />
        <input type="hidden" id="lang" value="RU" />
     </div>
  </section>
    <script>
        $(document).ready(function(){
            $("h1").css("color", "blue");
            document.getElementById('role').value = "${user.role}";
            if($('#role').val() === "ADMIN"){
                document.getElementById("linkToMain").href = "../admin/work-start";
            }
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

</body>
</html>