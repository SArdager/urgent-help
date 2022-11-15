$(document).ready(function(){

    $('#select_lang').on('change', function(){
        if($('#select_lang').val()==="KZ"){
            $('#lang').val("KZ");
            document.getElementById("kz_text").style.display = "block";
            document.getElementById("ru_text").style.display = "none";
            document.getElementById("kz_phone").style.display = "table-cell";
            document.getElementById("ru_phone").style.display = "none";
            document.getElementById("kz_password").style.display = "table-cell";
            document.getElementById("ru_password").style.display = "none";
            document.getElementById("reg").href = "reg-kz";
            document.querySelector("#reg").innerHTML = "Тіркелу";
            document.querySelector("#btn_title").innerHTML = "Кіру";
            document.querySelector("#forget_password").innerHTML = "Құпия сөзді ұмыттыңыз ба?";
            document.querySelector("#text_forget").innerHTML = "<b>Пароль қалпына келтірілмейді!</b> " +
                "<br>Егер сіз парольді ұмытып қалсаңыз, оны болдырмау керек. <br>" +
                "Уақытша пароль сіздің электрондық пошта мекенжайыңызға жіберіледі.<br>" +
                "Арқылы авторизациядан кейін сіз уақытша парольді өзгертуге болады.";
            document.querySelector("#btn_forget_password").innerHTML = "Пароль болдырмау";
        } else {
            $('#lang').val("RU");
            document.getElementById("kz_text").style.display = "none";
            document.getElementById("ru_text").style.display = "block";
            document.getElementById("kz_phone").style.display = "none";
            document.getElementById("ru_phone").style.display = "table-cell";
            document.getElementById("kz_password").style.display = "none";
            document.getElementById("ru_password").style.display = "table-cell";
            document.getElementById("reg").href = "reg-ru";
            document.querySelector("#reg").innerHTML = "Зарегистрироваться";
            document.querySelector("#btn_title").innerHTML = "Войти";
            document.querySelector("#forget_password").innerHTML = "Забыли пароль?";
            document.querySelector("#text_forget").innerHTML = "<b>Пароль не восстанавливается!</b> " +
                "<br>Если Вы забыли пароль, то потребуется его сбросить. <br>" +
                "Временный пароль будет отправлен на Ваш адрес электронной почты.<br>" +
                "После авторизации по временному паролю Вы можете поменять пароль.";
            document.querySelector("#btn_forget_password").innerHTML = "Сбросить пароль";
        }
    });

    var forgetNumber = 0;
    var show_forget = document.getElementById("show_forget");
    $('#forget_password').on('click', function(){
        if(forgetNumber==0){
            show_forget.style.display = "block";
            forgetNumber++;
        } else{
            show_forget.style.display = "none";
            forgetNumber=0;
        }
    });

    $('#btn_forget_password').on('click', function(){
        var phone = $('#phone').val();
        if(phone.length>0){
            $('#result_line').html("Направлен запрос на сброс пароля. Ожидайте ответа.");
            $.ajax({
                url: 'forget-password',
                method: 'POST',
                dataType: 'text',
                data: {username: phone},
                success: function(message) {
                    if(message.length>0){
                        $('#result_line').html(message);
                    }
                },
                error:  function(response) {
                }
            });
        } else {
           $('#result_line').html("Введите логин");
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

});
