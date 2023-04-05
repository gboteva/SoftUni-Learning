function solution() {
  const BASE_URL = "http://localhost:3030/jsonstore/advanced/articles/list/";
  const CONTENT_URL =
    "http://localhost:3030/jsonstore/advanced/articles/details/";
  const container = document.getElementById("main");

  fetch(BASE_URL)
    .then((res) => res.json())
    .then((data) => {
      Object.values(data).forEach((obj) => {
        let div = createElement("div", "", container, "", ["accordion"]);
        let divHead = createElement("div", "", div, "", ["head"]);
        createElement("span", obj.title, divHead);
        let moreBtn = createElement("button", "More", divHead, obj._id, [
          "button",
        ]);
        moreBtn.addEventListener("click", showMoreHandler);

        let extraDiv = createElement("div", "", div, "", ["extra"]);
        createElement("p", '', extraDiv);
      });
    })
    .catch((err) => console.error(err));

  function showMoreHandler(event) {
    let id = event.target.id;
    let divAccordion = event.target.parentNode.parentNode;
    let extraDiv = divAccordion.querySelector('.extra');
    let p = divAccordion.querySelector('.extra > p');

    if (event.target.textContent === "More"){
        fetch(CONTENT_URL + id)
        .then((res) => res.json())
        .then((data) => {
            p.textContent = data.content;
            extraDiv.style.display = 'block';
            event.target.textContent = "Less"
        })
      .catch((err) => console.error(err));
       
    }  else {
        extraDiv.style.display = 'none';
        event.target.textContent = "More"
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

solution();
