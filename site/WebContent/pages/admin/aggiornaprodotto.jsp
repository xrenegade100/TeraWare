<%@ page language="java" 
pageEncoding="utf-8"%> <%@page import="net.teraware.model.bean.ProdottoBean" %>
<%@ page import="net.teraware.model.ProdottoModel"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Aggiorna Prodotto</title>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() +
    "/assets/css/aggiornaprodotto.css" %>">
  </head>
  <style>
    
  </style>
  <body>
    <%@include file="../../components/navbar.jsp"%> 
    <%
    ProdottoBean prodotto = null;
    if(request.getParameter("id") != null){
    int productId = Integer.parseInt(request.getParameter("id")); 
    prodotto = new ProdottoModel().doRetrieveByKey(productId); } %>
    <div class="container">
		<form enctype="multipart/form-data" method="post" action="<%= getServletContext().getContextPath() + "/admin/aggiornaprodotto" %>">
			<div class="two-row-input">
				<div class="input">
				  <label>Nome</label>
				  <input
					required
					class="text-input"
					value="<%= prodotto != null ? prodotto.getNome() : "" %>"
					type="text"
					name="nomeProdotto"
				  />	
				</div>
				<div class="input">
				  <label>Prezzo</label>
				  <input
					required
					type="number"
					name="prezzo"
					value="<%= prodotto != null ? prodotto.getPrezzo() : "" %>"
				  />
				</div>
			  </div>
			  <div class="two-row-input">
				<div class="input">
				  <label>Quantit&agrave;</label>
				  <input
				  	required
					type="number"
					min="0"
					name="quantita"
					value="<%= prodotto != null ? prodotto.getQuantita() : "" %>"
				  />
				</div>
				<div class="input">
				  <label>Brand</label>
				  <input required type="text" name="brand" value="<%= prodotto != null ? prodotto.getBrand() : "" %>" />
				</div>
			  </div>
			  <textarea name="informazioni" rows="5" cols="45" placeholder="Descrizione prodotto"><%= prodotto != null ? prodotto.getInformazioni() : "" %></textarea>
			 <div class="two-row-input">
			 	<div class="input">
					<label>Visibile</label>
					<input type="checkbox" name="visibile" value="true" <%= prodotto != null && prodotto.isVisibile() ? "checked" : ""%>>
				</div>
				<div class="input">
					<label>Categoria</label>
					<select name="categoria" id="categoria">
						<option value="cpu">Cpu</option>
						<option value="scheda madre">Scheda Madre</option>
						<option value="ram">Ram</option>
						<option value="scheda video">Scheda Video</option>
						<option value="ssd">SSD</option>
						<option value="hdd">HDD</option>
						<option value="alimentatore">Alimentatore</option>
						<option value="dissipatore">Dissipatore</option>
						<option value="case">Case</option>
						<option value="preassemblati">Preassemblati</option>
						<option value="mouse">Mouse</option>
						<option value="tastiera">Tastiera</option>
						<option value="cuffie">Cuffie</option>
						<option value="microfono">Microfono</option>
						<option value="monitor">Monitor</option>
						<option value="audio">Audio</option>
						<option value="video">Video</option>
						<option value="immagini">Immagini</option>
						<option value="office">Office</option>
						<option value="gioco">Gioco</option>
					</select>
				</div>
			 </div>
			  <div class="img-choice-container">
				<img id="product-img" src="<%= prodotto != null ? getServletContext().getContextPath() + prodotto.getImageUrl() : "https://via.placeholder.com/300x225" %>"
				alt="prodotto">
				<input onchange="previewImage(this)" type="file" name="prod_img" />
			  </div>
			  <%if(request.getParameter("id") != null){ %>
				   <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
			  <% } %>
			  <input type="submit" value="Aggiorna" />
		</form>
      
    </div>
	<script src="<%= getServletContext().getContextPath() + "/assets/admin/js/catalogo.js" %>"></script>
  </body>
</html>
