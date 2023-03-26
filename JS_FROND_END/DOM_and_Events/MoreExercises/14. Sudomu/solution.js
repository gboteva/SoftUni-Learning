function solve() {
  const buttons = document.getElementsByTagName("button");
  
  const rows = Array.from(document.querySelectorAll("tbody tr"));
  const inputs = document.querySelectorAll("td input");

  const table = document.getElementsByTagName("table")[0];
  let paragraph = document.querySelector("#check p");

  buttons[0].addEventListener("click", check);
  buttons[1].addEventListener("click", clear);

  function check() {
    //take the rows
    let isCorrectByRows = true;

    for (let row of rows) {
      let inputsInRow = Array.from(row.querySelectorAll("td input"));

      let sumForRow = inputsInRow.reduce((acc, input) => {
        return acc + Number(input.value);
      }, 0);

      let setForRows = new Set();
      inputsInRow.forEach((input) => setForRows.add(Number(input.value)));

      setForRows.forEach((digit) => {
        if (digit < 1 || digit > 3 || setForRows.size !== 3) {
          isCorrectByRows = false;
        }
      });

      if ( sumForRow !== 6 || !isCorrectByRows) {
        break;
      }
    }

    //take columns
    let isCorrectByColumns = true;

    let matrix = [
      [inputs[0].value, inputs[1].value, inputs[2].value],
      [inputs[3].value, inputs[4].value, inputs[5].value],
      [inputs[6].value, inputs[7].value, inputs[8].value],
    ];

    for (let i = 0; i < 3; i++) {
      let firstValue = Number(inputs[i].value);
      let secondValue = Number(inputs[i + 3].value);
      let thirdValue = Number(inputs[i + 6].value);
      let sum = firstValue + secondValue + thirdValue;

      let setForColumns = new Set();
      setForColumns.add(firstValue);
      setForColumns.add(secondValue);
      setForColumns.add(thirdValue);

      setForColumns.forEach((digit) => {
        if (digit < 1 || digit > 3 || setForColumns.size !== 3) {
          isCorrectByColumns = false;
        }
      });

      if (sum !== 6) {
        isCorrectByColumns = false;
        break;
      }
    }

    if (!isCorrectByRows || !isCorrectByColumns) {
      table.style.border = "2px solid red";
      paragraph.textContent = "NOP! You are not done yet...";
      paragraph.style.color = "red";
    } else{
      table.style.border = "2px solid green";
      paragraph.textContent = "You solve it! Congratulations!";
      paragraph.style.color = "green";
    }

  }

  function clear() {
    table.style.border = "none";
    paragraph.textContent = "";
    paragraph.style.color = "";

    inputs.forEach(input=> input.value = ' ')
  }
}
