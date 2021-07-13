<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
  <head>
    <title>TeraWare - Errore</title>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() +
    "/assets/css/error.css" %>">
  </head>
  <body>
    <%@ include file="../../components/navbar.jsp" %>
    <div class="react">
      <img width="70" height="70" src=" <%= getServletContext().getContextPath()
      + "/assets/img/sad.svg" %>">
    </div>
    <h3 class="error">Si &egrave; verificato un errore.<br /></h3>
    <h4 class="error">Contattare l'amministratore</h4>
    <%@ include file="../../components/footer.jsp" %>
  </body>
</html>
