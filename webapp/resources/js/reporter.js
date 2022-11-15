$(document).ready(function(){

    $('#first_title').on("click", function(){
        document.getElementById('date_area').style.display = "block";
        document.getElementById('first_area').style.display = "block";
        document.getElementById('second_area').style.display = "none";
        document.getElementById('third_area').style.display = "none";
        document.getElementById('visits_area').style.display = "none";
        document.getElementById('user_visits_area').style.display = "none";
        $('#totalVisits').val(0);
    });
    $('#second_title').on("click", function(){
        document.getElementById('date_area').style.display = "block";
        document.getElementById('first_area').style.display = "none";
        document.getElementById('second_area').style.display = "block";
        document.getElementById('third_area').style.display = "none";
    });
    $('#third_title').on("click", function(){
        document.getElementById('date_area').style.display = "block";
        document.getElementById('first_area').style.display = "none";
        document.getElementById('second_area').style.display = "none";
        document.getElementById('third_area').style.display = "block";
        $('#totalVisits').val(0);
    });
    $('#hide_all_areas').on("click", function(){
        document.getElementById('date_area').style.display = "none";
        document.getElementById('first_area').style.display = "none";
        document.getElementById('second_area').style.display = "none";
        document.getElementById('third_area').style.display = "none";
        $('#totalVisits').val(0);
    });
    $('#visits').on("click", function(){
        document.getElementById('visits_area').style.display = "block";
        document.getElementById('count_area').style.display = "block";
        document.getElementById('user_visits_area').style.display = "none";
    });
    $('#hide_visits_area').on("click", function(){ document.getElementById('visits_area').style.display = "none";});
    $('#user_visits').on("click", function(){
        document.getElementById('user_visits_area').style.display = "block";
        document.getElementById('visits_area').style.display = "none";
        document.getElementById('count_area').style.display = "none";
    });
    $('#hide_users_area').on("click", function(){
        document.getElementById('user_visits_area').style.display = "none";
        document.getElementById('count_area').style.display = "block";
    });

    $('#select_region').on('change', function(){
        $('#regionId').val($('#select_region').val());
        $('#visits_pages_title').html('');
        $('#visits_table_body').html('');
        $('#totalVisits').val(0);
    });
    $('#startDate').on('change', function(){
        $('#visits_pages_title').html('');
        $('#visits_table_body').html('');
        $('#totalVisits').val(0);
        $('#regions_table_body').html('');
        $('#user_table_body').html('');
    });
    $('#endDate').on('change', function(){
        $('#visits_pages_title').html('');
        $('#visits_table_body').html('');
        $('#totalVisits').val(0);
        $('#regions_table_body').html('');
        $('#user_table_body').html('');
    });

    $('#reload_visits').on('click', function(){
        let total = $('#totalVisits').val();
        if(total>0){
            showVisitsPage(0);
        } else{
            $.ajax({
                url: '../admin/load-data/report-totalVisits',
                method: 'POST',
                dataType: 'json',
                data: {startDate: $('#startDate').val(), endDate: $('#endDate').val(), regionId: $('#regionId').val()},
                success: function(totalElements) {
                    var count = parseInt(totalElements);
                    if(count > 0 ){
                     $('#totalVisits').val(count);
                     showVisitsPage(0);
                    }
                },
                error:  function(response) {
                    $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
                }
            });
        }
    });

    function showVisitsPage(pageNumber){
        var totalVisits = $('#totalVisits').val();
        var pageSize = $('#pageSize').val();
        $.ajax({
            url: '../admin/load-data/visits-report',
            method: 'POST',
            dataType: 'json',
            data: {startDate: $('#startDate').val(), endDate: $('#endDate').val(),
                    regionId: $('#regionId').val(), pageNumber: pageNumber, pageSize: $('#pageSize').val()},
            success: function(visits) {
                var visits_table_body = $('#visits_table_body');
                var visits_pages_title = $('#visits_pages_title');
                visits_pages_title.html('');
                visits_table_body.html('');
                var visits_html = "";
                var pages_html = "";
                if(visits.length > 0 ){
                    pages_html = getPagesHtml(pageNumber, pageSize, totalVisits);
                    visits_pages_title.prepend(pages_html);

                    $.each(visits, function(key, visit){
                        visits_html += "<tr><td>" + visit.visitDate + "</td><td>" + visit.userName + "</td><td>" +
                            visit.region + "</td><td>" + visit.workPlace + "</td><td>" + visit.position  + "</td></tr>";
                    });
                } else{
                    visits_html = "<tr><td colspan='5' style='color: blue; text-decoration: underline'>Посещений пользователями портала за указанный период (последний месяц) не зафиксировано</td></tr>"
                }
                visits_table_body.prepend(visits_html);
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
        });
    }

    $('#visits_pages_title').on('click', function(event){
        let elem = event.target || event.srcElement;
        let cellContent = elem.innerHTML;
        let pageSize = $('#pageSize').val();
        let titleText = $('#visits_pages_title').text();
        let pageNumber = getPageNumber(pageSize, cellContent, titleText);
        showVisitsPage(pageNumber);
    });

    $('#user_pages_title').on('click', function(event){
        let elem = event.target || event.srcElement;
        let cellContent = elem.innerHTML;
        let pageSize = $('#userPageSize').val();
        let titleText = $('#user_pages_title').text();
        let pageNumber = getPageNumber(pageSize, cellContent, titleText);
        showUserVisitsPage(pageNumber);
    });

    function getPageNumber(pageSize, cellContent, titleText){
        var currentRow = titleText;
        var totalVisits = $('#totalVisits').val();
        var totalPages;
        if(totalVisits%pageSize>0){
            totalPages = parseInt(totalVisits/pageSize) + 1;
        } else{
            totalPages = parseInt(totalVisits/pageSize);
        }
        var currentVisitNumber;
        var pos1 = cellContent.indexOf("(") + 1;
        var pos2 = cellContent.indexOf("..");
        if(pos2>1){
            currentVisitNumber = cellContent.substring(pos1, pos2);
            pageNumber = parseInt(currentVisitNumber/pageSize);
        } else{
            let pos = currentRow.indexOf(". .");
            if(pos < 6) {
                currentRow = currentRow.substring(12);
                pos1 = currentRow.indexOf("(") + 1;
                pos2 = currentRow.indexOf("..");
                currentVisitNumber = currentRow.substring(pos1, pos2);
                pageNumber = parseInt(currentVisitNumber/pageSize);
                if(pageNumber>3){
                   pageNumber-=3;
                } else{
                    pageNumber--;
                }
            } else {
                currentRow = currentRow.substring(0, pos-3);
                while(currentRow.indexOf("(")>=0){
                    currentRow = currentRow.substring(currentRow.indexOf("(")+1);
                }
                pos1 = currentRow.indexOf("..") + 3;
                pos2 = currentRow.indexOf(")");
                currentVisitNumber = currentRow.substring(pos1, pos2);
                pageNumber = parseInt(currentVisitNumber/pageSize);
                if((totalPages - pageNumber)>3){
                   pageNumber+=3;
                } else{
                    pageNumber++;
                }
            }
        }
        return pageNumber;
    }

    function getPagesHtml(pageNumber, pageSize, totalVisits){
        var totalPages;
        if(totalVisits%pageSize>0){
            totalPages = parseInt(totalVisits/pageSize) + 1;
        } else{
            totalPages = parseInt(totalVisits/pageSize);
        }
        console.log("pageNumber, pageSize, totalVisits, totalPages: " + pageNumber + "/ "+ pageSize + "/" + totalVisits + "/" + totalPages);
        var pages_html = "<tr>";
        if(pageNumber>2){
            pages_html+="<td class='pages'> ( . . . )  </td>";
        }
        for(let i=0; i<totalPages-1; i++){
            if(i - pageNumber<3 && pageNumber - i<3){
                if(i - pageNumber==0){
                    pages_html+="<td class='pages'><b>  (" + (Number(i*pageSize)+1) + "..." + (i+1)*pageSize + ")  </b></td>";
                } else {
                    pages_html+="<td class='pages'>  (" + (Number(i*pageSize)+1) + "..." + (i+1)*pageSize + ")  </td>";
                }
            }
        }
        if(totalPages-pageNumber>4){
            pages_html+="<td class='pages'> ( . . . )  </td>";
        }
        if(pageNumber==totalPages-1){
            pages_html += "<td class='pages'><b> (" + (Number((totalPages-1)*pageSize)+1) + "..." + totalVisits + ")  </b></td></tr>";
        } else {
            pages_html += "<td class='pages'> (" + (Number((totalPages-1)*pageSize)+1) + "..." + totalVisits + ")  </td></tr>";
        }
        return pages_html;
    }

    $('#reload_users').on('click', function(){
            $.ajax({
                url: '../admin/load-data/user-visits-count',
                method: 'POST',
                dataType: 'json',
                data: {startDate: $('#startDate').val(), endDate: $('#endDate').val(), regionId: $('#regionId').val()},
                success: function(userVisitsList) {
                    var users_visits_table_body = $('#users_visits_table_body');
                    users_visits_table_body.html('');
                    var users_visits_html = "";
                    if(userVisitsList.length > 0 ){
                        $.each(userVisitsList, function(key, userVisits){
                            users_visits_html += "<tr><td>" + userVisits.regionName + "</td><td>" + userVisits.userName + "</td><td>" +
                                userVisits.workPlace + "</td><td>" + userVisits.position + "</td><td>" + userVisits.visitsCount + "</td></tr>";
                        });
                    } else{
                        users_visits_html = "<tr><td colspan='5' style='color: blue; text-decoration: underline'>Посещений пользователями портала за указанный период (последний месяц) не зафиксировано</td></tr>"
                    }
                    users_visits_table_body.prepend(users_visits_html);
                },
                error:  function(response) {
                    $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
                }
            });
    });

    $('#reload_regions').on('click', function(){
        $.ajax({
            url: '../admin/load-data/regions-report',
            method: 'POST',
            dataType: 'json',
            data: {startDate: $('#startDate').val(), endDate: $('#endDate').val()},
            success: function(result) {
                var regionVisitsList = new Array();
                regionVisitsList = result;
                var regions_table_body = $('#regions_table_body');
                regions_table_body.html('');
                var regions_html = "";
                let total = 0;
                $.each(regionVisitsList, function(key, regionVisits){
                    regions_html += "<tr><td>" + regionVisits.regionName + "</td><td style='text-align: center;'>" + regionVisits.visitsCount + "</td></tr>";
                    if(regionVisits.regionName === "Всего по регионам"){
                        total = parseInt(regionVisits.visitsCount);
                    }
                });
                if(total == 500){
                    regions_html += "<tr><td colspan='2' style='font-size: 0.8em;'>Достигнут предел выборки в 500 посещений. Следует разбить период выборки на меньшие временные интервалы</td></tr>";
                }
                regions_table_body.prepend(regions_html);
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
        });
    });

    $('#reload_user').on('click', function(){
        let total = $('#totalVisits').val();
        if(total>0){
            showUserVisitsPage(0);
        } else{
            $.ajax({
                url: '../admin/load-data/report-userAllVisits',
                method: 'POST',
                dataType: 'json',
                data: {startDate: $('#startDate').val(), endDate: $('#endDate').val(), userId: $('#userId').val()},
                success: function(totalElements) {
                    var count = parseInt(totalElements);
                    if(count > 0 ){
                     $('#totalVisits').val(count);
                     showUserVisitsPage(0);
                    }
                },
                error:  function(response) {
                    $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
                }
            });
        }
    });

    function showUserVisitsPage(pageNumber){
        var totalVisits = $('#totalVisits').val();
        var pageSize = $('#userPageSize').val();
        $.ajax({
            url: '../admin/load-data/user-visits',
            method: 'POST',
            dataType: 'json',
            data: {startDate: $('#startDate').val(), endDate: $('#endDate').val(),
                    userId: $('#userId').val(), pageNumber: pageNumber, pageSize: $('#userPageSize').val()},
            success: function(visits) {
                var user_pages_title = $('#user_pages_title');
                var user_table_body = $('#user_table_body');
                user_pages_title.html('');
                user_table_body.html('');
                var pages_html = "";
                var visits_html = "";
                if(visits.length > 0 ){
                    pages_html = getPagesHtml(pageNumber, pageSize, totalVisits);
                    user_pages_title.prepend(pages_html);

                    $.each(visits, function(key, visit){
                        visits_html += "<tr><td>" + visit.visitDate + "</td></tr>";
                    });
                } else {
                    visits_html = "<tr><td colspan='5' style='color: blue; text-decoration: underline'>Посещений портала за указанный период (последний месяц) не зафиксировано</td></tr>"
                }
                user_table_body.prepend(visits_html);
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
        });
    }

    document.getElementById("user_name").oninput = function(){
        if($('#user_name').val()!=null){
            var textValue = $('#user_name').val().trim();
            if(textValue.length>2 && textValue.length<7){
                $('#user_name').readOnly = true;
                $('#user_info_body').html('');
                $.ajax({
                    url : 'edit-rights/search-user',
                    method: 'POST',
                    dataType: 'json',
                    data : {text: textValue},
                    success : function(users) {
                        $('#select_user').empty();
                        document.getElementById("show_select").style.display = "block";
                        $('#select_user').append('<option value=0>Выберите пользователя</option>');
                        $.each(users, function(key, user){
                            $('#select_user').append('<option value="' + user.id + '">' +
                                user.userSurname + ' ' + user.userFirstname + '; ' +
                                user.phone + '</option');
                        });
                        $('#user_name').readOnly = false;
                    },
                    error:  function(response) {
                        $('#user_name').readOnly = false;
                    }
                });
            } else if (textValue.length<3){
                document.getElementById("show_select").style.display = "none";
                $('#totalVisits').val(0);
                $('#user_info_body').html('');
                $('#user_pages_title').html('');
                $('#user_table_body').html('');
                $('#userId').val(0);
                $('#user_name').readOnly = false;
            } else {
                document.getElementById("show_select").style.display = "none";
            }
        }
    };

    $('#select_user').on('change', function(){
        var userId = $('#select_user').val();
        $('#totalVisits').val(0);
        if(userId>0){
            var userFullName = $('#select_user option:selected').text();
            let pos = userFullName.indexOf(";");
            var userName = userFullName.substring(0, pos);
            var phone = userFullName.substring(pos+1).trim();
            $('#userId').val(userId);
            $('#user_name').val(userName);
            document.getElementById("show_select").style.display = "none";
            document.getElementById("load_line").style.display = "block";
            getUserLine(userId);
        }
    });

    $('#btn_close').on('click', function(){
        $('#user_name').val("");
        $('#totalVisits').val(0);
        $('#user_info_body').html('');
        $('#user_pages_title').html('');
        $('#user_table_body').html('');
        $('#userId').val(0);
        document.getElementById("load_line").style.display = "none";
    });

    function getUserLine(userId){
        $.ajax({
            url : 'get-user',
            method: 'POST',
            dataType: 'json',
            data : {userId: userId},
            success : function(user) {
                var user_info_body = $('#user_info_body');
                var info_html = "";
                user_info_body.html('');
                if(user!=null){
                    info_html += "<tr><td><b>Регион:</b></td><td>" + user.regionName + "</td><td><b>Организация:</b></td><td>" + user.workPlace + "</td></tr>" +
                        "<tr><td><b>Должность:</b></td><td>" + user.position + "</td><td><b>Телефон:</b></td><td>+7-" + user.phone + "</td></tr>" +
                        "<tr><td></td><td></td><td><b>Email:</b></td><td>" + user.email + "</td></tr>";
                }
                user_info_body.prepend(info_html);
            },
            error:  function(response) {
                $('#user_name').readOnly = false;
            }
        });
    }

    $('#excel_visits').on('click', function(){
        var form = $('#export_excel');
        form.prop("action", "load-data/visits-exportExcel");
        form.prop("method", "post");
        form.submit();
    });

    $('#excel_regions').on('click', function(){
        var form = $('#export_excel');
        form.prop("action", "load-data/regions-exportExcel");
        form.prop("method", "post");
        form.submit();
    });

    $('#excel_users').on('click', function(){
        var form = $('#export_excel');
        form.prop("action", "load-data/users-visits-exportExcel");
        form.prop("method", "post");
        form.submit();
    });

    $('#excel_user').on('click', function(){
        var form = $('#export_excel');
        form.prop("action", "load-data/user-info-exportExcel");
        form.prop("method", "post");
        form.submit();
    });

});
