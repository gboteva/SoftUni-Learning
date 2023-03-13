function oddOccurrences(input){
    input = input.toLowerCase().split(/\s+/);
    let associativeArray = {};
    
    for (let word of input) {
        if (associativeArray.hasOwnProperty(word)){
            associativeArray[word.toString()]+=1;
        }else {
            associativeArray[word.toString()] = 1;
        }
    }

    let entries = Object.entries(associativeArray);
    let oddOccurrences = [];
    for (const entry of entries) {
        if (entry[1] % 2 != 0){
            oddOccurrences.push(entry[0]);
        }
    }

 


    console.log(oddOccurrences.join(' '));
}

oddOccurrences('Java C# Php PHP Java PhP 3 C# 3 1 5 C#')