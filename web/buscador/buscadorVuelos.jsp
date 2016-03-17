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
    <label for="origenVuelo">Origen</label>
    <input id="origenVuelo" class="form-control" placeholder="Origen">
</div>
<div class="form-group">
    <label for="destinoVuelo">Destino</label>
    <input id="destinoVuelo" class="form-control" placeholder="Destino">
</div>
<div class="form-group">
    <label for="datepicker">Salida: </label>
    <input type="text" id="fecVuelo" class="form-control">
</div>
<div class="form-group">
    <a onclick="buscaVuelos()" class="btn btn-primary">Buscar</a>
</div>