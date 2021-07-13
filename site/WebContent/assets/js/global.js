const overlay = document.querySelector('#overlay-container');
const hamburger = document.querySelector('.hamburger-menu');
const navMenu = document.querySelectorAll('.nav-menu-dropdown');
const navLink = document.querySelectorAll('.nav-link');
const dropdownItems = document.querySelectorAll('.details-list');
const listItems = document.querySelectorAll('.section-title-super-small');
const navItems = document.querySelectorAll('.nav-item');
const footerLast = document.querySelector('.footer-last');

function showLoading(element) {
	element.classList.toggle('loading-spinner');
}

function dropdown() {
	if (getWidth() <= 768) {
		listItems[6].classList.remove('hide');
		listItems[7].classList.remove('hide');
		listItems[8].classList.remove('hide');
		listItems[9].classList.remove('hide');
		dropdownItems.forEach((item) => item.classList.add('hide'));
	} else {
		listItems[6].classList.add('hide');
		listItems[7].classList.add('hide');
		listItems[8].classList.add('hide');
		listItems[9].classList.add('hide');
		dropdownItems.forEach((item) => item.classList.remove('hide'));
	}
}

function overlayPositioning() {
	const userPic = document.querySelector('.pro-pic').getBoundingClientRect();
	overlay.style.top = userPic.bottom + 'px';
	overlay.style.left = Number(userPic.left - 147) + 'px';
}

function navItemHover(element) {
	if (getWidth() > 768) {
		navMenu[element].classList.add('active');
		navItems[element].classList.add('active-item');
	}
}

function navItemOut(element) {
	if (getWidth() > 768) {
		navMenu[element].classList.remove('active');
		navItems[element].classList.remove('active-item');
	}
}

function showOverlay() {
	overlay.classList.toggle('show');
	overlay.classList.toggle('hide');
	overlay.style.width = 10.75 + 'rem';
	overlay.style.height = 'auto';
}

/*document.onclick = function hideOverlay(){
    overlay.classList.add('hide');
}*/

hamburger.addEventListener('click', mobileMenu);

function mobileMenu() {
	hamburger.classList.toggle('active');
	navMenu[0].classList.toggle('active');
}

navLink.forEach((n) => n.addEventListener('click', closeMenu));

function closeMenu() {
	hamburger.classList.remove('active');
	navMenu[0].classList.remove('active');
}

function showDetails(dropNumber) {
	if (getWidth() <= 768) {
		dropdownItems[dropNumber].classList.remove('hide');
		listItems[dropNumber].classList.add('active');

		for (let index = 0; index < listItems.length; index++) {
			if (index != dropNumber) {
				dropdownItems[index].classList.add('hide');
				listItems[index].classList.remove('active');
			}
		}
	}
}

dropdown();
overlayPositioning();

window.onresize = function () {
	dropdown();
	overlayPositioning();
};
