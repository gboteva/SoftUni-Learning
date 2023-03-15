function flightSchedule(input){
    let [allFlights, changes, flightStatusToCheck] = input;

    let flights = {}
    for (const fly of allFlights) {
        let flightNumber = fly.split(' ')[0];
        let destination = fly.substring(flightNumber.length + 1)
        flights[flightNumber] = destination
    }

    let canceled = [];

    for (const change of changes) {
        let flightNumber = change.split(' ')[0];
        if (flights.hasOwnProperty(flightNumber)){
            canceled.push(flights[flightNumber])
            delete flights[flightNumber];
        }
    }

    let sorted = canceled.sort((a,b)=>{
       return a.localeCompare(b);
    })

    if (flightStatusToCheck[0] === 'Cancelled'){
        for (const fly of sorted) {
            console.log(`{ Destination: '${fly}', Status: 'Cancelled' }`); 
        }
     
    }else {
        for (const key in flights) {
           console.log(`{ Destination: '${flights[key]}', Status: 'Ready to fly' }`);
        }
    }

}


flightSchedule(
    [['WN269 Delaware',
    'FL2269 Oregon',
     'WN498 Las Vegas',
     'WN3145 Ohio',
     'WN612 Alabama',
     'WN4010 New York',
     'WN1173 California',
     'DL2120 Texas',
     'KL5744 Illinois',
     'WN678 Pennsylvania'],
     ['DL2120 Cancelled',
     'WN612 Cancelled',
     'WN1173 Cancelled',
     'SK430 Cancelled'],
     ['Cancelled']
 ]
 
 
  
)