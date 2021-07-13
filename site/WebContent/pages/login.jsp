<% if (request.getSession().getAttribute("user") != null) {
	response.sendRedirect(getServletContext().getContextPath() + "/");
	return;
} %>
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/global.css"%>">
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/login.css"%>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <title>Login</title>
  </head>
  <body>
    <div class="form-container">
      <div class="welcome-side">
        <h3>Benvenuto in TeraWare!</h3>
        <hr class="divider" />
        <p class="small-text">
          Il miglior sito per assemblare la tua postazione di lavoro/gaming
          casalinga con i migliori componenti disponibili sul mercato!
        </p>
        <br />
        <div class="reg">
          <p class="small-text bold spacing">Non hai ancora un account?</p>
          <br>
          <a href="register.jsp" class="btn-register">
            <span class="paragraph bold">REGISTRATI</span>
          </a>
      </div>
      </div>
      <div class="login-side">
         <form id="loginform" action="<%= getServletContext().getContextPath() + "/login" %>" method="POST">
          <div class="logo-container">
            <img class="logo" src="../assets/img/logo/vlogo.svg" />
            <p class="logo-title">TeraWare</p>
          </div>
          <input
            class="text-input paragraph"
            type="email"
            name="email"
            id="email"
            placeholder="Inserisci l'email"
            required
          />
          <br>
          <input
            id="password"
            class="text-input paragraph"
            type="password"
            name="password"
            placeholder="Inserisci la password"
            required
          />
          <p class="paragraph login-feedback">Username o password errati</p>
          <button type="submit" class="btn-login">
            <span class="paragraph bold button-text">ACCEDI</span>
          </button>
        </form>
      </div>
      <div class="reg bottom-reg hide">
        <p class="small-text bold spacing">Non hai ancora un account?</p>
        <a href="register.jsp" class="btn-register">
          <span class="paragraph bold">REGISTRATI</span>
        </a>
      </div>
    </div>
    <script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous"></script>
    <script src="../assets/js/module.js"></script>
    <script src="../assets/js/login.js"></script>
  </body>
</html>
