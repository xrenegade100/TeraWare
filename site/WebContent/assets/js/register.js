const REGEX = {
	NOME: /^[a-zA-Z\s]+$/,
	PASSWORD: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/,
};

$(document).ready(() => {
	const provinciaSelect = document.querySelector('.provincia');
	provinciaSelect.addEventListener('change', () => {
		const provincia = $('.provincia option:selected').text();

		const provinciaTrimmed = provincia.substring(
			provincia.indexOf('-') + 2
		);
		creaCapSelect('.cap', provinciaTrimmed);
	});

	const signupForm = document.querySelector('.register-side');
	signupForm.addEventListener('submit', async (event) => {
		event.preventDefault();
		const fields = {
			nome: $('#nome').val(),
			email: $('#email').val(),
			cognome: $('#cognome').val(),
			password: $('#password').val(),
		};

		const selectors = {
			nome: '#nome',
			email: '#email',
			cognome: '#cognome',
			password: '#password',
		};

		if (!valida({ fields, selectors })) {
			return;
		}

		$.ajax(`/TeraWere_Site/checkemail?email=${fields.email}`).done(
			(data) => {
				if (data === '1') {
					showFeedback(selectors.email);
					return;
				} else {
					signupForm.submit();
				}
			}
		);
	});
});

function valida({ fields, selectors }) {
	let valid = true;

	hideAllFeedbacks(selectors);

	if (!REGEX.NOME.test(fields.nome) && fields.nome.trim() !== '') {
		showFeedback(selectors.nome);
		valid = false;
	}
	if (!REGEX.NOME.test(fields.cognome) && fields.cognome.trim() !== '') {
		showFeedback(selectors.cognome);
		valid = false;
	}
	if (!REGEX.PASSWORD.test(fields.password)) {
		showFeedback(selectors.password);
		valid = false;
	}
	return valid;
}

function hideAllFeedbacks(selectors) {
	for (const [, value] of Object.entries(selectors)) {
		$(value).removeClass('invalid-field');
		$(value).next('p').css('display', 'none');
	}
}

function showFeedback(selector) {
	$(selector).addClass('invalid-field');
	$(selector).next('p').css('display', 'block');
}
