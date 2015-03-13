<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<main>
    <article class="container">

        <!-- SECCION 1 -->
        <!-- CÓDIGO Y EXISTENCIAS -->
        <section class="row subraya_gris">
            <div class="col-sm-4">
                <label>Código: </label>
                <input type="text" value="${selectedItem.item.code}" readonly class="resalta_rojo">
            </div>

            <div class="col-sm-4 pull-right">
                <label>Existencia: </label>
                <input type="text" value="${selectedItem.item.existence}&nbsp;${selectedItem.item.unitOfMeasure.name}" readonly  class="resalta_rojo">
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
                    <ol id="breadcumbCategory" class="breadcrumb inline-block">
                    </ol>

                    <label>Descripción: </label>
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
                            <input type="text" value="${selectedItem.location.project}" readonly id="txt_proyecto" class="resalta_negro width_100 text-center">
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
                    <div class="col-sm-6"><label for="txt_costo" class="width_100 text-right">Costo:</label></div>
                    <div class="col-sm-6"><input type="text" value="$ ${selectedItem.item.cost}" readonly id="txt_costo" class="resalta_negro width_100 text-left"></div>
                </div>
                <div class="row">
                    <div class="col-sm-6"><label for="txt_precio_venta" class="width_100 text-right">Precio de Venta:</label></div>
                    <div class="col-sm-6"><input type="text" value="$ ${selectedItem.item.salePrice}" readonly id="txt_precio_venta" class="resalta_negro width_100 text-left"></div>
                </div>
                <div class="row">
                    <div class="col-sm-6"><label for="txt_precio_renta" class="width_100 text-right">Precio de Renta:</label></div>
                    <div class="col-sm-6"><input type="text" value="$ ${selectedItem.item.rentPrice}" readonly id="txt_precio_renta" class="resalta_negro width_100 text-left"></div>
                </div>
            </div>

            <!-- COLUMNA 3 -->
            <!-- FOTOS del Producto -->
            <div class="col-sm-4">
                <figure id="vista_producto_grande" class="width_100 img_redondeada">
                    <img src="http://placehold.it/1025x1025&text=Foto%20del%20Producto" alt="Foto del producto" class="img-responsive img_centered">
                </figure>

                <div class="row">
                    <div class="col-sm-4">
                        <figure id="producto_miniatura_1" class="width_100 img_redondeada">
                            <img src="<c:url value='/resources/images/demo/placeholder_118px_articulo.png'/>" alt="Foto del producto" class="img-responsive img_centered">
                        </figure>
                    </div>
                    <div class="col-sm-4">
                        <figure id="producto_miniatura_2" class="width_100 img_redondeada">
                            <img src="<c:url value='/resources/images/demo/placeholder_118px_articulo.png'/>" alt="Foto del producto" class="img-responsive img_centered">
                        </figure>
                    </div>
                    <div class="col-sm-4">
                        <figure id="producto_miniatura_3" class="width_100 img_redondeada">
                            <img src="<c:url value='/resources/images/demo/placeholder_118px_articulo.png'/>" alt="Foto del producto" class="img-responsive img_centered">
                        </figure>
                    </div>
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
