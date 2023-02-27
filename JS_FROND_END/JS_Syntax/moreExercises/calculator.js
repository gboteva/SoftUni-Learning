function calculator(num1, sign, num2) {
  switch (sign) {
    case "+":
        console.log((num1 + num2).toFixed(2));
      break;
    case "-":
        console.log((num1 - num2).toFixed(2));
      break;
    case "/":
        console.log((num1 / num2).toFixed(2));
      break;
    case "*":
        console.log((num1 * num2).toFixed(2));
      break;
  }
}

calculator(5,'+', 10)
calculator(25.5,'-', 3)
calculator(18, '/', 6)
