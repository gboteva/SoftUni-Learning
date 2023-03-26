function solve() {
  let buttons = Array.from(document.getElementsByClassName("add-product"));
  let textAria = document.getElementsByTagName("textarea")[0];

  buttons.forEach((btn) => btn.addEventListener("click", buyProduct));

  let checkoutBtn = document.getElementsByClassName("checkout")[0];
  checkoutBtn.addEventListener("click", checkout);

  let shoppingCart = [];
  let uniqueNames = new Set();
  function buyProduct(event) {
    let product = event.currentTarget.parentElement.parentElement;
    let productName =
      product.getElementsByClassName("product-title")[0].textContent;
    let money =
      product.getElementsByClassName("product-line-price")[0].textContent;
    shoppingCart.push(product);

    textAria.value += `Added ${productName} for ${money} to the cart.\n`;

    uniqueNames.add(productName);
  }

  
  let totalMoney = 0;

  function checkout() {
    for (let product of shoppingCart) {
      let money =
        product.getElementsByClassName("product-line-price")[0].textContent;
      totalMoney += Number(money);
    }

    textAria.value += `You bought ${Array.from(uniqueNames).join(', ')} for ${totalMoney.toFixed(2)}.`

    let btns = Array.from(document.getElementsByTagName('button'));
    btns.forEach(btn => {
      btn.setAttribute('disabled', '');
    })
  }
}
