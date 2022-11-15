$(document).ready(function(){

    $('#select_lang').on('change', function(){
        var select_lang = $('#select_lang').val();
        if(select_lang === "KZ"){
           document.location.href = "../kz/analyses";
        }
    });

    $(document).keypress(function(event) {
        if(event.keyCode==13){
            let divId = event.target.id;
            if(divId != "atv"){
                $(event.target).closest('tr').next().find('input').focus();
                event.preventDefault();
            } else{
                $('#atv').blur();
            }
        }
    });


    function showMessage(message){
        document.getElementById("main_display").style.display = "block";
        $('#display_text').html(message);
    }

    $('#close_display').on('click', function(){
        document.getElementById("main_display").style.display = "none";
    });


    document.getElementById("blade").onchange = function(){
        let bladeValue = $('#blade').val();
        if(bladeValue!="" && bladeValue>1499){
            let mnoValue = $('#mno').val();
            let atvValue = $('#atv').val();
            if(mnoValue!="" && atvValue!="" && mnoValue>1.725 && atvValue>54.75){
                showMessage("При удлинении МНО и АЧТВ  более чем в 1,5 раза с кровопотерей более 1500 мл рекомендуется СЗП. " +
                  "<br/>При наличии противопоказаний к СЗП, отсутствии эффекта от терапии или снижении уровня фибриногена менее 1,5 г/л " +
                  "на фоне проводимой терапии СЗП рекомендуется введение криопреципитата");
            } else {
                showMessage("При кровопотере более 1500 мл и/или снижении уровня гемоглобина и гематокрита и\или прогрессирование " +
                "клинических и гемодинамических нарушений, несмотря на интенсивную инфузионную и медикаментозную терапию (снижение сатурации, " +
                "продолжающееся кровотечение, нестабильность гемодинамики) " +
                 "рекомендуется введение эритроцитарной массы");
            }
        }
    };
    document.getElementById("mno").onchange = function(){ checkMnoAtv();};

    document.getElementById("atv").onchange = function(){
        let atvValue = $('#atv').val();
        if(atvValue!="" && atvValue>54.75){
            let mnoValue = $('#mno').val();
            if(mnoValue!="" && mnoValue>1.725){
                checkMnoAtv();
            } else {
                checkPvAtvFib();
            }
        }
    };
    document.getElementById("pv").onchange = function(){ checkPvAtvFib();};
    document.getElementById("fib").onchange = function(){
        checkPvAtvFib();
    };

    function checkMnoAtv(){
        let mnoValue = $('#mno').val();
        let atvValue = $('#atv').val();
        let bladeValue = $('#blade').val();
        if(mnoValue!="" && atvValue!=""){
            if(mnoValue>1.725 && atvValue>54.75){
                if(bladeValue>1500){
                    showMessage("При удлинении МНО и АЧТВ  более чем в 1,5 раза с кровопотерей более 1500 мл рекомендуется СЗП. " +
                      "<br/>При противопоказании к СЗП, отсутствии эффекта от терапии или снижении уровня фибриногена менее 1,5 г/л " +
                      "на фоне проводимой терапии СЗП рекомендуется введение криопреципитата");
                }
            }
        }
    };

    function checkPvAtvFib(){
        let pvValue = $('#pv').val();
        let fibValue = $('#fib').val();
        let atvValue = $('#atv').val();
        if(fibValue!="" && fibValue < 1.5)
        if(fibValue!="" && pvValue!="" && atvValue!=""){
            if(fibValue < 1.5 && pvValue>19.35 && atvValue>54.75){
                showMessage("При удлинении ПВ/АЧТВ более 1,5 нормы и снижениии фибриногена менее 1,5 г/л рекомендуется СЗП. " +
                   "<br/>При противопоказании к СЗП, отсутствии эффекта от терапии или снижении уровня фибриногена менее 1,5 г/л " +
                   "на фоне проводимой терапии СЗП рекомендуется введение криопреципитата");
            }
        }
    };

    document.getElementById("tro").onchange = function(){
        let troValue = $('#tro').val();
        if(troValue!="" && troValue < 50){
            showMessage("При снижениии уровня тромбоцитов менее 50 тыс/мкл рекомендуется введение тромбоцитарного концентрата");
        }
    };

    document.getElementById("hb").onchange = function(){
        let hbValue = $('#hb').val();
        if(hbValue!="" && hbValue < 70){
            showMessage("При снижениии гемоглобина менее 70 г/л и/или гематокрита менее 23-25% рекомендуется введение эритроцитарной массы");
        }
    };
    document.getElementById("ht").onchange = function(){
        let htValue = $('#ht').val();
        if(htValue!="" &&  htValue < 25){
            showMessage("При снижениии гемоглобина менее 70 г/л и/или гематокрита менее 23-25% рекомендуется введение эритроцитарной массы");
        }
    };


});