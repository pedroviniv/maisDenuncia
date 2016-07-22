<%-- 
    Document   : user.jsp
    Created on : 26/06/2016, 17:29:29
    Author     : kieckegard
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.utils.DenunciaGsonUtils"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.model.QueryDenunciaBo"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>.:: +Denúncia ::.</title>
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<link href='https://fonts.googleapis.com/css?family=Raleway:400,600' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        
        
        
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
    <!-- Login modal -->
    
    
    
    <!-- end of login modal -->
    
	<header>
		<nav class="navigation">
			<a href="user.jsp" class="link-logo">
				<h1 class="logo">
					<i class="fa fa-map-marker" aria-hidden="true"></i> 
					<i class="fa fa-plus" aria-hidden="true"></i>Denúncia
				</h1>
			</a>
                        <c:if test="${sessionScope.loggedUser != null}">
                            <a href="registrarDenuncia.jsp" class="button-link green-button menu-button">
                                    Nova Denúncia 
                                    <i class="fa fa-plus-square" aria-hidden="true"></i>
                            </a>
                        </c:if>
		</nav>
		<div class="profile-container">
                    <c:if test="${sessionScope.loggedUser != null}">
			<div class="profile-header">
				<p>Bem Vindo, <i class="author">${sessionScope.loggedUser.getNome()}</i></p>
				<a class="button-link" href="Logout"> Sair?</a>
			</div>
                    </c:if>
                    <c:if test="${sessionScope.loggedUser == null}">
                        <div class="profile-header">
				<p>Bem Vindo, <i class="author">Usuário</i></p>
				<a class="button-link" href="login.jsp"> log In</a>
                                <a class="button-link" href="cadastroUsuario.jsp"> Sign In</a>
			</div>
                    </c:if>
		</div>
	</header>
	<div class="content">
		<div class="container-map">
			<input id="pac-input" class="search-input" type="text" name="" placeholder="Search a place!">
			<div class="control-map">
				<button id="toggle-heatmap" class="control-buttons google-button" onclick="toggleHeatMap();">Mapa de calor</button>
			</div>
			<div id="map">
				
			</div>	
		</div>
		<div class="aside">
			<div class="query-container">
				<div class="group-buttons">

					<!-- HIDDEN PERSONALIZED PERIOD FORM -->
						<div class="personalized-period" style="display: none;">
							<form id="personalized">
								<input id="startDate" class="input" pattern="{0-9}[2]/{0-9}[2]/{0-9}[4]" type="text" placeholder="Data início" required>
								<input id="endDate" class="input" pattern="{0-9}[2]/{0-9}[2]/{0-9}[4]" type="text" placeholder="Date final"  required>
								<button class="button green-button small-button" onclick="loadMarksByDate();" type="submit">Atualizar</button>
							</form>
						</div>
					<!-- END OF HIDDEN PERSONALIZED PERIOD FORM -->

					<!-- HIDDEN PERIOD OPTIONS-->
					<div class="periodFilter" style="display: none;">
						<div id="filter_date">
							<a onclick="loadAllMarks();" class="button-link">Todos</a>
							<a onclick="loadMarksByMonth();" class="button-link">Mensal</a>
							<a href="#" onclick="tooltip.pop(this, '#personalized');" class="button-link">	
								Personalizado
							</a>
						</div>
					</div>
					<!-- END OF HIDDEN PERIOD OPTIONS -->

					<a  class="button-link" href="#" onclick="tooltip.pop(this, '#filter_date');">
						Filtrar período 
						<i class="fa fa-calendar" aria-hidden="true"></i>
					</a>
					<a  class="button-link" href="#">
						Filtrar Denúncias 
						<i class="fa fa-filter" aria-hidden="true"></i>
					</a>
					
				</div>

				<div class="last-denuncias-box">
					<div class="last-denuncias-box-header">
						<h2>Últimas denuncias</h2>
					</div>
					<div class="last-denuncias-box-content">
						<ul>
							<% 
                                                            QueryDenunciaBo bo = new QueryDenunciaBo();
                                                            List<Denuncia> denuncias = bo.getDenuncias();
                                                            for(Denuncia d : denuncias){
                                                                out.print("<li>");
                                                                out.print("<a href=''>");
                                                                out.print("<div class='a-content'>");
                                                                out.print("<p class='title'>"+d.getDescricao()+"</p>");
                                                                out.print("<p>"+d.getTipoDenuncia().name()+"</p>");
                                                                out.print("<p>"+DateUtils.formatToBrazilPattern(d.getData())+"</p>");
                                                                out.print("</div>");
                                                                out.print("</a>");
                                                                out.print("</li>");
                                                            }
                                                        %>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer style="">
			<p>Made with <i class="fa fa-heart" aria-hidden="true"></i> by <i class="author">Kieckegard</i></p>
	</footer>

</body>
</html>
