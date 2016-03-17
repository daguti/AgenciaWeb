<%-- 
    Document   : ofertaVuelos
    Created on : 12-abr-2015, 17:12:27
    Author     : David
--%>
<script>
    $(document).ready(function() {
        waitingDialog.show();
        table = $('#inventarioVuelos').dataTable({
        "processing": true,
        "ajax": {
            "url": "InventarioTableLoader?vuelos=1",
            "dataSrc": "vuelos",
            "type": "GET"
        }
        });
        $('#inventarioVuelos tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $('#delete').click( function () {
            var first =  $("td:eq(0)", "tbody tr.selected").text();
            alert(first);
            table.row('.selected').remove().draw( false );
        });
        waitingDialog.hide();
        $("form").submit(function() {
            waitingDialog.show(); 
        });
    });  
</script>
<div class="panel panel-primary">
   <div class="panel-heading">
       <span class="glyphicon glyphicon-file"></span>
      <h3 class="panel-title" style="display: inline-block;">
         Inventario
      </h3>
   </div>
   <div class="table-responsive">
       <table id="inventarioVuelos" class="table table-striped table-bordered" cellSpacing="0">
            <thead>
                <tr>
                    <th>Origen</th>
                    <th>Destino</th>
                    <th>Fecha Vuelo</th>
                    <th>Disponible Turista</th>
                    <th>Reservado Turista</th>
                    <th>Precio Turista(Eur)</th>
                    <th>Disponible Economica</th>
                    <th>Reservado Economica</th>
                    <th>Precio Economica(Eur)</th>
                    <th>Disponible Bussines</th>
                    <th>Reservado Bussines</th>
                    <th>Precio Bussines(Eur)</th>
                </tr>
            </thead>
        </table> 
   </div>
</div>
