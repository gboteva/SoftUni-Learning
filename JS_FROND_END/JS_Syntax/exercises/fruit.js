function solve(fruit, grams, pricePerKg){
    let kg = grams / 1000;
    let sum = kg * pricePerKg;
 
    console.log(`I need $${sum.toFixed(2)} to buy ${kg.toFixed(2)} kilograms ${fruit}.`);
 }
 
 solve('orange', 2500, 1.80);

 solve('apple', 1563, 2.35);