function solve() {
    let taskInput = document.getElementById('task');
    let descTextarea = document.getElementById('description');
    let dateInput = document.getElementById('date');
    const btnAdd = document.getElementById('add');
    const orangeSection = document.querySelector('.orange').parentNode.parentNode;
    const yellowDiv = document.getElementById('in-progress');
    const greenSection = document.querySelector('.green').parentNode.parentNode;

    btnAdd.addEventListener('click', addTaskHandler);

    function addTaskHandler(event){
        event.preventDefault();
       if (taskInput.value === '' || descTextarea.value === '' || dateInput.value === ''){
        return
       }
       const orangeDiv = Array.from(orangeSection.children)[1];
       let article = createElement('article', '', orangeDiv);
       createElement('h3', taskInput.value, article);
       createElement('p', `Description: ${descTextarea.value}`, article);
       createElement('p', `Due Date: ${dateInput.value}`, article);

       let btnsContainer = createElement('div', '', article, '', ['flex']);
       let startBtn = createElement('button', 'Start', btnsContainer, '', ['green']);
       startBtn.addEventListener('click', inProgressHandler);

       let deleteBtn = createElement('button', 'Delete', btnsContainer, '', ['red']);
       deleteBtn.addEventListener('click', deleteTaskHandler);

       taskInput.value = ''
       descTextarea.value = '' 
       dateInput.value = ''
    }

    function inProgressHandler(event){
        let article = event.target.parentNode.parentNode;
        yellowDiv.appendChild(article);
        event.target.remove();
        article.children[3].children[0].remove();

        let deleteBtn = createElement('button', 'Delete', article.children[3], '',['red']);
        deleteBtn.addEventListener('click', deleteTaskHandler)
        
        let finishBtn = createElement('button', 'Finish', article.children[3], '',['orange'])
        finishBtn.addEventListener('click', completeTaskHandler);

    }

    function deleteTaskHandler(event){
        event.target.parentNode.parentNode.remove()
    }

    function completeTaskHandler(event){
        let article =  event.target.parentNode.parentNode;
        const greenDiv = Array.from(greenSection.children)[1];
        greenDiv.appendChild(article);
        greenDiv.children[0].children[3].remove();
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