<%-- 
    Document   : buscadorVuelos
    Created on : 12-abr-2015, 18:06:39
    Author     : David
--%>

<script>
    $(function() {
        $('#nombre').autocomplete({
            source: function(request, response) {
              $.get( "filtros?nombres='ok'", function( data ) {
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
        $('#apellidos').autocomplete({
            source: function(request, response) {
              $.get( "filtros?apellidos='ok'", function( data ) {
                    var result = "";
                    var match = $("#apellidos").val();
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
        
        $('#compania').autocomplete({
            source: function(request, response) {
              $.get( "filtros?companias='ok'", function( data ) {
                    var result = "";
                    var match = $("#compania").val();
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
    <label for="nombre" class="col-sm-1">Nombre: </label>
    <div class="col-sm-2">
        <input id="nombre" class="form-control" placeholder="Nombre">
    </div>
    <label for="apellidos" class="col-sm-1">Apellidos: </label>
    <div class="col-sm-2">
        <input id="apellidos" class="form-control" placeholder="Apellidos">
    </div>
</div>
<div class="form-group">
    <label for="compania" class="col-sm-1">Compañia: </label>
    <div class="col-sm-2">
        <input id="compania" class="form-control" placeholder="Compañia">
    </div>
    <div class="col-sm-4">
        <a onclick="filtraDatos()" class="btn btn-primary">Buscar</a>
        <a onClick="viewCreate()" class="btn btn-primary">Crear</a>
        <a id="modify" class="btn btn-primary">Modificar</a>
        <a id="delete" class="btn btn-primary">Eliminar</a>
    </div>
</div>