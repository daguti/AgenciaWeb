<%-- 
    Document   : crearUsuario
    Created on : Apr 29, 2015, 12:38:21 PM
    Author     : ESa10969
--%>
<script>
    var regVal;
    $(document).ready(function() {
        $( "#fecVuelo" ).datepicker({
        dateFormat:"dd-mm-yy"
        });
        $('#horaSalida').timepicker({ 'timeFormat': 'H:i:s' });
        $('#duracion').timepicker({ 'timeFormat': 'H:i:s' });
        $('#origen').autocomplete({
            source: function(request, response) {
              $.get( "filtros?aeropuertos='ok'", function( data ) {
                    var result = "";
                    var match = $("#origen").val();
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
        $('#destino').autocomplete({
            source: function(request, response) {
              $.get( "filtros?aeropuertos='ok'", function( data ) {
                    var result = "";
                    var match = $("#destino").val();
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
        regVal = $( "#registraVuelo" ).validate({
                rules:{
                    refAvion: {
                      required: true,
                      numeric: true
                    },
                    fecVuelo: {
                      required: true,
                      date: true
                    },
                    preTur: {
                      required: true,
                      numeric: true
                    },
                    preEco: {
                      required: true,
                      numeric: true
                    },
                    preBuss: {
                      required: true,
                      numeric: true
                    },
                    origen: {
                      required: true
                    },
                    destino: {
                      required: true,
                    }
                },
                messages: {
                    refAvion: "La referencia del avion es obligatoria",
                    origen: "El origen del vuelo es obligatorio",
                    destino: "El destino del vuelo es obligatorio",
                    fecVuelo: {
                      required: "La fecha del vuelo es obligatoria",
                      date: "Debe introducir una fecha valida (Ej. 12-12-2012)"
                    },
                    horaSalida: {
                      required: "La hora de salida del vuelo es obligatoria",
                      date: "Debe introducir una fecha valida (Ej. 14:30:00)"
                    },
                    duracion: {
                      required: "La duración del vuelo es obligatoria",
                      date: "Debe introducir una fecha valida (Ej. 14:30:00)"
                    },
                    preTur: {
                      required: "El precio de plazas clase Turista es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    preEco: {
                      required: "El precio de plazas clase Economica es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    preBuss: {
                      required: "El precio de plazas clase Bussines es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
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
                        <div class="panel-title">Hotel</div>
                    </div>  
                    <div class="panel-body" >
                        <form id="registraHotel" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/altasManuales?vuelo=1'/>" method="post">
                            <div id="signupalert" style="display:none" class="alert alert-danger">
                                <p>Error:</p>
                                <span></span>
                            </div>
                            <div class="form-group">
                                <label for="refAvion" class="col-md-3 control-label">Ref. Avion</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="refAvion" name="refAvion" placeholder="Ref. Avion">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fecVuelo" class="col-md-3 control-label">Fec. Vuelo</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="fecVuelo" name="fecVuelo" placeholder="Fec. Vuelo">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="horaSalida" class="col-md-3 control-label">Hora Salida</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="horaSalida" name="horaSalida" placeholder="Hora Salida">
                                </div>
                                <label for="duracion" class="col-md-3 control-label">Duración</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="duracion" name="duracion" placeholder="Duracion">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="origen" class="col-md-3 control-label">Origen</label>
                                <div class="col-md-9">
                                    <input type="text" id="origen" class="form-control" name="origen" placeholder="Origen">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="destino" class="col-md-3 control-label">Destino</label>
                                <div class="col-md-9">
                                    <input type="text" id="destino" class="form-control" name="destino" placeholder="Destino">
                                </div>
                            </div>
                            <div class="form-group">
                                <h4 class="col-md-12">Precio Plazas</h4>
                            </div>
                            <div class="form-group">
                                <label for="preTur" class="col-sm-3 control-label">Turista</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preTur" class="form-control" name="preTur" placeholder="TUR">
                                </div>
                                <label for="preEco" class="col-sm-3 control-label">Economica</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preEco" class="form-control" name="preEco" placeholder="ECO">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="preBuss" class="col-sm-3 control-label">Bussines</label>
                                <div class="col-sm-2">
                                    <input type="text" id="preBuss" class="form-control" name="preBuss" placeholder="BUS">
                                </div>
                            </div>
                            <div class="form-group">
                                <h4 class="col-md-12">Oferta</h4>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="ofertaVuelo" name="ofertaVuelo" value="1"> Oferta
                                </label>
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
                            <input type="hidden" name="refVuelo" id="refVuelo" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                     </div>
                </div>   
     </div> 
</div>
