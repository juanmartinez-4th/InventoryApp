<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<c:if test="${not empty selectedCategory}">
    <input id="itemCategory" type="hidden" value="${selectedCategory}">
</c:if>
<c:if test="${empty selectedCategory}">
    <input id="itemCategory" type="hidden" value="0">
</c:if>

<section id="barra_acciones" class="container">
    <div class="row">
        <div class="botones_izquierda col-xs-3 col-sm-2"><!-- btn_chico btn btn-primary -->
            <ul id="categories-menu" class="sm sm-clean">
                <li><a href="#">Categorías</a>
		            <ul id="categoriesMenu">
		                <li><a href="<c:url value='/listItems'/>">Todo</a></li>
		            </ul>
	            </li>
            </ul>
        </div>

        <div class="col-xs-7 col-sm-4">
            <div class="form-control" style="border:none; box-shadow:none;">
                <ol class="breadcrumb" id="breadcrumbCategory">
                    <li><a href="#">Categoría seleccionada</a></li>
                </ol>
            </div>
        </div>

        <div class="botones_derecha col-xs-12 col-sm-6 pull-right">
            <div id="botones_vista" class="col-xs-4 col-xs-offset-4">
                <p class="inline_block">Vista</p>
                <div class="btn-group" role="group">
                    <button type="button" class="btn_chico btn btn-primary btn_dark" 
                            data-toggle="tooltip" title="Mostrar resultados en vista de lista" data-placement="top">
                        <span class="glyphicon glyphicon-th-list"></span>
                    </button>
                    <a href="<c:url value='/listItems?showGrid=true'/>" class="btn_chico btn btn-primary" 
                            data-toggle="tooltip" title="Mostrar resultados en vista de mosaico" data-placement="top">
                        <span class="glyphicon glyphicon-th"></span>
                    </a>
                </div>
            </div>
            <!-- <div class="col-xs-4">
                <button type="button" class="btn_chico width_100 btn btn-primary">
                    <span class="glyphicon glyphicon-barcode"></span>
                    Etiquetas
                </button>
            </div> -->
            <div class="col-xs-4">
                <a class="btn_chico width_100 btn btn-success" href="<c:url value='/captureItem'/>"
                        data-toggle="tooltip" title="Agregar un nuevo artículo" data-placement="left">
	                <span class="glyphicon glyphicon-plus"></span>
	                Nuevo artículo
	            </a>
            </div>
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
    
        <!-- <section class="row"> -->
        <section class="table-responsive">
            <table class="table table-condensed table-hover">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Descripción</th>
                        <th>Ubicación</th>
                        <th class="text-center">Existencias</th>
                        <!-- <th class="text-center">Seleccionar</th>-->
                        <th><input type="checkbox"></th>
                    </tr>
                </thead>
                <tbody>
			        <c:choose>
			            <c:when test="${not empty itemsList}">
			                <c:forEach items="${itemsList}" var="currentItem">
				                <!-- LISTADO DE ITEMS -->
				                <tr>
	                                <td>
	                                    <a href="<c:url value='/showItem?itemId=${currentItem.id}' />">${currentItem.code}</a>
	                                </td>
	                                <td class="resalta_rojo">
	                                    <a href="<c:url value='/showItem?itemId=${currentItem.id}' />">${currentItem.description}</a>
	                                </td>
	                                <td>
	                                    S:${currentItem.location.section}, P:${currentItem.location.hall}, A:${currentItem.location.rack}, C:${currentItem.location.box}
	                                </td>
	                                <td class="text-center">
	                                    ${currentItem.existence}&nbsp;${currentItem.unitOfMeasure.name}
	                                </td>
	                                <!-- <td class="text-center"><input type="text" placeholder="Cantidad..."></td>-->
	                                <td><input type="checkbox"></td>
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

<!-- -------------- SCRIPTS ---------------- -->
<script src="<c:url value='/resources/js/utils.js' />"></script>
<script src="<c:url value='/resources/js/items-catalog.js' />"></script>
<!-- -------------- SCRIPTS ---------------- -->
