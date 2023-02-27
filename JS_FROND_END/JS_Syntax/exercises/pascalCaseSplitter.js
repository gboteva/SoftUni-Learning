function pascalCaseSplitter(text){
    
    let pattern = /(?=[A-Z])/; //Positive Lookahead
    let words = text.split(pattern);
    console.log(words.join(', '));
}

pascalCaseSplitter('SplitMeIfYouCanHaHaYouCantOrYouCan')