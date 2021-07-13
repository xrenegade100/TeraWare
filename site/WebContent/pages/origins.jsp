<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() +
    "/assets/css/our_story.css"%>"> <%@ include file="../components/navbar.jsp"
    %>
    <title>Origins</title>
  </head>
  <body>
    <div class="contenitore">
      <h3>Tutto quello che c'&egrave; da sapere su Teraware</h3>
      <p class="paragraph">
        Teraware &egrave; una societ&agrave; specializzata in vendita PC Gaming
        e assistenza Computer, nata nel 2021, &egrave; situata a Salerno.<br />
        L'obiettivo &egrave; di fornire il miglior servizio possibile all'Utente
        finale, non soffermandosi solamente al lato vendita face-to-face ma
        garantendo anche la possibilit&agrave; di uno shop online.<br />
        Il suo personale qualificato &egrave; sempre a disposizione del cliente
        al fine di soddisfare le sue richieste e poterlo orientare verso la
        scelta dei prodotti pi&ugrave; consoni alle esigenze indicate.<br />
        Punto di forza sono i Personal Computer assemblati (completamente
        personalizzabili), frutto di un'accurata scelta dei componenti e sempre
        aggiornati con lo scopo di garantire qualit&agrave; e prestazioni
        ottimali. Presso il punto vendita &egrave; inoltre possibile effettuare
        riparazioni e/o upgrade immediati.
      </p>
      <img height="300" width="550" src="<%=
      getServletContext().getContextPath() + "/assets/img/assistenza.jpg"%>">
    </div>
  </body>
  <%@ include file="../components/footer.jsp" %>
</html>
