window.addEventListener('load', solution);

function solution() {
  const nameInput = document.getElementById('fname');
  const emailInput = document.getElementById('email');
  const phoneInput = document.getElementById('phone');
  const addressInput = document.getElementById('address');
  const postCodeInput = document.getElementById('code');
  const submitBtn = document.getElementById('submitBTN');
  const previewContainer = document.getElementById('infoPreview');
  const editBtn = document.getElementById('editBTN');
  const continueBtn =document.getElementById('continueBTN');
  const mainContainer = document.getElementById('block')

  const info = {};

  submitBtn.addEventListener('click', addInfoHandler);
  editBtn.addEventListener('click', editHandler);
  continueBtn.addEventListener('click', continueHandler)

  function addInfoHandler(){
    if (nameInput.value === '' || emailInput.value === ''){
      return
    }

    const liName = document.createElement('li');
    liName.textContent = `Full Name: ${nameInput.value}`
    info.fullName = nameInput.value;
    
    const liEmail = document.createElement('li');
    liEmail.textContent = `Email: ${emailInput.value}`
    info.email = emailInput.value;
    
    const liPhone = document.createElement('li');
    liPhone.textContent = `Phone Number: ${phoneInput.value}`
    info.phone = phoneInput.value;

    const liAddress = document.createElement('li');
    liAddress.textContent = `Address: ${addressInput.value}`
    info.address = addressInput.value;

    const liCode = document.createElement('li');
    liCode.textContent = `Postal Code: ${postCodeInput.value}`
    info.code = postCodeInput.value;

    previewContainer.appendChild(liName);
    previewContainer.appendChild(liEmail);
    previewContainer.appendChild(liPhone);
    previewContainer.appendChild(liAddress);
    previewContainer.appendChild(liCode);
    
    nameInput.value = '';
    emailInput.value = '';
    phoneInput.value = '';
    addressInput.value = '';
    postCodeInput.value = '';
    submitBtn.disabled = true;
    editBtn.disabled = false;
    continueBtn.disabled = false;

  }

  function editHandler(){
    nameInput.value = info.fullName;
    emailInput.value = info.email;
    phoneInput.value = info.phone;
    addressInput.value = info.address;
    postCodeInput.value = info.code;
    previewContainer.innerHTML = '';
    submitBtn.disabled = false;
    editBtn.disabled = true;
    continueBtn.disabled = true;

  }

  function continueHandler(){
    mainContainer.innerHTML = '';
    const h3 = document.createElement('h3');
    h3.textContent ='Thank you for your reservation!';
    mainContainer.appendChild(h3);
  }
}
