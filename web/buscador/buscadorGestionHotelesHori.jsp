<%-- 
    Document   : buscadorVuelos
    Created on : 12-abr-2015, 18:06:39
    Author     : David
--%>

<script>
    $(function() {
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
        $('#nombre').autocomplete({
            source: function(request, response) {
              $.get( "filtros?hotel='ok'", function( data ) {
                    var result = "";
                    var match = $("#nombre").val();
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
    <label for="destinoVuelo" class="col-sm-1">Destino</label>
    <div class="col-sm-4">
        <input id="destinoVuelo" class="form-control" placeholder="Destino">
    </div>
    <label for="nombre" class="col-sm-1">Nombre</label>
    <div class="col-sm-4">
        <input id="nombre" class="form-control" placeholder="Nombre">
    </div>
</div>
<div class="form-group">
    <div class="col-sm-2">
        <button type="submit" class="btn btn-primary">Buscar</button>
    </div>
    <div class="col-sm-2">
        <a onClick="viewCreate()" class="btn btn-primary">Crear</a>
        <a id="modify" class="btn btn-primary">Modificar</a>
    </div>
</div>
