<%-- 
    Document   : index
    Created on : 09-abr-2015, 14:22:14
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-2.1.4.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <script src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
        <script src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-modal/2.2.5/js/bootstrap-modal.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <title>EASY TRAVEL</title>
        <script>
            var numEcoIda, numTurIda, numBussIda, preTurIda, preEcoIda, preBussIda;
            var numEcoVu, numTurVu, numBussVu, preTurVu, preEcoVu, preBussVu;
            var numInd, numDob, numSup, preInd, preDob, prepreSup;
            $(document).ready(function(){
                if(<%=session.getAttribute("vueloIda")%> !== null) {
                    $("#origen").val('<%=session.getAttribute("origen")%>');
                    $("#destino").val('<%=session.getAttribute("destino")%>');
                    $("#fecVueloIda").val('<%=session.getAttribute("fecha")%>');
                    $("#hora").val('<%=session.getAttribute("hora")%>');
                    $("#duracion").val('<%=session.getAttribute("duracion")%>');
                    numEcoIda = '<%=session.getAttribute("numEco")%>';
                    numTurIda = '<%=session.getAttribute("numTur")%>';
                    numBussIda = '<%=session.getAttribute("numBuss")%>';
                    preTurIda = <%=session.getAttribute("preTur")%>;
                    preEcoIda = <%=session.getAttribute("preEco")%>;
                    preBussIda = <%=session.getAttribute("preBuss")%>;
                    $("#preTur").text(preTurIda);
                    $("#preBuss").text(preBussIda);
                    $("#preEco").text(preEcoIda);
                } else {
                    $("#ida").hide();
                    $("#addIda").show();
                }
                if(<%=session.getAttribute("vueloVuelta")%> === null) {
                    $("#vuelta").hide();
                }
                var nomHotel = "<%=session.getAttribute("nomHotel")%>";
                if(nomHotel !== null) { 
                    $("#nombreH").val('<%=session.getAttribute("nombreH")%>');
                    $("#destinoH").val('<%=session.getAttribute("destinoH")%>');
                    $("#categoriaH").val('<%=session.getAttribute("categoriaH")%>');
                    numDob = '<%=session.getAttribute("habDobH")%>';
                    numInd = '<%=session.getAttribute("habIndH")%>';
                    numSup = '<%=session.getAttribute("habSupH")%>';
                    preInd = <%=session.getAttribute("preIndH")%>;
                    preDob = <%=session.getAttribute("preDobH")%>;
                    preSup = <%=session.getAttribute("preSupH")%>;
                    $("#preInd").text(preInd);
                    $("#preDob").text(preDob);
                    $("#preSup").text(preSup);
                    $("#hotel").show();
                    $("#addHotel").hide();
                } else {
                    $("#hotel").hide();
                }
                $("#numEco").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numEco").val();
                    total = total + (num * preEcoIda);
                    $("#total").val('');
                    $("#total").val(total);
                    if(num > numEcoIda) {
                        this.val(0);
                        alert("No hay tantas plazas economicas libres");
                    }
                });
                $("#numTur").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numTur").val();
                    total = total + (num * preTurIda);
                    $("#total").val('');
                    $("#total").val(total);
                    if(num > numTurIda) {
                        this.val(0);
                        alert("No hay tantas plazas economicas libres");
                    }
                });
                $("#numBuss").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numBuss").val();
                    total = total + (num * preBussIda);
                    $("#total").val('');
                    $("#total").val(total);
                    if(num > numBussIda) {
                        this.val(0);
                        alert("No hay tantas plazas economicas libres");
                    }
                });
                $("#numEcoVu").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numEcoVu").val();
                    total = total + (num * preEcoVu);
                    $("#total").val('');
                    $("#total").val(total);
                    if(num > numEcoVu) {
                        this.val(0);
                        alert("No hay tantas plazas economicas libres");
                    }
                });
                $("#numTurVu").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numTurVu").val();
                    total = total + (num * preTurVu);
                    $("#total").val('');
                    $("#total").val(total);
                    if(num > numTurVu) {
                        this.val(0);
                        alert("No hay tantas plazas economicas libres");
                    }
                });
                $("#numBussVu").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numBussVu").val();
                    total = total + (num * preBussVu);
                    $("#total").val('');
                    $("#total").val(total);
                    if(num > numBussVu) {
                        this.val(0);
                        alert("No hay tantas plazas economicas libres");
                    }
                });
                $("#numDob").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numDob").val();
                    total = total + (num * preDob);
                    $("#total").val('');
                    $("#total").val(total);
                });
                $("#numInd").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numInd").val();
                    total = total + (num * preInd);
                    $("#total").val('');
                    $("#total").val(total);
                });
                $("#numSup").change(function(){
                    var total = Number($("#total").val());
                    var num = $("#numSup").val();
                    total = total + (num * preSup);
                    $("#total").val('');
                    $("#total").val(total);
                });
                $("#addIda").click(function() {
                    waitingDialog.show();
                    table = $('#tableGestVuelos').dataTable({
                        "bFilter": false,
                        "processing": true,
                        "columnDefs": [
                            {
                                "targets": [ 1 ],
                                "visible": false,
                                "searchable": false
                            }
                        ],
                        "ajax": {
                            "async": false,
                            "url": "VuelosTableLoader",
                            "dataSrc": "vuelos",
                            "type": "GET",
                            "complete": function() {
                                waitingDialog.hide();
                            }
                        }
                    });
                    $('#tableGestVuelos tbody').on( 'click', 'tr', function () {
                        if ( $(this).hasClass('selected') ) {
                            $(this).removeClass('selected');
                        }
                        else {
                            table.$('tr.selected').removeClass('selected');
                            $(this).addClass('selected');
                        }
                    });
                });
                
                $("#addVuelta").click(function() {
                    waitingDialog.show();
                    table = $('#tableGestVuelos2').dataTable({
                        "bFilter": false,
                        "processing": true,
                        "columnDefs": [
                            {
                                "targets": [ 1 ],
                                "visible": false,
                                "searchable": false
                            }
                        ],
                        "ajax": {
                            "async": false,
                            "url": "VuelosTableLoader",
                            "dataSrc": "vuelos",
                            "type": "GET",
                            "complete": function() {
                                waitingDialog.hide();
                            }
                        }
                    });
                    $('#tableGestVuelos2 tbody').on( 'click', 'tr', function () {
                        if ( $(this).hasClass('selected') ) {
                            $(this).removeClass('selected');
                        }
                        else {
                            table.$('tr.selected').removeClass('selected');
                            $(this).addClass('selected');
                        }
                    });
                });
                
                $('#eligeIda').click( function () {
                    waitingDialog.show();
                    var data;
                    var refVuelo =  $("td:eq(0)", "tbody tr.selected").text();
                    $.ajax({
                        url : 'GestionReservas?refVueloA=' + refVuelo,
                        async: false,
                        type: "GET",
                        dataType: "text",
                        success : function(responseText) {   
                            data = responseText;
                            var result = data.split("$$");
                            $("#origen").val(result[0]);
                            $("#destino").val(result[1]);
                            $("#fecVueloIda").val(result[2]);
                            $("#hora").val(result[3]);
                            $("#duracion").val(result[4]);
                            numEcoIda = result[5];
                            numTurIda = result[6];
                            numBussIda = result[7];
                            preTurIda = result[9];
                            preEcoIda = result[8];
                            preBussIda = result[10];

                            $("#preTur").text(preTurIda);
                            $("#preBuss").text(preBussIda);
                            $("#preEco").text(preEcoIda);
                            $("#ida").show();
                            $("#addIda").hide();
                            $("#tableGestVuelos tbody tr").removeClass('selected');
                            waitingDialog.hide();
                        },
                        error: function(responseText) {
                            waitingDialog.hide();
                            alert("Error " +responseText);
                        }
                    });
                });
                $('#eligeVuelta').click( function () {
                    waitingDialog.show();
                    var data;
                    var refVuelo =  $("td:eq(0)", "tbody tr.selected").text();
                    $.ajax({
                        url : 'GestionReservas?refVueloVuelta=' + refVuelo,
                        async: false,
                        type: "GET",
                        dataType: "text",
                        success : function(responseText) {   
                            data = responseText;
                            var result = data.split("$$");
                            $("#origenVu").val(result[0]);
                            $("#destinoVu").val(result[1]);
                            $("#fecVueloVu").val(result[2]);
                            $("#horaVu").val(result[3]);
                            $("#duracionVu").val(result[4]);
                            numEcoVu = result[5];
                            numTurVu = result[6];
                            numBussVu = result[7];
                            preTurVu = result[9];
                            preEcoVu = result[8];
                            preBussVu = result[10];

                            $("#preTurVu").text(preTurVu);
                            $("#preBussVu").text(preBussVu);
                            $("#preEcoVu").text(preEcoVu);
                            $("#vuelta").show();
                            $("#addVuelta").hide();
                            $("#tableGestVuelos2 tbody tr").removeClass('selected');
                            waitingDialog.hide();
                        },
                        error: function(responseText) {
                            waitingDialog.hide();
                            alert("Error " +responseText);
                        }
                    });
                });
                $("#addHotel").click(function() {
                    waitingDialog.show();
                    table = $('#tableGestHoteles').dataTable({
                        "bFilter": false,
                        "processing": true,
                        "ajax": {
                            "async": false,
                            "url": "HotelesTableLoader",
                            "dataSrc": "hoteles",
                            "type": "GET",
                            "complete": function() {
                                waitingDialog.hide();
                            }
                        }
                    });
                    $('#tableGestHoteles tbody').on( 'click', 'tr', function () {
                        if ( $(this).hasClass('selected') ) {
                            $(this).removeClass('selected');
                        }
                        else {
                            table.$('tr.selected').removeClass('selected');
                            $(this).addClass('selected');
                        }
                    });
                });
                
                $('#eligeHotel').click( function () {
                    var data;
                    waitingDialog.show();
                    var nomHotel =  $("td:eq(0)", "tbody tr.selected").text();
                    $.ajax({
                        url : 'GestionReservas?nombreHotel=' + nomHotel,
                        async: false,
                        type: "GET",
                        dataType: "text",
                        success : function(responseText) {   
                            data = responseText;
                            var result = data.split("$$");
                            $("#nombreH").val(result[0]);
                            $("#destinoH").val(result[1]);
                            $("#categoriaH").val(result[2]);
                            numDob = result[4];
                            numInd = result[3];
                            numSup = result[5];
                            preInd = result[6];
                            preDob = result[7];
                            preSup = result[8];

                            $("#preInd").text(preInd);
                            $("#preDob").text(preDob);
                            $("#preSup").text(preSup);
                            $("#hotel").show();
                            $("#addHotel").hide();
                            $("#tableGestHoteles tbody tr").removeClass('selected');
                            waitingDialog.hide();
                        },
                        error: function(responseText) {
                            waitingDialog.hide();
                            alert("Error " + responseText);
                        }
                    });
                    
                });
                $("#cancelar").click(function(){
                    $.ajax({
                         url : 'GestionReservas?cancelar=1',
                         async: false,
                         type: "GET",
                         dataType: "text",
                         success : function(responseText) {   
                             var data = responseText;
                             waitingDialog.hide();
                         },
                         error: function(responseText) {
                             waitingDialog.hide();
                             alert("Error " + responseText);
                         }
                     });
                });
                $("#subida").click(function() {
                    $.ajax({
                         url : 'GestionReservas?save=1&fecEntradaH='+$("#fecLlegadaH").val()+'&fecOutH='+$("#fecOutH").val()
                                + '&numEco=' + $("#numEco").val() + '&numTur=' + $("#numTur").val() + '&numBuss=' + $("#numBuss").val()
                                + '&numEcoVu=' + $("#numEcoVu").val() + '&numTurVu=' + $("#numTurVu").val() + '&numBussVu=' + $("#numBussVu").val()
                                + '&numInd=' + $("#numInd").val() + '&numDob=' + $("#numDob").val() + '&numSup=' + $("#numSup").val() + '&total=' + $("#total").val(),
                         async: false,
                         type: "GET",
                         dataType: "text",
                         success : function(responseText) {   
                             var data = responseText;
                             waitingDialog.hide();
                         },
                         error: function(responseText) {
                             waitingDialog.hide();
                             alert("Error " + responseText);
                         }
                     });
                });
                $( "#fecLlegadaH" ).datepicker({
                    dateFormat:"dd-mm-yy"
                });
                $( "#fecOutH" ).datepicker({
                    dateFormat:"dd-mm-yy"
                });
            });
        </script>
        <style>
            .table-striped>tbody>tr:nth-of-type(odd).selected{background-color:red}
            .table-striped>tbody>tr:nth-of-type(even).selected{background-color:red}
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <%@include file='static/header.jsp'%>
            </div>            
            <div class="row">
                <%@include file='formCrear/formReserva.jsp'%>
            </div>
        </div>
        <footer class="footer">
            <%@include file='static/footer.jsp'%>
        </footer>
    </body>
</html>
