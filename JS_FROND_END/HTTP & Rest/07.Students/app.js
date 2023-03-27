function attachEvents() {
  const BASE_URL = 'http://localhost:3030/jsonstore/collections/students';
  const inputs = document.querySelectorAll('.inputs>input');
  const btn = document.getElementById('submit');

  load()
  const tableBody = document.querySelector('#results tbody')
  btn.addEventListener('click', addStudent);

  async function load(){
    let response = await fetch(BASE_URL);
    let data = await response.json();
    for (let key in data){
      let fName = data[key].firstName;
      let lastName = data[key].lastName;
      let facultyNumber = data[key].facultyNumber;
      let grade = data[key].grade;
      
      let tr = createElement('tr', '', tableBody);
      createElement('td', fName, tr);
      createElement('td', lastName, tr);
      createElement('td', facultyNumber, tr);
      createElement('td', grade, tr);
    }
  }

  function addStudent(){
    let firstName = inputs[0].value;
    let lastName = inputs[1].value;
    let facultyNumber = inputs[2].value;
    let grade = inputs[3].value;

    let student = {
      firstName, lastName, facultyNumber, grade
        // firstName: firstName,
        // lastName: lastName,
        // facultyNumber: facultyNumber,
        // grade: grade
    }

    let httpHeader = {
      method: 'POST',
      body: JSON.stringify(student)
    }

    fetch(BASE_URL, httpHeader)
    .then(res=>res.json)
    .then(load())
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

    if (classes) {
      element.classList.add([...classes]);
    }

    if (attributes) {
      for(const key in attributes){
        element.setAttribute(key, attributes[key]);
      } 
    }

    return element;
  }
}

attachEvents();