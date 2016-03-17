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
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
        <script src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
        <script src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <title>EASY TRAVEL</title>
        <script>
            if(${result == 'ok'}){
             alert("Fichero Subido Correctamente");
             ${result == null};
            }
            var table;
            $(document).ready(function() {
                waitingDialog.show();
                table = $('#tableGestHoteles').dataTable({
                    "bFilter": false,
                    "processing": true,
                    "ajax": {
                        "url": "HotelesTableLoader",
                        "dataSrc": "hoteles",
                        "type": "GET"
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
                $('#delete').click( function () {
                    waitingDialog.show();
                    var codigo =  $("td:eq(0)", "tbody tr.selected").text();
                    $.ajax({
                        url : 'altasManuales?hotel=1&delete=1&nombreOld=' + codigo,
                        async: false,           
                        success : function(responseText) {   
                            data = responseText; 
                        }        
                    });
                    table.row('.selected').remove().draw( false );
                    waitingDialog.hide();
                });
                $('#modify').click( function () {
                    $("#nombreCre").val($("td:eq(0)", "tbody tr.selected").text());
                    $("#nombreOld").val($("td:eq(0)", "tbody tr.selected").text());
                    $("#categoria").val($("td:eq(1)", "tbody tr.selected").text());
                    $("#compania").val($("td:eq(2)", "tbody tr.selected").text());
                    $("#descripcion").val($("td:eq(3)", "tbody tr.selected").text());
                    $("#direccion").val($("td:eq(4)", "tbody tr.selected").text());
                    $("#ciudad").val($("td:eq(5)", "tbody tr.selected").text());
                    $("#numIndC").val($("td:eq(6)", "tbody tr.selected").text());
                    $("#preIndC").val($("td:eq(7)", "tbody tr.selected").text());
                    $("#numDobC").val($("td:eq(8)", "tbody tr.selected").text());
                    $("#preDobC").val($("td:eq(9)", "tbody tr.selected").text());
                    $("#numSupC").val($("td:eq(10)", "tbody tr.selected").text());                    
                    $("#preSupC").val($("td:eq(11)", "tbody tr.selected").text());
                    $("#nombreCre").prop('disabled', true);
                    $("#horizontalSearch").hide();
                    $("#tableGestHoteles").hide();
                    $("#subidaExcel").show();
                    $("#formCrear").show();
                });
                waitingDialog.hide();
                $("form").submit(function() {
                    waitingDialog.show(); 
                });
            });  
            function filtraDatos(){
                waitingDialog.show();
                var data;
                var i = 0;
                $.ajax({
                    url : 'HotelesTableLoader?filtro=' + $("#origenVuelo").val() 
                                                             + '$$' + $("#destinoVuelo").val()
                                                             + '$$' + $("#datepicker").val(),
                    async: false,           
                    success : function(responseText) {   
                        data = responseText; 
                    }        
                });
                waitingDialog.hide();
                location.reload();
            }
            function viewCreate() {
                $("#horizontalSearch").hide();
                $("#tabla").hide();
                $("#subidaExcel").show();
                $("#formCrear").show();
            }
            function goBack() {
                $("#nombreCre").val("");
                $("#nombreOld").val("");
                $("#categoria").val("");
                $("#compania").val("");
                $("#descripcion").val("");
                $("#direccion").val("");
                $("#ciudad").val("");
                $("#numInd").val("");
                $("#preInd").val("");
                $("#numDob").val("");
                $("#preDob").val("");
                $("#numSup").val("");                    
                $("#preSup").val("");
                $("#nombreCre").prop('disabled', false);
                $("#subidaExcel").hide();
                $("#formCrear").hide();
                $("#horizontalSearch").show();
                $("#tableGestHoteles").show();
            }
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
            <div id="horizontalSearch" class="row">
                <form class="form-horizontal" role="form">
                    <%@include file='buscador/buscadorGestionHotelesHori.jsp'%>
                </form>
            </div>
            <div id="subidaExcel" style="display: none;" class="row col-sm-offset-4">
                <div class="container-fluid">
                    <form action="UploadDownloadFileServlet?${_csrf.parameterName}=${_csrf.token}&hoteles=1" method="post" enctype="multipart/form-data" role='form'>
                        <div class="form-group"> 
                            <div class="col-sm-12">
                                Selecciona fichero Excel para importar:
                                <input type="file" name="fileName">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-1">
                                <input type="submit" value="Upload" class="btn btn-primary">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div id="formCrear" style="display: none;" class="row">
                <%@include file='formCrear/crearHotel.jsp'%>
            </div>
            <div class="row" id="tabla">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-12 resetGrid">
                            <div id="ofertas" class="col-xs-12">
                                <%@include file='tablas/hoteles.jsp'%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer">
            <%@include file='static/footer.jsp'%>
        </footer>
    </body>
</html>
