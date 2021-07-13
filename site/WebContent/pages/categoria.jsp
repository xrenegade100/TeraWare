<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<%@page import="net.teraware.model.bean.ProdottoBean" %>
<%@page import="net.teraware.model.ProdottoModel" %>
<%@page import="java.util.ArrayList" %>
<%! @SuppressWarnings("unchecked") %>
<html>
  <head>
    <title>TeraWare - <%= request.getParameter("category") %></title>
  	<link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/categoria.css" %>">
  </head>
  <body>
  <%@ include file="../components/navbar.jsp" %>
    <div class="search-box">
    	<input type="text" class="text-input search">
    	<button class="btn-accent" id="search-button"
                  >
                    <span class="paragraph bold">Cerca</span>
                  </button>
    </div>
    <div class="container">
      <%	String categoria = request.getParameter("categoria");
      		int numProdotti = (int) request.getAttribute("numProdotti");
      		ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti"); 
      		for (int i = 0; i < prodotti.size(); i++) { 
      			ProdottoBean prodotto = prodotti.get(i);
          		String categoriaP = new ProdottoModel().getCategoriaProdotto(prodotto);
          		if (prodotto.isVisibile()) {
      		%>
        	<div class="card">
              <div class="card-img-container">
                <a href="<%= getServletContext().getContextPath() + "/product?id=" + prodotto.getIdProdotto() %>">
                  <img
                    src="<%= prodotto.getImageUrl().startsWith("http") ? prodotto.getImageUrl() : getServletContext().getContextPath() + "/" + prodotto.getImageUrl() %>"
                  />
                </a>
        
                <div class="toast-container">
                  <a href="<%= getServletContext().getContextPath() + "/category?category="+categoriaP+"&skip=0&limit=10" %>" class="toast-accent small-text bold">#<%= categoriaP %></a>
                </div>
              </div>
              <div class="card-body">
                <h3 class="product-title"><%= prodotto.getNome() %></h3></a>
                <h4 class="product-brand"><%= prodotto.getBrand() %></h4>
                <p class="paragraph product-description">
                  <%= prodotto.getInformazioni() %>
                </p>
                <div class="card-footer">
                  <button
                    class="btn-accent bottom-left"
                    onclick="addToCart(<%= prodotto.getIdProdotto() %>, 1, this)"
                  >
                    <i class="fas fa-shopping-cart fa-lg"></i>
                    <span class="paragraph bold">Aggiungi</span>
                  </button>
                  <h4 class="price-text"><%= String.format("%.2f", prodotto.getPrezzo()) %> &euro;</h4>
                </div>
              </div>
            </div>
      <% } } %>
      </div>
      <div class="nav-containers">
	    <%  
	    	int skip = Integer.parseInt(request.getParameter("skip"));
	    	int limit = Integer.parseInt(request.getParameter("limit")); 
	   	%>
	  		<% if (skip > 0) { %>
		    <form action="<%= getServletContext().getContextPath() + "/category?category=" + request.getParameter("category") + "&skip=" + ((int)skip - 10) + "&limit=10" %>">
		    	<input type="hidden" name="category" value="<%= request.getParameter("category") %>">
				<input type="hidden" name="skip" value="<%= ((int)skip - 10) %>">
				<input type="hidden" name="limit" value="<%= 10 %>">
				<button type="submit" class="btn-accent nav-button">
			    	<span class="paragraph bold">Indietro</span>
		    	</button>
		    </form>
	    <% } %>
		<% if (skip + limit < numProdotti && numProdotti != 0) {  %>
			<form action="<%= getServletContext().getContextPath() + "/category" %>">
				<input type="hidden" name="category" value="<%= request.getParameter("category") %>">
				<input type="hidden" name="skip" value="<%= ((int)skip + 10) %>">
				<input type="hidden" name="limit" value="<%= 10 %>">
				<button type="submit" class="btn-accent nav-button">
			    	<span class="paragraph bold">Avanti</span>
		    	</button>
			</form>
		<% } %>
  	</div>
    <%@ include file="../components/footer.jsp" %>
    <script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/cart.js" %>"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/categoria.js" %>"></script>
  </body>
</html>
