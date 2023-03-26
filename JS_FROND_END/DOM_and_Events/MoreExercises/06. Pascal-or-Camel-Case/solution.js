function solve() {
  const textInput = document.getElementById("text");
  const namingConvention = document.getElementById("naming-convention");
  const resultField = document.getElementById("result");

  let words = textInput.value.toLowerCase().split(" ");
  let output = "";

  if (namingConvention.value === "Camel Case") {
    output = getCamelCase(words);
  } else if (namingConvention.value === "Pascal Case") {
    output = getPascalCase(words);
  } else {
    output = "Error!";
  }

  resultField.textContent = output;

  function getCamelCase(words){
    let output = '';
    output += words[0];

    for (let i = 1; i < words.length; i++){
      let word = words[i];
      word = word.charAt(0).toUpperCase() + word.substring(1);
      output += word;
    }
   
    return output;
  }

  function getPascalCase(words){
    let output = '';
  
    for (let i = 0; i < words.length; i++){
      let word = words[i];
      word = word.charAt(0).toUpperCase() + word.substring(1);
      output += word;
    }
   
    return output;
  }
}
