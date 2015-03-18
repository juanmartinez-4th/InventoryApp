<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<section id="barra_acciones" class="container captura_items">
    <div class="row">
        <section id="nav_nivel_2_b" class="col-sm-9">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value='/adminCategories'/>">Categorías</a></li>
                <li><a href="<c:url value='/adminUnitsOfMeasure'/>">Unidades de medida</a></li>
                <li><a href="#" class="active">Producciones</a></li>
            </ul>
        </section>
        
        <div class="col-sm-3 pull-right btn_nuevo_item">
            <!-- Button para MODAL [+ nueva producción] -->
            <button onclick="javascript:showProductionModal(0, '', '', '')" type="button" class="btn_chico btn btn-success pull-right side_paddings" data-toggle="modal">
                <span class="glyphicon glyphicon-plus"></span>
                Nueva Producción
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
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th class="text-center">Editar</th>
                        <th class="text-center">Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty productionsList}">
                        <c:forEach items="${productionsList}" var="currProduction">
	                    <tr>
	                        <td>${currProduction.code}</td>
	                        <td>${currProduction.name}</td>
	                        <td>${currProduction.description}</td>
	                        <td class="text-center">
                                <a onclick="javascript:showProductionModal(${currProduction.id}, '${currProduction.code}', '${currProduction.name}', '${currProduction.description}')"
                                    data-toggle="modal" 
                                    id="edit_${currProduction.id} }" 
                                    class="btn btn-primary btn_dark btn_tabla" 
                                    href="#"><i class="fa fa-pencil fa-lg"></i></a>
                            </td>
                            <td class="text-center">
                                <a onclick="javascript:confirmDelete(${currProduction.id}, '${currProduction.name}')"
                                    data-toggle="modal" 
                                    id="delete_${currProduction.id} }" 
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


<!-- MODAL [+ nueva producción] -->
<div class="modal fade" id="modal_nueva_produccion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <input type="hidden" id="productionAction" value="new" />
        <form:form modelAttribute="production" id="production" method="POST">
            <form:input path="id" type="hidden" id="productionId" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="catModalTitle" class="modal-title">Nueva Producción</h4>
            </div>
            
            <div class="modal-body"><!-- CONTENIDO del modal -->
                <!-- CAMPO 01 / Código de la producción   -->
                <label for="txt_produccion_codigo">Código</label>
                <div class="input-group" >
                    <form:input path="code" id="txt_produccion_codigo" type="text" class="form-control" 
                        placeholder="" data-toggle="popover" data-placement="top" title="Indique el código de la producción" 
                        maxlength="10" />
                    <span class="input-group-btn">
                        <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->
                
                <!-- CAMPO 02 / Nombre de la producción   -->
                <label for="txt_produccion_nombre">Nombre</label>
                <div class="input-group" >
                    <form:input path="name" id="txt_produccion_nombre" type="text" class="form-control" 
                        placeholder="" data-toggle="popover" data-placement="top" title="Indique el nombre de la producción" 
                        maxlength="30" />
                    <span class="input-group-btn">
                        <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->
                
                <!-- CAMPO 03 / Descripción   -->
                <label for="txt_descripcion">Descripción</label>
                <div class="input-group" >
                    <form:input path="description" id="txt_descripcion" type="text" class="form-control" placeholder="" maxlength="80" />
                    <span class="input-group-btn">
                        <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->
            </div>
            <div class="modal-footer">
                <button id="btnSaveProduction" class="btn btn-success pull-right">
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


<!-- MODAL [eliminar producción] -->
<div class="modal fade" id="modal_confirm_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <input type="hidden" id="productionToDelete" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Eliminar producción</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error: </span> ¿Realmente desea eliminar la producción &nbsp;
                    <label id="productionNameToDelete"></label>?
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteProduction" class="btn btn-success pull-right">
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
<script src="<c:url value='/resources/js/productions.js'/>"></script>
<!-- -------------- SCRIPTS ---------------- -->
