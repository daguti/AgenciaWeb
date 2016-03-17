<%-- 
    Document   : buscadorVuelos
    Created on : 12-abr-2015, 18:06:39
    Author     : David
--%>

<script>
    $(function() {
        $('#descripcion').autocomplete({
            source: function(request, response) {
              $.get( "filtros?descAvion='ok'", function( data ) {
                    var result = "";
                    var match = $("#descripcion").val();
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
    });

</script>
<div class="form-group">
    <label for="descripcion" class="col-sm-1">Descripcion</label>
    <div class="col-sm-4">
        <input id="descripcion" class="form-control" placeholder="Descripcion">
    </div>
</div>
<div class="form-group">
    <label for="plazasDesdeTur" class="col-sm-1" style="margin-right: -25px">Desde Tur.</label>
    <div class="col-sm-1" style="width: 30px;">
        <input id="plazasDesdeTur" class="form-control" placeholder="Turista">
    </div>
    <label for="plazasHastaTur" class="col-sm-1" style="margin-right: -70px">Hasta</label>
    <div class="col-sm-1" style="width: 30px;">
        <input id="plazasHastaTur" class="form-control" placeholder="Turista">
    </div>
    <label for="plazasDesdeEco" class="col-sm-1" style="margin-right: -25px">Desde Eco.</label>
    <div class="col-sm-1" style="width: 30px;">
        <input id="plazasDesdeEco" class="form-control" placeholder="Economica">
    </div>
    <label for="plazasHastaEco" class="col-sm-1" style="margin-right: -70px">Hasta</label>
    <div class="col-sm-1" style="width: 30px;">
        <input id="plazasHastaEco" class="form-control" placeholder="Economica">
    </div>
    <label for="plazasDesdeBus" class="col-sm-1" style="margin-right: -25px">Desde Bus.</label>
    <div class="col-sm-1" style="width: 30px;">
        <input id="plazasDesdeBus" class="form-control" placeholder="Bussines">
    </div>
    <label for="plazasHastaBus" class="col-sm-1" style="margin-right: -70px">Hasta</label>
    <div class="col-sm-1" style="width: 30px;">
        <input id="plazasHastaBus" class="form-control" placeholder="Bussines">
    </div>
</div>
<div class="form-group">
    <div class="col-sm-4">
        <a onclick="filtraDatos()" class="btn btn-primary">Buscar</a>
        <a onClick="viewCreate()" class="btn btn-primary">Crear</a>
        <a id="modify" class="btn btn-primary">Modificar</a>
        <a id="delete" class="btn btn-primary">Eliminar</a>
    </div>
</div>