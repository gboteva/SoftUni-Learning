function passwordValidator(password) {
  let isValidLength = checkLength(password);

  if (!isValidLength) {
    console.log("Password must be between 6 and 10 characters");
  }

  let isValidContent = checkContent(password);

  if (!isValidContent) {
    console.log("Password must consist only of letters and digits");
  }

  let isValidCountOfDigit = checkDigit(password);
  if (!isValidCountOfDigit) {
    console.log("Password must have at least 2 digits");
  }

  if (isValidLength && isValidContent && isValidCountOfDigit) {
    console.log("Password is valid");
  }

  function checkLength(password) {
    return password.length >= 6 && password.length <= 10;
  }

  function checkContent(password) {
    password = password.toLowerCase();
    let characters = [...password];

    for (let i = 0; i < characters.length; i++) {
      let code = characters[i].charCodeAt(0);

      if (!((code >= 48 && code <= 57) || (code >= 97 && code <= 122))) {
        return false;
      }
    }

    return true;
  }

  function checkDigit(password) {
    let characters = [...password];
    let countOfDigit = 0;

    for (let i = 0; i < characters.length; i++) {
      let code = characters[i].charCodeAt(0);

      if ((code >= 48 && code <= 57)) {
            countOfDigit++;
      }
    }
    if (countOfDigit >=2){
        return true;
    }else {
        return false;
    }
  }
}

passwordValidator("Pa$s$s");
