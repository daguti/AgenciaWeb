<%-- 
    Document   : crearUsuario
    Created on : Apr 29, 2015, 12:38:21 PM
    Author     : ESa10969
--%>
<script>
    var regVal;
    $(document).ready(function() {
        $('#crearTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show');
        });
        regVal = $( "#registraHotel" ).validate({
                rules:{
                    nombre: {
                      required: true
                    },
                    categoria: {
                      required: true
                    },
                    compania: {
                      required: true
                    },
                    direccion: {
                      required: true
                    },
                    ciudad: {
                      required: true
                    },
                    numInd: {
                      required: true,
                      numeric: true
                    },
                    numDob: {
                      required: true,
                      numeric: true
                    },
                    numSup: {
                      required: true,
                      numeric: true
                    },
                    preInd: {
                      required: true,
                      numeric: true
                    },
                    preDob: {
                      required: true,
                      numeric: true
                    },
                    preSup: {
                      required: true,
                      numeric: true
                    }
                },
                messages: {
                    nombre: "El nombre del Hotel es obligatorio",
                    categoria: "la categoria del Hotel es obligatoria",
                    compania: "El nombre de la compañia es obligatorio",
                    direccion: "La dirección del hotel es obligatoria",
                    ciudad: "La ciudad del Hotel es obligatorio",
                    numInd: {
                      required: "El numero de habitaciones individuales es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    numDob: {
                      required: "El numero de habitaciones dobles es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    numSup: {
                      required: "El numero de habitaciones superiores es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    preInd: {
                      required: "El precio de habitaciones individuales es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    preDob: {
                      required: "El precio de habitaciones dobles es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    },
                    preSup: {
                      required: "El precio de habitaciones superiores es obligatorio",
                      numeric: "Debe introducir un numero (Decimal 1.5 no 1,5)"
                    }
                },
                submitHandler: function(form) {
                    $(form).ajaxSubmit();
                }
            });
        });
</script>
<div id="containerCrear">
    <div role="tabpanel">
        <ul class="nav nav-tabs" role="tablist" id="crearTab">
            <li role="presentation" class="active"><a href="#registraHotel" aria-controls="registraHotel" role="tab" data-toggle="tab">Registro Hotel</a></li>
            <li role="presentation"><a href="#ofertaHotel" aria-controls="ofertaHotel" role="tab" data-toggle="tab">Oferta Hotel</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="registraHotel">
                <%@include file='registroHotel.jsp'%> 
            </div>
            <div role="tabpanel" class="tab-pane" id="ofertaHotel">
                <%@include file='ofertaHotel.jsp'%>
            </div>
        </div>
    </div>
</div>
