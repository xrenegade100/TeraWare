<%@ page language="java" contentType="text/html;"%>
<%@ page import="net.teraware.model.Cart"%>
<%@ page import="net.teraware.model.helper.CartEntry"%>
<%@ page import="java.util.Iterator"%>
<%
if(request.getSession().getAttribute("user") == null) {
response.sendRedirect(getServletContext().getContextPath() +
"/pages/login.jsp"); } 
else if (request.getSession().getAttribute("cart") == null || ((Cart) request.getSession().getAttribute("cart")).getNumProdotti() == 0) {
	response.sendRedirect(getServletContext().getContextPath() +"/pages/index.jsp");
}
else { %>
<!DOCTYPE html>
<html>
  <head>
    <title>Checkout</title>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() +
    "/assets/css/global.css"%>"> <link rel="stylesheet" href="<%=
    getServletContext().getContextPath() + "/assets/css/checkout.css"%>">
  </head>
  <body>
    <%@ include file="../components/navbar.jsp" %>
    <% Cart cart = (Cart) request.getSession().getAttribute("cart"); %>
    <div class="checkout">
      <div class="payment-card">
        <div class="payment-card-header">
          <h3>Checkout</h3>
          <i id="card-type" class="fas fa-lg fa-credit-card"></i>
        </div>
        <div class="payment-card-body">
          <div class="field">
            <label class="paragraph bold">Nome titolare carta</label>
            <input class="payment-long-input text-input" type="text" />
          </div>
          <div class="field">
            <label class="paragraph bold">Numero carta</label>
            <input
              class="payment-long-input text-input cc-number"
              type="text"
            />
          </div>
          <div class="row-2-input">
            <div class="field">
              <label class="paragraph bold">Scadenza</label>
              <input class="text-input cc-scadenza" type="text" />
            </div>
            <div class="field">
              <label class="paragraph bold">CCV</label>
              <input required class="text-input cc-ccv" type="text" />
            </div>
          </div>
        </div>
      </div>
      <div class="total-amount">
        <div class="payment-card-header">
          <h3>Pagamento</h3>
        </div>
		<div class="info-body">
			<!-- TODO FINIRE CON JSP
				Prendi carrello da sessione
				moltiplica quantità per prezzo prodotto
				calcola totale subtotale
			-->
			<%
				float totale = 0;
				Iterator<CartEntry> i = cart.getProdotti();
				while (i.hasNext()) {
					CartEntry entry = i.next();
					totale += entry.getQuantita() * entry.getProdotto().getPrezzo();
				}
				float subtotale = (totale * 100) / 122;
			%>
			<table class="paragraph">
			  <tr>
				<td class="bold">Subtotale</td>
				<td><%= String.format("%.2f", subtotale) %> &euro;</td>
			  </tr>
	
			  <tr>
				<td class="bold">IVA</td>
				<td><%= String.format("%.2f", totale - subtotale) %> &euro;</td>
			  </tr>
	
			  <tr>
				<td class="bold">Spedizione</td>
				<td>4.90 &euro;</td>
			  </tr>
	
			  <tr>
				<td class="bold">Totale</td>
				<td class="bold"><%= String.format("%.2f", totale + 4.90) %> &euro;</td>
			  </tr>
			</table>
			<form class="confirm-button" action="<%= getServletContext().getContextPath() + "/checkout" %>">
				<button type="submit" class="btn-accent nav-button confirm-button">
					<span class="paragraph bold">Conferma</span>
				</button>
			</form>
		</div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/cleave.js@1.6.0/dist/cleave.min.js"></script>
    <script>
      const cctypes = {
        visa: "fa-cc-visa",
        amex: "fa-cc-amex",
        diners: "fa-cc-diners-club",
        jcb: "fa-cc-jcb",
        mastercard: "fa-cc-mastercard",
        discover: "fa-cc-discover",
        unknown: "fa-credit-card",
      };

      new Cleave(".cc-number", {
        creditCard: true,
        onCreditCardTypeChanged: (cardType) => {
          console.log(cardType);
          $("#card-type").removeClass();
          $("#card-type").addClass(["fab", "fa-3x", cctypes[cardType]]);
        },
      });

      new Cleave(".cc-scadenza", {
        date: true,
        delimiter: "/",
        datePattern: ["m", "Y"],
      });

      new Cleave(".cc-ccv", {
        number: true,
        blocks: [3],
      });

      const CC_REGEX =
        /^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/;
    </script>
    <script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
  </body>
</html>
<% } %>
