<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<form:form modelAttribute="selectedItem" id="selectedItemForm" action="editItem" method="POST">
<main class="detalle_articulo">
    <article class="container">

        <!-- SECCION 1 -->
        <!-- CÓDIGO Y EXISTENCIAS -->
        <section class="row subraya_gris">
            <div class="col-sm-4 col-xs-6">
                <label>Código: </label>
                <form:input id="itemCode" path="item.code" />
            </div>

            <div class="pull-right">
                <label>Existencia: </label>
                <form:input path="item.existence" id="existencia_value" />
                <form:hidden path="item.unitOfMeasure.id" id="itemUnitId" />
                <form:input path="item.unitOfMeasure.name" id="existencia_unit" />
            </div>
        </section>

        <!-- SECCION 2 -->
        <section class="row">

            <!-- COLUMNA 1 -->
            <!-- CATEGORÍA / DESCRIPCIÓN / CARACTERÍSTICAS / LOCALIZACIÓN -->
            <div class="col-sm-4">
                <div class="subraya_gris">
                    <label>Categoría: </label>
                    <form:hidden id="itemCategory" path="category.id" />
                    <ol id="breadcumbCategory" class="breadcrumb">
                    </ol>

                    <label class="diplay_block">Descripción: </label>
                    <form:input id="itemDescription" path="item.description" class="resalta_negro width_100" />
                </div>

                <div class="subraya_gris">
                    <label>Características / Observaciones: </label>
                    <form:textarea id="itemDetail" path="item.detail" class="resalta_negro width_100"/>
                </div>

                <div>
                    <label>Localización: </label>
                    <label class="resalta_negro">Almacén</label>

                    <div class="row">
                        <form:hidden path="location.id" id="locationId"  />
                        <div class="col-xs-2">
                            <label for="txt_seccion" class="width_100 text-center">Sección</label>
                            <form:input path="location.section" id="txt_seccion" class="resalta_negro width_100 text-center" />
                        </div>

                        <div class="col-xs-2">
                            <label for="txt_pasillo" class="width_100 text-center">Pasillo</label>
                            <form:input path="location.hall" id="txt_pasillo" class="resalta_negro width_100 text-center" />
                        </div>

                        <div class="col-xs-2">
                            <label for="txt_anaquel" class="width_100 text-center">Anaquel</label>
                            <form:input path="location.rack" id="txt_anaquel" class="resalta_negro width_100 text-center" />
                        </div>

                        <div class="col-xs-2">
                            <label for="txt_casilla" class="width_100 text-center">Casilla</label>
                            <form:input path="location.box" id="txt_casilla" class="resalta_negro width_100 text-center" />
                        </div>
                        
                        <div class="col-xs-4">
                            <label for="txt_proyecto" class="width_100 text-center">Proyecto</label>
                            <form:hidden path="location.production.id" id="projectId" />
                            <form:input path="location.production.code" id="txt_proyecto" class="resalta_negro width_100 text-center" />
                        </div>
                    </div>

                </div>

            </div>

            <!-- COLUMNA 2 -->
            <!-- CÓDIGO DE BARRAS y precios -->
            <div class="col-sm-4">
                <figure id="codigo_barras" class="width_100">
                    <a onclick="javascript:showLabelsModal();" href="#" 
                            data-toggle="tooltip" title="Imprimir etiquetas" data-placement="bottom">
                        <img src="data:image/png;base64,${selectedItem.itemBarcode}" alt="Código de barras del producto" class="img-responsive img_centered">
                    </a>
                </figure>
                <div class="row">
                    <div class="col-xs-6"><label for="txt_costo" class="width_100 text-right">Costo: $ </label></div>
                    <div class="col-xs-6"><form:input path="item.cost" id="txt_costo" class="resalta_negro width_100 text-left" /></div>
                </div>
                <div class="row">
                    <div class="col-xs-6"><label for="txt_precio_venta" class="width_100 text-right">Precio de Venta: $ </label></div>
                    <div class="col-sm-6"><form:input path="item.salePrice" id="txt_precio_venta" class="resalta_negro width_100 text-left" /></div>
                </div>
                <div class="row">
                    <div class="col-xs-6"><label for="txt_precio_renta" class="width_100 text-right">Precio de Renta: $ </label></div>
                    <div class="col-xs-6"><form:input path="item.rentPrice" id="txt_precio_renta" class="resalta_negro width_100 text-left" /></div>
                </div>
            </div>

            <!-- COLUMNA 3 -->
            <!-- FOTOS del Producto -->
            <div class="col-sm-4">
                <div class="row">
	                <figure id="vista_producto_grande" class="width_100 img_redondeada">
	                    <%-- <img id="imgFullSize" src="/uploads/${selectedItem.itemPictures[0]}" alt="Foto del producto" class="img-responsive img_centered"> --%>
	                    <img id="efecto_zoom_01" class="hidden-xs efecto_zoom_screen_big img-responsive" src="/uploads/${selectedItem.itemPictures[0]}" data-zoom-image="/uploads/${selectedItem.itemPictures[0]}">
                        <img id="efecto_zoom_02" class="visible-xs efecto_zoom_screen_small img-responsive" src="/uploads/${selectedItem.itemPictures[0]}" data-zoom-image="/uploads/${selectedItem.itemPictures[0]}">
	                </figure>
	            </div>

                <div class="row">
                    <c:forEach items="${selectedItem.itemPictures}" var="itemPict">
                    <div class="col-sm-3 col-xs-2">
                        <figure class="width_100 img_redondeada" onclick="javascript:showPicture('/uploads/${itemPict}')">
                            <a href="#">
                                <img src="/uploads/${itemPict}" alt="Foto del producto" class="img-responsive img_centered">
                            </a>
                        </figure>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </section>

    </article>
</main>

<footer class="navbar navbar-default navbar-fixed-bottom">
    <article class="container">
        <div class="col-xs-6">
	        <a href="<c:url value='/listItems'/>" class="btn btn-primary btn_dark">
	            <span class="glyphicon glyphicon-arrow-left"></span>
	            Regresar
	        </a>
        </div>
        <div class="col-xs-6">
	        <button type="submit" class="btn btn-success">
	            <span class="fa fa-pencil fa-lg"></span>
	            Modificar artículo
	        </button>
	        <a class="btn btn-default pull-right" href="#">
                <span class="glyphicon glyphicon-remove"></span>
                Eliminar sartículo
            </a>
	    </div>
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
<script src="<c:url value='/resources/js/util/jquery.elevateZoom-3.0.8.min.js'/>"></script>
<script src="<c:url value='/resources/js/utils.js'/>"></script>
<script src="<c:url value='/resources/js/item-detail.js'/>"></script>
<!-- -------------- SCRIPTS ---------------- -->
