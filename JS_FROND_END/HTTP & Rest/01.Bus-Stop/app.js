function getInfo() {
  const BASE_URL = "http://localhost:3030/jsonstore/bus/businfo/";
  const input = document.getElementById("stopId");
  const busContainer = document.getElementById('buses');
  const stopNameContainer = document.getElementById('stopName');
  
  const busId = input.value;
  busContainer.innerHTML = '';
  fetch(BASE_URL + busId)
  .then(res=>res.json())
  .then((busInfo) => {
    
    const {name, buses} = busInfo;
    stopNameContainer.textContent = name;
    
    for(const busId in buses){
        const li = document.createElement('li');
        li.textContent = `Bus ${busId} arrives in ${buses[busId]} minutes`;
        busContainer.appendChild(li)
    }
  })
  .catch((err) => {
    stopNameContainer.textContent = "Error"
  })
}
