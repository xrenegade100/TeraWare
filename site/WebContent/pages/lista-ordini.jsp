<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="net.teraware.util.Config" %>
    <%@ page import="net.teraware.util.ConfigHelper" %>
    <%@ page import="net.teraware.manager.*" %>
    <%@ page import="net.teraware.util.Config" %>
    <%@ page import="java.sql.SQLException" %>
    <%@ page import="java.io.FileNotFoundException" %>
    <%@ page import="net.teraware.model.bean.UtenteBean" %>
    <%@ page import="net.teraware.model.bean.OrdineBean" %>
    <%@ page import="net.teraware.model.OrdineModel"%>
    <%@ page import="net.teraware.model.ProdottoOrdineModel"%>
    <%@ page import="net.teraware.model.bean.ProdottoOrdineBean"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>I Tuoi Ordini</title>
	<link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/products.css"%>">
	<title>Lista Ordini</title>
</head>
<body>
	
	<%@ include file="../components/navbar.jsp" %>
		
	<%
		if(user == null){
			request.getSession().setAttribute("redirect", "/pages/lista-ordini.jsp");
			response.sendRedirect("login.jsp");
			return;
		}

		OrdineModel ordineModel = new OrdineModel();
		ProdottoOrdineModel prodOrdineModel = new ProdottoOrdineModel();
		ArrayList<OrdineBean> ordini = null;
		try {
			ordini = (ArrayList<OrdineBean>) ordineModel.doRetriveByUser(user, "_data DESC");
		} catch (SQLException e) {
			response.getWriter().println("Errore");
			e.printStackTrace();
			return;
		}
	%>
	
    <table>
        <tr>
            <th id="table-heading">
                <h3>I Tuoi Ordini</h3>
            </th>
        </tr>
        <%
			for(OrdineBean ordine: ordini){
				ArrayList<ProdottoOrdineBean> prodotti = prodOrdineModel.doRetrivebyOrder(ordine);
				float total = 0;
				for(ProdottoOrdineBean prodotto: prodotti){
					total += prodotto.getPrezzoEffettivo() * prodotto.getQuantita();
					
				}
		 %>
        <tr>
            <td>
				<p class="paragraph"><span class="bold">Id Ordine:</span> <%= ordine.getIdOrdine()%></p>
				<p class="paragraph"><span class="bold">Data: </span> <%= new SimpleDateFormat("dd MM yyyy - HH:mm", Locale.ITALIAN).format(ordine.getData()).toString()%></p>
				<p class="paragraph"><span class="bold">Stato:</span> <%= ordine.getStato()%></p>
				<p class="paragraph"><span class="bold">Prezzo Totale</span>: <%= total%></p>
				<a class="details paragraph" href="<%= getServletContext().getContextPath() + "/pages/ordine.jsp?id=" + ordine.getIdOrdine()%>">Dettagli</a>
			</td>
        </tr>
        <%
			} 
		%>
    </table>
    <%@ include file="../components/footer.jsp" %>
</body>
</html>