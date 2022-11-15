$(document).ready(function(){
    var actionsClickCount = 1;
    var surgeonClickCount = 1;
    var firstClickCount = 1;
    var secondClickCount = 1;
    var thirdClickCount = 1;
    var forthClickCount = 1;
    var fifthClickCount = 1;
    var sixthClickCount = 1;
    var sevenClickCount = 1;
    var eightClickCount = 1;

    $('#actions').on("click", function(){
        actionsClickCount++;
        if(actionsClickCount%2==0){
            document.getElementById('actions_area').style.display = "block";
        } else {
            document.getElementById('actions_area').style.display = "none";
        }
    });
    $('#surgeon').on("click", function(){
        surgeonClickCount++;
        if(surgeonClickCount%2==0){
            document.getElementById('surgeon_area').style.display = "block";
        } else {
            document.getElementById('surgeon_area').style.display = "none";
        }
    });
    $('#dbc_page').on("click", function(){
        document.location.href = "dbc-page";
    });
    $('#analyses').on("click", function(){
        document.location.href = "analyses";
    });
    $('#no_hem').on("click", function(){
        document.location.href = "continue-care";
    });

    $('#first_title').on("click", function(){
        firstClickCount++;
        if(firstClickCount%2==0){
            document.getElementById('first_area').style.display = "block";
        } else {
            document.getElementById('first_area').style.display = "none";
        }
    });
    $('#second_title').on("click", function(){
        secondClickCount++;
        if(secondClickCount%2==0){
            document.getElementById('second_area').style.display = "block";
        } else {
            document.getElementById('second_area').style.display = "none";
        }
    });
    $('#third_title').on("click", function(){
        thirdClickCount++;
        if(thirdClickCount%2==0){
            document.getElementById('third_area').style.display = "block";
        } else {
            document.getElementById('third_area').style.display = "none";
        }
    });
    $('#forth_title').on("click", function(){
        forthClickCount++;
        if(forthClickCount%2==0){
            document.getElementById('forth_area').style.display = "block";
        } else {
            document.getElementById('forth_area').style.display = "none";
        }
    });
    $('#fifth_title').on("click", function(){
        fifthClickCount++;
        if(fifthClickCount%2==0){
            document.getElementById('fifth_area').style.display = "block";
        } else {
            document.getElementById('fifth_area').style.display = "none";
        }
    });
    $('#sixth_title').on("click", function(){
        sixthClickCount++;
        if(sixthClickCount%2==0){
            document.getElementById('sixth_area').style.display = "block";
        } else {
            document.getElementById('sixth_area').style.display = "none";
        }
    });
    $('#seven_title').on("click", function(){
        sevenClickCount++;
        if(sevenClickCount%2==0){
            document.getElementById('seven_area').style.display = "block";
        } else {
            document.getElementById('seven_area').style.display = "none";
        }
    });
    $('#eight_title').on("click", function(){
        eightClickCount++;
        if(eightClickCount%2==0){
            document.getElementById('eight_area').style.display = "block";
        } else {
            document.getElementById('eight_area').style.display = "none";
        }
    });

    $('#select_lang').on('change', function(){
        let select_lang = $('#select_lang').val();
        let lang = $('#lang').val();
        if(select_lang === lang){
          $.ajax({
            url: '../article/get-texts',
            method: 'POST',
            dataType: 'json',
            data: {lang: lang},
            success: function(articles) {
                for(let i=1; i<11; i++){
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
                            place.innerHTML = text.replace(/\n/g, '<br/>');
                        }
                    }
                }
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
          });
        } else{
            if(select_lang === "KZ"){
               document.location.href = "../kz/work-page";
            } else{
               document.location.href = "../ru/work-page";
            }
        }
    });

    $('#select_language').on('change', function(){
        let select_language = $('#select_language').val();
        let language = $('#language').val();
        if(select_language === language){
          $.ajax({
            url: '../article/get-texts',
            method: 'POST',
            dataType: 'json',
            data: {lang: language},
            success: function(articles) {
                for(let i=11; i<19; i++){
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
                            place.innerHTML = text.replace(/\n/g, '<br/>');
                        }
                    }
                }
            },
            error:  function(response) {
                $('#result_line').html("Ошибка обращения в базу данных. Повторите.");
            }
          });
        } else{
            if(select_language === "KZ"){
               document.location.href = "../kz/work-page";
            } else{
               document.location.href = "../ru/work-page";
            }
        }
    });


//    $('#first_title').on("click", function(){
//        firstClickCount++;
//        if(firstClickCount%2==0){
//            document.getElementById('first_area').style.display = "block";
//            $('#first_title').addClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('first_title').textContent = "1. Первоначальные действия (скрыть поле)";
//            } else {
//                document.getElementById('first_title').textContent = "1. Бастапқы әрекеттер (өрісті жасыру)";
//            }
//        } else {
//            document.getElementById('first_area').style.display = "none";
//            $('#first_title').removeClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('first_title').textContent = "1. Первоначальные действия";
//            } else {
//                document.getElementById('first_title').textContent = "1. Бастапқы әрекеттер";
//            }
//        }
//    });
//
//    $('#second_title').on("click", function(){
//        secondClickCount++;
//        if(secondClickCount%2==0){
//            document.getElementById('second_area').style.display = "block";
//            $('#second_title').addClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('second_title').textContent = "2. Манипуляции (скрыть поле)";
//            } else {
//                document.getElementById('second_title').textContent = "2. Манипуляциялар (өрісті жасыру)";
//            }
//        } else {
//            document.getElementById('second_area').style.display = "none";
//            $('#second_title').removeClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('second_title').textContent = "2. Манипуляции";
//            } else {
//                document.getElementById('second_title').textContent = "2. Манипуляциялар";
//            }
//        }
//    });
//
//    $('#third_title').on("click", function(){
//        thirdClickCount++;
//        if(thirdClickCount%2==0){
//            document.getElementById('third_area').style.display = "block";
//            $('#third_title').addClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('third_title').textContent = "3. Назначение анализов (скрыть поле)";
//            } else {
//                document.getElementById('third_title').textContent = "3. Талдаудың мақсаты (өрісті жасыру)";
//            }
//        } else {
//            document.getElementById('third_area').style.display = "none";
//            $('#third_title').removeClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('third_title').textContent = "3. Назначение анализов";
//            } else {
//                document.getElementById('third_title').textContent = "3. Талдаудың мақсаты";
//            }
//        }
//    });
//
//    $('#forth_title').on("click", function(){
//        forthClickCount++;
//        if(forthClickCount%2==0){
//            document.getElementById('forth_area').style.display = "block";
//            $('#forth_title').addClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('forth_title').textContent = "4. Тромбоэластография (скрыть поле)";
//            } else {
//                document.getElementById('forth_title').textContent = "4. Тромбоэластография (өрісті жасыру)";
//            }
//        } else {
//            document.getElementById('forth_area').style.display = "none";
//            $('#forth_title').removeClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('forth_title').textContent = "4. Тромбоэластография";
//            } else {
//                document.getElementById('forth_title').textContent = "4. Тромбоэластография";
//            }
//        }
//    });
//
//    $('#fifth_title').on("click", function(){
//        fifthClickCount++;
//        if(fifthClickCount%2==0){
//            document.getElementById('fifth_area').style.display = "block";
//            $('#fifth_title').addClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('fifth_title').textContent = "5. Установление причины (скрыть поле)";
//            } else {
//                document.getElementById('fifth_title').textContent = "5. Себебін анықтау (өрісті жасыру)";
//            }
//        } else {
//            document.getElementById('fifth_area').style.display = "none";
//            $('#fifth_title').removeClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('fifth_title').textContent = "5. Установление причины (4Т)";
//            } else {
//                document.getElementById('fifth_title').textContent = "5. Себебін анықтау (4Т)";
//            }
//        }
//    });
//
//    $('#sixth_title').on("click", function(){
//        sixthClickCount++;
//        if(sixthClickCount%2==0){
//            document.getElementById('sixth_area').style.display = "block";
//            $('#sixth_title').addClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('sixth_title').textContent = "6. Продолжить инфузионно-трансфузионную терапию (скрыть поле)";
//            } else {
//                document.getElementById('sixth_title').textContent = "6. Инфузиялық-трансфузиялық терапияны жалғастыру (өрісті жасыру)";
//            }
//        } else {
//            document.getElementById('sixth_area').style.display = "none";
//            $('#sixth_title').removeClass("cut_title");
//            if($('#lang').val() === "RU"){
//                document.getElementById('sixth_title').textContent = "6. Продолжить инфузионно-трансфузионную терапию (ИТТ)";
//            } else {
//                document.getElementById('sixth_title').textContent = "6. Инфузиялық-трансфузиялық терапияны жалғастыру (ИТТ)";
//            }
//        }
//    });


});
