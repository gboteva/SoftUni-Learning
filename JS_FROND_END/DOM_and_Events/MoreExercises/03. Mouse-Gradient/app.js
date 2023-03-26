function attachGradientEvents() {
  const result = document.getElementById("result");
  const gradient = document.getElementById("gradient");
  gradient.addEventListener("mousemove", displayResult);

  function displayResult(event) {
    let x = event.offsetX;
    result.textContent = Math.trunc((x / 300) * 100) + "%";
  }
}
