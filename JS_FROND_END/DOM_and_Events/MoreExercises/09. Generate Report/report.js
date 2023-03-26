function generateReport() {
  const outputArea = document.getElementById("output");
  const tableHeaderInputs = Array.from(document.querySelectorAll("th>input"));
  const tableRows = Array.from(document.querySelectorAll("tbody>tr"));

  let indexesOfCheckedInputs = {}
  for (let input of tableHeaderInputs){
    if (input.checked) {
        indexesOfCheckedInputs[input.parentElement.textContent.trim().toLowerCase()] = tableHeaderInputs.indexOf(input)
    }
  }
  
  let objects = [];
 
  for(let row of tableRows){
    let object = {};
 
    for (let nameOfHeader in indexesOfCheckedInputs){
        let index = indexesOfCheckedInputs[nameOfHeader];
        let property = row.querySelectorAll('td')[index].textContent.trim();
        object[nameOfHeader] = property;
    }
    
    objects.push(object);
  }
  
    outputArea.value = JSON.stringify(objects);
}
