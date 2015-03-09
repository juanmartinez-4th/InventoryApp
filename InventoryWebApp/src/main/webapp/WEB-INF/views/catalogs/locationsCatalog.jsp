<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<input type="hidden" id="appContextPath" value="${pageContext.request.contextPath}">

<section id="barra_acciones" class="container">
    <div class="row">
        <section id="nav_nivel_2">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/adminCategories">Categorías</a></li>
                <li><a href="" class="active">Ubicaciones</a></li>
                <li><a href="${pageContext.request.contextPath}/adminProjects">Proyectos</a></li>
                <li><a href="${pageContext.request.contextPath}/adminUnitsOfMeasure">Unidades de medida</a></li>
            </ul>
        </section>
        
        <div class="col-sm-3 pull-right">
    
            <!-- Button para MODAL [+ nueva ubicación] -->
            <button onclick="javascript:showLocationModal(0, '', '')" type="button" class="btn_chico btn btn-success pull-right side_paddings" data-toggle="modal">
                <span class="glyphicon glyphicon-plus"></span>
                Nueva Ubicación
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
                            <div class="alert alert-danger" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Cerrar">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <span class="sr-only">Error: </span> ${alertMsg.message}
                            </div>
                        </c:when>
                        <c:when test="${alertMsg.status eq 'SUCCESS'}">
                            <div class="form-group alert alert-success" role="alert">
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
            <table id="tableLocations" class="table table-condensed table-hover sortable">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th class="text-center">Editar</th>
                        <th class="text-center">Eliminar</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
	                    <c:when test="${not empty locationsList}">
	                        <c:forEach items="${locationsList}" var="currLocation">
		                    <tr>
		                        <td>${currLocation.name}</td>
		                        <td>${currLocation.description}</td>
		                        <td class="text-center">
		                            <a onclick="javascript:showLocationModal(${currLocation.id}, '${currLocation.name}', '${currLocation.description}')" 
		                                data-toggle="modal" 
                                        id="edit_${currLocation.id} }"
		                                class="btn btn-primary btn_dark btn_tabla" 
		                                href="#"><i class="fa fa-pencil fa-lg"></i></a></td>
		                        <td class="text-center">
	                                <a onclick="javascript:confirmDelete(${currLocation.id}, '${currLocation.name}')"
	                                    data-toggle="modal" 
	                                    id="delete_${currLocation.id} }" 
	                                    class="btn btn-danger btn_chico btn_tabla" 
	                                    style="background-color:red;" 
	                                    href="#"><i class="fa fa-trash fa-lg"></i></a>
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


<!-- MODAL [+ nueva ubicación] -->
<div class="modal fade" id="modal_nueva_ubicacion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <input type="hidden" id="locationAction" value="new" />
        <form:form modelAttribute="location" id="location" method="POST">
            <form:input path="id" type="hidden" id="locationId" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="locationModalTitle" class="modal-title">Nueva Ubicación</h4>
            </div>
            
            <div class="modal-body"><!-- CONTENIDO del modal -->
                <!-- CAMPO 01 / Nombre de ubicación   -->
                <label for="txt_ubicacion_nombre">Nombre</label>
                <div class="input-group" >
                    <form:input path="name" id="txt_ubicacion_nombre" type="text" class="form-control" 
                        placeholder="" data-toggle="popover" data-placement="top" title="Indique el nombre de la ubicación" 
                        maxlength="35" />
                    <span class="input-group-btn">
                        <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->
                
                <!-- CAMPO 02 / Descripción   -->
                <label for="txt_descripcion">Descripción</label>
                <div class="input-group" >
                    <form:input path="description" id="txt_descripcion" type="text" class="form-control" placeholder="" maxlength="60" />
                    <span class="input-group-btn">
                        <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->               
            </div>
            <div class="modal-footer">
                <button id="btnSaveLocation" class="btn btn-success pull-right">
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


<!-- MODAL [eliminar ubicación] -->
<div class="modal fade" id="modal_confirm_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <input type="hidden" id="locationToDelete" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Eliminar ubicación</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error: </span> Realmente desea eliminar la ubicación &nbsp;
                    <label id="locationNameToDelete"></label>
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteLocation" class="btn btn-success pull-right">
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
<script src="${pageContext.request.contextPath}/resources/js/locations.js"></script>
<!-- -------------- SCRIPTS ---------------- -->
