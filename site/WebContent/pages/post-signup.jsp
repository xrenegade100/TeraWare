<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione Effettuata</title>
</head>
<style>
	.main {
		text-align: center;
	}
</style>
<body>
	<%@ include file="../components/navbar.jsp" %>
	<div class="main">
	<h1>Ti sei registrato correttamente!</h1>
	<h3>Tra 3 secondi sarai reindirizzato al login...</h3>
	<i class="fas fa-5x fa-circle-notch fa-spin"></i>
	</div>
	<script>
		setTimeout(() => {
			window.location.replace("<%= getServletContext().getContextPath() + "/pages/login.jsp" %>");
		}, 3000);
	</script>
</body>
</html>