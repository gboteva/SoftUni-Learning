function modernTimes(input){
    let inputArr = input.split(' ');
    
    let pattern = new RegExp("#[A-Za-z]+")
    
    for (const word of inputArr) {
        if (pattern.test(word)){
            console.log(word.slice(1));
        }
    }

}

modernTimes('Nowadays everyone uses # to tag a #special word in #socialMedia');