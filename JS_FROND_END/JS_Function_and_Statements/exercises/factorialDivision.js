function factorialDivision(num1, num2){
    let firstFactorial = getFactorial(num1);
    let secondFactorial = getFactorial(num2);
    let divide = firstFactorial / secondFactorial;
    
    console.log(divide.toFixed(2));


    function getFactorial(num){
        let result = 1;
        for (let i=1; i<= num; i++){
            result = result * i;
        }

        return result;
    }
}

factorialDivision(6,2)