function subtract() {
   let firstNumber = Number(document.getElementById('firstNumber').value);
   let secondNumber = Number(document.getElementById('secondNumber').value);
   let result = firstNumber - secondNumber;
   console.log(result);
   document.getElementById('result').textContent = result;
}