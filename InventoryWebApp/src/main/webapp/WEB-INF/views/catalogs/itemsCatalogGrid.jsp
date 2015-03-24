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
        <div class="botones_izquierda col-xs-3 col-sm-2">
            <button type="button" class="btn_chico width_100 btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                Categorías <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" id="categoriesMenu">
                <li><a href="<c:url value='/listItems'/>">Todo</a></li>
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
                    <a href="<c:url value='/listItems'/>" class="btn_chico btn btn-primary"><span class="glyphicon glyphicon-th-list"></span></a>
                    <button type="button" class="btn_chico btn btn-primary btn_dark"><span class="glyphicon glyphicon-th"></span></button>
                </div>
            </div>
            <!-- <div class="col-xs-4">
                <button type="button" class="btn_chico width_100 btn btn-primary">
                    <span class="glyphicon glyphicon-barcode"></span>
                    Etiquetas
                </button>
            </div> -->
            <div class="col-xs-4">
                <a class="btn_chico width_100 btn btn-success" href="<c:url value='/captureItem'/>">
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
    
        <section class="row">
        <c:choose>
            <c:when test="${not empty itemsList}">
                <c:forEach items="${itemsList}" var="currentItem">
	                <!-- LISTADO DE ITEMS -->
	                <div class="col-md-2 col-sm-3 col-xs-4 grid_item">
	                    <figure>
	                        <a href="<c:url value='/showItem?itemId=${currentItem.id}' />">
	                           <c:if test="${empty currentItem.defaultPicture}">
	                               <img src="http://placehold.it/152x152&text=Foto de Producto" alt="Foto de Producto" height="152" width="152" class="img-responsive">
	                           </c:if>
	                           <c:if test="${not empty currentItem.defaultPicture}">
                                   <img src="/uploads/${currentItem.code}/${currentItem.defaultPicture}" alt="Foto de Producto" height="152" width="152" class="img-responsive">
                               </c:if>
	                        </a>
	                    </figure>
	                    <h4><a href="<c:url value='/showItem?itemId=${currentItem.id}' />">${currentItem.description}</a></h4>
	                    <p><a href="<c:url value='/showItem?itemId=${currentItem.id}' />">${currentItem.code}</a></p>
	                    <p>Existencia:&nbsp;${currentItem.existence}&nbsp;${currentItem.unitOfMeasure.name}</p>
	                </div>
	            </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-md-2 col-sm-3 col-xs-4 grid_item">
                    <h4>No se encontraron resultados</h4>
                </div>
            </c:otherwise>
        </c:choose>
        </section>
    </article>    
</main>


<!-- -------------- SCRIPTS ---------------- -->
<script src="<c:url value='/resources/js/utils.js' />"></script>
<script src="<c:url value='/resources/js/items-catalog.js' />"></script>
<!-- -------------- SCRIPTS ---------------- -->
