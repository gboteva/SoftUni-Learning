function stringSubstring(word, text){
    let arr = text.split(' ');

    let matched = [];
    for (const element of arr) {
        if (word.toLowerCase() === element.toLowerCase()){
            matched.push(word);
            break;
        }
    }
    if (matched.length === 0){
        console.log(`${word} not found!`);
    }else {
        console.log(matched[0]);
    }
}

stringSubstring('python',
'JavaScript is the best programming language'
)