function characterInRange(char1, char2){
    let code1 = char1.charCodeAt(0);
    let code2 = char2.charCodeAt(0);

    let asciiCodes = [];
    if (code1 < code2){
        for (let i=code1+1; i<code2; i++){
            asciiCodes.push(String.fromCharCode(i))
        }
    }else {
        for (let i=code2+1; i<code1; i++){
            asciiCodes.push(String.fromCharCode(i))
        }
    }

    console.log(asciiCodes.join(' '));
}

characterInRange('C',
'#'

)