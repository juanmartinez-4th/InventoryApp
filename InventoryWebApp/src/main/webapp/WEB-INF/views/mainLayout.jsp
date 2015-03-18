<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
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
    
    <!-- -------------- SCRIPTS ---------------- -->
    <script src="<c:url value='/resources/js/util/jquery-2.1.3.min.js' />"></script>
    <script src="<c:url value='/resources/js/util/bootstrap.min.js' />"></script>
    <script>var ctx = "${pageContext.request.contextPath}"</script>
    <!-- -------------- SCRIPTS ---------------- -->
</head>

<body>

    <div id="mask"></div>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
    
</body>
</html>
