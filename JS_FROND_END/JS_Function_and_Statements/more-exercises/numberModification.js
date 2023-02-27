function numberModification(number){
    let numberAsString = number.toString();

    let isValid = avgSumOfDigitMoreThanFive(numberAsString);

    while(!isValid){
        numberAsString+='9';
        isValid = avgSumOfDigitMoreThanFive(numberAsString);
    }

    console.log(numberAsString);
 

    function avgSumOfDigitMoreThanFive(number){
        let digits = [...number];
        let sum = 0;
        
        for (const digit of digits) {
           sum+=Number(digit);
        }

        return (sum /digits.length) > 5;

    }
}

numberModification(5835)