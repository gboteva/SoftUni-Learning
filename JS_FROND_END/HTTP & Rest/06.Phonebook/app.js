function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/phonebook';
    const ul = document.getElementById('phonebook');
    const btnLoad = document.getElementById('btnLoad');
    const btnCreate = document.getElementById('btnCreate')
    const nameInput = document.getElementById('person');
    const phoneInput = document.getElementById('phone');

    btnLoad.addEventListener('click', loadContacts);

    btnCreate.addEventListener('click', postContact);
    
    function loadContacts(){
        ul.innerHTML = '';
        
        fetch(BASE_URL)
        .then((response => response.json()))
        .then(data =>{
            Object.keys(data)
            .forEach(key=>{
                const liItem = document.createElement('li');
                liItem.id = data[key]._id;
                liItem.textContent = `${data[key].person}: ${data[key].phone}`;
                const button = document.createElement('button');
                button.textContent = 'Delete';
                button.addEventListener('click', deleteContact);
                liItem.appendChild(button);
                ul.appendChild(liItem);
        })})
        .catch(err => console.error(err.message));
    }

    function deleteContact(event){
        
        let currentBtn = event.target;
        let id = currentBtn.parentElement.id;

        let httpHeaders = {
            method: "DELETE",
        }

        fetch(`${BASE_URL}/${id}`, httpHeaders)
            .then(response => response.json())
            .then(loadContacts())
            .catch(err=>console.error(err.message))
    }

    function postContact(){
        let name = nameInput.value;
        let number = phoneInput.value;

        let contact = {
            "person": name,
            "phone": number
        }

        let httpHeaders = {
            method: "POST",
            body: JSON.stringify(contact)
        }

        fetch(BASE_URL, httpHeaders)
        .then(response => response.json())
        .then(() => {
            phoneInput.value = '';
            nameInput.value = '';
            loadContacts();
        })
        .catch(err=>{
            console.error(err.message);
        })
        
    }
}

attachEvents();