<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<section id="barra_acciones" class="container captura_items">
    <div class="row">
        <div class="col-sm-3 pull-right btn_nuevo_item">
            <!-- Button para MODAL [+ nuevo usuario] -->
            <button onclick="javascript:showUserModal('', '', '', '')" type="button" class="btn_chico btn btn-success pull-right side_paddings" 
                    data-toggle="tooltip" title="Agregar un nuevo usuario" data-placement="left">
                <span class="glyphicon glyphicon-plus"></span>
                Nuevo usuario
            </button>
        </div>
    </div>
</section>

<main>
    <article class="container">
        <div class="row">
            <div class="col-sm-12 pull-left">
	            <!-- Mensajes -->
	            <c:if test="${not empty alertMsg}">
                    <c:choose>
                        <c:when test="${alertMsg.status eq 'FAIL'}">
                            <div class="alert alert-danger flash" role="alert">
		                        <button type="button" class="close" data-dismiss="alert" aria-label="Cerrar">
		                            <span aria-hidden="true">&times;</span>
		                        </button>
		                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		                        <span class="sr-only">Error: </span> ${alertMsg.message}
		                    </div>
                        </c:when>
                        <c:when test="${alertMsg.status eq 'SUCCESS'}">
                            <div class="form-group alert alert-success flash" role="alert">
		                        <button type="button" class="close" data-dismiss="alert" aria-label="Cerrar">
		                            <span aria-hidden="true">&times;</span>
		                        </button>
		                        <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
		                        <span class="sr-only">Mensaje: </span> ${alertMsg.message}
		                    </div>
                        </c:when>
                    </c:choose>
                </c:if>
	        </div>
        </div>
        <section class="table-responsive">
            <table class="table table-condensed table-hover">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Perfiles</th>
                        <th>Estado</th>
                        <th class="text-center">Editar</th> 
                        <th class="text-center">Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty usersList}">
                        <c:forEach items="${usersList}" var="currUser">
	                    <tr>
	                        <td>${currUser.userName}</td>
	                        <td>
	                            <c:if test="${not empty currUser.authorities}">
	                                ${currUser.authorities[0].authority}
	                            </c:if>
                            </td>
	                        <td>
	                            <c:if test="${currUser.enabled eq true}">Habilitado</c:if>
	                            <c:if test="${currUser.enabled eq false}">Deshabilitado</c:if>
                            </td>
	                        <td class="text-center">
	                            <a onclick="javascript:showUserModal('${currUser.userName}', '${currUser.password}', '${currUser.authorities[0].authority}', '${currUser.enabled}')"
	                                data-toggle="tooltip"
	                                title="Modificar usuario" 
	                                data-placement="left"
	                                id="edit_${currUser.userName}" 
	                                class="btn btn-primary btn_dark btn_tabla" 
	                                href="#"><i class="fa fa-pencil fa-lg"></i></a>
	                        </td>
	                        <td class="text-center">
	                            <c:if test="${pageContext.request.userPrincipal.name != currUser.userName}">
		                            <a onclick="javascript:confirmDelete('${currUser.userName}')" 
	                                    data-toggle="tooltip" 
                                        title="Eliminar usuario" 
	                                    data-placement="left" 
	                                    id="delete_${currUser.userName}" 
	                                    class="btn btn-danger btn_chico btn_tabla" 
	                                    style="background-color:red;" 
	                                    href="#"><i class="fa fa-trash fa-lg"></i></a>
	                            </c:if>
	                        </td>
	                    </tr>
	                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>No se encontraron resultados</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                    
                </tbody>
            </table>
        </section>
    </article>
</main>


<!-- MODAL [+ nuevo usuario] -->
<div class="modal fade" id="modal_nuevo_usuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <input type="hidden" id="userAction" value="new" />
        <form:form modelAttribute="user" id="userForm" method="POST">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="catModalTitle" class="modal-title">Agregar usuario</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">            
                <!-- CAMPO 01 / Nombre del usuario   -->
                <label for="txt_nombre_usuario">Nombre</label>
                <form:input path="userName" id="txt_nombre_usuario" type="text" class="form-control no_spaces_field" 
                    placeholder="" data-toggle="tooltip" data-placement="top" title="Indique el nombre de usuario" 
                    maxlength="15"/>
                
                <!-- CAMPO 02 / Descripción   -->
                <label for="txt_descripcion">Contraseña</label>
                <form:input path="password" id="txt_password" type="password"  class="form-control" placeholder="" 
                    data-toggle="tooltip" data-placement="top" title="Indique la contraseña del usuario"
                    maxlength="60"/>
               
                <!-- CAMPO 03 / Perfil del usuario   -->
                <label for="txt_profile">Perfil</label>
                <div class="row">
                    <div class="col-xs-8">
                        <form:input path="authorities[0].authority" id="txt_profile" type="text" class="form-control" 
                                value="" data-toggle="tooltip" data-placement="top" title="Indique el perfil del usuario" />
                    </div>

                    <div class="col-xs-4">
                        <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            Perfiles <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <c:if test="${not empty authoritiesCatalog}"></c:if>
                            <c:forEach items="${authoritiesCatalog}" var="currAuthorityItem">
                                <li onclick="javascript:setSelectedAuthority('${currAuthorityItem}')"><a href="#">${currAuthorityItem}</a></li>
                            </c:forEach>                            
                        </ul>
                    </div>
                </div>
               
                <!-- CAMPO 04 / Estado del usuario   -->
                <label for="txt_enabled">Estado</label>   
                <div class="row">        
                    <div class="col-xs-8">
                        <form:input path="enabled" type="hidden" id="userEnabled" value="true" />
                        <input id="txt_enabled" type="text" class="form-control" readonly value="Habilitado" 
                                data-toggle="tooltip" data-placement="top" title="Indique el estado del usuario">
                    </div>

                    <div class="col-xs-4">
                        <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            Estado <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li onclick="javascript:setEnabledUser('true')"><a href="#">Habilitado</a></li>
                            <li onclick="javascript:setEnabledUser('false')"><a href="#">Deshabilitado</a></li>                            
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnSaveUser" class="btn btn-success pull-right">
                    <span class="glyphicon glyphicon-ok"></span>
                    Guardar
                </button>

                <button type="button" class="btn btn-primary btn_dark"  data-dismiss="modal">
                    <span class="glyphicon glyphicon-remove"></span>
                    Cancelar
                </button>
            </div>
        </form:form>
        </div>
    </div>
</div>


<!-- MODAL [eliminar usuario] -->
<div class="modal fade" id="modal_confirm_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Eliminar usuario</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error: </span> ¿Realmente desea eliminar el usuario&nbsp;
                    <label id="userToDelete"></label>?
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteUser" class="btn btn-success pull-right">
                    <span class="glyphicon glyphicon-ok"></span>
                    Eliminar
                </button>

                <button type="button" class="btn btn-primary btn_dark"  data-dismiss="modal">
                    <span class="glyphicon glyphicon-remove"></span>
                    Cancelar
                </button>
            </div>
        </div>
    </div>
</div>


<!-- -------------- SCRIPTS ---------------- -->
<script src="<c:url value='/resources/js/utils.js'/>"></script>
<script src="<c:url value='/resources/js/users.js'/>"></script>
<!-- -------------- SCRIPTS ---------------- -->
