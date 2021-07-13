<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@page import="java.util.ArrayList"%>
    <%@page import="net.teraware.model.bean.ProdottoBean" %>
    <%@page import="net.teraware.model.interf.IBean" %>
    <%@ page import="net.teraware.model.Cart"%>
    <%@ page import="net.teraware.model.helper.CartEntry"%>
    <%! @SuppressWarnings("unchecked") %>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/products.css"%>">
    <title>Document</title>
</head>
<body>
	<%@ include file="../components/navbar.jsp" %>

	<%
		ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");
		if(prodotti != null){
	%>
    <h2>I Nostri Prodotti</h2>
    <table>
        <tr id="table-heading">
            <th><h4>Nome</h4></th>
            <th><h4>Prezzo</h4></th>
            <th><h4>Brand</h4></th>
            <th><h4>Informazioni</h4></th>
            <th><h4>Foto</h4></th>
            <th><h4>Azioni</h4></th>
        </tr>
         <tr>
        	<td class="paragraph"><a href="<%= getServletContext().getContextPath() + "/pages/admin/aggiornaprodotto.jsp" %>">Aggiungi nuovo prodotto</a></td>
        </tr>
        <%
        		request.getSession().setAttribute("redirect", "/products");
        		Cart cart = (Cart) request.getSession().getAttribute("cart");
        		for(ProdottoBean p: prodotti){
        			CartEntry prodInCart = null;
        			if (cart != null) {
        				prodInCart = cart.findById(p.getIdProdotto());       				
        			}
        			
        			if(p.isVisibile()){
        							
        %>
        			<tr>
        				<td class="paragraph"><%= p.getNome()%></td>
        				<%
        					String price = Float.toString(p.getPrezzo());
        					StringBuilder newPrice = new StringBuilder(price);
        					newPrice.setCharAt(price.indexOf('.'), ',');
        				%>
        				<td class="paragraph"><%= newPrice%> &euro;</td>
        				<td class="paragraph"><%= p.getBrand()%></td>
        				<td class="paragraph"><%= p.getInformazioni()%></td>
        				<td><img src="<%= p.getImageUrl().startsWith("http") ? p.getImageUrl() : getServletContext().getContextPath() + p.getImageUrl() %>" class="table-image" alt="<%= p.getNome() %>" title="<%= p.getNome() %>"/></td>
        				<td class="paragraph">		
							<a href="<%= getServletContext().getContextPath() + "/pages/admin/aggiornaprodotto.jsp?id=" + p.getIdProdotto() %>" class="details">Modifica »</a>
       					</td>
        			</tr>	
        <% }
        		}
        %>
        </table>
        <%
        	}
        	else{
        %>
    	    <h2>Impossibile accedere ai prodotti</h2>
            <br>
            <p class="paragraph">
                Ci dispiace, potrebbero non esserci prodtti all'interndo del database,<br>
                oppure stai cercando di effettuare un accesso a questa pagina non autorizzato
            </p>
        <%
        	}
        %>
        <%@ include file="../components/footer.jsp" %>
</body>
</html>