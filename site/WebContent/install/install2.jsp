<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../css/global.css" />
    <title>Install2</title>
  </head>
  <body>
  <%@ include file="../components/navbar.jsp" %>
	
    <h3>Continua a compilare i campi del form seguente:</h3>
    <form action="/TeraWere_Site/install" method="post">
      <fieldset>
        <legend class="paragraph">
          <b>Dati societ&agrave; :</b>
        </legend>
        <label class="paragraph">Nome:</label>
        <input
          type="text"
          name="nomesocieta"
          placeholder="TeraWare	"
          required
        /><br />
        <label class="paragraph">Email:</label>
        <input
          type="email"
          name="emailsocieta"
          placeholder="ellegi@example.it"
          required
        /><br />
        <label class="paragraph">P.Iva:</label>
        <input
          type="text"
          name="piva"
          placeholder="12345678910"
          required
        /><br />
      </fieldset>
      <fieldset>
        <legend class="paragraph">
          <b>Indirizzo</b>
        </legend>
        <label class="paragraph">Provincia:</label>
        <input
          type="text"
          name="provincia"
          maxlength="2"
          placeholder="RM"
          required
        /><br />
        <label class="paragraph">Cap:</label>
        <input type="text" name="cap" placeholder="00130" required /><br />
        <label class="paragraph">Via:</label>
        <input type="text" name="via" placeholder="Via Roma" /><br />
        <label class="paragraph">Civico:</label>
        <input type="number" name="civico" placeholder="13" required /><br />
      </fieldset>
	  <fieldset>
      	<legend class="paragraph"><b>Account Amministratore</b></legend>
      	<label class="paragraph">Nome:</label>
      	<input type="text" name="adminNome" placeholder="Nome" required /><br />
      	<label class="paragraph">Cognome:</label>
      	<input type="text" name="adminCognome" placeholder="Cognome" required /><br />
      	<label class="paragraph">Email:</label>
      	<input type="email" name="adminEmail" placeholder="La tua email" required /><br />
        <label class="paragraph">Password:</label>
      	<input type="password" name="adminPassword" placeholder="Scegli una password" required /><br />
        <label class="paragraph">Provincia:</label>
      	<input type=""text"" name="adminProvincia" placeholder="NA" required /><br />
      	<label class="paragraph">CAP:</label>
      	<input type=""text"" name="adminCap" placeholder="80062" required /><br />
     	<label class="paragraph">Via:</label>
      	<input type="text" name="adminVia" placeholder="Via roma" required /><br />
     	<label class="paragraph">Civico:</label>
      	<input type="number" name="adminCivico" placeholder="Numero civico" required /><br />
       </fieldset>
      <br />
      <input type="submit" class="btn-accent paragraph bold" value="Next" />
    </form>
  </body>
</html>
