function extract(content) {
    let text = document.getElementById(content).textContent;
    let regExp = /\([^)]+\)/g;
    let matches = text.match(regExp);
    
    let words = []
    for(let match of matches){
        let word = match.substring(1, match.length-1);
        words.push(word);
    }
    
    return words.join('; ');
}