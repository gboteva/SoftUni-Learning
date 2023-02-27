function bitcoinMining (goldForShifts){
    let bitcoinPrice = 11949.16;
    let pricePerGramGold = 67.51;

    let totalAmountOfMoney = 0;
    let countBitcoins = 0;

    let dayOfShift = 0;
    let firstDayOfBuyingBitcoin = 0;
    for (let i = 0; i < goldForShifts.length; i++){
        dayOfShift++;
        let currentGoldGrams = goldForShifts[i];

        if (dayOfShift % 3 === 0){
            currentGoldGrams = currentGoldGrams*0.7;
        }

        totalAmountOfMoney+= currentGoldGrams * pricePerGramGold;

        if (totalAmountOfMoney >= bitcoinPrice){
            if (countBitcoins === 0){
                firstDayOfBuyingBitcoin = dayOfShift;
            }
            let canBuy = Math.trunc(totalAmountOfMoney / bitcoinPrice);
            countBitcoins+= canBuy;
            totalAmountOfMoney-=(bitcoinPrice * canBuy)
        }
    }
    console.log(`Bought bitcoins: ${countBitcoins}`);
    if (countBitcoins > 0){
        console.log(`Day of the first purchased bitcoin: ${firstDayOfBuyingBitcoin}`);
    }
    console.log(`Left money: ${totalAmountOfMoney.toFixed(2)} lv.`);
}

bitcoinMining([3124.15, 504.212, 2511.124])