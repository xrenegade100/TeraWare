/**
 * Util per dati anagrafici
 */

const COMUNI_ITA_BASE_URL = 'https://comuni-ita.herokuapp.com';

function creaProvinceSelect(selector) {
	const element = document.querySelector(selector);
	$.ajax(`${COMUNI_ITA_BASE_URL}/api/province`).done((data) => {
		const province = data;
		province
			.sort((a, b) => a.sigla.localeCompare(b.sigla))
			.forEach((p) => {
				const opt = document.createElement('option');
				opt.value = p.sigla;
				opt.setAttribute('nomeProvincia', p.nome);
				opt.appendChild(
					document.createTextNode(
						`${p.sigla.toUpperCase()} - ${capitalizeFirstLetter(
							p.nome
						)}`
					)
				);
				element.appendChild(opt);
			});
	});
}

function creaCapSelect(selector, provincia) {
	const element = document.querySelector(selector);
	$(selector).empty();
	$.ajax(`${COMUNI_ITA_BASE_URL}/api/comuni/provincia/${provincia}`).done(
		(data) => {
			const province = data;
			province
				.sort((a, b) => a.cap.localeCompare(b.cap))
				.forEach((p) => {
					const opt = document.createElement('option');
					opt.value = p.cap;
					opt.appendChild(
						document.createTextNode(`${p.cap} - ${p.nome}`)
					);
					element.appendChild(opt);
				});
		}
	);
}

function getCittaByCap(cap, element) {
	const endpoint = `${COMUNI_ITA_BASE_URL}/api/comuni/`;
	$.ajax(endpoint).done((data) => {
		const citta = data.filter((a) => {
			return a.cap === cap;
		});
		$(element).html(`${citta[0].nome}`);
	});
}

function capitalizeFirstLetter(string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
}
