<%-- 
    Document   : crearUsuario
    Created on : Apr 29, 2015, 12:38:21 PM
    Author     : ESa10969
--%>

<div class="container">    
    <div id="signupbox" style="margin-top:10px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Perfil</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="signupform" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/userStorage?update=1'/>" method="post">
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
                                    <input type="text" id="email" class="form-control" name="email" placeholder="Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="direccion" class="col-md-3 control-label">Dirección</label>
                                <div class="col-md-9">
                                    <input type="text" id="direccion" class="form-control" name="direccion" placeholder="Direccion">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="poblacion" class="col-md-3 control-label">Población</label>
                                <div class="col-md-9">
                                    <input type="text" id="poblacion" class="form-control" name="poblacion" placeholder="Poblacion">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pais" class="col-md-3 control-label">País</label>
                                <div class="col-md-9">
                                    <input type="text" id="pais" class="form-control" name="pais" placeholder="País">
                                </div>
                            </div>
                            <div class="form-group">
                                <!-- Button -->                                        
                                <div class="col-md-offset-3 col-md-9">
                                    <input id="btn-signup" name="submit" type="submit" value="Guardar" class="btn btn-info"/>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                     </div>
                </div>   
     </div> 
</div>
