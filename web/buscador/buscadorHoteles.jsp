<%-- 
    Document   : buscadorVuelos
    Created on : 12-abr-2015, 18:06:39
    Author     : David
--%>

<script>
    $(function() {
        $('#destinoHotel').autocomplete({
            source: function(request, response) {
              $.get( "filtros?destinos='ok'", function( data ) {
                    var result = "";
                    var match = $("#destinoHotel").val();
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
    <label for="destinoHotel">Destino</label>
    <input id="destinoHotel" class="form-control" placeholder="Destino">
</div>
<div class="form-group">
    <label for="entrada">Fecha Entrada: </label>
    <input type="text" id="entrada" class="form-control">
</div>
<div class="form-group">
    <label for="salida">Fecha Salida: </label>
    <input type="text" id="salida" class="form-control">
</div>
<div class="form-group">
    <a onclick="buscarHoteles()" class="btn btn-primary">Buscar</a>
</div>