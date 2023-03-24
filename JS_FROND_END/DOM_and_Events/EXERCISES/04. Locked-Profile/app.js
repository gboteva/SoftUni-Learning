function lockedProfile() {
  let btn = Array.from(document.getElementsByTagName("button"));
  btn.forEach((button) => {
    button.addEventListener("click", toggle);
  });

  function toggle(event) {
    let profile = event.currentTarget.parentElement;

    let lockedInput = profile.children[2];

    if (lockedInput.checked) {
      return;
    }

    let hiddenDiv = profile.children[9];

    if (hiddenDiv.style.display === "none" || hiddenDiv.style.display === "") {
      hiddenDiv.style.display = "block";
      event.currentTarget.textContent = "Hide it";
    } else {
      hiddenDiv.style.display = "none";
      event.currentTarget.textContent = "Show more";
    }
  }
}

//.....old solution......
// let buttons = document.getElementsByTagName("button");

//   for (let btn of buttons) {
//     btn.addEventListener("click", showInfo);
//   }

//   function showInfo(event) {
//     let currentInput = event.target.parentElement.querySelectorAll(
//       "input[value=unlock]")[0];

//     let numberOfUser = currentInput.name.charAt(4);

//     let validID = "user" + numberOfUser + "HiddenFields";

//     let isChecked = currentInput.checked;

//     if (isChecked) {
//       document.getElementById(validID).style.display = "block";
//       event.target.textContent = "Hide it";
//       event.target.addEventListener("click", hideInfo);
//     }
//   }

//   function hideInfo(event) {
//     let currentInput =
//       event.target.parentElement.querySelectorAll("input[value=unlock]")[0];
    
//       let numberOfUser = currentInput.name.charAt(4);

//     let validID = "user" + numberOfUser + "HiddenFields";

//     let isChecked = event.target.parentElement.querySelectorAll('input[value=unlock]')[0].checked;
//     if (isChecked) {
//         document.getElementById(validID).style.display = "none";
//         event.target.textContent = "Show more";
//       }
   
//   }
