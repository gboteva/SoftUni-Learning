function attachEventsListeners() {
  const buttons = Array.from(document.querySelectorAll("input[type=button]"));
  
  for (let button of buttons) {
    button.addEventListener("click", convertTime);
  }

  function convertTime(e) {
    let inputValue = Number(e.target.parentElement.querySelector('input[type=text]').value)
    let id = e.target.id.substring(0, e.target.id.length-3);
    let days = 0;
    let hours = 0;
    let minutes = 0;
    let seconds = 0;

    if (id === 'days'){
        days = inputValue;
        hours = days * 24;
        minutes = hours * 60;
        seconds = minutes * 60
    }else if (id === 'hours'){
        hours = inputValue;
        days = hours / 24;
        minutes = hours * 60;
        seconds = minutes * 60;
    }else if (id === 'minutes'){
        minutes = inputValue;
        hours = minutes / 60;
        days = hours / 24;
        seconds = minutes * 60;
    }else {
        seconds = inputValue;
        minutes = seconds / 60;
        hours = minutes / 60;
        days = hours / 24;
    }

    let inputs = Array.from(document.querySelectorAll('input[type=text]'));
    inputs[0].value = days;
    inputs[1].value = hours;
    inputs[2].value = minutes;
    inputs[3].value = seconds;
    
  }
}
