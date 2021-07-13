const formDataElement = document.querySelector('.form-data');
const formNominElement = document.querySelector('.form-nominativo');
const ricercaDataButton = document.querySelector('.ricerca-data');
const ricercaNominativoButton = document.querySelector('.ricerca-nominativo');

window.addEventListener('DOMContentLoaded', () => {
	// Funzione
	ricercaDataButton.addEventListener('click', () => {
		formNominElement.classList.remove('🧱');
		formDataElement.classList.toggle('🧱');
	});
	ricercaNominativoButton.addEventListener('click', () => {
		formDataElement.classList.remove('🧱');
		formNominElement.classList.toggle('🧱');
	});
});
