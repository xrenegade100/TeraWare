function getQuantita() {
	const element = document.querySelector('#quantita');
	return element.value;
}

document.querySelector('.addtocart').addEventListener('click', () => {
	const element = $('.addtocart');
	addToCart(productId, getQuantita(), element);
});
