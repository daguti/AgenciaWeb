<%-- 
    Document   : registrar
    Created on : Apr 27, 2015, 11:57:54 AM
    Author     : ESa10969
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
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <title>EASY TRAVEL</title>
        <script>
            var regVal;
            var table;
            $(document).ready(function() {
                waitingDialog.show();
              var data;
              $.ajax({
                    url : 'ProfileLoader',
                    async: false,           
                    success : function(responseText) {   
                        data = responseText.split("$$");
                        $('#username').val(data[0]);
                        $('#password').val(data[1]);
                        $('#nombre').val(data[2]);
                        $('#apellidos').val(data[3]);
                        $('#email').val(data[4]);
                        $('#direccion').val(data[5]);
                        $('#poblacion').val(data[6]);
                        $('#pais').val(data[7]);
                    }        
                });
                regVal = $( "#signupform" ).validate({
                    rules:{
                       username: {
                          required: true,
                          minlength: 4
                        },
                        password: {
                          required: true,
                          minlength: 8
                        },
                        nombre: {
                          required: true
                        },
                        apellidos: {
                          required: true
                        },
                        email: {
                          required: true,
                          email: true
                        } 
                    },
                    messages: {
                        username: "El nombre de usuario es obligatorio",
                        password: "La password es obligatoria",
                        nombre: "El nombre es obligatorio",
                        apellidos: "El/los apellido/apellidos es/son obligatorios",
                        email: {
                          required: "El email es obligatorio",
                          email: "El formato de email es --> name@domain.com"
                        }
                    },
                    submitHandler: function(form) {
                        $(form).ajaxSubmit();
                    }
                });
                waitingDialog.hide();
                $("form").submit(function() {
                    waitingDialog.show(); 
                });
                table = $('#listaReservas').dataTable({
                "bFilter": false,
                "processing": true,
                "ajax": {
                    "url": "ReservasTableLoader",
                    "dataSrc": "reservas",
                    "type": "GET"
                }
                });
                $('#listaReservas tbody').on( 'click', 'tr', function () {
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }
                    else {
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
                $('#detalle').click( function () {
                    waitingDialog.show();
                    var codigo =  $("td:eq(0)", "tbody tr.selected").text();
                    $.ajax({
                        url : 'ReservasTableLoader?detail=1&refRes=' + codigo,
                        async: false,           
                        success : function(responseText) {   
                            data = responseText;
                            waitingDialog.hide();
                            location.href="/AgenciaWeb/detalleReserva.jsp"
                        },
                        error: function(responseText) {
                            waitingDialog.hide();
                            alert("Error");
                        }
                    });
                });
            });
            function validaForm() {
                regVal.validate();
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
            <div class="row">
                <div role="tabpanel">
                    <ul class="nav nav-tabs" role="tablist" id="crearTab">
                        <li role="presentation" class="active"><a href="#perfil" aria-controls="perfil" role="tab" data-toggle="tab">Perfil</a></li>
                        <li role="presentation"><a href="#reservas" aria-controls="reservas" role="tab" data-toggle="tab">Reservas</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="perfil">
                            <%@include file='formCrear/crearUsuario.jsp'%>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="reservas">
                            <%@include file='tablas/listadoReservas.jsp'%>
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
