function spiceMustFlow (yield){
    let totalSpice = 0;  
    let countDay = 0;
    
    while(yield >= 100){
        countDay++;

        totalSpice+=(yield - 26);
        yield-=10;
    }

    if (totalSpice >=26){
        totalSpice-=26;
    }else {
        totalSpice=0;
    }

    console.log(countDay);
    console.log(totalSpice);
}

spiceMustFlow(450)