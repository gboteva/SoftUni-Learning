function wordsUppercase(text) {
    
    let result = text.toUpperCase()
    .match(/\w+/g)
    .join(', ');
  
     console.log(result);
}

wordsUppercase('Hi, how are you?');
wordsUppercase('hello');