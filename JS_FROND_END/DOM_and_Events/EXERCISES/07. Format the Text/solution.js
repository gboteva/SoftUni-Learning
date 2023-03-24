function solve() {
  let text = document.getElementById("input").value;
 
  let sentences = text.split(".").filter(e => e.trim());

  let output = document.getElementById('output');

  while(sentences.length > 0){
    let paragraph = document.createElement('p');
    paragraph.textContent = sentences.splice(0,3) + '.';
    output.appendChild(paragraph)
  }

}



// .........old solution......
// let text = document.getElementById("input").value;
 
// let sentences = text.split(".").filter(e=>e.trim());

// if (sentences.length <= 3) {
//   let finalP = `<p>${text}</p>`
//   document.getElementById("output").innerHTML = finalP;

// } else {

//   let countNeededParagraphs = Math.ceil(sentences.length / 3);
  
  
//   for (i = 1; i <= countNeededParagraphs; i++) {
//     let textContent = ''
//     for (let i = 0; i < 3; i++) {  
//       if (sentences.length === 0){
//         break;
//       }
//       textContent += sentences.shift() + '.';
//     }
//     let finalP = `<p>${textContent}</p>`
  
//     document.getElementById("output").innerHTML += finalP;
//     if (sentences.length === 0){
//       return;
//     }
//   }
// }
