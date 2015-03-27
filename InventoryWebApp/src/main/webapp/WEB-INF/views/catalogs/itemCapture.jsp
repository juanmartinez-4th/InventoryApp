<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<input type="hidden" id="nextItemId" value="${nextItemId}">

<form:form modelAttribute="itemCaptureForm" id="itemCaptureForm" action="insertItem" enctype="multipart/form-data" method="POST">
<main>
    <article class="container">
        <form:input path="item.id" type="hidden" id="itemId" value="0" />
        <form:input path="redirectNew" type="hidden" id="redirectNew" value="false" />
        
        <div class="row">
            <div id="divMessages" class="col-sm-12 pull-left">
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
        
        <!-- SECCION 1 -->
        <section class="row">
            <div class="col-sm-6">
                
                <!-- CAMPO 01 / CÓDIGO auto generado   -->
                <label for="txt_codigo">Código</label>   
                <div class="row">        
                    <div class="col-xs-8">
                        <form:input path="item.code" id="txt_codigo" type="text" class="form-control" placeholder="XXX0000000001XXX" />
                    </div>

                    <div class="col-xs-4">
                        <button onclick="javascript:showLabelsModal();" type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            <span class="glyphicon glyphicon-barcode"></span>
                            Etiquetas
                        </button>
                    </div>
                </div>
                
<!--                CAMPO 02 / Categoría   -->
                <label for="txt_categoria">* Categoría</label>   
                <div class="row">        
                    <div class="col-xs-10">
                        <div id="txt_categoria" class="form-control height_auto" 
                                data-toggle="tooltip" data-placement="top" title="Indique la categoría del artículo">
                            <form:input path="category.id" type="hidden" id="itemCategory" value="0" />
                            <input type="hidden" id="itemCategoryName" value="" />
                            <ol class="breadcrumb" id="breadcrumbCategory">
                                <li class="active">Categoría</li>
                            </ol>
                        </div><!-- /input-group -->
                    </div>
        
                    <div class="col-xs-2">
                        <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" id="categoriesMenu">
                        </ul>
                    </div>
                </div>
                
<!--                CAMPO 03 / Descripción   -->
                <label for="txt_descripcion">* Descripción</label>
                <form:input path="item.description" id="txt_descripcion" type="text" class="form-control" placeholder="" 
                    data-toggle="tooltip" data-placement="top" title="Capture la descripción del artículo"
                    maxlength="30"/>

            </div>

<!--            CAMPO 04 / Imágenes   -->            
            <div class="col-sm-6" id="captura_imagenes">
                <label for="txt_codigo">* Imágenes</label>
                <div class="row">
                    <figure class="col-xs-4">
                        <div style='height: 0px;width: 0px; overflow:hidden;'>
                            <input id="inputFile0" name="pictureFiles[0]" type="file" value="upload" 
                                onchange="javascripts:setSelectedFile('0')"/>
                        </div>
                        <a href="javascript:selectFile('0')">
                            <img src="<c:url value='/resources/images/placeholder_118px_articulo.png'/>" alt="" class="img-responsive">
                        </a>
                        <div id="selectedFileName0"></div>
                    </figure>
                    <figure class="col-xs-4">
                        <div style='height: 0px;width: 0px; overflow:hidden;'>
                            <input id="inputFile1" name="pictureFiles[1]" type="file" value="upload" 
                                onchange="javascripts:setSelectedFile('1')"/>
                        </div>
                        <a href="javascript:selectFile('1')">
                            <img src="<c:url value='/resources/images/placeholder_118px_articulo.png'/>" alt="" class="img-responsive">
                        </a>
                        <div id="selectedFileName1"></div>
                    </figure>
                    <figure class="col-xs-4">
                        <div style='height: 0px;width: 0px; overflow:hidden;'>
                            <input id="inputFile2" name="pictureFiles[2]" type="file" value="upload" 
                                onchange="javascripts:setSelectedFile('2')"/>
                        </div>
                        <a href="javascript:selectFile('2')">
                            <img src="<c:url value='/resources/images/placeholder_118px_articulo.png'/>" alt="" class="img-responsive">
                        </a>
                        <div id="selectedFileName2"></div>
                    </figure>
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
                        <form:input path="item.cost" type="text" class="form-control decimal_field" id="txt_costo" placeholder="" />
                    </div>
                    
                    <div class="col-xs-4">
                        <label for="txt_precio_venta">Precio de venta</label>
                        <form:input path="item.salePrice" type="text" class="form-control decimal_field" id="txt_precio_venta" placeholder="" />
                    </div>
                    
                    <div class="col-xs-4">
                        <label for="txt_precio_renta">Precio de renta</label>
                        <form:input path="item.rentPrice" type="text" class="form-control decimal_field" id="txt_precio_renta" placeholder="" />
                    </div>
                </div>
            
            
<!--                CAMPO 06 / Existencia -->
                <label for="txt_existencia">* Existencia</label>   
                <div class="row">        
                    <div class="col-xs-8">
                        <div class="input-group" >
                            <form:input path="location.quantity" class="form-control decimal_field" id="txt_existencia" placeholder="" 
                                data-toggle="tooltip" data-placement="top" title="Indique la cantidad a ingresar al inventario y la unidad de medida"/>
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
                <label for="txt_localización">Producción</label>   
                <div class="row">
                    <div class="col-xs-8">
                        <form:input path="location.production.id" type="hidden" id="productionId" value="0" />
                        <input type="text" class="form-control" id="txt_produccion" readonly 
                            data-toggle="tooltip" data-placement="top" title="Indique la producción del artículo" />
                    </div>

                    <div class="col-xs-4">
                        <button type="button" class="btn_chico btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="width:100%;">
                            Producciones <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <c:if test="${not empty productionsList}">
                                <c:forEach items="${productionsList}" var="currProduction">
                                    <li onclick="javascript:setProduction(${currProduction.id}, '${currProduction.name}')"><a href="#">${currProduction.code} - ${currProduction.name}</a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
<!--                CAMPO 07 / Localización -->
                <label for="txt_localización">* Localización</label>   
                <div class="row">
                    <div class="col-xs-2">
                        <label for="txt_seccion">Sección</label>
                        <form:input path="location.section" type="text" class="form-control" id="txt_seccion" placeholder="" maxlength="5"/>
                    </div>
                    
                    <div class="col-xs-2">
                        <label for="txt_pasillo">Pasillo</label>
                        <form:input path="location.hall" type="text" class="form-control" id="txt_pasillo" placeholder="" maxlength="5"/>
                    </div>
                    
                    <div class="col-xs-2">
                        <label for="txt_anaquel">Anaquel</label>
                        <form:input path="location.rack" type="text" class="form-control" id="txt_anaquel" placeholder="" maxlength="5"/>
                    </div>
                    
                    <div class="col-xs-2">
                        <label for="txt_casilla">Casilla</label>
                        <form:input path="location.box" type="text" class="form-control" id="txt_casilla" placeholder="" maxlength="5"/>
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
        <button onclick="javascript:saveItem(true)" id="btnSaveNewItem" class="btn btn-success pull-right">
            <span class="glyphicon glyphicon-ok"></span>
            Guardar Nuevo
        </button>

        <button onclick="javascript:saveItem(false)" id="btnSaveItem" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-floppy-disk"></span>
            Guardar
        </button>

        <a href="<c:url value='/listItems'/>" class="btn btn-primary btn_dark">
            <span class="glyphicon glyphicon-remove"></span>
            Cancelar
        </a>
    </article>
</footer>

</form:form>

<!-- MODAL [imprimir etiquetas] -->
<div class="modal fade" id="modal_print_labels" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Imprimir etiquetas</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">
                <label for="txt_label_code">Código del artículo</label>
                <div class="input-group" >
                    <input id="txt_label_code" type="text" class="form-control" readonly>
                    <span class="input-group-btn">
                        <button id="name-help" class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-barcode"></span></button>
                    </span>
                </div><!-- /input-group -->
                
                <label for="txt_label_copies">Número de copias</label>
                <input id="txt_label_copies" type="text" class="form-control numeric_field" 
                    placeholder="" data-toggle="tooltip" data-placement="top" title="Indique el número de etiquetas a imprimir" 
                    maxlength="2"/>
            </div>
            
            <div class="modal-footer">
                <button id="btnPrintLabels" class="btn btn-success pull-right">
                    <span class="glyphicon glyphicon-print"></span>
                    Imprimir
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
<script src="<c:url value='/resources/js/items.js'/>"></script>
<!-- -------------- SCRIPTS ---------------- -->
