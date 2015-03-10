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
                <li><a href="" class="active">Unidades de medida</a></li>
            </ul>
        </section>
        
        <div class="col-sm-3 pull-right">
            <!-- Button para MODAL [+ nueva unidad de medida] -->
            <button onclick="javascript:showUnitModal(0, '', '')" type="button" class="btn_chico btn btn-success pull-right side_paddings" data-toggle="modal">
                <span class="glyphicon glyphicon-plus"></span>
                Nueva Unidad de Medida
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
            <table class="table table-condensed table-hover">
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
                    <c:when test="${not empty unitsList}">
                        <c:forEach items="${unitsList}" var="currUnit">
	                    <tr>
	                        <td>${currUnit.name}</td>
	                        <td>${currUnit.description}</td>
	                        <td class="text-center">
                                <a onclick="javascript:showUnitModal(${currUnit.id}, '${currUnit.name}', '${currUnit.description}')"
                                    data-toggle="modal" 
                                    id="edit_${currUnit.id} }" 
                                    class="btn btn-primary btn_dark btn_tabla" 
                                    href="#"><i class="fa fa-pencil fa-lg"></i></a>
                            </td>
                            <td class="text-center">
                                <a onclick="javascript:confirmDelete(${currUnit.id}, '${currUnit.name}')"
                                    data-toggle="modal" 
                                    id="delete_${currUnit.id} }" 
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


<!-- MODAL [+ nueva unidad de medida] -->
<div class="modal fade" id="modal_nueva_unidadMedida" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <input type="hidden" id="unitAction" value="new" />
        <form:form modelAttribute="unitOfMeasure" id="unitOfMeasure" method="POST">
            <form:input path="id" type="hidden" id="unitId" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="catModalTitle" class="modal-title">Nueva Unidad de Medida</h4>
            </div>
            
            <div class="modal-body"><!-- CONTENIDO del modal -->
                <!-- CAMPO 01 / Nombre de unidad de medida   -->
                <label for="txt_unidadMedida_nombre">Nombre</label>
                <div class="input-group" >
                    <form:input path="name" id="txt_unidadMedida_nombre" type="text" class="form-control" 
                        placeholder="" data-toggle="popover" data-placement="top" title="Indique el nombre de la unidad de medida" 
                        maxlength="25" />
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
                <button id="btnSaveUnit" class="btn btn-success pull-right">
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


<!-- MODAL [eliminar unidad de medida] -->
<div class="modal fade" id="modal_confirm_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <input type="hidden" id="unitToDelete" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Eliminar unidad de medida</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error: </span> ¿Realmente desea eliminar la unidad de medida &nbsp;
                    <label id="unitNameToDelete"></label>?
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteUnit" class="btn btn-success pull-right">
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
<script src="${pageContext.request.contextPath}/resources/js/units-of-measure.js"></script>
<!-- -------------- SCRIPTS ---------------- -->
