function lockedProfile() {
   const BASE_URL = 'http://localhost:3030/jsonstore/advanced/profiles/';
   
   const container = document.getElementById('main');
   
   let countUsers = 0;
   container.innerHTML = '';
   fetch(BASE_URL)
   .then(res=> res.json())
   .then(data => {
    Object.values(data).forEach(obj => {
      countUsers++;
      let div = createElement('div', '', container, '', ['profile']);
      createElement('img', '', div, '', ['userIcon'], {'src': './iconProfile2.png'});
      createElement('label', 'Lock', div);
      createElement('input', '', div, '', '', {
        'type': 'radio',
        'name': `user${countUsers}Locked`,
        'value': 'lock',
        'checked': true
      })
      createElement('label', 'Unlock', div);
      createElement('input', '', div, '', '', {
        'type': 'radio',
        'name': `user${countUsers}Locked`,
        'value': 'unlock',
      })
      createElement('br', '', div);
      createElement('hr', '', div);

      createElement('label', 'Username', div);
      createElement('input', '', div, '', '', {
        'type': 'text',
        'name': `user${countUsers}Username`,
        'value': `${obj.username}`,
        'disabled': true,
        'readonly': true
      })

      let hiddenDiv = createElement('div', '', div, `user${countUsers}HiddenFields`);
      hiddenDiv.style.display = 'none';
      createElement('hr', '', hiddenDiv);
      createElement('label', 'Email:', hiddenDiv);
      createElement('input', '', hiddenDiv, '', '', {
        'type': 'email',
        'name': `user${countUsers}Email`,
        'value': `${obj.email}`,
        'disabled': true,
        'readonly': true
      })
      createElement('label', 'Age:', hiddenDiv);
      createElement('input', '', hiddenDiv, '', '', {
        'type': 'email',
        'name': `user${countUsers}Age`,
        'value': `${obj.age}`,
        'disabled': true,
        'readonly': true
      })

      let showBtn = createElement('button', 'Show more', div);
      showBtn.addEventListener('click', showMoreInfoHandler);
    })
   })

   function showMoreInfoHandler (event){
    let children = Array.from(event.target.parentNode.children);
    let inputUnlock = children[4];
    let hiddenDiv = children[children.length - 2];

    if (event.target.textContent === 'Show more' && inputUnlock.checked){
        hiddenDiv.style.display = 'block'
        event.target.textContent = 'Hide it';
    }else if (event.target.textContent === 'Hide it' && inputUnlock.checked){
      hiddenDiv.style.display = 'none';
      event.target.textContent = 'Show more';
    }

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