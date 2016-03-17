<%-- 
    Document   : ofertaVuelos
    Created on : 12-abr-2015, 17:12:27
    Author     : David
--%>
<script>
    $(document).ready(function() {
        waitingDialog.show();
        table = $('#inventarioHoteles').dataTable({
        "processing": true,
        "ajax": {
            "url": "InventarioTableLoader?hoteles=1",
            "dataSrc": "hoteles",
            "type": "GET"
        }
        });
        $('#inventarioHoteles tbody').on( 'click', 'tr', function () {
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
       <table id="inventarioHoteles" class="table table-striped table-bordered" cellSpacing="0">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Fecha</th>
                    <th>Disponible Individual</th>
                    <th>Reservado Individual</th>
                    <th>Precio Individual(Eur)</th>
                    <th>Disponible Doble</th>
                    <th>Reservado Doble</th>
                    <th>Precio Doble(Eur)</th>
                    <th>Disponible Superior</th>
                    <th>Reservado Superior</th>
                    <th>Precio Superior(Eur)</th>
                </tr>
            </thead>
        </table> 
   </div>
</div>
