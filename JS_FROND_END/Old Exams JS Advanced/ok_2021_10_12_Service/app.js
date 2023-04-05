window.addEventListener("load", solve);

function solve() {
  const inputs = {
    form: document.querySelector("form"),
    select: document.getElementById("type-product"),
    descArea: document.getElementById("description"),
    name: document.getElementById("client-name"),
    phone: document.getElementById("client-phone"),
  };

  const other = {
    submitBtn: document.querySelector("form > button"),
    receiveCtn: document.getElementById("received-orders"),
    completeCtn: document.getElementById("completed-orders"),
    clearBtn: document.querySelector(".clear-btn"),
  };

  other.submitBtn.addEventListener("click", submitHandler);
  other.clearBtn.addEventListener("click", clearHandler);

  function submitHandler(event) {
    event.preventDefault();

    let desc = inputs.descArea.value;
    let clientName = inputs.name.value;
    let clientPhone = inputs.phone.value;
    let typeOfDevice = inputs.select.value;

    if (desc.trim() === "" || clientName.trim() === "" || clientPhone.trim() === "") {
      return;
    }

    let div = createElement("div", "", other.receiveCtn, "", ["container"]);
    createElement("h2", `Product type for repair: ${typeOfDevice}`, div);
    createElement("h3",`Client information: ${clientName}, ${clientPhone}`,div);
    createElement("h4", `Description of the problem: ${desc}`, div);

    let btnStart = createElement("button", "Start repair", div, "", [
      "start-btn",
    ]);
    btnStart.addEventListener("click", () => {
      btnStart.disabled = true;
      finishBtn.disabled = false;
    });

    let finishBtn = createElement("button", "Finish repair", div, "", [
      "finish-btn",
    ]);

    finishBtn.disabled = true;
    finishBtn.addEventListener("click", completeHandler);

    inputs.form.reset();
  }

  function completeHandler(event) {
    let div = event.target.parentNode;
    Array.from(div.children)[4].remove();
    Array.from(div.children)[3].remove();
    other.completeCtn.appendChild(div);
  }

  function clearHandler() {
    other.completeCtn.innerText = '';
    createElement('h2', 'Completed orders:', other.completeCtn);
    createElement('img', '', other.completeCtn, '', '', {'src': './style/img/completed-order.png'});
    createElement('button', 'Clear', other.completeCtn, '', ['clear-btn']);
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
