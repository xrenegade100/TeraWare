<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TeraWare - Amministratore</title>
<link href="<%= getServletContext().getContextPath() + "/assets/css/admin.index.css" %>" rel="stylesheet">
</head>
<body>
<%@include file="../../components/navbar.jsp"%>
<div class="container">
	<div class="top">
		<h1>TeraWare - Amministratore</h1>
		<div class="buttons-container">
			<a href="#" class="action-button ricerca-data">
				<i class="far fa-calendar-alt icon"></i>
				<p class="">Ricerca ordini per data</p>
			</a>
			<a href="#" class="action-button ricerca-nominativo">
				<i class="fas fa-users icon"></i>
				<p class="">Ricerca ordini per email</p>
			</a>
			<a href="<%= getServletContext().getContextPath() + "/admin/products" %>" class="action-button">
				<i class="fas fa-box-open icon"></i>
				<p class="">Gestione catalogo</p>	
			</a>
		</div>
	</div>
	<div class="bottom">
		<form action="<%= getServletContext().getContextPath() + "/admin/listaordinidata"%>" class="form-data 👻">
			<p class="paragraph">Da:</p> <input type="date" class="text-input" name="dateStart"><br />
			<p class="paragraph">A:</p> <input type="date" class="text-input" name="dateEnd"><br />
			<input type="hidden" name="skip" value="0">
			<input type="hidden" name="limit" value="10">
			<input class="submit" type="submit">
		</form>
	
		<form action="<%= getServletContext().getContextPath() + "/admin/listaordiniemail"%>" class="form-nominativo 👻">
			<input type="text" class="text-input" name="email" placeholder="Email Cliente">
			<input type="hidden" name="skip" value="0">
			<input type="hidden" name="limit" value="10">
			<input type="submit">
		</form>
	</div>
	
	
</div>


<script src="<%= getServletContext().getContextPath() + "/assets/admin/js/index.js" %>"></script>
</body>
</html>