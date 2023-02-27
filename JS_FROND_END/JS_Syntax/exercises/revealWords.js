function revealWords(words, text){
    let searchedWords = words.split(', ');
    let textArray = text.split(' ');

    for (const word of searchedWords) {
        for (let i=0; i<textArray.length; i++){
            if (textArray[i].startsWith('*') 
                && textArray[i].length == word.length){
                    textArray[i] = word;  
            }
        }
    }
    console.log(textArray.join(' '));

}

// revealWords('great, learning',
// 'softuni is ***** place for ******** new programming languages'
// );

revealWords('great',
'softuni is ***** place for learning new programming languages'
)