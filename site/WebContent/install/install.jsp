<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/global.css" %>" />
    <title>Install</title>
  </head>
  <body>
  	<%@ include file="../components/navbar.jsp" %>
	
    <h3>Compila i seguenti campi:</h3>
    <form action="/TeraWere_Site/install" method="POST">
      <fieldset>
        <legend>
          <i><b>Credenziali database</b></i>
        </legend>
        <label class="paragraph">Username:</label>
        <input type="text" name="username" placeholder="Mario" required /><br />
        <label class="paragraph">Password:</label>
        <input
          type="password"
          name="password"
          maxlength="16"
          placeholder="Max 16 chars"
          required
        /><br />
        <label class="paragraph">Host:</label>
        <input
          type="text"
          name="host"
          placeholder="http://placeholder.it"
          required
        /><br />
        <label class="paragraph">Porta:</label>
        <input type="number" name="port" placeholder="3306" /><br />
      </fieldset>
      <br />
      <input type="submit" class="btn-accent paragraph bold" value="Next" />
    </form>
  </body>
  <script>
    const hash = window.location.hash.substr(1);

    const result = hash.split("&").reduce(function (res, item) {
      var parts = item.split("=");

      res[parts[0]] = parts[1];

      return res;
    }, {});

    const { error } = result;
    console.log(result);
    if (error) {
      alert("Non è stato possibile connettersi al database");
    }
  </script>
</html>
