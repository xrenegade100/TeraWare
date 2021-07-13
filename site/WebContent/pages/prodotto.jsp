<%@ page language="java" contentType="text/html;"%> <%@ page
import="net.teraware.model.bean.ProdottoBean" %>
<!DOCTYPE html>
<html>
  <head>
    <% ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prod"); %>
    <title>
      Prodotto<% if(prodotto != null) { %> - <%= prodotto.getNome() %><% } %>
    </title>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() +
    "/assets/css/product.css"%>">
  </head>
  <body>
    <%@ include file="../components/navbar.jsp" %> <% if (prodotto != null) {%>
    <div class="product-container">
      <img width="400" src="<%= prodotto.getImageUrl().startsWith("http") ?
      prodotto.getImageUrl() : getServletContext().getContextPath() +
      prodotto.getImageUrl() %>" />
      <div class="product-info">
        <h3 class="product-title">
          <span class="brand-name"><%= prodotto.getBrand() %></span> <%=
          prodotto.getNome() %>
        </h3>
        <p class="paragraph bold price"><%= prodotto.getPrezzo() %> &euro;</p>
        <p class="paragraph bold">
          <span class="brand-name">Descrizione</span>
        </p>
        <p class="paragraph"><%= prodotto.getInformazioni() %></p>
        <p class="paragraph bold">
          <span class="brand-name">Aggiungi al carrello</span>
        </p>
        <div class="field">
          <input
            type="hidden"
            name="pId"
            value="<%= prodotto.getIdProdotto()%>"
          />
          <input
            type="number"
            name="q"
            id="quantita"
            min="0"
            class="text-input"
            value="1"
            max="<%= prodotto.getQuantita() %>"
          />
          <button class="btn-accent nav-button addtocart">
            <i class="fas fa-shopping-cart fa-lg"></i>
			<span class="paragraph bold">Aggiungi</span>
          </button>
        </div>
      </div>
    </div>
    <% } %> <%@ include file="../components/footer.jsp" %>
    <script>
    	const productId = <%= prodotto.getIdProdotto() %>;
    </script>
    <script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/cart.js" %>"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/prodotto.js" %>"></script>
  </body>
</html>
