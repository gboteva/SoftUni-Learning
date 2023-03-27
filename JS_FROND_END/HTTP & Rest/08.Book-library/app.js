function attachEvents() {
  const BASE_URL = "http://localhost:3030/jsonstore/collections/books/";
  const btnLoadAll = document.getElementById("loadBooks");
  const tableBody = document.querySelector("tbody");
  const btnSubmit = document.querySelector("#form button");
  const inputs = document.querySelectorAll("#form input");
  const formHeader = document.querySelector("#form h3");

  btnLoadAll.addEventListener("click", loadAllBookHandler);

  btnSubmit.addEventListener("click", submitBookHandler);

  let editBookID = null;
  
  async function loadAllBookHandler() {
    let response = await fetch(BASE_URL);
    let data = await response.json();

    tableBody.innerHTML = "";

    for (let key in data) {
      let author = data[key].author;
      let title = data[key].title;
      let tr = createElement("tr", "", tableBody);
      tr.id = key;

      createElement("td", title, tr);
      createElement("td", author, tr);

      let tdButtons = createElement("td", "", tr);

      let btnEdit = createElement("button", "Edit", tdButtons);
      btnEdit.addEventListener("click", () =>{
        formHeader.textContent = "EditFORM";
        btnSubmit.textContent = "Save";
        editBookID = key;
        inputs[0].value = title;
        inputs[1].value = author;
      });

      let btnDelete = createElement("button", "Delete", tdButtons);
      btnDelete.addEventListener("click", deleteBookHandler);
    }
  }

  async function submitBookHandler() {
    let title = inputs[0].value;
    let author = inputs[1].value;
    
    let book = {
      author,
      title,
    };

    let httpHeaders = {};

    if (author.trim() && title.trim() && btnSubmit.textContent === "Submit") {
      httpHeaders = {
        method: "POST",
        body: JSON.stringify(book),
      };

      let postResponse = await fetch(BASE_URL, httpHeaders);
      let data = await postResponse.json();

    } else {
      httpHeaders = {
        method: "PUT",
        body: JSON.stringify(book),
      };
      let putResponse = await fetch(BASE_URL + editBookID, httpHeaders);
      let dataPut = putResponse.json();
    }

    loadAllBookHandler();
    inputs[0].value = '';
    inputs[1].value = '';
  }

  function deleteBookHandler(event) {
    let id = event.target.parentElement.parentElement.id;

    let httpHeader = {
      method: "DELETE",
    };

    fetch(BASE_URL + id, httpHeader)
      .then((res) => res.json())
      .then(loadAllBookHandler())
      .catch((err) => {
        console.error(err);
      });
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
      element.classList.add([...classes]);
    }

    if (attributes) {
      for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
      }
    }

    return element;
  }
}

attachEvents();
