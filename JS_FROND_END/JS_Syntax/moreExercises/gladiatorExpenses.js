function gladiatorExpenses (...arg) {
    let countLost = arg[0];
    let helmetPrice = arg[1];
    let swordPrice = arg[2];
    let shieldPrice = arg[3];
    let armorPrice = arg[4];

    let countBrokeHelmets = Math.trunc(countLost / 2);

    let countBrokenSwords = Math.trunc(countLost / 3);

    let countBrokenShields = Math.trunc(countLost / 6);

    let countBrokenArmor =Math.trunc(countBrokenShields / 2);

    let totalPrice = countBrokeHelmets * helmetPrice
                    + countBrokenSwords * swordPrice
                    + countBrokenShields * shieldPrice
                    + countBrokenArmor * armorPrice;
    console.log(`Gladiator expenses: ${totalPrice.toFixed(2)} aureus`);
}

gladiatorExpenses(23,
    12.50,
    21.50,
    40,
    200
    
    )