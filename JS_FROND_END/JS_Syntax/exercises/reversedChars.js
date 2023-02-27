function solve(...arg){
    
    // let result = '';
    // result = result.concat(c).concat(' ');
    // result = result.concat(b).concat(' ');
    // result = result.concat(a).concat(' ');
    
    // console.log(result);
    
    let arr = [...arg];
    arr.reverse();
    console.log(arr.join(' '));
 }
 
 solve('1', '5', 'g');