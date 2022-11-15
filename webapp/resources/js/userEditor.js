$(document).ready(function(){
    var show_select = document.getElementById("show_select");
    var user_name = document.getElementById("user_name");
    var show_table = document.getElementById("show_table");


    $('#select_user').on('click', function(){
        var userId = $('#select_user').val();
        var userFullName = $('#select_user option:selected').text();
        let posName = userFullName.indexOf(";");
        var userName = userFullName.substring(0, posName);
        var login = userFullName.substring(posName+1);
        $('#user_id').val(userId);
        $('#user_name').val(userName);
        show_select.style.display = "none";
        $.ajax({
            url : '../user/load-data/user',
            method: 'POST',
            dataType: 'json',
            data : {id: userId},
            success : function(user) {
                $('#surname').val(user.userSurname);
                $('#firstname').val(user.userFirstname);
                $('#position').val(user.position);
                $('#email').val(user.email);
                $('#username').val(user.username);
                if(user.isEnabled){
                    document.getElementById("isEnabled").checked = true;
                    $('#is_enabled').val("true");
                } else {
                    document.getElementById("isEnabled").checked = false;
                    $('#is_enabled').val("false");
                }
            },
            error:  function(response) {
                $('#user_name').readOnly = false;
            }
        });
    });

    $('#btn_reset').on('click', function(){
        var userId = $('#user_id').val();
        if(userId > 0){
            $.ajax({
                url: '../admin/reset-password',
                method: 'POST',
                dataType: 'text',
                data: {id: $('#user_id').val()},
                success: function(message) {
                    $('#result_line').html(message);
                    $('#user_name').val("");
                    $('#password').val("");
                },
                error:  function(response) {
                    alert("Ошибка обращения в базу данных. Повторите.");
                }
            });
        } else {
            $('#result_line').html("Выберите пользователя из списка");
        }
    });

    user_name.oninput = function(){
        var textValue = $('#user_name').val().trim();
        $('#user_id').val(0);
        if(textValue.length>2){
            $('#user_name').readOnly = true;
            $.ajax({
                url : '../admin/search-user',
                method: 'POST',
                dataType: 'json',
                data : {text: textValue},
                success : function(users) {
                    $('#select_user').empty();
                    show_select.style.display = "block";
                    $.each(users, function(key, user){
                        $('#select_user').append('<option value="' + user.id + '">' +
                            user.userSurname + ' ' + user.userFirstname + '; ' +
                            user.username + '</option');
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
                $('#surname').val("");
                $('#firstname').val("");
                $('#position').val("");
                $('#email').val("");
                $('#username').val("");
        }
    };

    $('#btn_edit_user').on('click', function(){
        var userId = $('#user_id').val();
        if(userId > 0){
            $.ajax({
                url: '../admin/edit-user',
                method: 'POST',
                dataType: 'text',
                data: {id: $('#user_id').val(), userSurname: $('#surname').val(),
                    userFirstname: $('#firstname').val(), position: $('#position').val(),
                    email: $('#email').val(), username: $('#username').val(),
                    isEnabled: $('#is_enabled').val()},
                success: function(message) {
                    $('#result_line').html(message);
                    $('#user_name').val("");
                    $('#password').val("");
                },
                error:  function(response) {
                    alert("Ошибка обращения в базу данных. Повторите.");
                }
            });
        } else {
            $('#result_line').html("Выберите пользователя из списка");
        }
    });

    var checkbox_enabled = document.getElementById("isEnabled");
    var checkbox_not_enabled = document.getElementById("isNotEnabled");
    $('#isEnabled').change ( function(){
        if($('#isEnabled').is(':checked')==true){
            checkbox_not_enabled.checked = false;
            $('#is_enabled').val("true");
        } else {
            $('#is_enabled').val("false");
            checkbox_not_enabled.checked = true;
        }
    });
    $('#isNotEnabled').change ( function(){
        if($('#isNotEnabled').is(':checked')==true){
            checkbox_enabled.checked = false;
            $('#is_enabled').val("false");
        } else {
            checkbox_enabled.checked = true;
            $('#is_enabled').val("true");
        }
    });

});
