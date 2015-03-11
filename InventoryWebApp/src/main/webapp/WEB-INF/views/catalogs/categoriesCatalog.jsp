<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<section id="barra_acciones" class="container">
    <div class="row">        
        <section id="nav_nivel_2">
            <ul class="nav navbar-nav">
                <li><a href="" class="active">Categorías</a></li>
				<li><a href="<c:url value='/adminUnitsOfMeasure'/>">Unidades de medida</a></li>
            </ul>
        </section>
        
        <div class="col-sm-3 pull-right">
            <!-- Button para MODAL [+ nueva categoría] -->
            <button onclick="javascript:showCategoryModal(0, '', '', 0)" type="button" class="btn_chico btn btn-success pull-right side_paddings" data-toggle="modal">
                <span class="glyphicon glyphicon-plus"></span>
                Nueva Categoría
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
        <section class="table-responsive">
            <table class="table table-condensed table-hover">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Catálogo de origen</th>
                        <th class="text-center">Editar</th> 
                        <th class="text-center">Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty categoriesList}">
                        <c:forEach items="${categoriesList}" var="currCategory">
	                    <tr>
	                        <td>${currCategory.name}</td>
	                        <td>${currCategory.description}</td>
	                        <td>
	                            <ol class="breadcrumb">
	                                <c:set var="currentSubcategory" value="${currCategory.parentCategory}" />
	                                <c:forEach begin="0" end="10">
                                        <c:if test="${not empty currentSubcategory}">
                                            <c:set var="parentCat" value="${currentSubcategory.parentCategory}" />
                                            <c:choose>
                                                <c:when test="${not empty parentCat}">
                                                    <li>${currentSubcategory.name}</li>
                                                    <c:set var="currentSubcategory" value="${currentSubcategory.parentCategory}" />
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="active">${currentSubcategory.name}</li>
                                                    <c:set var="currentSubcategory" value="" />
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
	                                </c:forEach>
	                            </ol>
	                        </td>
	                        <td class="text-center">
	                            <a onclick="javascript:showCategoryModal(${currCategory.id}, '${currCategory.name}', '${currCategory.description}', ${not empty currCategory.parentCategory ? currCategory.parentCategory.id : 0})"
	                                data-toggle="modal" 
	                                id="edit_${currCategory.id} }" 
	                                class="btn btn-primary btn_dark btn_tabla" 
	                                href="#"><i class="fa fa-pencil fa-lg"></i></a>
	                        </td>
	                        <td class="text-center">
	                            <a onclick="javascript:confirmDelete(${currCategory.id}, '${currCategory.name}')"
                                    data-toggle="modal" 
                                    id="delete_${currCategory.id} }" 
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


<!-- MODAL [+ nueva categoría] -->
<div class="modal fade" id="modal_nueva_categoria" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <input type="hidden" id="categoryAction" value="new" />
        <form:form modelAttribute="category" id="category" method="POST">
            <form:input path="id" type="hidden" id="categoryId" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 id="catModalTitle" class="modal-title">Nueva categoría</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">            
                <!-- CAMPO 01 / Nombre de categoría   -->
                <label for="txt_categoria_nombre">Nombre</label>
                <div class="input-group" >
                    <form:input path="name" id="txt_categoria_nombre" type="text" class="form-control" 
                        placeholder="" data-toggle="popover" data-placement="top" title="Indique el nombre de la categoría" 
                        maxlength="35"/>
                    <span class="input-group-btn">
                        <button id="name-help" class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->
                
                <!-- CAMPO 02 / Descripción   -->
                <label for="txt_descripcion">Descripción</label>
                <div class="input-group" >
                    <form:input path="description" id="txt_descripcion" type="text" class="form-control" placeholder="" maxlength="60"/>
                    <span class="input-group-btn">
                        <button class="btn_chico btn btn-default" type="button"><span class="glyphicon glyphicon-info-sign"></span></button>
                    </span>
                </div><!-- /input-group -->
               
                <!-- CAMPO 03 / Jerarquía de categorías   -->
                <label for="txt_categoria">Catálogo de origen</label>   
                <div class="row">        
                    <div class="col-xs-8">
                        <div class="input-group" >
                            <div id="txt_categoria" class="form-control">
                                <form:input path="parentCategory.id" type="hidden" id="parentCategory" value="0" />
                                <ol class="breadcrumb" id="breadcrumbParentcat">
                                    <li>Categoría padre</li>
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
                        <ul class="dropdown-menu" role="menu" id="categoriesMenu">
                            <li onclick="javascript:setParentCategory(0)"><a href="#">Ninguna</a></li>                            
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnSaveCategory" class="btn btn-success pull-right">
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


<!-- MODAL [eliminar categoría] -->
<div class="modal fade" id="modal_confirm_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <input type="hidden" id="categoryToDelete" value="0" />
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Eliminar categoría</h4>
            </div>
            
            <!-- CONTENIDO del modal -->
            <div class="modal-body">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error: </span> ¿Realmente desea eliminar la categoría &nbsp;
                    <label id="categoryNameToDelete"></label>?
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteCategory" class="btn btn-success pull-right">
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
<script src="<c:url value='/resources/js/categories.js'/>"></script>
<!-- -------------- SCRIPTS ---------------- -->
