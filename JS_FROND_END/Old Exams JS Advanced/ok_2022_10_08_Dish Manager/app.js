window.addEventListener("load", solve);

function solve() {
  const inputs = {
    firstName: document.getElementById('first-name'),
    lastName: document.getElementById('last-name'),
    age: document.getElementById('age'),
    gender: document.getElementById('genderSelect'),
    description: document.getElementById('task')
  }

  const containers = {
    progress: document.getElementById('in-progress'),
    finished: document.getElementById('finished')
  }

  const counterContainer = document.getElementById('progress-count');
  const btnSubmit = document.getElementById('form-btn');
  const btnClear = document.getElementById('clear-btn');

  let counter = 0;

  btnSubmit.addEventListener('click', submitHandler);
  btnClear.addEventListener('click', () => {
    containers.finished.innerHTML = '';
  });

  function submitHandler(event){
    event.preventDefault();
    let firstName = inputs.firstName.value;
    let lastName = inputs.lastName.value;
    let age = inputs.age.value;
    let gender = inputs.gender.value;
    let description = inputs.description.value;

    if (firstName === '' || lastName === '' || age === '' || gender === '' || description === ''){
      return
    }

    let li = createElement('li', '', containers.progress, '', ['each-line']);
    let article = createElement('article', '', li);
    createElement('h4', `${firstName} ${lastName}`, article);
    createElement('p', `${gender}, ${age}`, article);
    createElement('p', `Dish description: ${description}`, article);
    let editBtn = createElement('button', 'Edit', li, '', ['edit-btn']);
    let completeBtn = createElement('button', 'Mark as complete', li, '', ['complete-btn']);

    editBtn.addEventListener('click', editHandler);
    completeBtn.addEventListener('click', completeHandler);
    
    inputs.firstName.value = '';
    inputs.lastName.value = '';
    inputs.age.value = '';
    inputs.gender.value = '';
    inputs.description.value = '';
    
    counter++;
    counterContainer.textContent = counter;

  }

  function editHandler(event){
    let li = event.target.parentNode;
    let article = li.children[0];

    let firstName = article.children[0].textContent.split(' ')[0];
    let lastName = article.children[0].textContent.split(' ')[1];
    let age = article.children[1].textContent.split(', ')[1];
    let gender = article.children[1].textContent.split(', ')[0];
    let description = article.children[2].textContent.substring(18);

    inputs.firstName.value = firstName;
    inputs.lastName.value = lastName;
    inputs.age.value = age;
    inputs.gender.value = gender;
    inputs.description.value = description;

    li.remove();

    counter--;
    counterContainer.textContent = counter;
  }

  function completeHandler(event){
    let li = event.target.parentNode;
    event.target.previousSibling.remove();
    event.target.remove();
    containers.finished.appendChild(li);

    counter--;
    counterContainer.textContent = counter;
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