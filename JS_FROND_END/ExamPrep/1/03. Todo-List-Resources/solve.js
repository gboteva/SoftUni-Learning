
function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/tasks/';
    const list = document.getElementById('todo-list');
    const input = document.getElementById('title');
    const loadAllBtn = document.getElementById('load-button');
    const addBtn = document.getElementById('add-button');

    loadAllBtn.addEventListener('click', loadAllHandler);
    addBtn.addEventListener('click', addHandler);

    let idToEdit;

    function loadAllHandler (event){
        if (event){
            event.preventDefault();
        }
        
        list.innerHTML = '';

        fetch(BASE_URL)
        .then(response => response.json())
        .then (data => {
            Object.values(data).forEach(obj => {
                const li = createElement('li', '', list);
                createElement('span', obj.name, li);
                let removeBtn = createElement('button', 'Remove', li, obj._id);
                removeBtn.addEventListener('click', deleteHandler);
                let editBtn = createElement('button', 'Edit', li, obj._id);
                editBtn.addEventListener('click', editHandler);
            })
        })
        .catch(err=>console.error(err))

    }

    function addHandler(event){
        if (event){
            event.preventDefault();
        }

        let object = {name: input.value};
        let httpHeaders = {
            method: 'POST', 
            body: JSON.stringify(object)
        }

        fetch(BASE_URL, httpHeaders)
        .then(loadAllHandler()) 
        .catch(err=>console.error(err))
        
    }

    function deleteHandler(event){
        let id = event.target.id
        let httpHeaders = {
            method: 'DELETE'
        }
        fetch(`${BASE_URL}${id}`, httpHeaders)
        .then(loadAllHandler())
        .catch(err => console.error(err))
    }

    function editHandler(event){
        idToEdit = event.target.id;
        let liItem = event.target.parentNode;
        let text = liItem.querySelector('span').textContent;
        liItem.querySelector('span').remove();

        let input = createElement('input', text)
        liItem.prepend(input);
        event.target.remove();
        let submitBtn = createElement('button', 'Submit', liItem);
        submitBtn.addEventListener('click', submitHandler);
    }

    function submitHandler(event){
        let obj = {
            name: event.target.parentNode.querySelector('input').value,
            id_:idToEdit
        }
        let httpHeaders = {
            method: 'PATCH',
            body: JSON.stringify(obj)
        }

        fetch(BASE_URL + idToEdit, httpHeaders)
        .then(loadAllHandler())
        .catch(err => console.error(err))
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

attachEvents();
