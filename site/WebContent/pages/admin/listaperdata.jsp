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
	<%@include file="../../components/navbar.jsp"%>
	<% ArrayList<OrdineBean> ordini = (ArrayList<OrdineBean>) request.getAttribute("ordini"); 
		Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateStart"));
		Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateEnd"));
	%>
	<table>
        <tr>
            <th id="table-heading">
                <h3>
                	Ordini<br />
                	Dal <%= new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN).format(dateStart).toString() %><br />
                	Al <%= new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN).format(dateEnd).toString() %>
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
				<p class="paragraph"><span class="bold">Email utente:</span> <%= new UtenteModel().doRetrieveByKey(ordine.getIdUtente()).getEmail() %></p>
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
		int numOrdini = (Integer) request.getAttribute("numOrdini");
	%>
	<% if (skip > 0) { %>
	<a href="<%= getServletContext().getContextPath() + "/admin/listaordinidata?dateStart=" + request.getParameter("dateStart") + "&dateEnd=" + request.getParameter("dateEnd") + "&skip=" + ((int)skip - 10) + "&limit=10" %>">Indietro</a>
	<% } %>
	<% if (skip + limit < ordini.size() && ordini.size() != 0) {  %>
	<a href="<%= getServletContext().getContextPath() + "/admin/listaordinidata?dateStart=" + request.getParameter("dateStart") + "&dateEnd=" + request.getParameter("dateEnd") + "&skip=" + ((int)skip + 10) + "&limit=10" %>">Avanti</a>
	<% } %>
</body>
</html>