<%-- 
    Document   : buscadorVuelos
    Created on : 12-abr-2015, 18:06:39
    Author     : David
--%>

<script>
    $(function() {
        $('#nombre').autocomplete({
            source: function(request, response) {
              $.get( "filtros?aeropuertos='ok'", function( data ) {
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
        $('#ciudad').autocomplete({
            source: function(request, response) {
              $.get( "filtros?destinos='ok'", function( data ) {
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
    });

</script>
<div class="form-group">
    <label for="nombre" class="col-sm-1">Nombre</label>
    <div class="col-sm-4">
        <input id="nombre" class="form-control" placeholder="Nombre">
    </div>
    <label for="ciudad" class="col-sm-1">Ciudad</label>
    <div class="col-sm-4">
        <input id="ciudad" class="form-control" placeholder="Ciudad">
    </div>
    <div class="col-sm-4">
        <a onclick="filtraDatos()" class="btn btn-primary">Buscar</a>
        <a onClick="viewCreate()" class="btn btn-primary">Crear</a>
        <a id="modify" class="btn btn-primary">Modificar</a>
        <a id="delete" class="btn btn-primary">Eliminar</a>
    </div>
</div>