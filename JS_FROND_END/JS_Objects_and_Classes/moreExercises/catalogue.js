function catalogue(input) {
  let catalogue = {};

  input.forEach((item) => {
    productName = item.split(" : ")[0];
    productPrice = Number(item.split(" : ")[1]);
    catalogue[productName] = productPrice;
  });

  let sorted = Object.entries(catalogue).sort((a, b) => {
    return a[0].toLowerCase().localeCompare(b[0].toLowerCase());
  });

  let firstLetter = sorted[0][0].charAt(0);
  console.log(firstLetter);

  for (let entry of sorted) {
    let currentFirstLetter = entry[0].charAt(0);

    if (firstLetter === currentFirstLetter) {
      console.log("  " + entry[0] + ": " + entry[1]);
    } else {
      firstLetter = currentFirstLetter;
      console.log(firstLetter);
      console.log("  " + entry[0] + ": " + entry[1]);
    }
  }
}

catalogue(["Omlet : 5.4", "Shirt : 15", "Cake : 59"]);
