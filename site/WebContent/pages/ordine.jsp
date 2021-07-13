<%@ page language="java" contentType="text/html;"%>
    <%@ page import="net.teraware.util.Config" %>
    <%@ page import="net.teraware.util.ConfigHelper" %>
    <%@ page import="net.teraware.manager.*" %>
    <%@ page import="net.teraware.util.Config" %>
    <%@ page import="java.sql.SQLException" %>
    <%@ page import="java.io.FileNotFoundException" %>
    <%@ page import="net.teraware.model.bean.UtenteBean" %>
    <%@ page import="net.teraware.model.bean.OrdineBean" %>
    <%@page import="net.teraware.model.OrdineModel"%>
    <%@page import="net.teraware.model.ProdottoOrdineModel"%>
    <%@page import="net.teraware.model.bean.ProdottoOrdineBean"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="java.util.UUID"%>
    <%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<title>Riepilogo ordine</title>
<link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/ordine.css"%>">
</head>
<body>
	
	<%@ include file="../components/navbar.jsp" %>

	<%
		
		if(user == null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		OrdineBean ordine = new OrdineModel().doRetrieveByKey(UUID.fromString(request.getParameter("id")));	
		if(ordine.getIdUtente() != user.getIdUtente() && user.getRole() != 1){
			//L'utente sta cercando di visualizzare un ordine non effettuato da lui 
	%>
			<h3>Questo ordine non esiste</h3>
	<%		
			return;
		}
		
		ProdottoOrdineModel prodOrdineModel = new ProdottoOrdineModel();
		ArrayList<ProdottoOrdineBean> prodotti = prodOrdineModel.doRetrivebyOrder(ordine);
	%>	
	
	<div class="parent-main">
		<div class="prodotti">
		<div class="section-prodotti-header">
			<h3>Prodotti</h3>
		</div>
		<table class="prodotti-table">
			<thead class="paragraph bold">
				<tr>
					<td>Nome</td>
					<td>Brand</td>
					<td>Quantit&agrave;</td>
					<td>Prezzo totale</td>
				</tr>
			</thead>
			<tbody>
		<%
			float totale = 0;
			for(ProdottoOrdineBean prodotto: prodotti){
				totale += prodotto.getPrezzoEffettivo() * prodotto.getQuantita(); 
		%>
				<tr>
					<td><a class="" href="<%= getServletContext().getContextPath() + "/product?id=" + prodotto.getProdotto().getIdProdotto() %>"><%= prodotto.getProdotto().getNome()%></a></td>
					<td><p class="paragraph"><%= prodotto.getProdotto().getBrand()%></p></td>
					<td><p class="paragraph" style="text-align: center"><%= prodotto.getQuantita()%></p></td>
					<td><p class="paragraph bold"><%= prodotto.getProdotto().getPrezzo() * prodotto.getQuantita() %>&euro;</p></td>
				</tr>
			
		<%
			}
		%>
				</tbody>
			</table>
			<% float subtotale = (totale * 100) / 122; %>
			</div>
				<div class="sezione-destra">
					<div class="pagamento">
						<div class="section-prodotti-header">
							<h3>Pagamento</h3>
						</div>
						<table>
							<tr>
								<td><p class="paragraph">Subtotale:</p></td>
								<td><p class="paragraph bold"><%= String.format("%.2f", subtotale) %> &euro;</p></td>
							</tr>
							<tr>
								<td><p class="paragraph">IVA:</p></td>
								<td><p class="paragraph bold"><%= String.format("%.2f", totale-subtotale) %> &euro;</p></td>
							</tr>
							<tr>
								<td><p class="paragraph">Spedizione:</p></td>
								<td><p class="paragraph bold"><%= String.format("%.2f",4.90) %> &euro;</p></td>
							</tr>
							<tr>
								<td><p class="paragraph">Totale:</p></td>
								<td><p class="paragraph bold"><%= String.format("%.2f", totale+4.90) %> &euro;</p></td>
							</tr>
						</table>
					</div>
					<div class="info">
					<div class="section-prodotti-header">
							<h3>Informazioni</h3>
						</div>
						<table>
							<tr>
								<td><p class="paragraph">Id Ordine:</p></td>
								<td><p class="paragraph bold"><%= ordine.getIdOrdine() %></p></td>
							</tr>
							<tr>
								<td><p class="paragraph">Data: </p></td>
								<td><p class="paragraph bold"><%= new SimpleDateFormat("dd/MM/yyyy").format(ordine.getData()) %></p></td>
							</tr>
							<tr>
								<td><p class="paragraph">Stato: </p></td>
								<td><p class="paragraph bold"><%= ordine.getStato() %></p></td>
							</tr>
						</table></div>
				</div>
			
		</div>
		
<!-- 		<tr> -->
<!-- 			<td> -->
<%-- 				<img src="<%= getServletContext().getContextPath() + prodotto.getProdotto().getImageUrl()%>"/> --%>
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 				<p></p> --%>
<%-- 				 --%>
<%-- 				<p><%= prodotto.getProdotto().getInformazioni()%></p> --%>
<%-- 				<p><%= prodotto.getPrezzoEffettivo() * prodotto.getQuantita()%></p> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
		
	<%@ include file="../components/footer.jsp" %>
</body>
</html>