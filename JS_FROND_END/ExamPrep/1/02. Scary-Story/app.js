window.addEventListener("load", solve);

function solve() {
    const inputs = {
      firstName: document.getElementById('first-name'),
      lastName: document.getElementById('last-name'),
      age:document.getElementById('age'),
      title: document.getElementById('story-title'),
      genre: document.getElementById('genre'),
      story: document.getElementById('story')
    }

    const form = document.querySelector('form');
    const preview = document.getElementById('preview-list');
    const main = document.getElementById('main');
  

    const publishBtn = document.getElementById('form-btn');
    publishBtn.addEventListener('click', previewHandler);

    let currentStory = {};

    function previewHandler(){
      const {firstName, lastName, age, title, genre,story} = inputs;
      
      let nonEmptyFields = Object.values(inputs)
      .every(input => input.value !== '');
      if (!nonEmptyFields){
        return;
      }

      currentStory = {
        firstName: firstName.value,
        lastName:lastName.value,
        age:age.value,
        title:title.value,
        genre: genre.value,
        story:story.value
      }

      const listItem = createElement('li', '', preview, '', ['story-info']);
      const article = createElement('article', '', listItem);
      createElement('h4', `Name: ${firstName.value} ${lastName.value}`, article);
      createElement('p', `Age: ${age.value}`, article );
      createElement('p', `Title: ${title.value}`, article);
      createElement('p', `Genre: ${genre.value}`, article);
      createElement('p', `${story.value}`, article);

      const saveBtn = createElement('button', 'Save Story', listItem, '', ['save-btn']);
      saveBtn.addEventListener('click', saveStory);

      const editBtn = createElement('button', 'Edit Story', listItem, '', ['edit-btn']);
      editBtn.addEventListener('click', loadInformation)

      const deleteBtn = createElement('button', 'Delete Story', listItem, '', ['delete-btn']);
      deleteBtn.addEventListener('click', deleteHandler);

      form.reset();
      publishBtn.disabled = true;
    }

    function loadInformation(event){
      for(let key in inputs){
        inputs[key].value = currentStory[key]
      }

      event.target.parentNode.remove();

      publishBtn.disabled = false;
     
    }

    function saveStory(){
      main.innerHTML = '';
      createElement('h1', 'Your scary story is saved!', main)
    }

    function deleteHandler(event){
      event.target.parentNode.remove();
      publishBtn.disabled = false;
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
