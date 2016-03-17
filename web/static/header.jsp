<%-- 
    Document   : header
    Created on : 12-abr-2015, 16:12:29
    Author     : David
--%>
<script>
    function formSubmit() {
        waitingDialog.show(); 
        document.getElementById("logoutForm").submit();
    }
    var waitingDialog = (function ($) {
        // Creating modal dialog's DOM
            var $dialog = $(
                    '<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
                    '<div class="modal-dialog modal-m">' +
                    '<div class="modal-content">' +
                            '<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
                            '<div class="modal-body">' +
                                    '<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
                            '</div>' +
                    '</div></div></div>');

            return {
                    /**
                     * Opens our dialog
                     * @param message Custom message
                     * @param options Custom options:
                     * 				  options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
                     * 				  options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
                     */
                    show: function (message, options) {
                            // Assigning defaults
                            var settings = $.extend({
                                    dialogSize: 'm',
                                    progressType: ''
                            }, options);
                            if (typeof message === 'undefined') {
                                    message = 'Estamos trabajando en su petición...';
                            }
                            if (typeof options === 'undefined') {
                                    options = {};
                            }
                            // Configuring dialog
                            $dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
                            $dialog.find('.progress-bar').attr('class', 'progress-bar');
                            if (settings.progressType) {
                                    $dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
                            }
                            $dialog.find('h3').text(message);
                            // Opening dialog
                            $dialog.modal();
                    },
                    /**
                     * Closes dialog
                     */
                    hide: function () {
                            $dialog.modal('hide');
                    }
            }

    })(jQuery);
</script>
<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
</form>
<div class="top" id="header">
    <div class="container-fluid">
        <div class="row" style="padding-top: 30px;">
            <div id="headText" class="col-sm-7">
                <a href="index.jsp"><img src="css/img/logoAgencia.gif"/></a>
                <h1 id="firstPart">EASY</h1> <h1>TRAVEL</h1>
            </div>
            <div id="headLogin" class="col-sm-3 col-sm-offset-1">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <h4>
                        Usuario : <a href="perfil.jsp">${pageContext.request.userPrincipal.name} </a>| 
                                 <a href="javascript:formSubmit()"> Logout</a>
                    </h4>
		</c:if>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <h2>
                        <a class="btn btn-success" style="float: right;" href="login.jsp">Login</a>
                    </h2>
		</c:if>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-default">
    <ul class="nav navbar-nav">
        <li><a href="vuelos.jsp">Vuelos</a></li>
        <li><a href="hoteles.jsp">Hoteles</a></li>
        <li><a href="ultimaHora.jsp">Última Hora</a></li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin
              <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="gestionAdminComp.jsp">Gestión Admin. Compañías</a></li>
                <li><a href="gestionAero.jsp">Gestión Aeropuertos</a></li>
                <li><a href="gestionComp.jsp">Gestión Compañias</a></li>
              </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_AEREA', 'ROLE_HOTELERA')">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin Compañía
              <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <sec:authorize access="hasRole('ROLE_HOTELERA')">
                    <li><a href="gestionHoteles.jsp">Gestión Hoteles</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_AEREA')">
                    <li><a href="gestionVuelos.jsp">Gestión Vuelos</a></li>
                    <li><a href="gestionAviones.jsp">Gestión Aviones</a></li>
                </sec:authorize>
                <li><a href="inventario.jsp">Inventario</a></li>
              </ul>
            </li>
        </sec:authorize>
    </ul>
</nav>
