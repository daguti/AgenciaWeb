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
                $.ajax({
                    url : 'GestionReservas?detail=1',
                    async: false,
                    type: "GET",
                    dataType: "text",
                    success : function(responseText) {   
                        var data = responseText.split("$$");
                        $("#origen").val(data[0]);
                        $("#destino").val(data[1]);
                        $("#fecVueloIda").val(data[2]);
                        $("#hora").val(data[3]);
                        $("#duracion").val(data[4]);
                        numEcoIda = data[5];
                        numTurIda = data[6];
                        numBussIda = data[7];
                        preTurIda = data[8];
                        preEcoIda = data[9];
                        preBussIda = data[10];
                        $("#numTur").val(numTurIda);
                        $("#numBuss").val(numBussIda);
                        $("#numEco").val(numEcoIda);
                        $("#preTur").text(preTurIda);
                        $("#preBuss").text(preBussIda);
                        $("#preEco").text(preEcoIda);
                        $("#origenVu").val(data[11]);
                        $("#destinoVu").val(data[12]);
                        $("#fecVueloVu").val(data[13]);
                        $("#horaVu").val(data[14]);
                        $("#duracionVu").val(data[15]);
                        numEcoVu = data[16];
                        numTurVu = data[17];
                        numBussVu = data[18];
                        preTurVu = data[19];
                        preEcoVu = data[20];
                        preBussVu = data[21];
                        $("#numTurVu").val(numTurVu);
                        $("#numBussVu").val(numBussVu);
                        $("#numEcoVu").val(numEcoVu);
                        $("#preTurVu").text(preTurVu);
                        $("#preBussVu").text(preBussVu);
                        $("#preEcoVu").text(preEcoVu);
                        $("#nombreH").val(data[22]);
                        $("#destinoH").val(data[23]);
                        $("#categoriaH").val(data[24]);
                        numDob = data[25];
                        numInd = data[26];
                        numSup = data[27];
                        preInd = data[28];
                        preDob = data[29];
                        preSup = data[30];
                        $("#fecLlegadaH").val(data[31]);
                        $("#fecOutH").val(data[32]);
                        $("#numInd").val(numInd);
                        $("#numDob").val(numDob);
                        $("#numSup").val(numSup);
                        $("#preInd").text(preInd);
                        $("#preDob").text(preDob);
                        $("#preSup").text(preSup);
                        $("#total").val(data[33]);
                        if(data[22] === null || data[22] === 'null') {
                            $("#hotel").hide();
                        }
                        if(data[12] === null || data[12] === 'null') {
                            $("#vuelta").hide();
                        }
                        $("#numTur").prop("disabled", true);
                        $("#numBuss").prop("disabled", true);
                        $("#numEco").prop("disabled", true);
                        $("#numTurVu").prop("disabled", true);
                        $("#numBussVu").prop("disabled", true);
                        $("#numEcoVu").prop("disabled", true);
                        $("#numInd").prop("disabled", true);
                        $("#numDob").prop("disabled", true);
                        $("#numSup").prop("disabled", true);
                        $("#fecLlegadaH").prop("disabled", true);
                        $("#fecOutH").prop("disabled", true);
                        $("#subida").text("Confirmar");
                        $("#addVuelta").hide();
                        $("#addHotel").hide();
                        $("#subida").hide();
                        $("#cancelar").hide();
                        waitingDialog.hide();
                    },
                    error: function(responseText) {
                        waitingDialog.hide();
                        alert("Error " + responseText);
                    }
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
