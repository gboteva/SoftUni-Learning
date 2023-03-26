function encodeAndDecodeMessages() {
    const areas = document.getElementsByTagName('textarea');
    const buttons = document.getElementsByTagName('button');

    buttons[0].addEventListener('click', encode);
    buttons[1].addEventListener('click', decode);
    
    function encode(){
        let message = areas[0].value;
        let symbols = [...message];
        let decodeMessage = [];
        for(let symbol of symbols){
            let asciiCode = symbol.charCodeAt(0);
            let newCode = asciiCode + 1;
            decodeMessage.push(String.fromCharCode(newCode))
        }
        areas[0].value = '';
        areas[1].value = decodeMessage.join('');
    }

    function decode(){
        let message = areas[1].value;
        let symbols = [...message];
        let encodedMessage = [];
        for(let symbol of symbols){
            let asciiCode = symbol.charCodeAt(0);
            let newCode = asciiCode - 1;
            encodedMessage.push(String.fromCharCode(newCode))
        }
        areas[1].value = encodedMessage.join('');
    }

}