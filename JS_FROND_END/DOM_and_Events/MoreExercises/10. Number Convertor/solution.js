function solve() {
  const result = document.getElementById("result");
  const btn = document.querySelector("button");
  const select = document.getElementById("selectMenuTo");

  select.querySelector("option").textContent = "Binary";
  select.querySelector("option").value = "binary";

  let option = document.createElement("option");
  option.value = "hexadecimal";
  option.textContent = "Hexadecimal";
  select.appendChild(option);

  btn.addEventListener("click", convert);

  function convert() {
    let options = select.querySelectorAll("option");
    let system = "";
    for (let option of options) {
      if (option.selected) {
        system = option.value;
        break;
      }
    }

    let decimalNumber = Number(document.getElementById("input").value);
    let converted = 0;
    if (system === "binary") {
      converted = getBinaryCode(decimalNumber);
    } else if (system === "hexadecimal") {
      converted = getHexadecimalNumber(decimalNumber);
    }

    result.value = converted;

    function getBinaryCode(number) {
      // return (number >>> 0).toString(2);

      let binary = [];
      while (number > 0) {
        binary.push(number % 2);
        number = Math.trunc(number / 2);
      }

      return binary.reverse().join("");
    }

    function getHexadecimalNumber(number) {
        // return number.toString(16);
      let hexa = [];
      while (number > 0) {
        if (number < 10) {
          hexa.push(number.toString());
          break;
        }
        let remainder = number % 16;
        if (remainder > 10) {
          if (remainder === 10) {
            hexa.push("A");
          } else if (remainder === 11) {
            hexa.push("B");
          } else if (remainder === 12) {
            hexa.push("C");
          } else if (remainder === 13) {
            hexa.push("D");
          } else if (remainder === 14) {
            hexa.push("E");
          } else if (remainder === 15) {
            hexa.push("E");
          }
        } else {
          hexa.push(remainder.toString());
        }
        number = Math.trunc(number / 16);
      }
      return hexa.reverse().join("");
    }
  }
}
