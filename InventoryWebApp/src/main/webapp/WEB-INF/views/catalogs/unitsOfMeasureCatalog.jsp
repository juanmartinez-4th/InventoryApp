<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<!-- -------------- CSS ---------------- -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/util/data-tables/dataTables.bootstrap.css'/>">
<!-- -------------- CSS ---------------- -->

<section id="barra_acciones" class="container captura_items">
    <div class="row">
        <section id="nav_nivel_2_b"  class="col-sm-9">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value='/adminCategories'/>">Categorías</a></li>
                <li><a href="#" class="active">Unidades de medida</a></li>
                <li><a href="<c:url value='/adminProductions'/>">Producciones</a></li>
            </ul>
        </section>
        
        <div class="col-sm-3 pull-right btn_nuevo_item">
            <!-- Button para MODAL [+ nueva unidad de medida] -->
            <button onclick="javascript:showUnitModal(0, '', '')" type="button" class="btn_chico btn btn-success pull-right side_paddings" 
                    data-toggle="tooltip" title="Agregar una nueva unidad de medida" data-placement="left" >
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
            <table id="unitsTable" class="table table-condensed table-hover">
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
                                    data-toggle="tooltip" title="Modificar unidad de medida" data-placement="left"  
                                    id="edit_${currUnit.id}" 
                                    class="btn btn-primary btn_dark btn_tabla" 
                                    href="#"><i class="fa fa-pencil fa-lg"></i></a>
                            </td>
                            <td class="text-center">
                                <a onclick="javascript:confirmDelete(${currUnit.id}, '${currUnit.name}')"
                                    data-toggle="tooltip" title="Eliminar unidad de medida" data-placement="left"  
                                    id="delete_${currUnit.id}" 
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
                            <!-- Empty cells required by data-tables -->
                            <td></td><td></td><td></td>
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
                <label for="txt_unidadMedida_nombre" class="text-danger">* Abreviatura</label>
                <form:input path="name" id="txt_unidadMedida_nombre" type="text" class="form-control" 
                    placeholder="" data-toggle="tooltip" data-placement="top" title="Indique la abreviatura de la unidad de medida. Esta es la que se mostrará en la captura de nuevos artículos." 
                    maxlength="10" />
                
                <!-- CAMPO 02 / Descripción   -->
                <label for="txt_descripcion">Nombre</label>
                <form:input path="description" id="txt_descripcion" type="text" class="form-control"
                    placeholder="" data-toggle="tooltip" data-placement="top" title="Nombre completo de la unidad de medida. Este campo no es requerido." 
                    maxlength="20" />
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
<script src="<c:url value='/resources/js/util/jquery.dataTables.js' />"></script>
<script src="<c:url value='/resources/js/util/dataTables.bootstrap.js' />"></script>
<script src="<c:url value='/resources/js/utils.js'/>"></script>
<script src="<c:url value='/resources/js/units-of-measure.js'/>"></script>
<!-- -------------- SCRIPTS ---------------- -->
