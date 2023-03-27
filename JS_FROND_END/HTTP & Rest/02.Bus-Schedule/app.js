function solve() {
    const textContainer = document.querySelector('#info .info');
    const btnDepart = document.getElementById('depart');
    const btnArrive = document.getElementById('arrive');

    const BASE_URL = 'http://localhost:3030/jsonstore/bus/schedule/';
    let nextStop = 'depot';
    let stopName = null;

    function depart() {
        btnArrive.disabled = false;
        btnDepart.disabled= true;
        fetch(BASE_URL + nextStop)
        .then(result => result.json())
        .then(nextStopInfo => {
            textContainer.textContent = `Next stop ${nextStopInfo.name}` ;
            nextStop = nextStopInfo.next;
            stopName = nextStopInfo.name;
        })
        .catch(err =>{
            textContainer.textContent = "Error";
            btnDepart.disabled = true;
            btnArrive.disabled = true;
            
        })
    }

    async function arrive() {
        btnArrive.disabled = true;
        btnDepart.disabled= false;
        textContainer.textContent = `Arriving at ${stopName}`;
    }

    return {
        depart,
        arrive
    };
    
}

let result = solve();