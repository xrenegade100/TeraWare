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
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/register.css"%>">
    <title>Registrati</title>
  </head>
  <body>
    <div class="form-container">
      <form
        class="register-side"
        action="<%= getServletContext().getContextPath() + "/signup" %>" 
        method="POST"
        enctype="multipart/form-data"
      >
        <h3>Registrati</h3>
        <br />
        <div class="near-inputs-container">
          <div class="near-input">
            <div class="form-field">
              <p class="small-text bold">Nome</p>
              <input
                id="nome"
                class="text-input small-text"
                type="text"
                name="nome"
                placeholder="il tuo nome"
                required
              />
              <p class="form-text-feedback">Inserire un nome valido</p>
            </div>
          </div>
            <div class="near-input">
              <div class="form-field">
                <p class="small-text bold">Cognome</p>
                <input
                  id="cognome"
                  class="text-input small-text"
                  type="text"
                  name="cognome"
                  placeholder="il tuo cognome"
                  required
                />
                <p class="form-text-feedback">Inserire un cognome valido</p>
              </div>
          </div>
        </div>
        <div class="form-field">
          <p class="small-text bold">Immagine profilo</p>
          <label class="file small-text bold">
            <input type="file" name="foto" aria-label="File browser example" />
            <span class="file-custom"></span>
          </label>
        </div>
		    <div class="form-field">
          <p class="small-text bold">Email</p>
          <input
            class="text-input small-text"
            type="email"
            name="email"
            id="email"
            placeholder="la tua email"
            required
          />
          <p class="form-text-feedback">Email gi&agrave; registrata</p>
        </div>
        <div class="form-field">
          <p class="small-text bold">Password</p>
          <input
            id="password"
            class="text-input small-text"
            type="password"
            name="password"
            placeholder="la tua password"
            required
          />
          <p class="form-text-feedback">
            La password deve essere almeno di 8 caratteri<br />
            Deve contenere:<br />
            1. Almeno una lettera maiuscola<br />
            2. Almeno una lettera minuscola<br />
            3. Almeno un numero
          </p>
        </div>
        <div class="form-field">
          <p class="small-text bold">Indirizzo</p>
          <input
            class="text-input small-text"
            type="text"
            name="via"
            placeholder="il tuo indirizzo"
            required
          />
        </div>
        <div class="near-inputs-container">
          <div class="near-input">
            <p class="small-text bold">Provincia</p>
            <select
              class="text-input small-text provincia"
              name="provincia"
              required
            >
            </select>
          </div>
          <div class="near-input">
            <p class="small-text bold">CAP</p>
            <select
              class="text-input small-text cap"
              name="cap"
              required
            >
            </select>
          </div>
          <div class="near-input">
            <p class="small-text bold">Numero Civico</p>
            <input
              id="civico"
              class="text-input small-text"
              type="number"
              name="civico"
              placeholder="il tuo numero civico"
              min="0"
              required
            />
          </div>
        </div>
        <p class="small-text bold">Note</p>
        <textarea
          class="note small-text"
          name="note"
          cols="30"
          rows="5"
        ></textarea>

        <button class="btn-login" type="submit">
          <span class="paragraph bold">REGISTRATI</span>
        </button>
      </form>
      <div class="greeting-side">
        <h2>Il Tuo Mondo</h2>
        <hr class="divider" />
        <p class="paragraph">
          Bastano pochi click per avere accesso ai migliori prodotti sul mercato
          per il tuo setup!
        </p>
        <br /><br />
        <img class="register-img" src="../assets/img/logo/vlogo.svg" />
      </div>
    </div>
    <script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/util.js" %>"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/register.js" %>"></script>
	<script>
		creaProvinceSelect('.provincia');
	</script>
  </body>
</html>
