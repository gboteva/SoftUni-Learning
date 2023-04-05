function solve() {
  const form = document.querySelector('form');
   const inputs = {
    firstName: document.getElementById('fname'),
    lastName: document.getElementById('lname'),
    email: document.getElementById('email'),
    date: document.getElementById('birth'),
    position: document.getElementById('position'),
    salary: document.getElementById('salary')
   }
   const tableBody = document.getElementById('tbody');
   const budgetCtn = document.getElementById('sum');

   const addBtn = document.getElementById('add-worker');
   addBtn.addEventListener('click', addWorkerHandler);

  let budget = 0;
  
  function addWorkerHandler(event){
    event.preventDefault();
    let fName = inputs.firstName.value;
    let lName = inputs.lastName.value;
    let email = inputs.email.value;
    let dateOfBirth = inputs.date.value;
    let position = inputs.position.value;
    let salary = inputs.salary.value;

    if (!fName.trim() || !lName.trim() || !email.trim() || !dateOfBirth.trim() || !position.trim() || !salary.trim()){
        return
    }

    let row = createElement('tr', '', tableBody);
    createElement('td', `${fName}`, row);
    createElement('td', `${lName}`, row);
    createElement('td', `${email}`, row);
    createElement('td', `${dateOfBirth}`, row);
    createElement('td', `${position}`, row);
    createElement('td', `${salary}`, row);
    let tdBtn = createElement('td', '', row)
    let firedBtn = createElement('button', 'Fired', tdBtn, '', ['fired']);
    firedBtn.addEventListener('click', removeRecordHandler);

    let editBtn = createElement('button', 'Edit', tdBtn, '', ['edit']);
    editBtn.addEventListener('click', editInfoHandler)

    budget += Number(salary);
    budgetCtn.textContent = `${budget.toFixed(2)}`

    form.reset()
  }

  function editInfoHandler (event){
    let row = event.target.parentNode.parentNode;
    let children = Array.from(row.children);
    inputs.firstName.value = children[0].textContent;
    inputs.lastName.value = children[1].textContent;
    inputs.email.value = children[2].textContent;
    inputs.date.value = children[3].textContent;
    inputs.position.value = children[4].textContent;
    inputs.salary.value = children[5].textContent;
    
    budget -= Number(children[5].textContent);
    budgetCtn.textContent = `${budget.toFixed(2)}`
    row.remove();
  }

  function removeRecordHandler(event){
    let row = event.target.parentNode.parentNode;
    let salary = Array.from(row.children)[5].textContent;
    budget -= Number(salary);
    budgetCtn.textContent = `${budget.toFixed(2)}`
    row.remove();
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