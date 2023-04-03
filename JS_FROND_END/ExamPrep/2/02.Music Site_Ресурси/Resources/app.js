window.addEventListener('load', solve);

function solve() {
   const inputs = {
    genre: document.getElementById('genre'),
    name: document.getElementById('name'),
    author: document.getElementById('author'),
    date: document.getElementById('date')
   }

   const songContainer = document.querySelector('.all-hits-container');
   const savedSongContainer = document.querySelector('.saved-container');
   const likeContainer = document.querySelector('.likes> p');
   const form = document.querySelector('form');

   const addBtn = document.getElementById('add-btn');
   addBtn.addEventListener('click',addSongHandler);

   let totalLikes = 0;

   function addSongHandler(event){
    if (event){
        event.preventDefault()
    }

    let emptyFields = Object.values(inputs)
    .filter(inp => 
        inp.value === '')

    if (emptyFields.length !== 0){
        return
    }

    const {genre, name, author, date} = inputs;
    let div = createElement('div', '', songContainer, '', ['hits-info'])
    createElement('img', '', div, '', '', {src: './static/img/img.png'});
    createElement('h2', `Genre: ${genre.value}`, div);
    createElement ('h2', `Name: ${name.value}`, div);
    createElement('h2', `Author: ${author.value}`, div);
    createElement('h3', `Date: ${date.value}`, div);
    let saveBtn = createElement('button', 'Save song', div, '', ['save-btn']);
    saveBtn.addEventListener('click', () => {
        savedSongContainer.appendChild(div);
        saveBtn.remove();
        likeBtn.remove()
    })
    
    let likeBtn = createElement('button', 'Like song', div, '', ['like-btn']);
    likeBtn.addEventListener('click', () => {
        totalLikes++;
        likeContainer.textContent = `Total Likes: ${totalLikes}`;
        likeBtn.disabled = true;
    })

    let deleteBtn = createElement('button', 'Delete', div, '', ['delete-btn']);
    deleteBtn.addEventListener('click', deleteHandler);

    form.reset()
   }

   function deleteHandler(event){
    event.target.parentNode.remove()
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