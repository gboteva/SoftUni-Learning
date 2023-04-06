window.addEventListener("load", solve);

function solve() {
  const inputs = {
    make: document.getElementById('make'),
    model:document.getElementById('model'),
    year: document.getElementById('year'),
    fuel: document.getElementById('fuel'),
    originalCost: document.getElementById('original-cost'),
    sellingPrice: document.getElementById('selling-price')
  }

  const publishBtn = document.getElementById('publish');
  publishBtn.addEventListener('click', publishHandler);

  let tableBody = document.getElementById('table-body');
  let ulSel = document.getElementById('cars-list');
  let priceContainer = document.getElementById('profit');

  let totalPrice = 0;

  function publishHandler(event){
    event.preventDefault();
    let make = inputs.make.value;
    let model = inputs.model.value;
    let year = inputs.year.value;
    let fuel = inputs.fuel.value;
    let originalCost = Number(inputs.originalCost.value);
    let sellingPrice = Number(inputs.sellingPrice.value);

    if (make ==='' || model ==='' || year === '' || fuel === '' || originalCost > sellingPrice){
      return
    } 

    let row = createElement('tr', '', tableBody, '', ['row']);
    createElement('td', `${make}`, row);
    createElement('td', `${model}`, row);
    createElement('td', `${year}`, row);
    createElement('td', `${fuel}`, row);
    createElement('td', `${originalCost}`, row);
    createElement('td', `${sellingPrice}`, row);
    let btnCtn = createElement('td', '', row);
    let editBtn = createElement('button', 'Edit', btnCtn, '', ['action-btn', 'edit']);
    editBtn.addEventListener('click', editHandler);
    
    let sellBtn = createElement('button', 'Sell', btnCtn, '', ['action-btn', 'sell']);
    sellBtn.addEventListener('click', sellHandler);

    Object.values(inputs)
    .forEach(inp => inp.value = '')
  }

  function editHandler(event){
    let row = event.target.parentNode.parentNode;
   
    let make = row.children[0].textContent;
    let model = row.children[1].textContent;
    let year = row.children[2].textContent;
    let fuel = row.children[3].textContent;
    let orPrice = row.children[4].textContent;
    let sPrice = row.children[5].textContent;

    row.remove();
    
    inputs.make.value = make;
    inputs.model.value = model;
    inputs.year.value = year;
    inputs.fuel.value = fuel;
    inputs.originalCost.value = orPrice;
    inputs.sellingPrice.value = sPrice;
  }

  function sellHandler(event){
    let row = event.target.parentNode.parentNode;

    let make = row.children[0].textContent;
    let model = row.children[1].textContent;
    let year = row.children[2].textContent;
    let orPrice = row.children[4].textContent;
    let sPrice = row.children[5].textContent;
    let dif = Number(sPrice) - Number(orPrice);

    row.remove()

    let li = createElement('li', '', ulSel, '', ['each-list']);
    createElement('span', `${make} ${model}`, li);
    createElement('span', `${year}`, li);
    createElement('span', `${dif}`, li);

    totalPrice+=Number(dif);
    priceContainer.textContent = `${totalPrice.toFixed(2)}`
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

    if (classes) { //подаваме масив
      element.classList.add(...classes);
    }

    if (attributes) {//подаваме обект
      //{'href': 'http', 'src': 'link'}
      for(const key in attributes){
        element.setAttribute(key, attributes[key]);
      } 
    }

    return element;
  }
}
