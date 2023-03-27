function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/messenger';
    const textArea = document.getElementById('messages');

    const newMessageInputs = document.querySelectorAll('#controls div input')
    const nameInput = newMessageInputs[0];
    const messageInput = newMessageInputs[1];

    const sendBTN = document.getElementById('submit');
    const refreshBtn = document.getElementById('refresh');

    sendBTN.addEventListener('click', createMessageHandler);
    refreshBtn.addEventListener('click', loadAllMessageHandler);

    async function createMessageHandler(){
        let sender = nameInput.value;
        let message = messageInput.value;
       
        let object = {
            author: sender,
            content: message
          };

        let httpHeader = {
            method: "POST",
            body: JSON.stringify(object)
        }

        await fetch(BASE_URL, httpHeader);
        nameInput.value = '';
        messageInput.value = '';
    }

    async function loadAllMessageHandler(){
        textArea.value = '';
        
        let response = await fetch(BASE_URL);
        let data = await response.json();
        let length = data.length;

        Object.values(data)
        .forEach((data, index) => {
            let content = '';
            if (index === length -1){
                content = `${data.author}: ${data.content}`;
                textArea.value += content;
            }else {
                content = `${data.author}: ${data.content}\n`;
                textArea.value += content;
            }
        });
    }

}

attachEvents();