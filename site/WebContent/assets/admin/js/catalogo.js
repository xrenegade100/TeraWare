const imageElement = document.getElementById('product-img');

const previewImage = (data) => {
	const imgSrc = window.URL.createObjectURL(data.files[0]);
	imageElement.src = imgSrc;
};
