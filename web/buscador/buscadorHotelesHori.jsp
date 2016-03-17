<%-- 
    Document   : buscadorVuelos
    Created on : 12-abr-2015, 18:06:39
    Author     : David
--%>

<script>
    $(function() {
        $('#destino').autocomplete({
            source: function(request, response) {
              $.get( "filtros?destino='ok'", function( data ) {
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
        $( "#entrada" ).datepicker({
            dateFormat:"dd-mm-yy"
        });
        $( "#salida" ).datepicker({
            dateFormat:"dd-mm-yy"
        });
    });

</script>
<div class="form-group">
    <label for="destino" class="col-sm-1">Destino</label>
    <div class="col-sm-4">
        <input id="destinoVuelo" class="form-control" placeholder="Destino">
    </div>
</div>
<div class="form-group">
    <label for="entrada" class="col-sm-1">Entrada </label>
    <div class="col-sm-2">
        <input type="text" id="entrada" class="form-control col-sm-2">
    </div>
    <label for="salida" class="col-sm-1">Salida: </label>
    <div class="col-sm-2">
        <input type="text" id="salida" class="form-control col-sm-2">
    </div>
    <div class="col-sm-2">
        <a onclick="filtraDatos()" class="btn btn-primary">Buscar</a>
        <a id="reservar" class="btn btn-primary">Reservar</a>
    </div>
</div>