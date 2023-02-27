function solve(input){
   
    // let func = num=>Number(num);
    // let digits = Array.from(String(number), func);

    // isSameNumber = true;
    // let firstDigit = digits[0];
    
    // let sumOfNumbers = Number(firstDigit);

    // for (let i=1; i<=digits.length-1; i++){
    //     if (firstDigit !== digits[i]){
    //         isSameNumber = false;
    //     }
    //     sumOfNumbers+=Number(digits[i]);
    // }
   
    // console.log(isSameNumber);
    // console.log(sumOfNumbers);


    let array = input.toString().split(''); 
    let first = array[0]
    let isSame = true;
 
    array.forEach(n=>{
        if (n!==first){
            isSame = false;
            return;
        }
    });
    console.log(isSame);

    let sum = 0;
    array.forEach(n=>sum+=Number(n))
    console.log(sum);
 }
 
 solve(2222222);