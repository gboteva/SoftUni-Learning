function storeProvision(currentStoke, orderedStoke) {
  let marketPlace = {};

  for (let i = 0; i < currentStoke.length; i += 2) {
    let productName = currentStoke[i];
   
    let quantity = Number(currentStoke[i + 1]);
    
    marketPlace[productName] = quantity;

  }

  for (let i = 0; i < orderedStoke.length; i += 2) {
    let productName = orderedStoke[i];
    let quantity = Number(orderedStoke[i + 1]);
   
    if (marketPlace.hasOwnProperty(productName)) {
      marketPlace[productName] += quantity;
    } else {
      marketPlace[productName] = quantity;
    }
  }

  let entries = Object.entries(marketPlace);
  for(let entry of entries){
    console.log(entry[0] + ' -> ' + entry[1]);
  }

}

storeProvision(
  [
    'Salt', '2', 'Fanta', '4', 'Apple', '14', 'Water', '4', 'Juice', '5'
    ],
    [
    'Sugar', '44', 'Oil', '12', 'Apple', '7', 'Tomatoes', '7', 'Bananas', '30'
    ]
    );
