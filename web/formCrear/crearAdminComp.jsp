<%-- 
    Document   : crearUsuario
    Created on : Apr 29, 2015, 12:38:21 PM
    Author     : ESa10969
--%>
<script>
    var regVal;
    $(document).ready(function() {
        regVal = $( "#registraAdmin" ).validate({
                rules:{
                   username: {
                      required: true,
                      minlength: 4
                    },
                    password: {
                      required: true,
                      minlength: 8
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
        });
</script>
<div class="container">    
    <div id="signupbox" style="margin-top:10px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Administrador Compañia</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="registraAdmin" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/userStorage?admin=1'/>" method="post">
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-md-3 control-label">Username</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                                    <input type="hidden" id="usernameOld" name="usernameOld"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-md-3 control-label">Password</label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-md-3 control-label">Email</label>
                                <div class="col-md-9">
                                    <input type="text" id="email" class="form-control" name="email" placeholder="Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="userType" class="col-md-3 control-label">Tipo de Admin.</label>
                                <div class="col-md-9">
                                    <select id="userType" name="userType" class="form-control">
                                        <option value="1">Aerea</option>
                                        <option value="2">Hotelera</option>
                                      </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="compName" class="col-md-3 control-label">Compañia</label>
                                <div class="col-md-9">
                                    <input type="text" id="compName" class="form-control" name="compName" placeholder="Compañia">
                                </div>
                            </div>
                            <div class="form-group">
                                <!-- Button -->                                        
                                <div class="col-md-offset-3 col-md-4">
                                    <input id="btn-signup" name="submit" type="submit" value="Guardar" class="btn btn-info"/>
                                </div>
                                <div class="col-md-4">
                                    <a onClick="goBack()" class="btn btn-primary">Atras</a>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                     </div>
                </div>   
     </div> 
</div>
