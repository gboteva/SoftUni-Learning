function shoppingList(input) {
  let products = input.shift().split("!");

  let command = input.shift();

  while (command !== "Go Shopping!") {
    let tokens = command.split(" ");

    commandParser(tokens);

    command = input.shift();
  }

  console.log(products.join(', '));

  function commandParser(tokens) {
    let action = tokens[0];
    let item = tokens[1];

    switch (action) {
      case "Urgent":
        if (!products.includes(item)){
            products.unshift(item);
        }
        break;
      case "Unnecessary":
        if (products.includes(item)){
            let index = products.indexOf(item);
            products.splice(index, 1);
        }
        break;
      case "Correct":
        if (products.includes(item)){
            let index = products.indexOf(item);
            products[index] = tokens[2];
        }
        break;
      case "Rearrange":
        if (products.includes(item)){
            let index = products.indexOf(item);
            products.splice(index, 1);
            products.push(item)
        }
        break;
    }
  }
}

// shoppingList([
//   "Tomatoes!Potatoes!Bread",
//   "Unnecessary Milk",
//   "Urgent Tomatoes",
//   "Go Shopping!",
// ]);

shoppingList(
    (["Milk!Pepper!Salt!Water!Banana",
    "Urgent Salt",
    "Unnecessary Grapes",
    "Correct Pepper Onion",
    "Rearrange Grapes",
    "Correct Tomatoes Potatoes",
    "Go Shopping!"])

)
