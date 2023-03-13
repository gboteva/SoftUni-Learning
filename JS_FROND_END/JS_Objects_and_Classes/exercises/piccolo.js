function piccolo(input){
    let parkingLot ={};
    for (let element of input) {
        let token = element.split(', ');
        let direction = token[0];
        let carNumber = token[1];

        if (direction === 'IN'){
            parkingLot[carNumber] = 1;
        }else {
           delete parkingLot[carNumber];
        }
    }
    let sorted = Object.keys(parkingLot)
                .sort((a,b)=> a.localeCompare(b))
    
    if (sorted.length === 0){
        console.log('Parking Lot is Empty');
    }else {
        sorted.forEach(element => {
            console.log(element);
        });
    }
}

piccolo(['IN, CA2844AA',
'IN, CA1234TA',
'OUT, CA2844AA',
'IN, CA9999TT',
'IN, CA2866HI',
'OUT, CA1234TA',
'IN, CA2844AA',
'OUT, CA2866HI',
'IN, CA9876HH',
'IN, CA2822UU']
)