<div class="row">
    <div class="container-fluid">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Reserva</div>
            </div>  
            <div class="panel-body" >
                <form name="orderForm" method="POST" class="form-horizontal" role="form">

                    <input type="hidden" name="_flowExecutionId" value="<c:out value="${flowExecutionId}"/>"/>
                    <input type="hidden" name="_eventId" value="submit"/>
                    <div class="panel panel-info" id="ida">
                        <div class="panel-heading">
                            <div class="panel-title"><span class="glyphicon glyphicon-send"></span>Vuelo Ida</div>
                        </div>  
                        <div class="panel-body" >
                            <div class="row">
                                <label for="origen" class="col-sm-2 control-label vueloOrig">Origen</label>
                                <div class="col-sm-4 vueloOrig">
                                    <input type="text" class="form-control" id="origen" name="origen" disabled="true">
                                </div>
                                <label for="destino" class="col-sm-2 control-label">Destino</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="destino" name="destino" disabled="true">
                                </div>
                            </div>
                            <div class="row">
                                <label for="fecSalida" class="col-sm-2 control-label">Fec. Salida</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="fecVueloIda" name="fecVuelo" disabled="true">
                                </div>
                                <label for="hora" class="col-sm-2 control-label">Hora</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="hora" name="hora" disabled="true">
                                </div>
                                <label for="duracion" class="col-sm-2 control-label">Duración</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="duracion" name="duracion" disabled="true">
                                </div>
                            </div>
                            <div class="row">
                                <label for="numEco" class="col-sm-2 control-label">Num. Eco(<a id="preEco"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numEco" name="numEco" value="0">
                                </div>
                                <label for="numTur" class="col-sm-2 control-label">Num. Tur(<a id="preTur"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numTur" name="numTur" value="0">
                                </div>
                                <label for="numBuss" class="col-sm-2 control-label">Num. Buss(<a id="preBuss"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numBuss" name="numBuss" value="0">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-info" id="vuelta">
                        <div class="panel-heading">
                            <div class="panel-title"><span class="glyphicon glyphicon-send"></span>Vuelo Vuelta</div>
                        </div>  
                        <div class="panel-body" >
                            <div class="row">
                                <label for="origen" class="col-sm-2 control-label vueloOrig">Origen</label>
                                <div class="col-sm-4 vueloOrig">
                                    <input type="text" class="form-control" id="origenVu" name="origenVu" disabled="true">
                                </div>
                                <label for="destino" class="col-sm-2 control-label">Destino</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="destinoVu" name="destinoVu" disabled="true">
                                </div>
                            </div>
                            <div class="row">
                                <label for="fecVueloVu" class="col-md-2 control-label">Fec. Salida</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="fecVueloVu" name="fecVueloVu" disabled="true">
                                </div>
                                <label for="hora" class="col-sm-2 control-label">Hora</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="horaVu" name="horaVu" disabled="true">
                                </div>
                                <label for="duracion" class="col-sm-2 control-label">Duración</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="duracionVu" name="duracionVu" disabled="true">
                                </div>
                            </div>
                            <div class="row">
                                <label for="numEco" class="col-sm-2 control-label">Num. Eco(<a id="preEcoVu"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numEcoVu" name="numEcoVu" value="0">
                                </div>
                                <label for="numTur" class="col-sm-2 control-label">Num. Tur(<a id="preTurVu"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numTurVu" name="numTurVu" value="0">
                                </div>
                                <label for="numBuss" class="col-sm-2 control-label">Num. Buss(<a id="preBussVu"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numBussVu" name="numBussVu" value="0">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-info" id="hotel">
                        <div class="panel-heading">
                            <div class="panel-title"><span class="glyphicon glyphicon-home"></span>Hotel</div>
                        </div>  
                        <div class="panel-body" >
                            <div class="row">
                                <label for="destinoH" class="col-sm-2 control-label">Destino</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="destinoH" name="destinoH" disabled="true">
                                </div>
                                <label for="nombre" class="col-sm-2 control-label">Nombre</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="nombreH" name="nombreH" disabled="true">
                                </div>
                            </div>
                            <div class="row">
                                <label for="categoria" class="col-sm-2 control-label">Categoria</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="categoriaH" name="categoriaH" disabled="true">
                                </div>
                                <label for="fecSalida" class="col-sm-2 control-label">Fec. Llegada</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="fecLlegadaH" name="fecLlegadaH">
                                </div>
                                <label for="fecVuelta" class="col-sm-2 control-label">Fec. Salida</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="fecOutH" name="fecOutH">
                                </div>
                            </div>
                            <div class="row">
                                <label for="numInd" class="col-sm-2 control-label">Individual(<a id="preInd"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numInd" name="numInd" value="0">
                                </div>
                                <label for="numDob" class="col-sm-2 control-label">Doble(<a id="preDob"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numDob" name="numDob" value="0">
                                </div>
                                    <label for="numSup" class="col-sm-2 control-label">Superior(<a id="preSup"></a><span class="glyphicon glyphicon-euro"></span>)</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="numSup" name="numSup" value="0">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="add" style="margin-bottom: 10px;">
                        <div class="col-sm-offset-5 col-sm-3">
                            <a style="display: none;" data-toggle="modal" href="#modalIda" id="addIda" class="btn btn-info">Añadir Ida</a>
                            <a data-toggle="modal" href="#modalVuelta" id="addVuelta" class="btn btn-info">Añadir Vuelta</a>
                            <a data-toggle="modal" href="#modalHotel" id="addHotel" class="btn btn-info">Añadir Hotel</a>
                        </div>
                    </div>
                    <div class="panel panel-info" id="hotel">
                        <div class="panel-heading">
                            <div class="panel-title"><span class="glyphicon glyphicon-euro"></span>TOTAL</div>
                        </div>  
                        <div class="panel-body" >
                            <div class="row">
                                <div class="col-sm-offset-5 col-sm-2">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="total" name="total" disabled="true" value="0">
                                        <div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-offset-5 col-sm-2">
                            <a href="${flowExecutionUrl}&_eventId=submit" style="width: 100%;" id="subida" class="btn btn-info">Reservar </a>
                            <a href="${flowExecutionUrl}&_eventId=cancel" style="width: 100%;" class="btn btn-danger" id="cancelar">Cancelar</a>
                            <a href="/AgenciaWeb/perfil.jsp" style="width: 100%;display: none;" class="btn btn-info" id="atras">Atras</a>
                            <a href="/AgenciaWeb/perfil.jsp" style="width: 100%;display: none;" class="btn btn-danger" id="borrar">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalIda" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-send"></span>Vuelo Ida</h4>
        </div>
        <div class="modal-body">
            <%@include file='../tablas/vuelos.jsp'%>
        </div>
        <div class="modal-footer">
          <button id="eligeIda" type="button" class="btn btn-default" data-dismiss="modal">Elegir</button>
        </div>
      </div>
    </div>
</div>
                            
<div class="modal fade" id="modalVuelta" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-send"></span>Vuelo Vuelta</h4>
        </div>
        <div class="modal-body">
            <%@include file='../tablas/vuelos2.jsp'%>
        </div>
        <div class="modal-footer">
          <button id="eligeVuelta" type="button" class="btn btn-default" data-dismiss="modal">Elegir</button>
        </div>
      </div>
    </div>
</div>
<div class="modal fade" id="modalHotel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-home"></span>Hotel</h4>
        </div>
        <div class="modal-body">
            <%@include file='../tablas/hoteles.jsp'%>
        </div>
        <div class="modal-footer">
          <button id="eligeHotel" type="button" class="btn btn-default" data-dismiss="modal">Elegir</button>
        </div>
      </div>
    </div>
</div>

