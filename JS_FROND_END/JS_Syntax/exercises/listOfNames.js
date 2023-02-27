function listOfNames(input){
    
   return [...input] //make a copy
        .sort((nameOne, nameTwo)=>
              nameOne.localeCompare(nameTwo)) //sort ascending order
        .map((n, index)=> `${index+1}.${n}`) //add number
        .join('\n');    //make String with new line after every name;
}   

console.log(listOfNames(
    ["John", "Bob", "Christina", "Ema"]
)); 