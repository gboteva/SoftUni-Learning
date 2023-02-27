function sortingNumbers(numbers){
    let sortedArr = [...numbers].sort((num1, num2) => num1-num2);

    let resultArray = [];
  
    while(sortedArr.length > 0){
        resultArray.push(sortedArr.shift());
        //if input array has odd length?
        if (sortedArr.length === 0){
            break
        }
        resultArray.push(sortedArr.pop());
    }
   
    return resultArray;

    
}

console.log(sortingNumbers([1, 65, 3, 52, 48, 63, 31, -3, 18]));