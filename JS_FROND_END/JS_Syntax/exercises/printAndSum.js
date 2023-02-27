function solve(start, end){
    let result = []
    let sum = 0;
    for (let i=start; i<=end; i++){
        result.push(i)
        sum+=i;
    }

    console.log(result.join(' '));
    console.log(`Sum: ${sum}`);
    
}

solve(0, 26);