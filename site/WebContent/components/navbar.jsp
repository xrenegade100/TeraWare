<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ page language="java"%>
    <%@ page import="net.teraware.model.bean.UtenteBean" %>
    <%@ page import="net.teraware.model.Cart" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/global.css" %>">
</head>
<body>
<nav class="navbar-container">
	<%
	Cart cartNav = (Cart) request.getSession().getAttribute("cart");
	UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
	String proPic = "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png";
	if(user != null && !user.getPropicUrl().equals("")){
		proPic = user.getPropicUrl();
	}
	%>
	<div class="navbar">
            <div class="hamburger-menu">
                <span class="bar"></span>
                <span class="bar"></span>
                <span class="bar"></span>
            </div>

            <ul class="nav-menu">
                <li class="nav-item" onmouseover="navItemHover(0);" onmouseout="navItemOut(0);">
                    <a class="link paragraph bold" href="#">ACQUISTI</a>
                </li>
                <li class="nav-item" onmouseover="navItemHover(1);" onmouseout="navItemOut(1);">
                    <a class="link paragraph bold" href="#">ESPLORA</a>
                </li>
            </ul>
            <a class="logo-container" href="<%= getServletContext().getContextPath() + "/pages/index.jsp" %>">
                <img class="nav-logo" src="<%= getServletContext().getContextPath() + "/assets/img/logo/vlogo.svg" %>"/>
                <span class="logo-title">TeraWare</span>
            </a>
            <ul class="nav-menu">
            <% if (user != null && user.getRole() == 1) { %>
            	<li class="nav-item">
            		<a class="link paragraph bold" href="<%= getServletContext().getContextPath() + "/pages/admin/index.jsp" %>">AMMINISTRATORE</a>
            	</li>
           	<% } %>
                <li class="nav-item">
                    <a class="cartNumber link paragraph bold" href="<%= getServletContext().getContextPath() + "/pages/carrello.jsp"%>">CARRELLO<%= cartNav != null && cartNav.getNumProdotti() != 0 ? "(" + cartNav.getNumProdotti() + ")" : "" %></a>
                </li>
                <li>
                    <div class="pro-pic-container">
                        <img class="pro-pic" onclick="showOverlay();" src="<%= proPic %>" alt="<%= user == null ? "Utente" : user.getNome() + " " + user.getCognome()%>"/>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <div class="nav-menu-dropdown" onmouseover="navItemHover(0);" onmouseout="navItemOut(0);">
        <ul class="mobile-dropdown-macro">
            <li class="mobile-dropdown-item">
                <span class="section-title-super-small" onclick="showDetails(0);">Shop</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=all&skip=0&limit=10"%>">Shop All</a></li>
                </ul>
            </li>
            <li class="mobile-dropdown-item" onclick="showDetails(1);">
                <span class="section-title-super-small inside-list-color inside-list-position">Assembly</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=cpu&skip=0&limit=10"%>">CPU</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=scheda%20madre&skip=0&limit=10"%>">Motherboard</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=ram&skip=0&limit=10"%>">RAM</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=scheda%20video&skip=0&limit=10"%>">Video Cards</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=ssd&skip=0&limit=10"%>">SSD</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=hdd&skip=0&limit=10"%>">HDD</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=alimentatore&skip=0&limit=10"%>">Power Suppliers</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=dissipatore&skip=0&limit=10"%>">Heatsinks</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=case&skip=0&limit=10"%>">Case</a></li>
                </ul>
            </li>
            <li class="mobile-dropdown-item" onclick="showDetails(2);">
                <span class="section-title-super-small inside-list-color inside-list-position">Pre-Assembled</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=preassemblati&skip=0&limit=10"%>">Our Set-Ups</a></li>
                </ul>
            </li>
            <li class="mobile-dropdown-item" onclick="showDetails(3);">
                <span class="section-title-super-small inside-list-color inside-list-position">Peripherials</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=mouse&skip=0&limit=10"%>">Mouse</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=tastiera&skip=0&limit=10"%>">Keyboard</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=monitor&skip=0&limit=10"%>">Monitor</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=cuffie&skip=0&limit=10"%>">Headphones</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=microfono&skip=0&limit=10"%>">Microphones</a></li>
                </ul>
            </li>
            <li class="mobile-dropdown-item" onclick="showDetails(4);">
                <span class="section-title-super-small inside-list-color inside-list-position">Software</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=audio&skip=0&limit=10"%>">Audio</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=video&skip=0&limit=10"%>">Video</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=immagini&skip=0&limit=10"%>">Images</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=office&skip=0&limit=10"%>">Office</a></li>
                </ul>
            </li>
            <li class="mobile-dropdown-item" onclick="showDetails(5);">
                <span class="section-title-super-small inside-list-color inside-list-position">Games</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/category?category=gioco&skip=0&limit=10"%>">All Games</a></li>
                </ul>
            </li>
            <li class="mobile-dropdown-item"><a class="section-title-super-small hide" href="<%= getServletContext().getContextPath() + "/pages/our_story.jsp" %>">Our Story</a></li>
            <li class="mobile-dropdown-item"><a class="section-title-super-small hide" href="<%= getServletContext().getContextPath() + "/pages/origins.jsp" %>">Origins</a></li>
            <li class="mobile-dropdown-item"><a class="section-title-super-small hide cartNumber" href="<%= getServletContext().getContextPath() + "/pages/carrello.jsp"%>">Cart <%= cartNav != null && cartNav.getNumProdotti() != 0 ? "(" + cartNav.getNumProdotti() + ")" : "" %></a></li>
        </ul>
    </div>

    <div class="nav-menu-dropdown" onmouseover="navItemHover(1);" onmouseout="navItemOut(1);">
        <ul class="mobile-dropdown-macro explore">
            <li class="mobile-dropdown-item">
                <span class="section-title-super-small inside-list-color inside-list-position">About Us</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/pages/our_story.jsp" %>">Our Story</a></li>
                    <li><a class="small-text details-link bold" href="<%= getServletContext().getContextPath() + "/pages/origins.jsp" %>">Origins</a></li>
                </ul>
            </li>
            <li class="mobile-dropdown-item">
                <span class="section-title-super-small inside-list-color inside-list-position">Social</span>
                <ul class="details-list hide">
                    <li><a class="small-text details-link bold" href="#" target="_blank">Support</a></li>
                    <li><a class="small-text details-link bold" href="https://instagram.com" target="_blank">Instagram</a></li>
                    <li><a class="small-text details-link bold" href="https://facebook.com" target="_blank">Facebook</a></li>
                    <li><a class="small-text details-link bold" href="https://twitter.com" target="_blank">Twitter</a></li>
                    <li><a class="small-text details-link bold" href="https://linkedin.com" target="_blank">LinkedIn</a></li>
                </ul>
            </li>
            
        </ul>
    </div>

    <div id="overlay-container" class="hide">
        <% if (user != null) { %>
        <a id="overlay-header" class="paragraph bold" href="" style="white-space: nowrap;text-overflow: ellipsis;">Registrato come:<br><%= user.getNome() + " " + user.getCognome() %> <br> </a>
        <hr class="overlay-divider">
        <a class="overlay-item paragraph bold" href="<%= getServletContext().getContextPath() + "/pages/profilo.jsp" %>">Il Tuo Profilo</a>
        <a class="overlay-item paragraph bold" href="<%= getServletContext().getContextPath() + "/pages/lista-ordini.jsp"%>">Il Tuoi Acquisti</a>
        <a class="overlay-item paragraph bold" href="">Preferiti</a>
        <hr class="overlay-divider">
        <form class="logout-form"action="<%= getServletContext().getContextPath() + "/logout" %>">
            <button class="overlay-item logout-button">
                <i class="fas fa-sign-out-alt fa-lg"></i>
                <span class="paragraph bold">Log Out</span>
            </button>
        </form>
        <% } else { %>
        	<form class="logout-form"action="<%= getServletContext().getContextPath() + "/pages/login.jsp" %>">
	            <button class="overlay-item logout-button">
	                <i class="fas fa-sign-in-alt fa-lg"></i>
	                <span class="paragraph bold">Login</span>
	            </button>
        	</form>
        <% } %>
    </div>

    <script src="<%= getServletContext().getContextPath() + "/assets/js/module.js"%>"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/global.js"%>"></script>
</body>	