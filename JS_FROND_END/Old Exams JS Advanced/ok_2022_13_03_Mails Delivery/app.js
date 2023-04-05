function solve() {
  const inputs = {
    form: document.querySelector("form"),
    name: document.getElementById("recipientName"),
    title: document.getElementById("title"),
    text: document.getElementById("message"),
  };
  const buttons = {
    btnAdd: document.getElementById("add"),
    resetBtn: document.getElementById("reset"),
  };

  const ulMails = document.getElementById("list");

  buttons.btnAdd.addEventListener("click", addHandler);
 
  buttons.resetBtn.addEventListener("click", (event) => {
    event.preventDefault();
    inputs.name.value = "";
    inputs.title.value = "";
    inputs.text.value = "";
  });

  function addHandler(event) {
    event.preventDefault();

    let name = inputs.name.value;
    let title = inputs.title.value;
    let text = inputs.text.value;
    if (name === "" || title === "" || text === "") {
      return;
    }


    let liItem = createElement("li", "", ulMails);
    createElement("h4", `Title: ${title}`, liItem);
    createElement("h4", `Recipient Name: ${name}`, liItem);
    createElement("span", `${text}`, liItem);
    let btnCtn = createElement("div", "", liItem, "list-action");
    let sendBtn = createElement("button", "Send", btnCtn, "send", "", {
      type: "submit",
    });
    sendBtn.addEventListener("click", sendMailHandler);

    let deleteBtn = createElement("button", "Delete", btnCtn, "delete", "", {
      type: "submit",
    });
    deleteBtn.addEventListener("click", deleteHandler);

    inputs.name.value = "";
    inputs.title.value = "";
    inputs.text.value = "";
  }

  function sendMailHandler(event) {
    event.preventDefault();
    const liItem = event.target.parentNode.parentNode;
    let title = liItem.children[0].textContent.split(": ")[1];
    let name = liItem.children[1].textContent.split(": ")[1];
    event.target.parentNode.parentNode.remove();

    const sendUl = document.querySelector(".sent-list");
    let li = createElement("li", "", sendUl);
    createElement("span", `To: ${name}`, li);
    createElement("span", `Title: ${title}`, li);
    let btnCtn = createElement("div", "", li, "", ["btn"]);
    let deleteBtn = createElement("button", "Delete", btnCtn, "", ["delete"], {
      type: "submit",
    });

    deleteBtn.addEventListener('click', deleteHandler)
  }

  function deleteHandler(event) {
    event.preventDefault();
    const liItem = event.target.parentNode.parentNode;
    let title = liItem.children[0].textContent.split(": ")[1];
    let name = liItem.children[1].textContent.split(": ")[1];

    const parent = document.querySelector('.delete-list');

    let li = createElement("li", "", parent);
    createElement("span", `To: ${name}`, li);
    createElement("span", `Title: ${title}`, li);

    liItem.remove()
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
      //подаваме масив
      element.classList.add(...classes);
    }

    if (attributes) {
      //подаваме обект
      //{'href': 'http', 'src': 'link'}
      for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
      }
    }

    return element;
  }
}
solve();
