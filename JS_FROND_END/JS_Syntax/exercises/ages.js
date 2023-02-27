function solve(age){

    let result = '';

    if (age >=0 && age <=2){
        result = 'baby';
    } else if (age >2 && age <=13){
        result = 'child';
    } else if (age >13 &&  age <=19){
        result = 'teenager'
    } else if (age >19 && age <=65){
        result = 'adult'
    } else if (age >65 && age >=66){
        result = 'elder'
    } else {
        result = 'out of bounds'
    }

    console.log(result);
}

solve(20);
solve(1);
solve(100);
solve(-1);
