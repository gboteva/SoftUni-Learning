function solve(){
    const BASE_URL = 'http://localhost:3030/jsonstore/grocery/';
    const inputs = {
        product: document.getElementById('product'),
        count: document.getElementById('count'),
        price: document.getElementById('price')
    }
    const btns = {
        addBtn: document.getElementById('add-product'),
        editBtn: document.getElementById('update-product'),
        loadBtn: document.getElementById('load-product')
    }

    const tableBody = document.getElementById('tbody');
    const form = document.querySelector('form');

    btns.loadBtn.addEventListener('click', loadAllHandler);
    btns.addBtn.addEventListener('click', addProductHandler);
    btns.editBtn.addEventListener('click', editProductHandler);
    let idToEdit;

    let currentProduct = {};

    function addProductHandler(event){
        if (event){
            event.preventDefault()
        }

        let obj = {
            product: inputs.product.value,
            count: inputs.count.value,
            price: inputs.price.value
        }

        let httpHeaders = {
            method: 'POST',
            body: JSON.stringify(obj)
        }

        fetch(BASE_URL, httpHeaders)
        .then(loadAllHandler())
        .catch(err =>console.error(err))

        form.reset();
    }

    function loadAllHandler(event){
       if (event){
        event.preventDefault()
       }

       tableBody.innerHTML = '';

        fetch(BASE_URL)
        .then(response => response.json())
        .then(data => {
            Object.values(data).forEach(obj => {
                let row = createElement('tr', '', tableBody);
                createElement('td', obj.product, row, '', ['name']);
                createElement('td', obj.count, row, '', ['count-product']);
                createElement('td', obj.price, row, '', ['product-price']);
                let btnsCol = createElement('td', '', row, '', ['btn']);
                let updateBtn = createElement('button', 'Update', btnsCol, '', ['update']);
                updateBtn.addEventListener('click', () =>{
                   inputs.product.value = obj.product;
                   inputs.count.value = obj.count;
                   inputs.price.value = obj.price;
                   
                   btns.editBtn.disabled = false;
                   btns.addBtn.disabled = true;
                   
                   idToEdit = obj._id;
                })
                
                let deleteBtn = createElement('button', 'Delete', btnsCol, '', ['delete']);
                deleteBtn.addEventListener('click', () =>{
                   let httpHeaders = {
                    method: 'DELETE'
                   }   
                   fetch(BASE_URL + obj._id, httpHeaders)
                   .then(res => loadAllHandler())
                   .catch(err=>console.error(err))
                })
            })
        })

        
        
    }

    function editProductHandler(event){
        let object = {
            product: inputs.product.value,
            count: inputs.count.value,
            price: inputs.price.value
        }
        let httpHeaders = {
            method: 'PATCH',
            body: JSON.stringify(object)
        }

        fetch(BASE_URL + idToEdit, httpHeaders)
        .then(res => loadAllHandler())
        .catch(err => console.error(err))

        form.reset()
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

solve()