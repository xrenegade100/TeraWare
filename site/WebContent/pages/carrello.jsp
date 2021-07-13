<%@ page language="java" contentType="text/html;"%>
    <%@ page import="net.teraware.model.Cart"%>
    <%@ page import="net.teraware.model.helper.CartEntry"%>
    <%@ page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<title>Carrello</title>
</head>
<body>
	
	<%@ include file="../components/navbar.jsp" %>
	<div class="cart">
		<div class="cart-header">
			<h2 id="#cart-title">Carrello</h2>
			<form action="<%= getServletContext().getContextPath() + "/category" %>">
				<input type="hidden" name="category" value="all">
				<input type="hidden" name="skip" value="0">
				<input type="hidden" name="limit" value="10">
				<button type="submit" class="btn-accent nav-button">
					<span class="paragraph bold">Continua lo shopping</span>
				</button>
			</form>
		</div>
		<div class="product-row t-header">
			<div class="product-info"><h3>Prodotto</h3></div>
			<div class="product-price"><h3>Prezzo</h3></div>
		</div>
		<%
		request.getSession().setAttribute("redirect", "/pages/carrello.jsp");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart != null){
			Iterator<CartEntry> cartIterator = cart.getProdotti();
			float total = 0;
			if(cartIterator.hasNext()){
				while(cartIterator.hasNext()){
					CartEntry entry = cartIterator.next();
					total += entry.getQuantita() * entry.getProdotto().getPrezzo();
	%>		
					<div class="product-row">
						<div class="product-info">
							<h4><%= entry.getProdotto().getNome() %></h4>
							<div class="qprice">
								<input class="text-input small-text" id="i-<%= entry.getProdotto().getIdProdotto() %>" onchange="editCart(<%= entry.getProdotto().getIdProdotto() %>,'p-<%= entry.getProdotto().getIdProdotto() %> %>')" class="product-quantity" min="0" max="<%= entry.getProdotto().getQuantita() %>" type="number" value="<%= entry.getQuantita() %>">
								<p class="paragraph bold sing-price">x <%= entry.getProdotto().getPrezzo() %> &euro;</p>
							</div>
						</div>
						<div class="product-price" id="p-<%= entry.getProdotto().getIdProdotto() %>">
							<h4>&euro; <%= String.format("%.2f", entry.getProdotto().getPrezzo() * entry.getQuantita()) %></h4>
							<button type="submit" class="btn-accent nav-button del-button" onclick="removeFromCart(<%= entry.getProdotto().getIdProdotto() %>,0)">
								<span class="paragraph bold">x</span>
							</button>
						</div>
					</div>
	<%
				}
				%>
				<div class="product-row totale-row">
					<input type="text" class="text-input small-text" placeholder="Codice coupon">
					<div class="total">
						<p class="paragraph bold subtotale">Subtotale: <%= String.format("%.2f",(total * 100) / 122) %> &euro;</p><br>
						<p class="paragraph bold iva">IVA: <%= String.format("%.2f", total - ((total * 100) / 122)) %> &euro;</p><br>
						<p class="paragraph bold">Spedizione: 4,90 &euro;</p><br>
						<p class="paragraph bold totale">Totale: <%= String.format("%.2f", total + 4.90) %> &euro;</p><br>
						<form action="<%= getServletContext().getContextPath() + "/pages/checkout.jsp" %>">
							<button type="submit" class="btn-accent nav-button checkout-button">
								<span class="paragraph bold">Checkout</span>
							</button>
						</form>
					</div>
				</div>
				
			<% }
			else{
	%>
				<h3>Non hai prodotti nel carrello</h3>
	<%
			}
		}
		else{
	%>
			<h2>Non hai inserito nessun prodotto nel carrello</h2>
	<%
		}
	%>
	</div>
	
	
	<%@ include file="../components/footer.jsp" %>
	<link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/carrello.css" %>">
	<script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous"></script>
	  <script src="<%= getServletContext().getContextPath() + "/assets/js/cart.js" %>"></script>
		<script src="<%= getServletContext().getContextPath() + "/assets/js/module.js" %>"></script>
</body>
</html>