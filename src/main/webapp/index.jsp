<%-- 
    Document   : index
    Created on : 24/07/2016, 19:07:52
    Author     : kieckegard
--%>

<%@page import="br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia"%>
<%@page import="br.edu.ifpb.bdnc.maisdenuncia.model.QueryDenunciaBo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>+Denúncia</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="assets/css/main.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/estilo.css" />
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
        <!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
        <!-- TOOLTIP JS -->
        <link type="text/css" rel="stylesheet" href="tooltip/tooltip.css" />
        <script type="text/javascript" src="tooltip/tooltip.js"></script>
        <!-- END TOOLTIP JS -->
    </head>
    <body class="landing">

        <!-- Header -->
        <header id="header" class="skel-layers-fixed">
            <h1><a href="index.jsp">+Denúncia</a></h1>
        </header>

        <!-- Banner -->
        <section id="banner" class="row uniform">
            <div class="6u 12u(xsmall)">
                <i class="icon fa-map-marker"></i>
                <h2>+Denúncia</h2>
                <p>Não se cale, denuncie!</p>
                <c:if test="${!empty sessionScope.loggedUser}">
                    <ul class="actions">
                        <li><a href="registerComplaint.jsp" class="button big special">Registrar Denúncia</a></li>
                    </ul>
                </c:if>
            </div>
            <div class="5u 12u(xsmall)">
                <c:if test="${empty sessionScope.loggedUser}" >
                    <h2>Contribua <span class="green">você</span> também!</h2>
                    <form action="Login" method="POST" class="row uniform 50%" style="padding: 0px 15px 15px 0px; background-color: rgba(0,0,0,0.6); border-radius: 8px">
                            
                        <c:if test="${requestScope.success == false}">
                            <h4 style="text-align: center !important; color: indianred;">Email e/ou Senha incorreto(s)!</h4>
                        </c:if>
                            
                        <div class="12u 12u(xsmall)"><input type="text" name="userEmail" placeholder="E-mail" required></div>
                        <div class="12u 12u(xsmall)"><input type="password" name="userPassword" placeholder="Senha" required></div>
                        <div class="12u" style="margin-top: 20px;">
                            <ul class="actions">
                                <li><input type="submit" class="button special fit" value="Entrar"></li>
                                <li><a href="registerUser.jsp" class="button alt fit">Cadastrar nova conta</a></li>
                            </ul>
                        </div>
                        
                        
                    </form>
                </c:if>
                <c:if test="${!empty sessionScope.loggedUser}">
                    <form class="row uniform 50%" style="padding: 0px 15px 15px 0px; background-color: rgba(0,0,0,0.6); border-radius: 8px; margin-top: 50px;">
                        <div class="12u 12u(xsmall)"><h2>Bem Vindo, <span class="green">${sessionScope.loggedUser.getNome()}</span></h2></div>
                        <div class="12u" style="margin-top: 20px;">
                            <ul class="actions">
                                <li>
                                    <a href="Logout" class="button alt fit">LogOut</a>
                                </li>
                            </ul>
                        </div>
                    </form>
                </c:if>
            </div>
        </section> 

        <input id="pac-input" class="search-input " type="text" name="" placeholder="Search a place!">
        <div class="row uniform 50%" style="height: 700px;">

            <!-- <div class="control-map">	
                    <button id="toggle-heatmap" class="control-buttons google-button" onclick="toggleHeatMap();">Mapa de calor</button>
            </div>  -->
            <!-- map -->
            <div id="googleMaps" class="9u 12u(xsmall)" style="background-color: #eee; height: 100%; ">	


            </div>
            <!-- menu -->
            <div class="3u 12u(xsmall)" style="background-color: #333; height: 100%; padding: 15px !important;">
                <h3 style="color: #fff; text-align: center;">Ações</h3>

                <!-- HIDDEN PERSONALIZED PERIOD FORM -->
                <div class="abra" style="display: none;">
                    <form id="personalized" style="margin-bottom: 10px !important;">
                        <div class="row uniform 50%">
                            <div class="12u 12u(xsmall)">
                                <input id="startDate" name="startDate" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" 
                                       title = "Por favor, insira a data no padrão 'dd/MM/yyyy'" 
                                       type="text" placeholder="Data início" required>
                            </div>
                            <div class="12u 12u(xsmall)">
                                <input id="endDate" name="endDate" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" 
                                       title = "Por favor, insira a data no padrão 'dd/MM/yyyy'" 
                                       type="text" placeholder="Date final"  required>
                            </div>
                            <div class="12u 12u">
                                <button class="button green-button small-button" style="margin-top: 10px;" onclick="loadMarksByDate();" type="button">Atualizar</button>
                            </div>
                        </div>

                    </form>
                </div>
                <!-- END OF HIDDEN PERSONALIZED PERIOD FORM -->

                <!-- HIDDEN PERIOD OPTIONS-->
                <div class="personalized-period" style="display: none;">
                    <div id="filter_date">
                        <a onclick="loadAllMarks();" class="button special fit">Todos</a>
                        <a onclick="loadMarksByMonth();" class="button special fit">Mensal</a>
                        <a onclick="tooltip.pop(this, '#personalized');" class="button special fit">Personalizado</a>	
                    </div>
                </div>
                <!-- END OF HIDDEN PERIOD OPTIONS -->
                <div class="personalized-period" style="display: none;">
                    <form id="filter_type" style="margin-bottom: 5px !important;">
                        <div class="12u 12u(xsmall) black-mark">
                            <input id="assedio"  type="checkbox" name="assedio">
                            <label for="assedio"><i class="fa fa-map-marker assedio" aria-hidden="true"></i> Assédio</label>
                        </div>
                        <div class="12u 12u(xsmall) black-mark">
                            <input id="agressao" type="checkbox" name="agressao">
                            <label for="agressao"><i class="fa fa-map-marker agressao" aria-hidden="true"></i> Agressão</label>
                        </div>
                        <div class="12u 12u(xsmall) black-mark">
                            <input id="estupro"  type="checkbox" name="estupro">
                            <label for="estupro"><i class="fa fa-map-marker estupro" aria-hidden="true"></i> Estupro</label>
                        </div>
                        <div class="12u 12u(xsmall)">
                            <button class="button green-button small-button" style="margin-top: 10px;" onclick="filterMarksByType();" type="button">Atualizar</button>
                        </div>
                    </form>
                </div>

                <div class="12u 12u"><input type="checkbox" id="usingHeatmap" onclick="toggleHeatMap();" style="color: #fff; !important" name="usingHeatmap"><label for="usingHeatmap">Usar Mapa de Calor</label></div>
                <div class="12u 12u"><a class="button special fit" onclick="tooltip.pop(this, '#filter_date');">Filtrar por data</a></div>
                <div class="12u 12u"><a class="button special fit" onclick="tooltip.pop(this, '#filter_type');">Filtrar por Categoria</a></div>
                <div class="12u 12u box-model">
                    <div class="box-model-header">
                        <h4>Ultimas Denúncias</h4>
                    </div>
                    <div class="box-model-body">
                        <ul class="list">
                            <%
                                QueryDenunciaBo bo = new QueryDenunciaBo();
                                List<Denuncia> denuncias = bo.lastOf(10);
                                for (Denuncia d : denuncias)
                                {
                                    out.print("<li>");
                                    out.print("<h4>" + d.getDescricao() + "</h4>");
                                    out.print("<p>" + d.getTipoDenuncia().name() + "</p>");
                                    out.print("<p>" + DateUtils.formatToBrazilPattern(d.getData()) + "</p>");
                                    out.print("</li>");
                                }
                            %>
                        </ul>
                    </div>

                </div>
            </div>
        </div>



        <script type="text/javascript" src="assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="assets/js/script.js"></script>
    </body>
</html>