<%-- 
    Document   : registrarDenuncia
    Created on : 30/06/2016, 00:57:25
    Author     : kieckegard
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Registro de Denúncia</title>
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<script type="text/javascript" src="js/register_map.js"></script>
	<link href='https://fonts.googleapis.com/css?family=Raleway:400,600' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
</head>
<body>
	<header class="spaced-bottom">
		<nav class="navigation">
			<a href="user.jsp">
				<h1 class="logo">
					<i class="fa fa-map-marker" aria-hidden="true"></i> 
					<i class="fa fa-plus" aria-hidden="true"></i>Denúncia
				</h1>
			</a>
		</nav>
	</header>
	<div class="content">
		<div class="centered-container complaint">
			<div class="centered-container-header map">
				<div id="map"></div>
			</div>
			<div class="centered-container-body">
				<form action="RegisterComplaint" method="POST">
					<p>Dados da denúncia</p>
					<input type="text" name="complaintDescription" placeholder="Descrição" required>
					<select id="complaintTypeSelector" name="complaintType" required>
						<option value="0" disabled selected>Tipo da Denúncia</option>
						<option value="1">Assédio</option>
						<option value="2">Agressão</option>
						<option value="3">Estupro</option>
					</select>
					<input type="text" name="complaintDate" placeholder="Data do acontecimento" required> 
					<select id="userTypeSelector" name="userType" required>
						<option value="0" disabled selected>Você...</option>
						<option value="1">É a vítima</option>
						<option value="2">Presenciou o acontecimento</option>
					</select>
					<input id="placeLat" style="display: none;" type="text" name="placeLat">
					<input id="placeLng" style="display: none;" type="text" name="placeLng">
					<button class="button green-button">Registrar Denúncia</button>
				</form>
                                <c:if test="${requestScope.successRegister = true}">
                                    <p style="text-align: center;">Registro de Denúncia realizado com sucesso!</p>
                                </c:if>
                                <c:if test="${requestScope.successRegister = false}">
                                    <p style="text-align: center;">falha ao realizar registro!</p>
                                </c:if>
			</div>
		</div>
	</div>

	<footer style="">
            <p>Made with <i class="fa fa-heart" aria-hidden="true"></i> by <i class="author">Kieckegard</i></p>
	</footer>
</body>
</html>