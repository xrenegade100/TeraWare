<%@page import="net.teraware.model.UtenteModel"%>
<%@page import="net.teraware.model.ProdottoOrdineModel"%>
<%@page import="java.util.ArrayList" %>
<%@page import="net.teraware.model.bean.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>TeraWare - Admin</title>
<link href="<%= getServletContext().getContextPath() + "/assets/css/global.css" %>" rel="stylesheet">
<link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/products.css" %>" />
</head>
<body>
	<%@include file="../../components/navbar.jsp" %>
	<% UtenteBean cliente = (UtenteBean) request.getAttribute("cliente");
		if (cliente == null) { %>
			<h1>Questo utente non esiste</h1>
		<%} else { 
	%> 
	<% ArrayList<OrdineBean> ordini = (ArrayList<OrdineBean>) request.getAttribute("ordini"); %>
	<table>
        <tr>
            <th id="table-heading">
                <h3>
                	Ordini dell'utente:<br />
                	<%= request.getParameter("email") %>
                </h3>
            </th>
        </tr>
        <%
			for(OrdineBean ordine: ordini){
				ArrayList<ProdottoOrdineBean> prodotti = new ProdottoOrdineModel().doRetrivebyOrder(ordine);
				float total = 0;
				for(ProdottoOrdineBean prodotto: prodotti){
					total += prodotto.getPrezzoEffettivo() * prodotto.getQuantita();
				}
		 %>
        <tr>
            <td>
				<p class="paragraph"><span class="bold">Id Ordine:</span> <%= ordine.getIdOrdine()%></p>
				<p class="paragraph"><span class="bold">Data: </span> <%= new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.ITALIAN).format(ordine.getData()).toString()%></p>
				<p class="paragraph"><span class="bold">Stato:</span> <%= ordine.getStato()%></p>
				<p class="paragraph"><span class="bold">Prezzo Totale</span> <%= total%> EUR</p>
				<a class="details paragraph" href="<%= getServletContext().getContextPath() + "/pages/ordine.jsp?id=" + ordine.getIdOrdine()%>">Dettagli</a>
			</td>
        </tr>
        <%
			} 
		%>
    </table>
	<% int skip = (Integer) request.getAttribute("skip");
		int limit = (Integer) request.getAttribute("limit");
	%>
	<% if (skip > 0) { %>
	<a href="<%= getServletContext().getContextPath() + "/admin/listaordiniemail?email=" + request.getParameter("email") + "&skip=" + ((int)skip - 10) + "&limit=10" %>">Indietro</a>
	<% } %>
	<% if (skip + limit < ordini.size() && ordini.size() != 0) {  %>
	<a href="<%= getServletContext().getContextPath() + "/admin/listaordiniemail?email=" + request.getParameter("email") + "&skip=" + ((int)skip + 10) + "&limit=10" %>">Avanti</a>
	<% } %>
<% } %>
</body>
</html>