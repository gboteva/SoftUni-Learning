function solve() {
  document.querySelector("#searchBtn").addEventListener("click", onClick);

  function onClick() {
    clear();
    let searchedWord = document.getElementById('searchField').value;
    let rows = document.querySelectorAll('tbody tr')
    document.getElementById('searchField').value = '';
    for(let row of rows){
      let textContent = row.textContent;
      if (textContent.includes(searchedWord) && searchedWord !==''){
        row.classList.add('select')
      }
    }
  }

  function clear() {
    let selected = Array.from(document.getElementsByClassName("select"));
    for (let select of selected) {
      select.classList.remove("select");
    }
  }
}

//...........old solution......
// let searchedWord = document
// .getElementById("searchField")
// .value;

// let tableData = Array.from(document.querySelectorAll("tbody tr td"));

// for (let data of tableData) {
// let text = data.textContent;

// if (text.includes(searchedWord) && searchedWord.trim() !== "") {
//   data.parentElement.classList.add("select");
//   console.log(data.parentElement);
// }
// }
// document.getElementById("searchField").value = "";
