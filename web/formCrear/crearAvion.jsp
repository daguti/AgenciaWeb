<%-- 
    Document   : crearUsuario
    Created on : Apr 29, 2015, 12:38:21 PM
    Author     : ESa10969
--%>
<script>
    var regVal;
    $(document).ready(function() {
        regVal = $( "#registraAvion" ).validate({
                rules:{
                   numTur: {
                      required: true,
                      number: true
                    },
                    numEco: {
                      required: true,
                      number: true
                    },
                    numBuss: {
                      required: true,
                      number: true
                    },
                    compania: {
                      required: true
                    }
                },
                messages: {
                    numTur: {
                        required: "El numero de plazas turista es obligatorio",
                        number: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    numEco: {
                        required: "El numero de plazas economicas es obligatorio",
                        number: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    numBuss: {
                        required: "El numero de plazas turista es obligatorio",
                        number: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    compania: "El nombre de la compañía es obligatorio"
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
                        <div class="panel-title">Avion</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="registraAvion" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/altasManuales?avion=1'/>" method="post">
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="descripcion" class="col-md-3 control-label">Descripción</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="descripcionCre" name="descripcion" placeholder="Descripción">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numTur" class="col-md-3 control-label">Pl. Turista</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="numTur" name="numTur" placeholder="Pl. Turista">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numEco" class="col-md-3 control-label">Pl. Economica</label>
                                <div class="col-md-9">
                                    <input type="text" id="numEco" class="form-control" name="numEco" placeholder="Pl. Turista">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numBuss" class="col-md-3 control-label">Pl. Bussines</label>
                                <div class="col-md-9">
                                    <input type="text" id="numBuss" class="form-control" name="numBuss" placeholder="Pl. Bussines">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="compania" class="col-md-3 control-label">Compañía</label>
                                <div class="col-md-9">
                                    <input type="text" id="compania" class="form-control" name="compania" placeholder="Compañía">
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
                            <input type="hidden" name="refAvion" id="refAvion" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                     </div>
                </div>   
     </div> 
</div>
