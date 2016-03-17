<script>
    $( "#inicio" ).datepicker({
        dateFormat:"dd-mm-yy"
    });
    $( "#fin" ).datepicker({
        dateFormat:"dd-mm-yy"
    });
</script>
<div class="container">    
    <div id="signupbox" style="margin-top:10px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Oferta Hotel</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="registraHotel" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/altasManuales?ofertaHotel=1'/>" method="post">
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group">
                                <h4 class="col-md-12">Precio Habitaciones</h4>
                            </div>
                            <div class="form-group">
                                <label for="preInd" class="col-sm-3 control-label">Individual</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preInd" class="form-control" name="preInd" placeholder="Ind">
                                </div>
                                <label for="preDob" class="col-sm-3 control-label">Doble</label>
                                <div class="col-sm-2">
                                    <input type="text" id="numDob" class="form-control" name="preDob" placeholder="Dob">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="preSup" class="col-sm-3 control-label">Superior</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preSup" class="form-control" name="preSup" placeholder="Sup">
                                </div>
                            </div>
                            <div class="form-group">
                                <h4 class="col-md-12">Fechas Oferta</h4>
                            </div>
                            <div class="form-group">
                                <label for="inicio" class="col-sm-1">Inicio: </label>
                                <div class="col-sm-2">
                                    <input type="text" id="inicio" name="inicio" class="form-control col-sm-2">
                                </div>
                                <label for="fin" class="col-sm-1">Fin: </label>
                                <div class="col-sm-2">
                                    <input type="text" id="fin" name="fin" class="form-control col-sm-2">
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