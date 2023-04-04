window.addEventListener('load', solve);

function solve() {
   const form = document.querySelector('form');
   const inputs = {
        modelInput: document.getElementById('model'),
        yearInput: document.getElementById('year'),
        descriptionInput: document.getElementById('description'),
        priceInput: document.getElementById('price')
   }

   const tableBody = document.getElementById('furniture-list');
   const totalPriceContainer = document.querySelector('.total-price');

   const addBtn = document.getElementById('add');
   addBtn.addEventListener('click', addInfoHandler);

   let totalPrice = 0;

   function addInfoHandler(event){
    event.preventDefault()
    let model = inputs.modelInput.value;
    let year = Number(inputs.yearInput.value);
    let desc = inputs.descriptionInput.value;
    let price = Number (inputs.priceInput.value);

    if (model === '' || desc === '' || year <= 0 || price <= 0){
        return //or !== 0
    }

    let row = createElement('tr', '', tableBody, '', ['info']);
    createElement('td', `${model}`, row);
    createElement('td', `${price.toFixed(2)}`, row);
    let btnsCtn = createElement('td', '', row);
    let btnMore = createElement('button', 'More Info', btnsCtn, '', ['moreBtn'])
    btnMore.addEventListener('click', showHiddenInfoHandler);
    
    let btnBuy = createElement('button', 'Buy it', btnsCtn, '', ['buyBtn']);
    btnBuy.addEventListener('click', buyHandler);

    let hiddenRow = createElement('tr', '', tableBody, '', ['hide']);
    createElement('td', `Year: ${year}`, hiddenRow);
    createElement('td', `Description: ${desc}`, hiddenRow, '', '', {'colspan': '3'} );


    form.reset()
   }

   function showHiddenInfoHandler(event){
    let hideRow = event.target.parentNode.parentNode.nextSibling;
    
    if (event.target.textContent === 'More Info'){
        event.target.textContent = 'Less Info';
        hideRow.style.display = 'contents'
    }else {
        event.target.textContent = 'More Info';
        hideRow.style.display = 'none'
    }
    
    
   }

   function buyHandler(event){
    let currentRow = event.target.parentNode.parentNode;
    let price = Number(Array.from(currentRow.children)[1].textContent);

    currentRow.nextSibling.remove();
    currentRow.remove();

    totalPrice += price;
    totalPriceContainer.textContent = totalPrice.toFixed(2)
    
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
