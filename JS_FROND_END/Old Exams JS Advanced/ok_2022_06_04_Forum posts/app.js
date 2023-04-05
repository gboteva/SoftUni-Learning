window.addEventListener("load", solve);

function solve() {
  const inputs = {
    form: document.querySelector('form.newPostContent'),
    title: document.getElementById('post-title'),
    category: document.getElementById('post-category'),
    content: document.getElementById('post-content')
  }

  const buttons = {
    publish: document.getElementById('publish-btn'),
    clear: document.getElementById('clear-btn')
  }

  const containers = {
    review: document.getElementById('review-list'),
    published: document.getElementById('published-list')
  }

  buttons.publish.addEventListener('click', reviewPostHandler);
  buttons.clear.addEventListener('click', clearPostHandler);

  let currentPost = {};

  function reviewPostHandler(e){
    e.preventDefault();
    let title = inputs.title.value;
    let category = inputs.category.value;
    let content = inputs.content.value;
    
    if (title === '' || category === '' || content === ''){
      return
    }

    currentPost = {title, category, content};

    let li = createElement('li', '', containers.review, '', ['rpost']);
    let article = createElement('article', '', li);
    createElement('h4', title, article);
    createElement('p', `Category: ${category}`, article);
    createElement('p', `Content: ${content}`, article);
    let editBtn = createElement('button', 'Edit', li, '', ['action-btn', 'edit'])
    editBtn.addEventListener('click', editHandler);
    
    let approveBtn = createElement('button', 'Approve', li, '', ['action-btn', 'approve'])
    approveBtn.addEventListener('click', approveHandler);

    inputs.form.reset()
  }

  function editHandler(event){
    inputs.title.value = currentPost.title;
    inputs.category.value = currentPost.category;
    inputs.content.value = currentPost.content;
    event.target.parentNode.remove()
  }

  function approveHandler(event){
    let li = event.target.parentNode
    li.children[1].remove();
   li.children[1].remove();
    containers.published.appendChild(li)
  }

  function clearPostHandler(e){
    containers.published.innerHTML = '';
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
