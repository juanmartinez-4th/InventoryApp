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
            <div class="col-xs-4 col-sm-offset-4 col-xs-offset-2">
                <button type="button" class="btn_chico width_100 btn btn-primary">
                    <span class="glyphicon glyphicon-barcode"></span>
                    Etiquetas
                </button>
            </div>
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
                <c:forEach items="${itemsList}" var="currenItem">
	                <!-- LISTADO DE ITEMS -->
	                <div class="col-md-2 col-sm-3 col-xs-4 grid_item">
	                    <figure>
	                        <a href="<c:url value='/showItem?itemId=${currenItem.id}' />">
	                           <c:if test="${empty currenItem.defaultPicture}">
	                               <img src="http://placehold.it/152x152&text=Foto de Producto" alt="Foto de Producto" height="152" width="152" class="img-responsive">
	                           </c:if>
	                           <c:if test="${not empty currenItem.defaultPicture}">
                                   <img src="/uploads/${currenItem.code}/${currenItem.defaultPicture}" alt="Foto de Producto" height="152" width="152" class="img-responsive">
                               </c:if>
	                        </a>
	                    </figure>
	                    <h4><a href="<c:url value='/showItem?itemId=${currenItem.id}' />">${currenItem.description}</a></h4>
	                    <p><a href="<c:url value='/showItem?itemId=${currenItem.id}' />">${currenItem.code}</a></p>
	                    <p>Existencia:&nbsp;${currenItem.existence}&nbsp;${currenItem.unitOfMeasure.name}</p>
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
