function addAndSubtract(num1, num2, num3){
    let sumResult = sum(num1, num2);
    let subResult = subtract(sumResult, num3);
    console.log(subResult);
    
    function sum(num1, num2){
        return num1 + num2;
    }
    function subtract (result, num3){
        return result - num3
    }
}



addAndSubtract(1,17, 30)