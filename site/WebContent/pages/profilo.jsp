<%@ page language="java" contentType="text/html;"%> <%@ page
import="net.teraware.model.bean.UtenteBean"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Profilo</title>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() +
    "/assets/css/profilo.css"%>">
  </head>
  <body>
    <%@ include file="../components/navbar.jsp" %>
    <div class="main-container">
      <div class="container-header">
        <h1 class="container-title">Profilo</h1>
        <img class="pro-pic" onclick="showOverlay();" src="<%= proPic %>"
        alt="<%= user == null ? "Utente" : user.getNome() + " " +
        user.getCognome()%>"/>
      </div>
      <div class="info-container">
        <div class="two-fields-row">
          <div class="field">
            <h3 class="bold">Nome:</h3>
            &nbsp;
            <h3 class="field-value"><%= user.getNome() %></h3>
          </div>
          <div class="field">
            <h3 class="bold">Cognome:</h3>
            &nbsp;
            <h3 class="field-value"><%= user.getCognome() %></h3>
          </div>
        </div>
        <div class="field">
          <h3 class="bold">Email:</h3>
          &nbsp;
          <h3 class="field-value"><%= user.getEmail() %></h3>
        </div>
        <div class="two-fields-row">
          <div class="field">
            <h3 class="bold">Provincia:</h3>
            &nbsp;
            <h3 class="field-value"><%= user.getProvincia() %></h3>
          </div>
          <div class="field">
            <h3 class="bold">Citt&agrave;:</h3>
            &nbsp;
            <h3 class="field-value citta"><i class="fas fa-circle-notch fa-spin"></i></h3>
          </div>
          <div class="field">
            <h3 class="bold">Indirizzo:</h3>
            &nbsp;
            <h3 class="field-value">
              <%= user.getVia() + ", " + user.getCivico() %>
            </h3>
          </div>
        </div>
      </div>
    </div>
    <%@ include file="../components/footer.jsp" %>
    <script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/util.js" %>"></script>
    <script>
 		  getCittaByCap('<%= user.getCap() %>', '.citta');
    </script>
  </body>
</html>
