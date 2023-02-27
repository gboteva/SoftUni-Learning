function printElements(stringArray, step){
    let arr = [];
    
    for (let i=0; i< stringArray.length; i+=step){
        arr.push(stringArray[i]);
    }

   return(arr);
}

// printElements(['5', 
// '20', 
// '31', 
// '4', 
// '20'], 
// 2
// );

// printElements(
// ['dsa',
// 'asd', 
// 'test', 
// 'tset'], 
// 2
// )

printElements(
['1', 
'2',
'3', 
'4', 
'5'], 
6
)