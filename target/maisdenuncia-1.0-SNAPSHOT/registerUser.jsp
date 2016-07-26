<%-- 
    Document   : registerUser
    Created on : 24/07/2016, 19:15:35
    Author     : kieckegard
--%>

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
		<h2 style="text-align: center;">Cadastro de <span class="green">Usuário</span></h2>
		<form id="userForm" action="RegisterUser" method="POST" style="margin-top: 20px;">
		<h3><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>Para realizar o seu cadastro preencha todos os campos abaixo!</h3>
			<div class="row uniform 50%">
				<div class="12u 12u(xsmall)">
					<input type="text" name="name" id="name" value="" placeholder="Name" required>
				</div>
                                <div class="12u 12u(xsmall)">
                                    <input id="date" type="text" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" 
                                               title = "Por favor, insira a data no padrão 'dd/MM/yyyy'" 
                                               name="birthdate" placeholder="Data de Nascimento" required>
                                </div>
                            
                                <div class="12u 12u(xsmall)">
                                        <input id="M" type="radio" name="sex" value="M">
                                        <label class="label" for="M">Masculino</label>
                                        <input id="F" type="radio" name="sex" value="F">
                                        <label class="label" for="F">Feminino</label>
                                </div>
                            
				<div class="12u 12u(xsmall)">
					<input type="text" name="email" id="email" value="" placeholder="E-mail" required>
				</div>

                                <div class="12u 12u(xsmall)">
					<input type="password" name="password" id="password" value="" placeholder="Senha" required>
				</div>
                            
				<div class="6u 12u(xsmall)">
					<input type="text" name="country" id="country" value="" placeholder="País" required>
				</div>

				<div class="6u 12u(xsmall)">
					<input type="text" name="state" id="state" value="" placeholder="Estado" required>
				</div>

				<div class="6u 12u(xsmall)">
					<input type="text" name="city" id="city" value="" placeholder="Cidade" required>
				</div>

				<div class="6u 12u(xsmall)">
					<input type="text" name="neighborhood" id="neighborhood" value="" placeholder="Bairro" required>
				</div>

				<div class="6u 12u(xsmall)">
					<input type="text" name="adress" id="street" value="" placeholder="Rua" required>
				</div>
                            
                                <div class="6u 12u(xsmall)">
					<input type="text" name="number" id="number" value="" placeholder="Número" required>
				</div>

				<div class="12u" style="margin-top: 20px;">
					<ul class="actions">
						<li><input class="special" type="submit" value="Completar Cadastro"></li>
						<li><input class="default" type="button" value="Resetar Formulário" onclick="cleanUserForm();"></li>
					</ul>
				</div>
			</div>
		</form>
		<!-- feedback alert -->
                <c:if test="${requestScope.success}">
                    <div class="12u 12u(xsmall) alert success"><h4 style="margin: 0px;">Cadastro Realizado com Sucesso!</h4></div>
                </c:if>
                <c:if test="${requestScope.success == false}">
                    <div class="12u 12u(xsmall) alert danger"><h4 style="margin: 0px;">${requestScope.errorMsg}</h4></div>
                </c:if>
                
	</div>

        
        <script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/register_user.js"></script>
</body>
</html>
