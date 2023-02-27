function loadingBar (number){
    if (number === 100){
        console.log('100% Complete!');
    }else {
       let loadingArray=[];
       let completedLevel = Number(number) / 10;
       for (let i= 1; i <= 10; i++){
            if (i <= completedLevel){
                loadingArray.push('%');
            }else {
                loadingArray.push('.');
            }
       } 

       console.log(`${number}% [${loadingArray.join('')}]`);
       console.log('Still loading...');

    }

}

loadingBar(50)