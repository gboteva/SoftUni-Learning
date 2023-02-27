function solve(number){
    // let myFunc = num => Number(num);
    
    // let digits = Array.from(String(number), myFunc);
    
   let sum = 0;
    
    // for (let i=0; i<=digits.length-1; i++){
    //    sum+=Number(digits[i]);
    // }

    // console.log(sum);

    let arr = number.toString().split('');
    arr.forEach(n=>sum+=Number(n));
    console.log(sum);
}

solve(245678);