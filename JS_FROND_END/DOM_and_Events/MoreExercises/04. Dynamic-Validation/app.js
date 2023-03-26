function validate() {
    
  const regularExpressionForEmail =
    /^[a-z]+\@[^\s]+\.[^\s]+$/;
  let input = document.getElementById("email");
 
  input.addEventListener("change", validateEmail);

  function validateEmail() {
    input.classList.remove('error')
    if (!input.value.match(regularExpressionForEmail)) {
      input.classList.add("error");
    }
  }
}
