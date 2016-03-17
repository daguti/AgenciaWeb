<%-- 
    Document   : crearUsuario
    Created on : Apr 29, 2015, 12:38:21 PM
    Author     : ESa10969
--%>
<script>
    var regVal;
    $(document).ready(function() {
        regVal = $( "#registraComp" ).validate({
                rules:{
                   ref: {
                      required: true
                    },
                    nombre: {
                      required: true
                    },
                    pais: {
                      required: true
                    },
                    tipo: {
                      required: true
                    }
                },
                messages: {
                    ref: "La referencia de la compañia es obligatoria",
                    nombre: "El nombre de la compañia es obligatorio",
                    pais: "El pais de la compañia es obligatorio",
                    tipo: "El tipo de compañia es obligatorio"
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
                        <div class="panel-title">Compañia</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="registraComp" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/altasManuales?comp=1'/>" method="post">
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="ref" class="col-md-3 control-label">Ref. Compañia</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="ref" name="ref" placeholder="Ref. Compañia">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pais" class="col-md-3 control-label">Pais</label>
                                <div class="col-md-9">
                                    <input type="text" id="pais" class="form-control" name="pais" placeholder="Pais">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tipo" class="col-md-3 control-label">Tipo de Comp.</label>
                                <div class="col-md-9">
                                    <select id="tipo" name="tipo" class="form-control">
                                        <option value="1">Aerea</option>
                                        <option value="2">Hotelera</option>
                                      </select>
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
                            <input type="hidden" name="refOld" id="refOld" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                     </div>
                </div>   
     </div> 
</div>
