<%-- 
    Document   : registerComplaint
    Created on : 24/07/2016, 19:13:17
    Author     : kieckegard
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<title>Registro de Denúncia</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="assets/css/estilo.css">
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
	</head>
<body class="landing">

	<!-- Header -->
	<header id="header" class="skel-layers-fixed">
		<h1><a href="index.jsp">+Denúncia</a></h1>
	</header>

	
	<div class="container" style="margin: 100px auto; border: 1px solid #eee; padding: 20px 20px 0px 20px; border-radius: 8px;">
            
                <c:if test="${requestScope.success}">
                    <div class="12u 12u(xsmall) alert success"><h4 style="margin: 0px;">Cadastro Realizado com Sucesso!</h4></div>
                </c:if>
                <c:if test="${requestScope.success == false}">
                    <div class="12u 12u(xsmall) alert danger"><h4 style="margin: 0px;">Cadastro Realizado com Sucesso!</h4></div>
                </c:if>
                    
		<h3>Clique no local onde você presenciou ou foi vítima do acontecimento!</h3>
                <input id="pac-input" class="search-input " type="text" name="" placeholder="Search a place!">
		<div id="map" style="height: 400px; background: lightblue;">
			
		</div>

		<form id="complaintForm" method="GET" action="RegisterComplaint" style="margin-top: 20px;">
		<h3>Preencha todos os campos!</h3>
			<div class="row uniform 50%">
				<div class="12u 12u(xsmall)">
					<textarea name="complaintDescription" placeholder="Descrição" required></textarea>
				</div>

				<div class="12u 12u(xsmall)">
					<div class="select-wrapper">
						<select id="complaintType" name="complaintType" required>
							<option value="0" disabled selected>Tipo da Denúncia</option>
                                                        <option value="1">Assédio</option>
                                                        <option value="2">Agressão</option>
                                                        <option value="3">Estupro</option>
						</select>
					</div>
				</div>
                                <div class="6u 12u(xsmall)">
					<input id="date" type="text" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" 
                                               title = "Por favor, insira a data no padrão 'dd/MM/yyyy'" 
                                               name="complaintDate" placeholder="Data do acontecimento" required>
				</div>
				<div class="3u 12u(xsmall)" style="margin-top: 10px;">
					<input id="vitima" value="1" type="radio" name="userType" required>
					<label for="vitima">Você foi a Vítima</label>
				</div>
				<div class="3u 12u(xsmall)" style="margin-top: 10px;">
					<input id="presenciou" value="2" type="radio" name="userType" required>
					<label for="presenciou">Você presenciou o ocorrido</label>
				</div>
                            
                                <div class="12u 12u(xsmall) black-mark" style="margin-top: 10px">
                                    <input id="ehAnonimo" type="checkbox" name="ehAnonimo" value="1" checked>
                                    <label for="ehAnonimo">A denúncia será realizada anonimamente</label>
                                </div>
                            
				<input type="text" id="lat" name="lat" style="display: none;">
				<input type="text" id="lng" name="lng" style="display: none;">
				<div class="12u" style="margin-top: 20px;">
					<ul class="actions">
						<li><input class="special" type="submit" onclick="validate(event);" value="Registrar Denúncia"></li>
						<li><input class="default" type="button" value="Resetar Formulário" onclick="cleanComplaintForm();"></li>
					</ul>
				</div>
			</div>
		</form>
                 <div id="alertPlaceNotSelected" class="12u 12u(xsmall) alert danger hidden"><h4 style="margin: 0px;">Por favor, marque o local do ocorrido!</h4></div>
                 <div id="alertDenunciaNotSelected" class="12u 12u(xsmall) alert danger hidden"><h4 style="margin: 0px;">Por favor, selecione o tipo da denúncia!</h4></div>
	</div>

	
        <script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/register_map.js"></script>
</body>
</html>
