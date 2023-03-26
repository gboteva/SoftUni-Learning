function attachEventsListeners() {
  const button = document.getElementById("convert").addEventListener("click", convert);

  function convert() {
    const inputDistance = Number(document.getElementById("inputDistance").value);
    const output = document.getElementById("outputUnits").value;

    const value = document.getElementById("inputUnits").value;
    const outputField = document.getElementById('outputDistance');
    
    let meters = getMeters(value);
    
    // document.getElementById('outputDistance').disabled = false;

    switch (output) {
        case "km":
            outputField.value = meters / 1000;
          break;
        case "m":
            outputField.value = meters;
          break;
        case "cm":
            outputField.value = meters * 100;
          break;
        case "mm":
            outputField.value = meters * 1000;
          break;
        case "mi":
            outputField.value = meters / 1609.34; //1mi = 1609.344m
          break;
        case "yrd":
            outputField.value = meters / 0.9144; //1yd = 0.9144m
          break;
        case "ft":
            outputField.value = meters / 0.3048; //1ft = 0.3048m
          break;
        case "in":
            outputField.value = meters / 0.0254; //1″ = 0.0254m
          break;
      }
    
    function getMeters(value){
        let meters = 0;
        switch (value) {
            case "km":
              meters = inputDistance * 1000;
              break;
            case "m":
              meters = inputDistance
              break;
            case "cm":
              meters = inputDistance * 0.01;
              break;
            case "mm":
              meters = inputDistance * 0.001;
              break;
            case "mi":
              meters = inputDistance * 1609.34
              break;
            case "yrd":
              meters = inputDistance * 0.9144
              break;
            case "ft":
              meters = inputDistance * 0.3048
              break;
            case "in":
              meters = inputDistance * 0.0254;
              break;
          }
          return meters;
    } 
    
  }
}
