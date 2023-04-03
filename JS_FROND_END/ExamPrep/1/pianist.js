function pianist(input) {
  let numberOfPieces = Number(input.shift());
  let pieces = {};
  while (numberOfPieces-- > 0) {
    let token = input.shift().split("|");
    pieces[token[0]] = [token[1], token[2]];
  }

  let command = input.shift();

  while (command !== "Stop") {
    let tokens = command.split("|");
    
    commandParser(tokens, pieces);

    command = input.shift();
  }

  for(let key in pieces){
    console.log(`${key} -> Composer: ${pieces[key][0]}, Key: ${pieces[key][1]}`);
  }

  function commandParser(tokens, pieces) {
    let action = tokens[0];
    let piece = tokens[1];
    switch (action) {
      case "Add":
        if (pieces.hasOwnProperty(piece)){
            console.log(`${piece} is already in the collection!`);
        }else {
            pieces[piece] = [tokens[2], tokens[3]];
            console.log(`${piece} by ${tokens[2]} in ${ tokens[3]} added to the collection!`);
        }
        break;
      case "Remove":
        if (!pieces.hasOwnProperty(piece)){
            console.log(`Invalid operation! ${piece} does not exist in the collection.`);
        }else {
            delete pieces[piece];
            console.log(`Successfully removed ${piece}!`);
        }
        break;
      case "ChangeKey":
        if (!pieces.hasOwnProperty(piece)){
            console.log(`Invalid operation! ${piece} does not exist in the collection.`);
        }else {
           let newKey = tokens[2]; 
           pieces[piece][1] = newKey;
           console.log(`Changed the key of ${piece} to ${newKey}!`);
        }
        break;
    }

  }
}

pianist([
    '4',
    'Eine kleine Nachtmusik|Mozart|G Major',
    'La Campanella|Liszt|G# Minor',
    'The Marriage of Figaro|Mozart|G Major',
    'Hungarian Dance No.5|Brahms|G Minor',
    'Add|Spring|Vivaldi|E Major',
    'Remove|The Marriage of Figaro',
    'Remove|Turkish March',
    'ChangeKey|Spring|C Major',
    'Add|Nocturne|Chopin|C# Minor',
    'Stop'
  ]
  );
