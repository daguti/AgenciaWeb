<%-- 
    Document   : buscadorVuelos
    Created on : 12-abr-2015, 18:06:39
    Author     : David
--%>

<script>
    $(function() {
        $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/MM/yyyy" );
        $('#origenVuelo').autocomplete({
            source: function(request, response) {
              $.get( "filtros?aeropuertos='ok'", function( data ) {
                    var result = "";
                    var match = $("#origenVuelo").val();
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
        $('#destinoVuelo').autocomplete({
            source: function(request, response) {
              $.get( "filtros?aeropuertos='ok'", function( data ) {
                    var result = "";
                    var match = $("#destinoVuelo").val();
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
        $( "#datepicker" ).datepicker({
            dateFormat:"dd-mm-yy"
        });
    });

</script>
<div class="form-group">
    <label for="origenVuelo" class="col-sm-1">Origen</label>
    <div class="col-sm-4">
        <input id="origenVuelo" class="form-control" placeholder="Origen">
    </div>
    <label for="destinoVuelo" class="col-sm-1">Destino</label>
    <div class="col-sm-4">
        <input id="destinoVuelo" class="form-control" placeholder="Destino">
    </div>
</div>
<div class="form-group">
    <label for="datepicker" class="col-sm-1">Fecha Salida: </label>
    <div class="col-sm-2">
        <input type="text" id="datepicker" class="form-control col-sm-2">
    </div>
    <div class="col-sm-2">
        <a onclick="filtraDatos()" class="btn btn-primary">Buscar</a>
        <a id="reservar" class="btn btn-primary">Reservar</a>
    </div>
</div>