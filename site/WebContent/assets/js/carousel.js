let slidePosition = 0;
let productsSlidePosition = 0;
const slides = document.querySelectorAll('.carousel-item');
const productSlides = document.querySelectorAll('.products-carousel-item');
const pointers = document.querySelectorAll('.pointer');

carousel();

function carousel() {
	try {
		moveToNext();
		slide();
		setInterval(carousel, 10000);
	} catch (e) {
		alert('Carousel' + e);
	}
}

document
	.querySelectorAll('.carousel-button')[0]
	.addEventListener('click', function () {
		moveToPrevious();
		slide();
	});

document
	.querySelectorAll('.carousel-button')[1]
	.addEventListener('click', function () {
		moveToNext();
		slide();
	});

pointers[0].addEventListener('click', function () {
	moveTo(0);
	slideProducts();
	toggleActive(0);
});

pointers[1].addEventListener('click', function () {
	moveTo(1);
	slideProducts();
	toggleActive(1);
});

pointers[2].addEventListener('click', function () {
	moveTo(2);
	slideProducts();
	toggleActive(2);
});

function toggleActive(i) {
	pointers.forEach((pointer, index) => {
		if (index === i) {
			pointer.classList.add('active');
		} else {
			pointer.classList.remove('active');
		}
	});
}

function moveToNext() {
	if (slidePosition === Number(slides.length - 1)) {
		slidePosition = 0;
	} else {
		slidePosition++;
	}
}

function moveToPrevious() {
	if (slidePosition === 0) {
		slidePosition = Number(slides.length - 1);
	} else {
		slidePosition--;
	}
}

function slide() {
	slides.forEach((slide, index) => {
		if (index === slidePosition) {
			slide.classList.add('carousel-item--visible');
			slide.classList.remove('carousel-item--hidden');
		} else {
			slide.classList.remove('carousel-item--visible');
			slide.classList.add('carousel-item--hidden');
		}
	});
}

function moveTo(product) {
	productsSlidePosition = product;
}

function slideProducts() {
	productSlides.forEach((slide, index) => {
		if (index === productsSlidePosition) {
			slide.classList.add('products-carousel-item--visible');
			slide.classList.remove('products-carousel-item--hidden');
		} else {
			slide.classList.remove('products-carousel-item--visible');
			slide.classList.add('products-carousel-item--hidden');
		}
	});
}
