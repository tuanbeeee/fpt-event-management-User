const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

function show(a) {
  var x = document.getElementById(a);
  var c = x.nextElementSibling
  if (x.getAttribute('type') == "password") {
    c.removeAttribute("class");
    c.setAttribute("class", "fas fa-eye-slash");
    x.removeAttribute("type");
    x.setAttribute("type", "text");
  } else {
    x.removeAttribute("type");
    x.setAttribute('type', 'password');
    c.removeAttribute("class");
    c.setAttribute("class", "fas fa-eye");
  }
}