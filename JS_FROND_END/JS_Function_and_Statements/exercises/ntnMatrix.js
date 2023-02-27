function ntnMatrix(number){
    for (let i=1; i<=number; i++){
        let row = [];
        for (let i=1; i<=number; i++){
            row.push(number)
        }
        console.log(row.join(' '));

    }

}

ntnMatrix(3)