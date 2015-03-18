<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<main class="detalle_articulo">
    <article class="container">

        <!-- SECCION 1 -->
        <!-- CÓDIGO Y EXISTENCIAS -->
        <section class="row subraya_gris">
            <div class="col-sm-4 col-xs-6">
                <label>Código: </label>
                <input type="text" value="${selectedItem.item.code}" readonly class="resalta_rojo">
            </div>

            <div class="pull-right">
                <label>Existencia: </label>
                <input type="text" value="${selectedItem.item.existence}&nbsp;${selectedItem.item.unitOfMeasure.name}" readonly id="existencia_value" class="resalta_rojo">
            </div>
        </section>

        <!-- SECCION 2 -->
        <section class="row">

            <!-- COLUMNA 1 -->
            <!-- CATEGORÍA / DESCRIPCIÓN / CARACTERÍSTICAS / LOCALIZACIÓN -->
            <div class="col-sm-4">
                <div class="subraya_gris">
                    <label>Categoría: </label>
                    <input type="hidden" id="itemCategory" value="${selectedItem.category.id}">
                    <ol id="breadcumbCategory" class="breadcrumb">
                    </ol>

                    <label class="diplay_block">Descripción: </label>
                    <input type="text" value="${selectedItem.item.description}" class="resalta_negro width_100" readonly>
                </div>

                <div class="subraya_gris">
                    <label>Características / Observaciones: </label>
                    <textarea type="text" readonly  name="" id="" class="resalta_negro width_100">${selectedItem.item.detail}</textarea>
                </div>

                <div>
                    <label>Localización: </label>
                    <input type="text" value="Almacén" readonly  class="resalta_negro">

                    <div class="row">
                        <div class="col-xs-2">
                            <label for="txt_seccion" class="width_100 text-center">Sección</label>
                            <input type="text" value="${selectedItem.location.section}" readonly id="txt_seccion" class="resalta_negro width_100 text-center">
                        </div>

                        <div class="col-xs-2">
                            <label for="txt_pasillo" class="width_100 text-center">Pasillo</label>
                            <input type="text" value="${selectedItem.location.hall}" readonly id="txt_pasillo" class="resalta_negro width_100 text-center">
                        </div>

                        <div class="col-xs-2">
                            <label for="txt_anaquel" class="width_100 text-center">Anaquel</label>
                            <input type="text" value="${selectedItem.location.rack}" readonly id="txt_anaquel" class="resalta_negro width_100 text-center">
                        </div>

                        <div class="col-xs-2">
                            <label for="txt_casilla" class="width_100 text-center">Casilla</label>
                            <input type="text" value="${selectedItem.location.box}" readonly id="txt_casilla" class="resalta_negro width_100 text-center">
                        </div>
                        
                        <div class="col-xs-2">
                            <label for="txt_proyecto" class="width_100 text-center">Proyecto</label>
                            <input type="text" value="${selectedItem.location.production.code}" readonly id="txt_proyecto" class="resalta_negro width_100 text-center">
                        </div>
                    </div>

                </div>

            </div>

            <!-- COLUMNA 2 -->
            <!-- CÓDIGO DE BARRAS y precios -->
            <div class="col-sm-4">
                <figure id="codigo_barras" class="width_100">
                    <!-- <img src="http://placehold.it/203x36&text=Logotipo" alt="Logotipo de Empresa" class="img-responsive pull_left_md"> -->
                    <img src="<c:url value='/resources/images/codigo_barras_ejemplo.png'/>" alt="Código de barras del producto" class="img-responsive img_centered">
                </figure>
                <div class="row">
                    <div class="col-xs-6"><label for="txt_costo" class="width_100 text-right">Costo:</label></div>
                    <div class="col-xs-6"><input type="text" value="$ ${selectedItem.item.cost}" readonly id="txt_costo" class="resalta_negro width_100 text-left"></div>
                </div>
                <div class="row">
                    <div class="col-xs-6"><label for="txt_precio_venta" class="width_100 text-right">Precio de Venta:</label></div>
                    <div class="col-sm-6"><input type="text" value="$ ${selectedItem.item.salePrice}" readonly id="txt_precio_venta" class="resalta_negro width_100 text-left"></div>
                </div>
                <div class="row">
                    <div class="col-xs-6"><label for="txt_precio_renta" class="width_100 text-right">Precio de Renta:</label></div>
                    <div class="col-xs-6"><input type="text" value="$ ${selectedItem.item.rentPrice}" readonly id="txt_precio_renta" class="resalta_negro width_100 text-left"></div>
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
        <a href="<c:url value='/listItems'/>" class="btn btn-primary btn_dark">
            <span class="glyphicon glyphicon-arrow-left"></span>
            Regresar
        </a>
    </article>
</footer>


<!-- -------------- SCRIPTS ---------------- -->
<script src="<c:url value='/resources/js/util/jquery.elevateZoom-3.0.8.min.js'/>"></script>
<script src="<c:url value='/resources/js/argos_custom__frontend_1.js'/>"></script>
<script src="<c:url value='/resources/js/item-detail.js'/>"></script>
<!-- -------------- SCRIPTS ---------------- -->
