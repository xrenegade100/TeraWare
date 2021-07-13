function search(query) {
	let resultingHtml = ``;
	$.ajax({
		url: `/TeraWere_Site/search?query=${query}`,
		success: (data) => {
			const parseData = JSON.parse(data);
			for (let i = 0; i < parseData.length; i++) {
				const element = parseData[i];
				resultingHtml += `<div class="card">
				<div class="card-img-container">
				  <a href="/TeraWere_Site/product?id=${element.idProdotto}">
					<img
					  src="${element.imageUrl}"
					/>
				  </a>
				</div>
				<div class="card-body">
				  <h3 class="product-title">${element.nome}</h3>
				  <h4 class="product-brand">${element.brand}</h4>
				  <p class="paragraph product-description">
				  ${element.informazioni}
				  </p>
				  <div class="card-footer">
					<button
					  class="btn-accent bottom-left"
					  onclick="addToCart(${element.idProdotto}, 1, this)"
					>
					  <i class="fas fa-shopping-cart fa-lg"></i>
					  <span class="paragraph bold">Aggiungi</span>
					</button>
					<h4 class="price-text">${element.prezzo} &euro;</h4>
				  </div>
				</div>
			  </div>`;
				/* resultingHtml += `
				<div class="product-card">
				<div class="card-image-container">
				  <a href="/TeraWere_Site/product?id=${element.idProdotto}"><img src="${element.imageUrl}" alt="" class="card-image"></a>
				</div>
				<div class="card-body">
				  <a class="h3-link-title" href="/TeraWere_Site/product?id=${element.idProdotto}">${element.nome}</a>
				  <hr class="card-divider">
				  <h4 class="product-producer">${element.brand}</h4>
				  <p class="paragraph product-description">${element.informazioni}</p>
				  <button class="btn-accent bottom-left" onclick="addToCart(${element.idProdotto}, 1, this)">
					<i class="fas fa-shopping-cart fa-lg"></i>
					<span class="paragraph bold">Aggiungi</span>
				  </button>
				  <h4 class="product-price">${element.prezzo} &euro;</h4>
				</div>
			  </div>
				`; */
			}
			$('.container').html(resultingHtml);
		},
	});
}

$(document).ready(() => {
	$('#search-button').click(() => {
		const searchTerm = $('.search').val();
		search(searchTerm);
	});
});
