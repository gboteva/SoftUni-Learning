function solve() {
  const [generateBtn, buyBtn] = document.getElementsByTagName("button");
  let [generateArea, resultArea] = document.getElementsByTagName("textarea");
  const tBody = document.querySelector("tbody");
  
  if (!generateArea.value) {
    return;
  }

  generateBtn.addEventListener("click", fillTable);

  buyBtn.addEventListener("click", buy);

  function fillTable() {
    const inputData = Array.from(JSON.parse(generateArea.value));
    
    for (let { img, name, price, decFactor } of inputData) {
      const row = createElement('tr', '', tBody);
      const tdImage = createElement('td', '', row)
      createElement('img', '', tdImage, '', '', {src: img} )

      createElement('td', name, row);
      createElement('td', price, row);
      createElement('td', decFactor, row);
      const tdCheckBox = createElement('td', '', row);
      createElement('input', '', tdCheckBox, '', '', {type: 'checkbox'})
    }
  }

  function buy() {
    let rows = document.querySelectorAll('tbody > tr')
    let products = [];
    let totalSum = 0;
    let decFactors = 0;
    
    for(const row of rows){

      let input = row.children[4].children[0];
      
      if (input.checked){
        let name = row.children[1].textContent;
        products.push(name);
        let price = Number(row.children[2].textContent);
        totalSum += price;

        let decFactor = Number(row.children[3].textContent);
        decFactors += decFactor
      }

    }

    resultArea.value += `Bought furniture: ${products.join(', ')}\n`
    resultArea.value += `Total price: ${totalSum.toFixed(2)}\n`
    resultArea.value += `Average decoration factor: ${decFactors / products.length}`
  }

  function createElement(type, content, parent, id, classes, attributes) {
    let element = document.createElement(type);

    if (content && type !== "input") {
      element.textContent = content;
    }

    if (content && type === "input") {
      element.value = content;
    }

    if (parent) {
      parent.appendChild(element);
    }

    if (id) {
      element.id = id;
    }

    if (classes) {
      element.classList.add(...classes);
    }

    if (attributes) {
      for(const key in attributes){
        element.setAttribute(key, attributes[key]);
      } 
    }

    return element;
  }
}
