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
                table = $('#tableHotelesCli').dataTable({
                    bFilter: false,
                    processing: true,
                    ajax: {
                        async: false,
                        url: "HotelesTableLoader?cliente=1",
                        dataSrc: "hotelesCli",
                        type: "GET",
                    }
                });
                $('#tableHotelesCli tbody').on( 'click', 'tr', function () {
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }
                    else {
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
                table = $('#tableVuelosCli').dataTable({
                    "bFilter": false,
                    "processing": true,
                    "ajax": {
                        "async": false,
                        "url": "VuelosTableLoader?cliente=1",
                        "dataSrc": "vuelosCli",
                        "type": "GET",
                    }
                });
                $('#tableVuelosCli tbody').on( 'click', 'tr', function () {
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }
                    else {
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <%@include file='static/header.jsp'%>
            </div>
            <div class="row">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-4 resetGrid">
                            <div id="sidebar-wrapper" class="busquedaLateral">
                                <%@include file='buscador/buscador.jsp'%>
                            </div>
                        </div>
                        <div class="col-sm-8 resetGrid">
                            <div id="container-fluid">
                                <div class="row">
                                    <div id="ofertas" class="col-sm-6">
                                        <%@include file='tablas/ofertaVuelos.jsp'%>
                                    </div>
                                    <div id="ofertas" class="col-sm-6">
                                        <%@include file='tablas/ofertaHoteles.jsp'%>
                                    </div>
                                </div>
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
