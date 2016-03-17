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
                $("#origen").val("<%=session.getAttribute("origen")%>");
                $("#destino").val("<%=session.getAttribute("destino")%>");
                $("#fecVueloIda").val("<%=session.getAttribute("fecha")%>");
                $("#hora").val("<%=session.getAttribute("hora")%>");
                $("#duracion").val("<%=session.getAttribute("duracion")%>");
                numEcoIda = <%=session.getAttribute("numEco")%>;
                numTurIda = <%=session.getAttribute("numTur")%>;
                numBussIda = <%=session.getAttribute("numBuss")%>;
                preTurIda = <%=session.getAttribute("preTur")%>;
                preEcoIda = <%=session.getAttribute("preEco")%>;
                preBussIda = <%=session.getAttribute("preBuss")%>;
                $("#numTur").val(numTurIda);
                $("#numBuss").val(numBussIda);
                $("#numEco").val(numEcoIda);
                $("#preTur").text(preTurIda);
                $("#preBuss").text(preBussIda);
                $("#preEco").text(preEcoIda);
                $("#origenVu").val("<%=session.getAttribute("origenVu")%>");
                $("#destinoVu").val("<%=session.getAttribute("destinoVu")%>");
                $("#fecVueloVu").val("<%=session.getAttribute("fechaVu")%>");
                $("#horaVu").val("<%=session.getAttribute("horaVu")%>");
                $("#duracionVu").val("<%=session.getAttribute("duracionVu")%>");
                numEcoVu = <%=session.getAttribute("numEcoVu")%>;
                numTurVu = <%=session.getAttribute("numTurVu")%>;
                numBussVu = <%=session.getAttribute("numBussVu")%>;
                preTurVu = <%=session.getAttribute("preTurVu")%>;
                preEcoVu = <%=session.getAttribute("preEcoVu")%>;
                preBussVu = <%=session.getAttribute("preBussVu")%>;
                $("#numTurVu").val(numTurVu);
                $("#numBussVu").val(numBussVu);
                $("#numEcoVu").val(numEcoVu);
                $("#preTurVu").text(preTurVu);
                $("#preBussVu").text(preBussVu);
                $("#preEcoVu").text(preEcoVu);
                $("#nombreH").val("<%=session.getAttribute("nombreH")%>");
                $("#destinoH").val("<%=session.getAttribute("destinoH")%>");
                $("#categoriaH").val("<%=session.getAttribute("categoriaH")%>");
                numDob = <%=session.getAttribute("habDobH")%>;
                numInd = <%=session.getAttribute("habIndH")%>;
                numSup = <%=session.getAttribute("habSupH")%>;
                preInd = <%=session.getAttribute("preIndH")%>;
                preDob = <%=session.getAttribute("preDobH")%>;
                preSup = <%=session.getAttribute("preSupH")%>;
                $("#fecLlegadaH").val("<%=session.getAttribute("fecEntradaH")%>");
                $("#fecOutH").val("<%=session.getAttribute("fecOutH")%>");
                $("#numInd").val(numInd);
                $("#numDob").val(numDob);
                $("#numSup").val(numSup);
                $("#preInd").text(preInd);
                $("#preDob").text(preDob);
                $("#preSup").text(preSup);
                $("#total").val("<%=session.getAttribute("total")%>");
                var nomHot = '<%=session.getAttribute("nombreH")%>';
                if( nomHot === null || nomHot === 'null') {
                    $("#hotel").hide();
                }
                var oriVu = '<%=session.getAttribute("origenVu")%>';
                if( oriVu === null || oriVu === 'null') {
                    $("#vuelta").hide();
                }
                var borrable = '<%=session.getAttribute("borrable")%>';
                if(borrable !== null || borrable !== 'null') $("#borrar").show();
                
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
                $("#atras").show();
                
                waitingDialog.hide();
                
                $("#atras").click(function(){
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
                $("#borrar").click(function(){
                    waitingDialog.show();
                    var codigo =  $("td:eq(0)", "tbody tr.selected").text();
                    $.ajax({
                         url : 'GestionReservas?delete=1',
                         async: false,
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
