const registrationButton = document.querySelectorAll('.reg');
const emailElement = document.querySelector('#email');
const passwordElement = document.querySelector('#password');

document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('#loginform');

	form.addEventListener('submit', (event) => {
		event.preventDefault();
		$('.login-feedback').css('display', 'none');
		$('.btn-login').attr('disabled', 'true');
		$('.button-text').html('<i class="fas fa-circle-notch fa-spin"></i>');

		const values = {
			email: $('#email').val(),
			password: $('#password').val(),
		};
		$.post({
			url: `/TeraWere_Site/login`,
			data: { email: values.email, password: values.password },
			error: () => {
				$('.login-feedback').css('display', 'block');
			},
			success: () => {
				location.replace(`/TeraWere_Site/`);
			},
			complete: () => {
				$('.button-text').html('ACCEDI');
				$('.btn-login').removeAttr('disabled');
			},
		});
	});
});

function responsiveRegisterPosition() {
	if (getWidth() <= 768) {
		registrationButton[0].classList.add('hide');
		registrationButton[1].classList.remove('hide');
	} else {
		registrationButton[0].classList.remove('hide');
		registrationButton[1].classList.add('hide');
	}
}

responsiveRegisterPosition();

window.onresize = responsiveRegisterPosition;
