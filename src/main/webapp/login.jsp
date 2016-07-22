<%-- 
    Document   : login
    Created on : 30/06/2016, 13:32:53
    Author     : kieckegard
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login - +Denuncia</title>
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<link href='https://fonts.googleapis.com/css?family=Raleway:400,600' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        
        <!-- jQuery -->
        <script type="text/javascript" src="js/jquery-1.12.3.js"></script>
        
        <!-- Ajax Form -->
        <script src="http://malsup.github.com/jquery.form.js"></script>

	<!-- TOOLTIP JS -->
	<link type="text/css" rel="stylesheet" href="tooltip/tooltip.css" />
	<script type="text/javascript" src="tooltip/tooltip.js"></script>
	<!-- END TOOLTIP JS -->

	<script type="text/javascript" src="js/script.js"></script>
</head>
<body>
    <header class="spaced-bottom">
		<nav class="navigation">
			<a href="index.html">
				<h1 class="logo">
					<i class="fa fa-map-marker" aria-hidden="true"></i> 
					<i class="fa fa-plus" aria-hidden="true"></i>Denúncia
				</h1>
			</a>
		</nav>
    </header>
    <div class="centered-container login">
		<div class="centered-container-header">
			<h2>Acesso de Usuário</h2>
		</div>
		<div class="centered-container-body ">
			<form class="formulary" action="Login" method="POST">
				<input type="text" name="userEmail" placeholder="E-mail">
				<input type="password" name="userPassword" placeholder="Senha">
				<button class="button green-button">Login</button>
			</form>
                        <c:if test="${requestScope.success == true}">
                            <div class="alert alert-success">
                                <strong>Success!</strong> Indicates a successful or positive action.
                            </div>
                        </c:if>
                        <c:if test="${requestScope.success == false}">
                            <div class="alert alert-danger">
                                <strong>Usuário e/ou Senha incorreto(s)!</strong> Indicates a successful or positive action.
                            </div>
                        </c:if>
		</div>
	</div>
</body>
</html>
