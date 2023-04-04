function solution() {
    const addBtn = document.querySelector('button');
    const inputField = document.querySelector('input');
    const lists = document.querySelectorAll('.card > ul');
    const listOfGiftsUl = lists[0];
    const sendGiftUl = lists[1];
    const discardedUl = lists[2];

    addBtn.addEventListener('click', addGiftHandler);


    function addGiftHandler(){
        let gift = inputField.value;
        
        let li = document.createElement('li');
        li.classList.add('gift');
        li.textContent = gift;
        
        let sendBtn = document.createElement('button');
        sendBtn.textContent = 'Send';
        sendBtn.id = 'sendButton';

        sendBtn.addEventListener('click', moveGiftHandler)

        let discardBtn = document.createElement('button');
        discardBtn.textContent = 'Discard';
        discardBtn.id = 'discardButton';
        discardBtn.addEventListener('click', discardHandler);
        
        li.appendChild(sendBtn);
        li.appendChild(discardBtn);
        listOfGiftsUl.appendChild(li);

        let sorted = Array.from(listOfGiftsUl.querySelectorAll('li'))
            .sort((a, b) =>{
             return a.textContent.localeCompare(b.textContent)
             })

        listOfGiftsUl.innerHTML = '';
        sorted.forEach(item => listOfGiftsUl.appendChild(item))

        inputField.value = '';
    }

    function moveGiftHandler(event){
        let li = event.target.parentNode;
        sendGiftUl.appendChild(li)
        li.children[0].remove();
        li.children[0].remove();
    }

    function discardHandler(event){
        let li = event.target.parentNode;
        discardedUl.appendChild(li)
        li.children[0].remove();
        li.children[0].remove();
    }
}