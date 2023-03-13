function wordTracker(input){
    let searchedWords = input[0].split(/\s+/);
    let occurrences = {};
    for (const word of searchedWords) {
        occurrences[word] = 0;
    }

    for (let i = 1; i < input.length; i++){
        if (searchedWords.includes(input[i])){
            occurrences[input[i]]+=1;
        }
    }
    
    let entries = Object.entries(occurrences).sort((a,b)=>{
        return b[1] - a[1];});
   
    for (let occur of entries) {
        console.log(`${occur[0]} - ${occur[1]}`);
    }
}

wordTracker(
    [
        'is the', 
        'first', 'sentence', 'Here', 'is', 'another', 'the', 'And', 'finally', 'the', 'the', 'sentence']
        
        
)