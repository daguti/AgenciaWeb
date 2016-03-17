<%-- 
    Document   : crearUsuario
    Created on : Apr 29, 2015, 12:38:21 PM
    Author     : ESa10969
--%>
<script>
    var regVal;
    $(document).ready(function() {
        regVal = $( "#registraAeropuerto" ).validate({
                rules:{
                   codigo: {
                      required: true
                    },
                    nombre: {
                      required: true
                    },
                    ciudad: {
                      required: true
                    },
                    pais: {
                        required: true
                    }
                },
                messages: {
                    codigo: "El código de aeropuerto es obligatorio",
                    nombre: "El nombre de aeropuerto es obligatorio",
                    ciudad: "La ciudad del aeropuerto es obligatoria",
                    pais: "El pais del aeropuerto es obligatorio"
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
                        <div class="panel-title">Aeropuerto</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="registraAeropuerto" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/altasManuales?aero=1'/>" method="post">
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="codigo" class="col-md-3 control-label">Código</label>
                                <div class="col-md-9">
                                    <input type="hidden" class="form-control" id="codigoOld" name="codigoOld">
                                    <input type="text" class="form-control" id="codigo" name="codigo" placeholder="Código">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="nombreCre" name="nombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pais" class="col-md-3 control-label">País</label>
                                <div class="col-md-9">
                                    <input type="text" id="pais" class="form-control" name="pais" placeholder="Pais">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="ciudad" class="col-md-3 control-label">Ciudad</label>
                                <div class="col-md-9">
                                    <input type="text" id="ciudadCre" class="form-control" name="ciudad" placeholder="Ciudad">
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
