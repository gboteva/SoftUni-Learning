function perfectNumber(number){
    let dividers = [];
    
    for (let i=1; i< number; i++){
        if (Number(number) % i === 0){
            dividers.push(i);
        }
    }

    let sumOfDividers = dividers.reduce((a,b)=>{
        return a + b;
    });
    
 

    if (sumOfDividers === number){
        console.log('We have a perfect number!');
    }else {
        console.log('It\'s not so perfect.');
    }
}

perfectNumber(1236498)