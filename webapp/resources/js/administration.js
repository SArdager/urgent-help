$(document).ready(function(){
    $('#begin_title').on("click", function(){document.getElementById('begin_area').style.display = "block";});
    $('#first_title').on("click", function(){document.getElementById('first_area').style.display = "block";});
    $('#second_title').on("click", function(){document.getElementById('second_area').style.display = "block";});
    $('#third_title').on("click", function(){document.getElementById('third_area').style.display = "block";});
    $('#forth_title').on("click", function(){document.getElementById('forth_area').style.display = "block";});
    $('#fifth_title').on("click", function(){document.getElementById('fifth_area').style.display = "block";});
    $('#sixth_title').on("click", function(){document.getElementById('sixth_area').style.display = "block";});
    $('#seven_title').on("click", function(){document.getElementById('seven_area').style.display = "block";});
    $('#eight_title').on("click", function(){document.getElementById('eight_area').style.display = "block";});
    $('#no_hem').on("click", function(){document.getElementById('care_area').style.display = "block";});

    $('#first_c_title').on("click", function(){document.getElementById('first_c_area').style.display = "block";});
    $('#second_c_title').on("click", function(){document.getElementById('second_c_area').style.display = "block";});

    $('#fifth_c_title').on("click", function(){document.getElementById('fifth_c_area').style.display = "block";});
    $('#sixth_c_title').on("click", function(){document.getElementById('sixth_c_area').style.display = "block";});
    $('#eight_c_title').on("click", function(){document.getElementById('eight_c_area').style.display = "block";});

    $('#hide_begin_area').on("click", function(){document.getElementById('begin_area').style.display = "none";});
    $('#hide_first_area').on("click", function(){document.getElementById('first_area').style.display = "none";});
    $('#hide_second_area').on("click", function(){document.getElementById('second_area').style.display = "none";});
    $('#hide_third_area').on("click", function(){document.getElementById('third_area').style.display = "none";});
    $('#hide_forth_area').on("click", function(){document.getElementById('forth_area').style.display = "none";});
    $('#hide_fifth_area').on("click", function(){document.getElementById('fifth_area').style.display = "none";});
    $('#hide_sixth_area').on("click", function(){document.getElementById('sixth_area').style.display = "none";});
    $('#hide_seven_area').on("click", function(){document.getElementById('seven_area').style.display = "none";});
    $('#hide_eight_area').on("click", function(){document.getElementById('eight_area').style.display = "none";});
    $('#hide_care_area').on("click", function(){document.getElementById('care_area').style.display = "none";});
    $('#hide_dbc_area').on("click", function(){document.getElementById('dbc_area').style.display = "none";});
    $('#hide_first_c_area').on("click", function(){document.getElementById('first_c_area').style.display = "none";});
    $('#hide_second_c_area').on("click", function(){document.getElementById('second_c_area').style.display = "none";});

    $('#hide_fifth_c_area').on("click", function(){document.getElementById('fifth_c_area').style.display = "none";});
    $('#hide_sixth_c_area').on("click", function(){document.getElementById('sixth_c_area').style.display = "none";});
    $('#hide_eight_c_area').on("click", function(){document.getElementById('eight_c_area').style.display = "none";});

    $('#select_lang').on('change', function(){
        $.ajax({
            url: '../article/get-texts',
            method: 'POST',
            dataType: 'json',
            data: {lang: $('#select_lang').val()},
            success: function(articles) {
                for(let i=1; i<19; i++){
                    var placeName = "text" + i;
                    var place = document.getElementById(placeName);
                    if(place){
                        var text = "";
                        for(let j=0; j<articles.length; j++){
                            if(articles[j].placeName === placeName){
                                text = articles[j].text;
                            }
                        }
                        if(text.length>0){
                            place.value = text;
                        }
                    }
                }
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
        });
    });

    $('#btn_first_text').on('click', function(){
        let text = $('#text1').val();
        let textNumber = "1";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_second_text').on('click', function(){
        let text = $('#text2').val();
        let textNumber = "2";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_third_text').on('click', function(){
        let text = $('#text3').val();
        let textNumber = "3";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_forth_text').on('click', function(){
        let text = $('#text4').val();
        let textNumber = "4";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_fifth_text').on('click', function(){
        let text = $('#text5').val();
        let textNumber = "5";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_sixth_text').on('click', function(){
        let text = $('#text6').val();
        let textNumber = "6";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_seven_text').on('click', function(){
        let text = $('#text7').val();
        let textNumber = "7";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_eight_text').on('click', function(){
        let text = $('#text8').val();
        let textNumber = "8";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_nine_text').on('click', function(){
        let text = $('#text9').val();
        let textNumber = "9";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_ten_text').on('click', function(){
        let text = $('#text10').val();
        let textNumber = "10";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_first_c_text').on('click', function(){
        let text = $('#text11').val();
        let textNumber = "11";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_second_c_text').on('click', function(){
        let text = $('#text12').val();
        let textNumber = "12";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_fifth_c_text').on('click', function(){
        let text = $('#text15').val();
        let textNumber = "15";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_sixth_c_text').on('click', function(){
        let text = $('#text16').val();
        let textNumber = "16";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_seven_c_text').on('click', function(){
        let text = $('#text17').val();
        let textNumber = "17";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });

    $('#btn_eight_c_text').on('click', function(){
        let text = $('#text18').val();
        let textNumber = "18";
        let lang = $('#select_lang').val();
        saveText(textNumber, text, lang);
    });


    function saveText(textNumber, text, lang){
        if(text.length>1){
            $.ajax({
                url: '../article/change-text',
                method: 'POST',
                dataType: 'text',
                data: {text: text, placeNumber: textNumber, lang: lang},
                success: function(message) {
                    $('#result_line').html(message);
                },
                error:  function(response) {
                    $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
                }
            });
        } else {
            $('#result_line').html("Внесите текст!!!");
        }
    }

    $('#select_region').on('change', function(){
        var btn_add = document.getElementById("btn_add");
        var btn_del = document.getElementById("btn_del");
        if($('#select_region').val()>0){
            btn_add.value = "Изменить";
            btn_del.type = "button";
            $.ajax({
                url: 'edit-regions/get-names',
                method: 'POST',
                dataType: 'json',
                data: {regionId: $('#select_region').val()},
                success: function(region) {
                    $('#regionName').val(region.regionName);
                    $('#regionKzName').val(region.regionKzName);
                },
                error:  function(response) {
                    $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
                }
            });
        } else{
            $('#regionName').val("");
            $('#regionKzName').val("");
            btn_add.value = "Создать";
            btn_del.type = "hidden";
        }
    });

    $('#btn_add').on('click', function(){
        var regionName = $('#regionName').val();
        var regionKzName = $('#regionKzName').val();
        if(regionName.length>1 && regionKzName.length>1){
            $.ajax({
                url: 'edit-regions/add-region',
                method: 'POST',
                dataType: 'text',
                data: {id: $('#select_region').val(), regionName: regionName, regionKzName: regionKzName},
                success: function(message) {
                    $('#result_line').html(message);
                },
                error:  function(response) {
                    $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
                }
            });
        } else {
            $('#result_line').html("Напишите названия нового региона");
        }
    });

    $('#btn_del').on('click', function(){
        $.ajax({
            url: 'edit-regions/del-region',
            method: 'POST',
            dataType: 'text',
            data: {id: $('#select_region').val()},
            success: function(message) {
                $('#result_line').html(message);
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
        });
    });

    var show_select = document.getElementById("show_select");
    var user_name = document.getElementById("user_name");

    user_name.oninput = function(){
        if($('#user_name').val()!=null){
            var textValue = $('#user_name').val().trim();
            $('#user_id').val(0);
            if(textValue.length>2){
                $('#user_name').readOnly = true;
                $.ajax({
                    url : 'edit-rights/search-user',
                    method: 'POST',
                    dataType: 'json',
                    data : {text: textValue},
                    success : function(users) {
                        $('#select_user').empty();
                        show_select.style.display = "block";
                        $.each(users, function(key, user){
                            $('#select_user').append('<option value="' + user.id + '">' +
                                user.userSurname + ' ' + user.userFirstname + '; ' +
                                user.username + ' - ' + user.role +'</option');
                        });
                        $('#user_name').readOnly = false;
                    },
                    error:  function(response) {
                        $('#user_name').readOnly = false;
                    }
                });
            } else {
                show_select.style.display = "none";
                $('#user_name').readOnly = false;
            }
        }
    };

    $('#select_user').on('change', function(){
        var userId = $('#select_user').val();
        var userFullName = $('#select_user option:selected').text();
        let posName = userFullName.indexOf(";");
        let posRole = userFullName.indexOf("-");
        var userName = userFullName.substring(0, posName);
        var login = userFullName.substring(posName+1, posRole).trim();
        var role = userFullName.substring(posRole+1).trim();
        $('#user_id').val(userId);
        $('#user_name').val(userName);
        $('#username').val(login);
        show_select.style.display = "none";
        if(role.indexOf("ADMIN")<0){
            document.getElementById("userRole").checked = true;
            document.getElementById("adminRole").checked = false;
        } else {
            document.getElementById("userRole").checked = false;
            document.getElementById("adminRole").checked = true;
        }
    });

    $('#userRole').change ( function(){
        if($('#userRole').is(':checked')==true){
            document.getElementById("adminRole").checked = false;
        } else {
            document.getElementById("adminRole").checked = true;
        }
    });

    $('#adminRole').change ( function(){
        if($('#adminRole').is(':checked')==true){
            document.getElementById("userRole").checked = false;
        } else {
            document.getElementById("userRole").checked = true;
        }
    });

    $('#btn_role').on('click', function(){
        var role;
        if($('#adminRole').is(':checked')==true){
            role = "ADMIN";
        } else {
            role = "USER";
        }
        $.ajax({
            url: 'edit-rights/change-rights',
            method: 'POST',
            dataType: 'text',
            data: {id: $('#user_id').val(), role: role},
            success: function(message) {
                $('#result_line').html(message);
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
        });
    });

});
