<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="net.teraware.model.ProdottoModel"%>
<%@ page import="net.teraware.model.bean.ProdottoBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>TeraWare - Amministratore</title>
<link href="<%= getServletContext().getContextPath() + "/assets/css/global.css" %>" rel="stylesheet">
</head>
<style>
	.table-image {
		max-width: 355px;
	}
</style>
<body>
<%@include file="../../components/navbar.jsp"%>
<h2>I Nostri Prodotti</h2>
    <table>
        <tr id="table-heading">
            <th><h4>Nome</h4></th>
            <th><h4>Prezzo</h4></th>
            <th><h4>Brand</h4></th>
            <th><h4>Informazioni</h4></th>
            <th><h4>Quantit&agrave;</h4></th>
            <th><h4>Foto</h4></th>
            <th><h4>Azioni</h4></th>
        </tr>
        <tr>
        	<td class="paragraph"><a href="<%= getServletContext().getContextPath() + "/pages/admin/aggiornaprodotto.jsp" %>" class="details">Aggiungi nuovo prodotto</a></td>
        </tr>
        <%
        		for(ProdottoBean p: new ProdottoModel().doRetrieveAll(null)){
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
   				<td class="paragraph"><%= p.getQuantita()%></td>	
   				<td><img src="<%= p.getImageUrl().startsWith("http") ? p.getImageUrl() : getServletContext().getContextPath() + p.getImageUrl() %>" class="table-image" alt="<%= p.getNome() %>" title="<%= p.getNome() %>"/></td>
   				<td class="paragraph">
   					<a href="<%= getServletContext().getContextPath() + "/pages/admin/aggiornaprodotto.jsp?id=" + p.getIdProdotto() %>" class="details">Modifica »</a>
  				</td>
   			</tr>
    <% } %>
	</table>
</body>
</html>