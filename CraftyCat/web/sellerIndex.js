let menu = document.querySelector('#menu-icon');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    menu.classList.toggle('bx bx-menu');
    navbar.classList.toggle('active');

};

window.onscroll = () => {
    menu.classList.remove('bx bx-menu');
    navbar.classList.remove('active');

};