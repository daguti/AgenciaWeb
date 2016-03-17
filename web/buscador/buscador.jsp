<%-- 
    Document   : buscador
    Created on : 12-abr-2015, 17:39:45
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    $('#myTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
    function buscaVuelos(){
        var data;
        var i = 0;
        $.ajax({
            url : 'VuelosTableLoader?filtro=' + $("#origenVuelo").val() 
                                                     + '$$' + $("#destinoVuelo").val()
                                                     + '$$' + $("#fecVuelo").val(),
            async: false,           
            success : function(responseText) {   
                data = responseText; 
            }        
        });
        location.href="vuelos.jsp";
    }
    function buscarHoteles(){
        var data;
        var i = 0;
        $.ajax({
            url : 'HotelesTableLoader?filtro=' + $("#destinoHotel").val() 
                                                     + '$$' + $("#entrada").val()
                                                     + '$$' + $("#salida").val(),
            async: false,           
            success : function(responseText) {   
                data = responseText; 
            }        
        });
        location.href="hoteles.jsp";
    }
</script>
<div id="buscador">
    <div role="tabpanel">
        <ul class="nav nav-tabs" role="tablist" id="searchTab">
            <li role="presentation" class="active"><a href="#vuelos" aria-controls="vuelos" role="tab" data-toggle="tab">Vuelos</a></li>
            <li role="presentation"><a href="#hoteles" aria-controls="hoteles" role="tab" data-toggle="tab">Hoteles</a></li>
            <li role="presentation"><a href="#vueloshoteles" aria-controls="vueloshoteles" role="tab" data-toggle="tab">Vuelo+Hotel</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="vuelos">
                <%@include file='buscadorVuelos.jsp'%> 
            </div>
            <div role="tabpanel" class="tab-pane" id="hoteles">
                <%@include file='buscadorHoteles.jsp'%>
            </div>
        </div>
    </div>
</div>
