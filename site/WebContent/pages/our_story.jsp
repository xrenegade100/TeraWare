<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/global.css"%>">
	<link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/our_story.css"%>">
    <%@ include file="../components/navbar.jsp" %>
    <title>Our story</title>
  </head>
  <body>
  <div class="contenitore">
    <h3>Chi siamo</h3>
    <p class="paragraph">
      Il nostro Team &egrave; composto da persone giovani e dinamiche, sempre
      alla ricerca di nuove soluzioni, e sempre aggiornati sulle continue
      evoluzioni che il mondo informatico riserva.<br />
      Grazie alle nostre competenze, e alla nostra passione, siamo cresciuti in
      modo esponenziale in questi anni, fino a diventare un punto di riferimento
      sia per privati che per le aziende di Salerno, ma anche in tutta
      Italia.<br />
    </p>
    <h3>Perch&egrave; tra tanti negozi dovresti scegliere proprio noi?</h3>
    <p class="paragraph">
      Il nostro Team &egrave; formato da persone giovani e preparate su tutto il
      mondo informatico.<br/> Siamo sempre aggiornati sulla continue e molteplici
      soluzioni ed evoluzioni tecnologiche ma ci differenziamo anche per alcuni
      aspetti non indifferenti nella scelta:
    </p>
    <ul>
      <li class="paragraph">
        Siamo specializzati da anni nel settore del Gaming, sia per la
        realizzazioni di PC, che per Upgrade o scelta dei componenti
      </li>
      <li class="paragraph">
        Rapidit&agrave; nell&#39;eseguire entro 1 o 2 giorni qualsiasi tipo di
        riparazione presso il nostro store ( salvo rare eccezioni ).
      </li>
      <li class="paragraph">
        Nel caso non possiate portarci, per qualsiasi motivo, il PC da riparare,
        il ritiro e la riconsegna presso la vostra abitazione, o luogo di
        lavoro, <br/>sono completamente GRATUITI su tutto il territorio di Torino e
        prima cintura.
      </li>
      <li class="paragraph">
        Cerchiamo costantemente le soluzione migliori, che generalmente il
        mercato non offre.
      </li>
      <li class="paragraph">
        Possibilit&agrave; di estendere la garanzia fino a 3 anni sull&#39;acquisto
        di un PC.
      </li>
      <li class="paragraph">
        Servizio Clienti / Post Vendita sempre a vostra disposizione anche
        telefonicamente.
      </li>
      <li class="paragraph">Orario flessibile ( 9.30-13 / 14.30-19 ). Dal Lunedi al Sabato.</li>
      <li class="paragraph">
        Comodit&agrave; ( Siamo raggiungibili facilmente anche con i mezzi
        pubblici ).
      </li>
    </ul>
  </div>  
  </body>
   <%@ include file="../components/footer.jsp" %>
</html>
