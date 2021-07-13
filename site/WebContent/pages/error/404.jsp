<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
  <head>
    <title>TeraWare - 404</title>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() +
    "/assets/css/404.css" %>">
  </head>
  <body>
    <%@ include file="../../components/navbar.jsp" %>

    <div class="react">
      <h1>4</h1>
      <img width="50" height="50" src=" <%= getServletContext().getContextPath()
      + "/assets/img/404react.svg" %>">
      <h1>4</h1>
    </div>

    <h3 class="error">La pagina da te richiesta non &egrave; stata trovata.<br /></h3>

    <a class="butt" href="<%= getServletContext().getContextPath()%>"
      >Ritorna alla home</a
    >
    <%@ include file="../../components/footer.jsp" %>
  </body>
</html>

