<%-- 
    Document   : cadastroUsuario
    Created on : 29/06/2016, 14:35:17
    Author     : kieckegard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Cadastro de Usuário</title>
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<link href='https://fonts.googleapis.com/css?family=Raleway:400,600' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
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
	<div class="content">
		<div class="centered-container">
			<div class="centered-container-header">
				<h2>Cadastro de Usuário</h2>
			</div>
			<div class="centered-container-body">
				<form class="formulary" action="RegisterUser" method="POST">
					<div class="form-inputs">
						<div class="form-group">
							<p>Pessoa</p>
							<input type="text" name="userName" placeholder="Nome completo">
							<input type="text" name="userBirthdate" pattern="{0-9}[2]/{0-9}[2]/{0-9}[4]" placeholder="Data de Nascimento">
							<div class="radio-group">
								<p>Sexo</p>
								<input type="radio" name="gender" value="Masculino" checked> Masculino<br>
							    <input type="radio" name="gender" value="Feminino"> Feminino<br>
							    <input type="radio" name="gender" value="Outro"> Outro
							</div>
						</div>
						<div class="form-group">
							<p>Usuário</p>
							<input type="text" name="userEmail" placeholder="Email">
							<input type="password" name="userPassword" placeholder="Senha">
						</div>	
						<div class="form-group">
							<p>Endereço</p>
							<input type="text" name="userCountry" placeholder="País">
							<input type="text" name="userState" placeholder="Estado">
							<input type="text" name="userCity" placeholder="Cidade">
							<input type="text" name="userNeighborhood" placeholder="Bairro">
							<input type="text" name="userStreet" placeholder="Rua">
							<input type="text" name="userPlaceNumber" placeholder="Número">
						</div>
					</div>

					<button class="button green-button">Cadastrar</button>
				</form>
			</div>
		</div>
	</div>

	<footer style="">
			<p>Made with <i class="fa fa-heart" aria-hidden="true"></i> by <i class="author">Kieckegard</i></p>
	</footer>
</body>
</html>
