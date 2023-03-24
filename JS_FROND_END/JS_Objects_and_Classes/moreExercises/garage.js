function garage(array) {
  let garages = {};

  for (let current of array) {
    let numberOfGarage = current.split(" - ")[0];
    let infoAboutTheCar = current.split(" - ")[1].split(", ");

    if (!garages.hasOwnProperty(numberOfGarage)) {
      garages[numberOfGarage] = [];
    }

    garages[numberOfGarage].push(infoAboutTheCar);
  }

  for (let number in garages) {
    console.log(`Garage № ${number}`);

    let cars = garages[number];

    for (let car of cars) {
        
     let info = []
     
      for (let prop of car) {
        //color: blue, fuel type: diesel
        let carProperties = prop.split(": ");
        let type = carProperties[0];
        let value = carProperties[1];
        info.push(`${type} - ${value}`) 
      }
      console.log(`--- ${info.join(', ')}`);
    }
  }
}

garage(['1 - color: green, fuel type: petrol',
'1 - color: dark red, manufacture: WV',
'2 - fuel type: diesel',
'3 - color: dark blue, fuel type: petrol']
);
