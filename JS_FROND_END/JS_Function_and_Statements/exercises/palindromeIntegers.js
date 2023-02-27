function palindromeIntegers(numbers){
    numbers.forEach(num => {
       let stringNum = num.toString();
       let backwardNum = getBackward(stringNum);

       console.log(stringNum === backwardNum);
    })

    function getBackward(num){
        let backward = '';
        for (let i=num.length-1; i>=0; i--){
            backward+= num[i];
        }
        return backward;
     }
}


palindromeIntegers([32,2,232,1010])