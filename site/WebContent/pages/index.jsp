	<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <%@page import="net.teraware.model.bean.ProdottoBean" %>
    <%@page import="net.teraware.model.ProdottoModel" %>
    <%@page import="java.util.ArrayList" %>
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/global.css"%>">
    <link rel="stylesheet" href="<%= getServletContext().getContextPath() + "/assets/css/index.css"%>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
</head>
  <body>
  	<%@ include file="../components/navbar.jsp" %>
    <header class="carousel-container">
      <div class="carousel">
        <div class="carousel-item carousel-item--visible">
          <img src="<%= getServletContext().getContextPath() + "/assets/img/index/carousel/pc.jpg" %>" alt="" class="carousel-image">
          <div class="carousel-text-container">
            <h1 class="title">GTX, Tutta la potenza di Nvidia</h1>
          </div>
        </div>
        <div class="carousel-item">
          <img src="<%= getServletContext().getContextPath() + "/assets/img/index/carousel/bitcoin.jpg" %>" alt="" class="carousel-image">
          <div class="carousel-text-container">
            <h1 class="title">Il Bitcoin e come funziona</h1>
          </div>
        </div>

        <div class="carousel-item">
          <img src="<%= getServletContext().getContextPath() + "/assets/img/index/carousel/motherboard.jpg" %>" alt="" class="carousel-image">
          <div class="carousel-text-container">
            <h1 class="title">Cervello e... corpo</h1>
          </div>
        </div>

        <div class="carousel-actions">
          <button class="carousel-button">
            <i class="fas fa-chevron-left fa-3x"></i>
          </button>
          <button class="carousel-button">
            <i class="fas fa-chevron-right fa-3x"></i>
          </button>
        </div>
      </div>
    </header>

    <section class="section-container vertical-structure">
        <p class="section-title title">Tutto per il tuo PC</p>
        <h4 class="subtitle">Tutto quello che potrebbe servirti per la tua postazione di lavoro o di gioco</h4>
        <a class="shop-link" href="<%= getServletContext().getContextPath() + "/category?category=all&skip=0&limit=10"%>">SHOP</a>
    </section>

    <section class="section-container vertical-structure">
      <p class="section-title title">I Pi&ugrave; Venduti</p>
      <h4 class="subtitle">Una carrellata dei nostri prodotti pi&ugrave; apprezzati dalla clientela</h4>
      <div class="shop-container">
        <div class="pointers">
            <div class="pointer active"></div>
            <div class="pointer"></div>
            <div class="pointer"></div>
        </div>
        <div class="products-carousel">
          <% ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) new ProdottoModel().doRetrieveMostSold(0, 3); %>
          
          <div class="products-carousel-item products-carousel-item--visible">
          <% for (int i = 0; i < prodotti.size(); i++) { 
          		ProdottoBean prodotto = prodotti.get(i);
          		String categoria = new ProdottoModel().getCategoriaProdotto(prodotto);
          %>
            <div class="card">
              <div class="card-img-container">
                <a href="#">
                  <img
                    src="<%= prodotto.getImageUrl().startsWith("http") ? prodotto.getImageUrl() : getServletContext().getContextPath() + "/" + prodotto.getImageUrl() %>"
                  />
                </a>
        
                <div class="toast-container">
                  <a href="<%= getServletContext().getContextPath() + "/category?category="+categoria+"&skip=0&limit=10" %>" class="toast-accent small-text bold">#<%= categoria %></a>
                </div>
              </div>
              <div class="card-body">
                <h3 class="product-title"><%= prodotto.getNome() %></h3>
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
			<% } %>  
          </div>
          <div class="products-carousel-item">
          <%
          	prodotti = (ArrayList<ProdottoBean>) new ProdottoModel().doRetrieveMostSold(3, 3);
          	for (int i = 0; i < prodotti.size(); i++) {
        	  ProdottoBean prodotto = prodotti.get(i);
        		String categoria = new ProdottoModel().getCategoriaProdotto(prodotto);
        	  %>
            <div class="card">
              <div class="card-img-container">
                <a href="#">
                  <img
                    src="<%= prodotto.getImageUrl().startsWith("http") ? prodotto.getImageUrl() : getServletContext().getContextPath() + "/" + prodotto.getImageUrl() %>"
                  />
                </a>
        
                <div class="toast-container">
                  <a href="<%= getServletContext().getContextPath() + "/category?category="+categoria+"&skip=0&limit=10" %>" class="toast-accent small-text bold">#<%= categoria %></a>
                </div>
              </div>
              <div class="card-body">
                <h3 class="product-title"><%= prodotto.getNome() %></h3>
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
			<% } %>
          </div>
          <div class="products-carousel-item">
           <% 
           prodotti = (ArrayList<ProdottoBean>) new ProdottoModel().doRetrieveMostSold(6, 3);
           for (int i = 0; i < prodotti.size(); i++) {
        	  ProdottoBean prodotto = prodotti.get(i);
        		String categoria = new ProdottoModel().getCategoriaProdotto(prodotto);
        	  %>
            <div class="card">
              <div class="card-img-container">
                <a href="#">
                  <img
                    src="<%= prodotto.getImageUrl().startsWith("http") ? prodotto.getImageUrl() : getServletContext().getContextPath() + "/" + prodotto.getImageUrl() %>"
                  />
                </a>
        
                <div class="toast-container">
                  <a href="<%= getServletContext().getContextPath() + "/category?category="+categoria+"&skip=0&limit=10" %>" class="toast-accent small-text bold">#<%= categoria %></a>
                </div>
              </div>
              <div class="card-body">
                <h3 class="product-title"><%= prodotto.getNome() %></h3>
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
			<% } %>
          </div>
        </div>
      </div>
      
    </section>
    
    <section class="section-container parallax vertical-structure">
      <p class="section-title title" style="color: #fff;">Pensa, Credi, Sogna e Osa</p>
      <h4 class="subtitle" style="color: #fff;">Ti offriamo tutto il necessario per poter sfruttare al massimo la tua creativit&agrave; e permetterti di fare grandi cose.</h4>
    </section>

    <section class="section-container vertical-structure">
      <p class="section-title title">News</p>
      <h4 class="subtitle">Le ultime news su di noi e le informazioni sul mondo del gaming</h4>
      <div class="card-container">
        <a class="news-card" href="#">
          <img class="news-image" src="<%= getServletContext().getContextPath() + "/assets/img/index/carousel/bitcoin.jpg" %>" alt="">
          <div class="news-text-container">
            <h2 class="news-title">Bitcoin</h2>
          </div>
        </a>
        <a class="news-card" href="#">
          <img class="news-image" src="<%= getServletContext().getContextPath() + "/assets/img/index/carousel/pc.jpg" %>" alt="">
          <div class="news-text-container">
            <h2 class="news-title">News Title</h2>
          </div>
        </a>
      </div>
    </section>

    <section class="section-container vertical-structure">
      <p class="section-title title">Il Nostro Team</p>
      <h4 class="subtitle">Il Team di ingegneri e designer che ha reso possibile tutto questo</h4>
      <div class="card-container">
        <a href="#" class="collaborator-card">
          <img class="collaborator-image" src="<%= getServletContext().getContextPath() + "/assets/img/index/noi/scognamiglio.jpg" %>" alt="">
          <div class="collaborator-text-container">
            <h3 class="collaborator-title">Antonio Scognamiglio</h3>
          </div>
        </a>
        <a href="#" class="collaborator-card">
          <img class="collaborator-image" src="<%= getServletContext().getContextPath() + "/assets/img/index/noi/gioia.jpg" %>" alt="">
          <div class="collaborator-text-container">
            <h3 class="collaborator-title">Domenico Antonio Gioia</h3>
          </div>
        </a>
        <div class="break"></div>
        <a href="#" class="collaborator-card">
          <img class="collaborator-image" src="<%= getServletContext().getContextPath() + "/assets/img/index/noi/esposito.jpg" %>" alt="">
          <div class="collaborator-text-container">
            <h3 class="collaborator-title">Manuela Esposito</h3>
          </div>
        </a>
        <a href="#" class="collaborator-card">
          <img class="collaborator-image" src="<%= getServletContext().getContextPath() + "/assets/img/index/noi/scorziello.jpg" %>" alt="">
          <div class="collaborator-text-container">
            <h3 class="collaborator-title">Giovanni Scorziello</h3>
          </div>
        </a>
      </div>
    </section>

    <section class="section-container vertical-structure">
      <div class="quote-container">
        <h1 style="font-size: 2rem"><i>Trasformiamo le idee dei nostri clienti in prodotti unici.</i></h1>
      </div>
    </section>
    <%@ include file="../components/footer.jsp" %>

    <script
	  src="https://code.jquery.com/jquery-3.6.0.min.js"
	  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	  crossorigin="anonymous"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/carousel.js" %>"></script>
    <script src="<%= getServletContext().getContextPath() + "/assets/js/cart.js" %>"></script>
  </body>
</html>
