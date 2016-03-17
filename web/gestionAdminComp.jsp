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
                table = $('#tableAdministradores').dataTable({
                    "bFilter": false,
                    "processing": true,
                    "ajax": {
                        "url": "AdministradoresTableLoader",
                        "dataSrc": "administradores",
                        "type": "GET"
                    }
                });
                $('#tableAdministradores tbody').on( 'click', 'tr', function () {
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
                    var username =  $("td:eq(1)", "tbody tr.selected").text();
                    $.ajax({
                        url : 'userStorage?delete=1&username=' + username,
                        async: false,           
                        success : function(responseText) {   
                            data = responseText; 
                        }        
                    });
                    table.row('.selected').remove().draw( false );
                    waitingDialog.hide();
                });
                $('#modify').click( function () {
                    $("#compName").val($("td:eq(0)", "tbody tr.selected").text());
                    $("#username").val($("td:eq(1)", "tbody tr.selected").text());
                    $("#usernameOld").val($("td:eq(1)", "tbody tr.selected").text());
                    $("#password").val($("td:eq(2)", "tbody tr.selected").text());
                    $("#email").val($("td:eq(3)", "tbody tr.selected").text());
                    $("#userType").val($("td:eq(4)", "tbody tr.selected").text());
                    $("#username").prop('disabled', true);
                    $("#horizontalSearch").hide();
                    $("#tabla").hide();
                    $("#subidaExcel").show();
                    $("#formCrear").show();
                });
                waitingDialog.hide(); 
                $("form").submit(function() {
                    waitingDialog.show(); 
                });
            });  
            function filtraDatos(){
                var data;
                var i = 0;
                $.ajax({
                    url : 'AdministradoresTableLoader?filtro=' + $("#nombre").val() 
                                                               + '$$' + $("#apellidos").val()
                                                               + '$$' + $("#compania").val(),
                    async: false,           
                    success : function(responseText) {   
                        data = responseText; 
                    }        
                });
                location.reload();
            }
            function viewCreate() {
                $("#horizontalSearch").hide();
                $("#tabla").hide();
                $("#subidaExcel").show();
                $("#formCrear").show();
            }
            function goBack() {
                $("#compName").val("");
                $("#username").val("");
                $("#password").val("");
                $("#email").val("");
                $("#userType").val("");
                $("#username").prop('disabled', false);
                $("#subidaExcel").hide();
                $("#formCrear").hide();
                $("#horizontalSearch").show();
                $("#tabla").show();
            }
        </script>
        <style>
            .table-striped>tbody>tr:nth-of-type(odd).selected{background-color:red}
            .table-striped>tbody>tr:nth-of-type(even).selected{background-color:red}
            #aviones_wrapper .row {
                margin-top: 10px;
                margin-bottom: 5px;
                margin-left: 5px;
                margin-right: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <%@include file='static/header.jsp'%>
            </div>
            <div id="horizontalSearch" class="row">
                <form class="form-horizontal" role="form">
                    <%@include file='buscador/buscadorAdminCompHori.jsp'%>
                </form>
            </div>
            <div id="subidaExcel" style="display: none;" class="row col-sm-offset-4">
                <div class="container-fluid">
                    <form action="UploadDownloadFileServlet?${_csrf.parameterName}=${_csrf.token}&adminComp=1" method="post" enctype="multipart/form-data" role='form'>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <label for="fileName" class="control-label">Selecciona fichero Excel para importar:</label>
                                <input type="file" id="fileName" name="fileName">
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
                <%@include file='formCrear/crearAdminComp.jsp'%>
            </div>
            <div id="tabla" class="row">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-12 resetGrid">
                            <div id="ofertas" class="col-xs-12">
                                <%@include file='tablas/adminCompanias.jsp'%>
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
