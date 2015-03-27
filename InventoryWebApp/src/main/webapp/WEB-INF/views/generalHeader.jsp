<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<header>
    <div class="container">
        <!-- <h1>Empresa</h1> -->
        <figure id="logo_empresa" class="col-sm-6">
            <!-- <img src="http://placehold.it/203x36&text=Logotipo" alt="Logotipo de Empresa" class="img-responsive pull_left_md"> -->
            <img src="<c:url value='/resources/images/logotipo_argos.png'/>" alt="Logotipo de Empresa" class="img-responsive">
        </figure>
        
        <div id="Subtitulo" class="col-sm-6">
            <h2><c:if test="${empty pageTitle}">Control de Almacén</c:if><c:if test="${not empty pageTitle}">${pageTitle}</c:if></h2>
        </div>
    </div>
</header>

<!-- BARRA de navegación-->
<!-- <nav class="navbar navbar-default navbar-static-top"> -->
<nav id="nav_nivel_1"  class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- <div class="navbar-collapse collapse img_centered"> -->
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value='/listItems'/>">
                    <span class="glyphicon glyphicon-home"></span> Almacén</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
	                <li class="dropdown" role="presentation">
	                    <a  class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" 
	                       aria-expanded="false"><span class="glyphicon glyphicon-wrench"></span>
	                       Administración <span class="caret"></span></a>
	                    <ul class="dropdown-menu" role="menu">
	                        <li><a href="<c:url value='/adminUsers'/>">Administración de usuarios</a></li>
                            <!-- <li><a href="#">Seguridad</a></li>
                            <li><a href="#">Parámetros</a></li> -->
                            <li><a href="<c:url value='/adminCategories'/>">Catálogos</a></li>
	                    </ul>
	                </li>
                </sec:authorize>
                <li class="dropdown pull-right" role="presentation">
                    <a  class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
	                    <span class="glyphicon glyphicon-user"></span>
	                    ${pageContext.request.userPrincipal.name}<span class="caret"></span>
	                </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:logout()">Cerrar sesión</a></li>
                    </ul>
                </li>
                <li class="col-sm-4 pull-right">
	                <c:if test="${empty resultsFilter}">
	                    <div id="barra_buscar" class="input-group">
	                        <input type="text" class="form-control" placeholder="Buscar...">
	                        <span class="input-group-btn">
	                            <button class="btn_chico btn btn-primary" type="button"><figure class="glyphicon glyphicon-search"></figure></button>
	                        </span>
	                    </div>
	                </c:if>
	                <c:if test="${not empty resultsFilter}">
	                    <form:form modelAttribute="resultsFilter" id="resultsFilter" action="${resultsFilter.filterName}" method="GET">
	                        <div id="barra_buscar" class="input-group" >
	                            <form:input path="filter1" id="txt_filter" type="text" class="form-control" placeholder="Buscar..." />
	                            <span class="input-group-btn">
	                                <button class="btn_chico btn btn-primary" type="submit"><figure class="glyphicon glyphicon-search"></figure></button>
	                            </span>
	                        </div>
	                    </form:form>
	                </c:if>
                </li>
            </ul>
        </div>
    </div>
</nav>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm" class="logout_form">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
