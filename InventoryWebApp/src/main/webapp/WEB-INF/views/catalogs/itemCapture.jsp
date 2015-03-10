<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<input type="hidden" id="appContextPath" value="${pageContext.request.contextPath}">

<form:form modelAttribute="itemCaptureForm" id="itemCaptureForm" method="POST">
<main>
    <article class="container">
        <form:input path="item.id" type="hidden" id="itemId" value="0" />
        
<!--        SECCION 1 -->
        <section class="row">
            <div class="col-sm-6">
                
<!--                CAMPO 01 / CÓDIGO auto generado   -->
                <label for="txt_codigo">Código</label>   
                <div class="row">        
                    <div class="col-xs-8">
                        <div class="input-group" >
                          <form:input path="item.code" id="txt_codigo" type="text" class="form-control" placeholder="MOB0000000001SIL" readonly="" />
                          <span class="input-group-btn">
                            <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                          </span>
                        </div><!-- /input-group -->
                    </div>

                    <div class="col-xs-4">
                        <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            <span class="glyphicon glyphicon-barcode"></span>
                            Etiquetas
                        </button>
                    </div>
                </div>
                
<!--                CAMPO 02 / Categoría   -->
                <label for="txt_categoria">Categoría</label>   
                <div class="row">        
                    <div class="col-xs-8">
                        <div class="input-group" >
                            <div id="txt_categoria" class="form-control"
                                    data-toggle="popover" data-placement="top" title="Indique la categoría del artículo">
                                <form:input path="category.id" type="hidden" id="itemCategory" value="0" />
                                <ol class="breadcrumb" id="breadcrumbCategory">
                                    <li class="active">Categoría</li>
                                </ol>
                            </div>
                            <span class="input-group-btn">
                                <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                            </span>
                        </div><!-- /input-group -->
                    </div>
        
                    <div class="col-xs-4">
                        <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            Categorías <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <c:if test="${not empty categoriesList}">
                                <c:forEach items="${categoriesList}" var="currCategory">
                                    <li onclick="javascript:setCategory(${currCategory.id})"><a href="#">${currCategory.name}</a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
                
<!--                CAMPO 03 / Descripción   -->
                <label for="txt_descripcion">Descripción</label>
                <div class="input-group" >
                    <form:input path="item.description" id="txt_descripcion" type="text" class="form-control" placeholder="" />
                    <span class="input-group-btn">
                        <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->

            </div>

<!--            CAMPO 04 / Imágenes   -->            
            <div class="col-sm-6" id="captura_imagenes">
                <label for="txt_codigo">Imágenes</label>
                <div class="row">
                    <figure class="col-xs-4"><a href="#"><img src="${pageContext.request.contextPath}/resources/images/demo/placeholder_118px_articulo.png" alt="" class="img-responsive"></a></figure>
                    <figure class="col-xs-4"><a href="#"><img src="${pageContext.request.contextPath}/resources/images/demo/placeholder_118px_articulo.png" alt="" class="img-responsive"></a></figure>
                    <figure class="col-xs-4"><a href="#"><img src="${pageContext.request.contextPath}/resources/images/demo/placeholder_118px_articulo.png" alt="" class="img-responsive"></a></figure>
                </div>
                
            </div>
        </section>
        
<!--        SECCION 2 -->
        <section class="row">
            <div class="col-sm-6">
<!--                CAMPO 05 / Costo y Precios   -->
                <div class="row">
                    <div class="col-xs-4">
                        <label for="txt_costo">Costo</label>
                        <form:input path="item.cost" type="text" class="form-control" id="txt_costo" placeholder="" />
                    </div>
                    
                    <div class="col-xs-4">
                        <label for="txt_precio_venta">Precio de venta</label>
                        <form:input path="item.salePrice" type="text" class="form-control" id="txt_precio_venta" placeholder="" />
                    </div>
                    
                    <div class="col-xs-4">
                        <label for="txt_precio_renta">Precio de renta</label>
                        <form:input path="item.rentPrice" type="text" class="form-control" id="txt_precio_renta" placeholder="" />
                    </div>
                </div>
            
            
<!--                CAMPO 06 / Existencia -->
                <label for="txt_existencia">Existencia</label>   
                <div class="row">        
                    <div class="col-xs-8">
                        <div class="input-group" >
                            <form:input path="location.quantity" type="text" class="form-control" id="txt_existencia" placeholder="" />
                            <span class="input-group-btn">
                                <button id="btnUnitOfM" class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                            </span>
                        </div><!-- /input-group -->
                    </div>

                    <div class="col-xs-4">
                        <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            Unidad de medida <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <form:input path="item.unitOfMeasure.id" type="hidden" id="itemUnit" value="0" />
                            <c:if test="${not empty unitsList}">
                                <c:forEach items="${unitsList}" var="currUnit">
                                    <li onclick="javascript:setUnitOfMeasure(${currUnit.id}, '${currUnit.name}')"><a href="#">${currUnit.name}</a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-sm-6">
<!--                CAMPO 07 / Localización -->
                <label for="txt_localización">Localización</label>   
                <div class="row">
                    <div class="col-xs-2 col-xs-offset-1">
                        <label for="txt_casilla">Proyecto</label>
                        <form:input path="location.project" type="text" class="form-control" id="txt_casilla" placeholder="" />
                    </div>
                    <div class="col-xs-2">
                        <label for="txt_seccion">Sección</label>
                        <form:input path="location.section" type="text" class="form-control" id="txt_seccion" placeholder="" />
                    </div>
                    
                    <div class="col-xs-2">
                        <label for="txt_pasillo">Pasillo</label>
                        <form:input path="location.hall" type="text" class="form-control" id="txt_pasillo" placeholder="" />
                    </div>
                    
                    <div class="col-xs-2">
                        <label for="txt_anaquel">Anaquel</label>
                        <form:input path="location.rack" type="text" class="form-control" id="txt_anaquel" placeholder="" />
                    </div>
                    
                    <div class="col-xs-2">
                        <label for="txt_casilla">Casilla</label>
                        <form:input path="location.box" type="text" class="form-control" id="txt_casilla" placeholder="" />
                    </div>
                </div>
            </div>
        </section>
        
        <section class="row">
            <div class="col-sm-6">
                <label for="txt_area_caracteristicas">Características / Observaciones</label>
                <form:textarea path="item.detail" class="form-control" rows="4" id="txt_area_caracteristicas" placeholder="Material, Color, Detalles, Estado de conservación" />
            </div>
        </section>
    
    </article>
    
</main>

<footer class="navbar navbar-default navbar-fixed-bottom">
    <article class="container">
        <button id="btnSaveNewItem" class="btn btn-success pull-right">
            <span class="glyphicon glyphicon-ok"></span>
            Guardar Nuevo
        </button>

        <button id="btnSaveItem" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-floppy-disk"></span>
            Guardar
        </button>

        <a href="${pageContext.request.contextPath}/listItems" class="btn btn-primary btn_dark">
            <span class="glyphicon glyphicon-remove"></span>
            Cancelar
        </a>
    </article>
</footer>

</form:form>

<!-- -------------- SCRIPTS ---------------- -->
<script src="${pageContext.request.contextPath}/resources/js/items.js"></script>
<!-- -------------- SCRIPTS ---------------- -->
