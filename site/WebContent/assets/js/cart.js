function addToCart(id, quantity, element) {
	$(element).attr('disabled', true);
	$(element).css('opacity', '100%');
	$(element)
		.html(`<svg id="check${id}" style="width: 20px; height: 20px" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 100 100" xml:space="preserve">
	<circle id="circle" cx="50" cy="50" r="46" fill="transparent" />
	<polyline id="tick" points="25,55 45,70 75,33" fill="transparent" />
  	</svg>`);
	$(`#check${id}`).toggleClass('progress');
	$.post(`/TeraWere_Site/addtocart`, { pId: id, q: quantity }, (data) => {
		const cart = JSON.parse(data);
		const cartElements = cart.prodotti.length;
		$('.cartNumber')[0].innerText = `CARRELLO(${cartElements})`;
		$('.cartNumber')[1].innerText = `Carrello(${cartElements})`;

		$(`#check${id}`).toggleClass('progress');
		$(`#check${id}`).toggleClass('ready');
		setTimeout(() => {
			$(element).html(`<i class="fas fa-shopping-cart fa-lg"></i>
			<span class="paragraph bold">Aggiungi</span>`);
			$(element).removeAttr('disabled');
		}, 1500);
	});
}

function removeFromCart(id) {
	$.post(`/TeraWere_Site/addtocart`, { pId: id, q: 0 }, () => {
		location.reload();
	});
}

function editCart(id) {
	const quantity = $(`#i-${id}`).val();
	$.post(`/TeraWere_Site/addtocart`, { pId: id, q: quantity }, (data) => {
		if (quantity !== '0') {
			let total = 0;
			const parsedData = JSON.parse(data);
			for (let i = 0; i < parsedData.prodotti.length; i++) {
				const entry = parsedData.prodotti[i];
				const q = entry.quantita;
				const p = entry.prodotto.prezzo;
				total += q * p;
				if (entry.prodotto.idProdotto === id) {
					$(`#p-${id}`).html(`<h4>&euro;${(p * q).toFixed(2)}<h4> 
					<form action="/TeraWere_Site/addtocart" method="POST">
					<input type="hidden" name="pId" value="${id}">
					<input type="hidden" name="q" value="0">
					<button type="submit" class="btn-accent nav-button del-button" onclick="removeFromCart(${
						entry.prodotto.idProdotto
					},0)">
								<span class="paragraph bold">x</span>
							</button>
				</form>`);
				}
			}

			const subtotale = ((total * 100) / 122).toFixed(2);
			$('.subtotale').html(`Subtotale: ${subtotale}`);
			$('.iva').html(`IVA: ${(total - subtotale).toFixed(2)}`);
			$('.totale').html(`Totale: ${(total + Number(4.9)).toFixed(2)}`);
		} else {
			location.reload();
		}
	});
}
