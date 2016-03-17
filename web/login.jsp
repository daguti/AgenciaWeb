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
        <<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <title>EASY TRAVEL</title>
        <script>
            $(document).ready(function() {
                waitingDialog.show();
                  $( "#signupform" ).validate({
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
                      }/*,
                      submitHandler: function(form) {
                        $(form).submit();
                      }*/
                  });
                 $( "#loginForm" ).validate({
                      rules:{
                         loginUsername: {
                            required: true,
                            minlength: 4
                          },
                          loginPassword: {
                            required: true,
                            minlength: 8
                          }
                      },
                      messages: {
                        username: "El nombre de usuario es obligatorio para iniciar sesión",
                        password: "La password es obligatoria para iniciar sesión"
                      }/*,
                      submitHandler: function(form) {
                        $(form).submit();
                      }*/
                  });
                  waitingDialog.hide();
                $("form").submit(function() {
                    waitingDialog.show(); 
                });
            });
        </script>
    </head>
    <body onload='$("#loginUsername").focus();'>
        <div class="container-fluid">
            <div class="row">
                <%@include file='static/header.jsp'%>
            </div>
        </div>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
            <script>$('#login-alert').show();</script>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <div class="container">    
            <div id="loginbox" style="margin-top:10px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info" > 
                        <div style="padding-top:30px" class="panel-body" >
                            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            <form id="loginForm" name='loginForm' novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/login' />" method='POST'>
                                <div style="margin-bottom: 25px" class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input id="loginUsername" type="text" class="form-control" name="loginUsername" value="" placeholder="Username">                                        
                                        </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <input id="loginPassword" type="password" class="form-control" name="loginPassword" placeholder="Password">
                                        </div>   
                                    <div style="margin-top:10px" class="form-group">
                                        <!-- Button -->
                                        <div class="col-sm-12 controls">
                                          <input name="submit" type="submit" value="Login" class="btn btn-success"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-12 control">
                                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                                ¿No estas registrado?
                                            <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                                Registrate aquí
                                            </a>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>     
                            </div>                     
                        </div>  
            </div>
            <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="panel-title">Registrar</div>
                                <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Login</a></div>
                            </div>  
                            <div class="panel-body" >
                                <form id="signupform" class="form-horizontal" role="form" action="<c:url value='/userStorage'/>" method="post">
                                    <div id="signupalert" style="display:none" class="alert alert-danger">
                                        <p>Error:</p>
                                        <span></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="username" class="col-md-3 control-label">Username</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-md-3 control-label">Password</label>
                                        <div class="col-md-9">
                                            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre" class="col-md-3 control-label">Nombre</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="apellidos" class="col-md-3 control-label">Apellidos</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Apellidos">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="email" class="col-md-3 control-label">Email</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="direccion" class="col-md-3 control-label">Dirección</label>
                                        <div class="col-md-9">
                                            <input type="text" id="direccion" id="direccion" class="form-control" name="direccion" placeholder="Direccion">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="poblacion" class="col-md-3 control-label">Población</label>
                                        <div class="col-md-9">
                                            <input type="text" id="poblacion" id="poblacion" class="form-control" name="poblacion" placeholder="Poblacion">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="pais" class="col-md-3 control-label">País</label>
                                        <div class="col-md-9">
                                            <input type="text" id="pais" id="pais" class="form-control" name="pais" placeholder="País">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <!-- Button -->                                        
                                        <div class="col-md-offset-3 col-md-9">
                                            <input id="btn-signup" name="submit" type="submit" value="Registrar" class="btn btn-info"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>
                             </div>
                        </div>   
             </div> 
        </div>
        <footer class="footer">
            <%@include file='static/footer.jsp'%>
        </footer>
    </body>
</html>
