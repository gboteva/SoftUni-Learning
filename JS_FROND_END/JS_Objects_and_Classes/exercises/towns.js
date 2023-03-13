function towns(input){
   
   input.forEach(element => {
       let info = element.split(' | ');
       let town = info[0];
       let latitude = Number(info[1]).toFixed(2);
       let longitude = Number(info[2]).toFixed(2);
       let obj = {town, latitude, longitude}
       console.log(obj);
    })

}

towns(['Sofia | 42.696552 | 23.32601',
'Beijing | 39.913818 | 116.363625']

)