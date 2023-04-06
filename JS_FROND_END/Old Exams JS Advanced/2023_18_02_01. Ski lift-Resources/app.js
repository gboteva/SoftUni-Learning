window.addEventListener('load', solve);

function solve() {
  const inputs = {
   form: document.querySelector('form'),
   firstName: document.getElementById('first-name'),
   lastName: document.getElementById('last-name'),
   numberOfPeople: document.getElementById('people-count'),
   date: document.getElementById('from-date'),
   days: document.getElementById('days-count')
  }

  const containers = {
   info: document.querySelector('.ticket-info-list'), 
   confirm: document.querySelector('.confirm-ticket'),
   main: document.getElementById('main'),
   body: document.getElementById('body')
  }

  const nextBtn = document.getElementById('next-btn');
  nextBtn.addEventListener('click', submitTicketHandler);

  let currentTicket = {}

  function submitTicketHandler(event){
   event.preventDefault();
   let firstName = inputs.firstName.value;
   let lastName = inputs.lastName.value;
   let people = inputs.numberOfPeople.value;
   let date = inputs.date.value;
   let days = inputs.days.value;

   if (firstName === '' || lastName === '' || people === '' || date === '' || days === ''){
       return
   }

   currentTicket = {firstName, lastName, people, date, days};

   let li = createElement('li', '', containers.info, '', ['ticket']);
   let article = createElement('article', '', li);
   createElement('h3', `Name: ${firstName} ${lastName}`, article);
   createElement('p',`From date: ${date}`, article);
   createElement('p', `For ${days} days`, article);
   createElement('p', `For ${people} people`, article);

   let editBtn = createElement('button', 'Edit', li, '', ['edit-btn']);
   let continueBtn = createElement('button', 'Continue', li, '', ['continue-btn']);

   inputs.form.reset();
   nextBtn.disabled = true;

   editBtn.addEventListener('click', editHandler);
   continueBtn.addEventListener('click', continueHandler);
  }

  function editHandler(event){
   inputs.firstName.value = currentTicket.firstName;
   inputs.lastName.value = currentTicket.lastName;
   inputs.numberOfPeople.value = currentTicket.people;
   inputs.date.value = currentTicket.date;
   inputs.days.value = currentTicket.days;

   nextBtn.disabled = false;
   event.target.parentNode.remove();
  }

  function continueHandler(event){
   const li = event.target.parentNode;
   event.target.previousSibling.remove();
   event.target.remove();

   let confirmBtn = createElement('button', 'Confirm', li, '', ['confirm-btn']);
   let cancelBtn = createElement('button', 'Cancel', li, '', ['cancel-btn']);

   containers.confirm.appendChild(li);
   
   cancelBtn.addEventListener('click', (event) =>{
       event.target.parentNode.remove();
       nextBtn.disabled = false;
   })

   confirmBtn.addEventListener('click', () => {
       containers.main.remove();
       createElement('h1', 'Thank you, have a nice day!',containers.body, 'thank-you');
       let backBtn = createElement('button', 'Back', containers.body, 'back-btn');

       backBtn.addEventListener('click', ()=>{
           window.location.reload(); 
       })
   })
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


    
    
