window.addEventListener('load', solve);

function solve() {
    const inputs = {
        form: document.querySelector('form'),
        firstName: document.getElementById('first-name'),
        lastName: document.getElementById('last-name'),
        dateIn: document.getElementById('date-in'),
        dateOut: document.getElementById('date-out'),
        people: document.getElementById('people-count')
    }

    const containers = {
        info: document.querySelector('.info-list'),
        confirm: document.querySelector('.confirm-list')
    }

    const header = document.getElementById('verification');

    const nextBtn = document.getElementById('next-btn');
    nextBtn.addEventListener('click', submitInfoHandler);

    let obj = {}

    function submitInfoHandler(event){
        event.preventDefault();

        header.textContent = '';
        header.classList.remove('reservation-cancelled');
        header.classList.remove('reservation-confirmed');

        let firstName = inputs.firstName.value;
        let lastName = inputs.lastName.value;
        let dateIn = inputs.dateIn.value;
        let dateOut = inputs.dateOut.value;
        let people = inputs.people.value;
        obj = {firstName, lastName, dateIn, dateOut, people}

        if (firstName === '' || lastName === '' || dateIn === '' || dateOut === '' || people === '' || 
        new Date(dateIn) >= new Date(dateOut)){
            return
        }

        let li = createElement('li', '', containers.info, '', ['reservation-content']);
        let article = createElement('article', '', li);
        createElement('h3', `Name: ${firstName} ${lastName}`, article);
        createElement('p', `From date: ${dateIn}`, article);
        createElement('p', `To date: ${dateOut}`, article);
        createElement ('p', `For ${people} people`, article);
        let editBtn = createElement('button', 'Edit', li, '', ['edit-btn']);
        let continueBtn = createElement('button', 'Continue', li, '', ['continue-btn']);

        editBtn.addEventListener('click', editHandler);
        continueBtn.addEventListener('click', continueHandler)

        inputs.form.reset();
        nextBtn.disabled = true;
    }   

    function continueHandler(event){
        let li = event.target.parentNode;
        event.target.previousSibling.remove();
        event.target.remove();
        let confBtn = createElement('button', 'Confirm', li, '', ['confirm-btn']);
        let cancelBtn = createElement('button', 'Cancel', li, '', ['cancel-btn']);
        containers.confirm.appendChild(li);

        confBtn.addEventListener('click', (event)=> {
            event.target.parentNode.remove();
            nextBtn.disabled = false;
           header.textContent = 'Confirmed.';
           header.classList.add('reservation-confirmed');
        })

        cancelBtn.addEventListener('click', (event) => {
            event.target.parentNode.remove();
            nextBtn.disabled = false;
          
           header.textContent = 'Cancelled.';
           header.classList.add('reservation-cancelled');
        })

    }

    function editHandler(event){
        inputs.firstName.value = obj.firstName;
        inputs.lastName.value = obj.lastName;
        inputs.dateIn.value = obj.dateIn;
        inputs.dateOut.value = obj.dateOut;
        inputs.people.value = obj.people;

        event.target.parentNode.remove()
        nextBtn.disabled = false;
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



    
    
