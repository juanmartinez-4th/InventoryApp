<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en" manifest="cache.appcache">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Argos - Control de Almacén</title>
    
    <!-- Cargar archivos CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/util/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/argos_styles.css'/>">
    
    <!-- Cargar fuente de google fonts -->
    <link href="http://fonts.googleapis.com/css?family=Roboto:400italic,100,100italic,400" rel="stylesheet" type="text/css">
    
    <!-- Cargar FONT AWESOME para importar librería de íconos -->
    <link rel="stylesheet" href="<c:url value='/resources/fonts/util/font-awesome-4.3.0/css/font-awesome.min.css'/>">
    
</head>

<body>
    <header class="pag_login">
        <div class="container">
                    
            <!-- <h1>Empresa</h1> -->
            <figure id="logo_empresa" class="col-sm-6">
                <!-- <img src="http://placehold.it/203x36&text=Logotipo" alt="Logotipo de Empresa" class="img-responsive pull_left_md"> -->
                <img src="<c:url value='/resources/images/logotipo_argos.png'/>" alt="Logotipo de Empresa" class="img-responsive">
            </figure>
            
            <div id="Subtitulo" class="col-sm-6">
                <h2>Control de Almacén</h2>
            </div>
        </div>
    </header>


    <main class="pag_login">
        <article class="container">
            <section class="row">
                <div class="col-sm-6">
                    <figure>
                        <img src="<c:url value='/resources/images/img_login.png' />" alt="Logotipo de Empresa" class="img_centered img-responsive">
                    </figure>
                </div>

                <div class="col-sm-6 _paddings">                
                    <form name="loginForm" action="<c:url value='/auth/login_check?targetUrl=${targetUrl}' />" method="POST" role="form">
                        <c:if test="${not empty error}">
	                        <div class="form-group alert alert-danger flash" role="alert">
	                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	                            <span class="sr-only">Error: </span> ${error}
	                        </div>
	                    </c:if>
	                    <c:if test="${not empty msg}">
	                        <div class="form-group alert alert-success flash" role="alert">
	                            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
	                            <span class="sr-only">Mensaje: </span> ${msg}
	                        </div>
	                    </c:if>
                    
                        <div class="form-group edit_text_capsula">
                            <figure class="glyphicon glyphicon-user"></figure>
                            <input type="text" id="username" class="" name="username" placeholder="usuario">
                        </div>

                        <div class="form-group edit_text_capsula">
                            <figure class="glyphicon glyphicon-lock"></figure>
                            <input type="password" id="password" class="" name="password" placeholder="contraseña">
                        </div>
                            
                        <c:if test="${empty loginUpdate}">
	                        <div class="form-group">
	                            <div class="text-center btn-group" data-toggle="buttons">
                                    <div class="btn btn-default active check_custom_table">
	                                    <input type="checkbox" name="remember-me" value="recordar_contrasena" id="remember-me" autocomplete="off">
	                                    <span class="glyphicon glyphicon-ok"></span>
	                                </div>
	                            </div>
	                            
	                            <label for="recordar_contrasena" id="lbl_recordar_contrasena">Recordar contraseña</label>
	                            <button id="btn_login" class="btn_capsula pull-right" type="submit">acceder</button>
	                        </div>
	                    </c:if>
                        
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
            </section>
        </article>
        
    </main>

    <!-- -------------- SCRIPTS ---------------- -->
    <script src="<c:url value='/resources/js/util/jquery-2.1.3.min.js' />"></script>
    <script src="<c:url value='/resources/js/util/bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/js/utils.js' />"></script>
    <!-- -------------- SCRIPTS ---------------- -->
    
    <script>
        $("#username").attr("required", ""); 
        $("#username").attr("autofocus", "");
        $("#password").attr("required", "");
    </script>

</body>
</html>
