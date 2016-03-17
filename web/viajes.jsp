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
        <script src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
        <script src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <title>EASY TRAVEL</title>
        <script>
            var table;
            $(document).ready(function() {
                waitingDialog.show();
                table = $('#tableViajes').dataTable({
                    "bFilter": false,
                    "processing": true,
                    "ajax": {
                        "url": "ViajesTableLoader",
                        "dataSrc": "viajes",
                        "type": "GET"
                    }
                });
                
                $('#tableViajes tbody').on( 'click', 'tr', function () {
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }
                    else {
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
                $('#delete').click( function () {
                    var first =  $("td:eq(0)", "tbody tr.selected").text();
                    alert(first);
                    table.row('.selected').remove().draw( false );
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
                    url : 'ViajesTableLoader?filtro=' + $("#origenVuelo").val()
                                                      + '$$' + $("#destinoVuelo").val()
                                                      + '$$' + $("#entrada").val()
                                                      + '$$' + $("#salida").val(),
                    async: false,           
                    success : function(responseText) {   
                        data = responseText; 
                    }        
                });
                waitingDialog.hide();
                location.reload();
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
                    <%@include file='buscador/buscadorViajesHori.jsp'%>
                </form>
            </div>
            <div class="row">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-12 resetGrid">
                            <div id="ofertas" class="col-xs-12">
                                <%@include file='tablas/ofertaViajes.jsp'%>
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
