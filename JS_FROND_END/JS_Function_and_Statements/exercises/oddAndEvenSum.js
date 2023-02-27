function oddAndEvenSum(number){
    let digits = [...number.toString()];
    let evenSum = 0;
    let oddSum = 0;
    digits.forEach(digit => {
        if (Number(digit) % 2 ===0){
            evenSum+=Number(digit);
        }else{
            oddSum += Number(digit);
        }
    });

    console.log(`Odd sum = ${oddSum}, Even sum = ${evenSum}`);
}

oddAndEvenSum(3495892137259234)