$(document).ready(function(){

    $('#select_lang').on('change', function(){
        if($('#select_lang').val() === "KZ"){
            document.location.href = "registration?lang=KZ";
        } else {
            document.location.href = "registration?lang=RU";
        }
    });

    var phoneInput = document.getElementById("phone");

    phoneInput.oninput = function(){
        let phoneValue = $('#phone').val();
        let countValue = $('#count').val();
        if(phoneValue.length > countValue){
           switch(phoneValue.length) {
                case 1:
                    if(phoneValue!="("){
                        $('#phone').val("(" + phoneValue);
                    }
                    break;
                case 4:
                    $('#phone').val(phoneValue + ")-");
                    break;
                case 5:
                    if(getLastSymbol(phoneValue)!=")"){
                        $('#phone').val(getWithoutLast(phoneValue) + ")-" + getLastSymbol(phoneValue));
                    }
                    break;
                case 6:
                    if(getLastSymbol(phoneValue)!="-"){
                        $('#phone').val(getWithoutLast(phoneValue) + "-" + getLastSymbol(phoneValue));
                    }
                    break;
                case 9:
                    $('#phone').val(phoneValue + "-");
                    break;
                case 10:
                    if(getLastSymbol(phoneValue)!="-"){
                        $('#phone').val(getWithoutLast(phoneValue) + "-" + getLastSymbol(phoneValue));
                    }
                    break;
                case 12:
                    $('#phone').val(phoneValue + "-");
                    break;
                case 13:
                    if(getLastSymbol(phoneValue)!="-"){
                        $('#phone').val(getWithoutLast(phoneValue) + "-" + getLastSymbol(phoneValue));
                    }
                    break;
                default:    break;
            }
        }
        $('#count').val(phoneValue.length);
    };

    function getLastSymbol(phoneValue){
        let lastSymbol = phoneValue.substring(phoneValue.length-1);
        return lastSymbol;
    }
    function getWithoutLast(phoneValue){
        let withoutLastSymbol = phoneValue.substring(0, phoneValue.length-1);
        return withoutLastSymbol;
    }

    $('#select_region').on('change', function(){
        $('#regionName').val($('#select_region option:selected').text());
    });

    $('#add_user').on('click', function(){
        var lang = $('#lang').val();
        var surname = $('#surname').val();
        var firstname = $('#firstname').val();
        var phone = $('#phone').val();
        var email = $('#email').val();
        var position = $('#position').val();
        var workPlace = $('#workPlace').val();
        var regionName = $('#regionName').val();
        var password = $('#password').val();
        var confirm_password = $('#confirm_password').val();

        function checkInput(){
            if(lang==="RU"){
                if(phone.length==15){
                    if(email.length>6 && email.indexOf("@")>0){
                        if(position.length > 4 && workPlace.length > 2 && firstname.length > 2 && surname.length > 2){
                            if(regionName.length>0){
                                return true;
                            } else {
                                $('#result_line').html("Выберите регион и повторите ввод");
                                return false;
                            }
                        } else {
                            $('#result_line').html("Проверьте правильность заполнения полей формы.");
                            return false;
                        }
                    } else {
                        $('#result_line').html("Проверьте правильность заполнения поля электронной почты. \n Если Вы забудете пароль, то не сможете его восстановить.");
                        return false;
                    }
                } else {
                    $('#result_line').html("Проверьте правильность заполнения поля номера телефона.\n Номер телефона будет использоваться в качестве логина");
                    return false;
                }
            } else {
                if(phone.length==15){
                console.log("phone: " + phone);
                    if(email.length>6 && email.indexOf("@")>0){
                        if(position.length > 4 && workPlace.length > 2 && firstname.length > 2 && surname.length > 2){
                            if(regionName.length>0){
                                return true;
                            } else {
                                $('#result_line').html("KZ Выберите регион и повторите ввод");
                                return false;
                            }
                        } else {
                            $('#result_line').html("KZ Проверьте правильность заполнения полей формы.");
                            return false;
                        }
                    } else {
                        $('#result_line').html("KZ Проверьте правильность заполнения поля электронной почты. \nЕсли Вы забудете пароль, то не сможете его восстановить.");
                        return false;
                    }
                } else {
                    $('#result_line').html("KZ Проверьте правильность заполнения поля номера телефона.\n Номер телефона будет использоваться в качестве логина");
                    return false;
                }
            }
        }

        function checkPassword(){
            if(lang==="RU"){
                if(password.length > 5){
                    if(password === confirm_password){
                        return true;
                    } else{
                        $('#result_line').html("Значения пароля НЕ СОВПАДАЮТ !!! Повторите ввод.");
                        return false;
                    }
                } else {
                    $('#result_line').html("Пароль должен содержать от 6 символов и более.");
                    return false;
                }
            } else {
                if(password.length > 5){
                    if(password === confirm_password){
                        return true;
                    } else{
                        $('#result_line').html("Значения пароля НЕ СОВПАДАЮТ !!! Повторите ввод.");
                        return false;
                    }
                } else {
                    $('#result_line').html("Пароль должен содержать от 6 символов и более.");
                    return false;
                }
            }
        }

        if(checkInput() && checkPassword()){
            $.ajax({
                url : 'registration/checkLogin',
                method: 'POST',
                dataType: 'text',
                data : {username: $('#phone').val()},
                success : function(message) {
                    if(message.indexOf("exist")>0){
                        if(lang==="RU"){
                            $('#result_line').html("Вы уже зарегистрированы на портале. \nНажмите на надпись `Забыли пароль?`на странице авторизации.");
                        } else {
                            $('#result_line').html("KZ Вы уже зарегистрированы на портале. \nНажмите на надпись `Забыли пароль?`на странице авторизации.");
                        }
                    } else {
                        $('#registration').submit();
                    }
                },
                error:  function(response) {
                    $('#result_line').html(response);
                }
            });
        }
    });

});
