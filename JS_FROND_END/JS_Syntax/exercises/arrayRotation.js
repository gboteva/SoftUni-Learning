function solve(inputArray, count) {
    count %= inputArray.length;
 
    for (let i = 1; i <= count; i++) {
        inputArray.push(inputArray.shift());
    }

  console.log(inputArray.join(" "));
}

//  solve([51, 47, 32, 61, 21], 2);
//  solve([32, 21, 61, 1], 4);
solve([2, 4, 15, 31], 5);
