<script>
    $('#ciudad').autocomplete({
            source: function(request, response) {
              $.get( "filtros?destino='ok'", function( data ) {
                    var result = "";
                    var match = $("#ciudad").val();
                    var values = data.split(",");
                    var i = 0;
                    while(i < values.length) {
                        if(values[i].indexOf(match) > -1) {
                            result += values[i] + ",";
                        }
                        i++;
                    }
                    result.substring(0, result.lastIndexOf(","));
                    response(result.split(","));
              });  
            }
        });
</script>
<div class="container">    
    <div id="signupbox" style="margin-top:10px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Registro Hotel</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="registraHotel" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/altasManuales?hotel=1'/>" method="post">
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="nombreCre" name="nombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="categoria" class="col-md-3 control-label">Categoria</label>
                                <div class="col-md-9">
                                    <select id="categoria" name="categoria" class="form-control">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                      </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="compania" class="col-md-3 control-label">Compañia</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="compania" name="compania" placeholder="Compañia">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="descripcion" class="col-md-3 control-label">Descripcion</label>
                                <div class="col-md-9">
                                    <input type="text" id="descripcion" class="form-control" name="descripcion" placeholder="Descripcion">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="direccion" class="col-md-3 control-label">Dirección</label>
                                <div class="col-md-9">
                                    <input type="text" id="direccion" class="form-control" name="direccion" placeholder="Dirección">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="ciudad" class="col-md-3 control-label">Ciudad</label>
                                <div class="col-md-9">
                                    <input type="text" id="ciudad" class="form-control" name="ciudad" placeholder="Ciudad">
                                </div>
                            </div>
                            <div class="form-group">
                                <h4 class="col-md-12">Numero Habitaciones</h4>
                            </div>
                            <div class="form-group">
                                <label for="numInd" class="col-sm-3 control-label">Individual</label>
                                <div class="col-sm-2">
                                    <input type="text" id="numIndC" class="form-control" name="numInd" placeholder="Ind">
                                </div>
                                <label for="numDob" class="col-sm-3 control-label">Doble</label>
                                <div class="col-sm-2">
                                    <input type="text" id="numDobC" class="form-control" name="numDob" placeholder="Dob">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numSup" class="col-sm-3 control-label">Superior</label>
                                <div class="col-sm-2">
                                    <input type="text" id="numSupC" class="form-control" name="numSup" placeholder="Sup">
                                </div>
                            </div>
                            <div class="form-group">
                                <h4 class="col-md-12">Precio Habitaciones</h4>
                            </div>
                            <div class="form-group">
                                <label for="preInd" class="col-sm-3 control-label">Individual</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preIndC" class="form-control" name="preInd" placeholder="Ind">
                                </div>
                                <label for="preDob" class="col-sm-3 control-label">Doble</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preDobC" class="form-control" name="preDob" placeholder="Dob">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="preSup" class="col-sm-3 control-label">Superior</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preSupC" class="form-control" name="preSup" placeholder="Sup">
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
                            <input type="hidden" name="nombreOld" id="nombreOld" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                     </div>
                </div>   
     </div> 
</div>