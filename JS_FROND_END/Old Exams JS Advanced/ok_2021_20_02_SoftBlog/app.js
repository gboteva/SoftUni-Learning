function solve(){
   const authorInput = document.getElementById('creator');
   const titleInput = document.getElementById('title');
   const categoryInput = document.getElementById('category');
   const contentArea = document.getElementById('content');
   const orderedListArchive = document.querySelector('.archive-section ol');
   const sectionPosts = document.querySelector('.site-content main section')

   const btnCreate = document.querySelector('.create');
   btnCreate.addEventListener('click', createArticleHandler);

   function createArticleHandler(event){
      event.preventDefault();
      if (authorInput.value === '' || titleInput.value === '' || categoryInput.value === ''
      || contentArea.value === ''){
         return;
      }

      let article = createElement('article', '', sectionPosts);
      createElement('h1', titleInput.value, article);
      let categoryP =  createElement('p', 'Category: ', article);
      createElement('strong', `${categoryInput.value}`, categoryP);
      let authorP =  createElement('p', 'Creator: ', article);
      createElement('strong', `${authorInput.value}`, authorP);

      createElement('p',contentArea.value, article);
     
      let divBtns = createElement('div', '', article,'', ['buttons']);
      let deleteBtn = createElement('button', 'Delete', divBtns, '', ['btn', 'delete']);
      deleteBtn.addEventListener('click', deleteHandler);

      let archiveBtn = createElement('button', 'Archive', divBtns, '', ['btn', 'archive']);
      archiveBtn.addEventListener('click', archiveHandler);
      
     document.querySelector('form').reset();

   }

   function deleteHandler(event){
      event.target.parentNode.parentNode.remove();
   }

   function archiveHandler(event){
      let article = event.target.parentNode.parentNode;
      let title = article.children[0].textContent;
      createElement('li', title, orderedListArchive);

      let sorted = Array.from(orderedListArchive.children).sort((a,b) => {
         return a.textContent.localeCompare(b.textContent);
      })


      orderedListArchive.innerHTML = '';
      sorted.forEach(li => orderedListArchive.appendChild(li))
      article.remove();
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