$(document).ready(function(){

    $('#select_lang').on('change', function(){
        if($('#select_lang').val() === "KZ"){
            document.location.href = "../kz/change-password";
        } else {
            document.location.href = "../ru/change-password";
        }
    });

    $('#btn_change_password').on('click', function(){
        var strPassword = $('#password').val();
        var confirmPassword = $('#confirm_password').val();
        var m_strUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var m_strLowerCase = "abcdefghijklmnopqrstuvwxyz";
        var m_strNumber = "0123456789";
        var m_strSymbol = "@#$%&-+*";
        var role = $('#role').val();
        var lang = $('#lang').val();
        var shotPassword;
        var differentPassword;
        var startRequest;
        var shotAdminPassword;
        if(lang === "RU"){
            shotPassword = "Пароль должен содержать от 4 символов и более.";
            differentPassword = "Значения пароля НЕ СОВПАДАЮТ !!! Повторите ввод.";
            startRequest = "Отправлен запрос на сброс пароля. Ожидайте.";
        } else {
            shotPassword = "Парольде 4 немесе одан көп таңба болуы керек.";
            differentPassword = "Пароль мәндері сәйкес келмейді !!! Енгізуді қайталаңыз.";
            startRequest = "Парольді қалпына келтіру туралы сұраныс жіберілді. Күте тұрыңыз.";
        }
        if(role === "USER"){
            if(strPassword.length > 3){
                if(strPassword === confirmPassword){
                   $('#result_line').html(startRequest);
                   changePassword();
                } else {
                   $('#result_line').html(differentPassword);
                }
            } else {
                $('#result_line').html(shotPassword);
            }
        } else {
            if(checkPassword(strPassword)){
                $('#result_line').html("Отправлен запрос на сброс пароля. Ожидайте.");
                changePassword();
            }
        }

        function checkPassword(strPassword){
           if(strPassword.length > 5){
               if(checkContain(strPassword, m_strLowerCase)>0){
                   if(checkContain(strPassword, m_strUpperCase)>0){
                       if(checkContain(strPassword, m_strNumber)>0){
                            if(checkContain(strPassword, m_strSymbol)>0){
                                return true;
                            } else {
                                $('#result_line').html("Пароль должен содержать спецсимвол @#$%&-+*");
                                return false;
                            }
                       } else {
                       $('#result_line').html("Пароль должен содержать цифру.");
                       return false;
                       }
                   } else {
                       $('#result_line').html("Пароль должен содержать латинскую букву в верхнем регистре.");
                       return false;
                   }
               } else {
               $('#result_line').html("Пароль должен содержать латинскую букву в нижнем регистре.");
               return false;
               }
            } else {
               $('#result_line').html("Пароль администратора должен содержать от 6 символов и более.");
               return false;
            }
        }

        function checkContain(strPassword, strCheck){
           var nCount = 0;
           for (i = 0; i < strPassword.length; i++)
           {
               if (strCheck.indexOf(strPassword.charAt(i)) > -1)
               {
                   nCount++;
               }
           }
           return nCount;
        }

    });

    $('#btn_forget_password').on('click', function(){
        var login = $('#login').val();
        if(login.length>0){
            $('#result_line').html("Направлен запрос на сброс пароля. Ожидайте ответа.");
            $.ajax({
                url: 'forget-password',
                method: 'POST',
                dataType: 'text',
                data: {username: login},
                success: function(message) {
                    if(message.length>0){
                        $('#result_line').html(message);
                    }
                },
                error:  function(response) {
                    $('#result_line').html(response);
                }
            });
        } else {
           $('#result_line').html("Введите логин");
        }
    });

    function changePassword(){
        $.ajax({
            url: '../change-password/change',
            method: 'POST',
            dataType: 'text',
            data: {password: $('#password').val()},
            success: function(message){
                $('#result_line').html(message);
            },
            error:  function(response) {
                $('#result_line').html(response);
            }
        });
    }
});
