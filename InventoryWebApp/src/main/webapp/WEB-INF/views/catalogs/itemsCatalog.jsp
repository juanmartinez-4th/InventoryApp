<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<section id="barra_acciones" class="container">
    <div class="row">
        <div class="col-sm-2">
            <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                Categorías <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
            <c:if test="${not empty categoriesList}">
                <c:forEach items="${categoriesList}" var="currCategory">
                    <li><a href="#"><c:out value="${currCategory.name}" /></a></li>
                </c:forEach>
            </c:if>
            </ul>
        </div>

        <div class="col-sm-4">
            <div id="" class="form-control" style="border:none; box-shadow:none;">
                <ol class="breadcrumb">
                    <li><a href="#">Categoría seleccionada</a></li>
                </ol>
            </div>
        </div>

        <div class="col-sm-6 pull-right">
            <div class="col-sm-4">
            </div>
            <div class="col-sm-4">
                <button type="button" class="btn_chico width_100 btn btn-primary">
                    <span class="glyphicon glyphicon-barcode"></span>
                    Etiquetas
                </button>
            </div>
            <div class="col-sm-4">
                <a class="btn_chico width_100 btn btn-success" href="${pageContext.request.contextPath}/captureItem">
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
    
        <section class="row">
        <c:choose>
            <c:when test="${not empty itemsList}">
                <c:forEach items="${itemsList}" var="currenItem">
	                <!-- LISTADO DE ITEMS -->
	                <div class="col-md-2 col-sm-3 col-xs-4 grid_item">
	                    <figure>
	                        <img src="http://placehold.it/152x152&text=Foto de Producto" alt="Foto de Producto" class="img-responsive">
	                    </figure>
	                    <h4><c:out value="${currenItem.description}" /></h4>
	                    <p><c:out value="${currenItem.code}" /></p>
	                    <p>Existencia: <c:out value="${currenItem.existence}" /> <c:out value="${currenItem.unitOfMeasure.name}" /></p>
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
<script src="${pageContext.request.contextPath}/resources/js/items.js"></script>
<!-- -------------- SCRIPTS ---------------- -->
