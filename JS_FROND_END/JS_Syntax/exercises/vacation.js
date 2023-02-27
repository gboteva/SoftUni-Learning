function solve(count, type, day){
    let totalPrice = 0;
    
    let price = 0;
    if (type === "Students"){
        if (day === "Friday"){
            price = 8.45;
        }else if (day == "Saturday"){
            price = 9.80;
        }else if (day == "Sunday"){
            price = 10.46;
        } 

    }else if (type === "Business"){
        if (day === "Friday"){
            price = 10.90;
        }else if (day == "Saturday"){
            price = 15.60;
        }else if (day == "Sunday"){
            price = 16;
        }

    }else if (type === "Regular"){
        if (day === "Friday"){
            price = 15;
        }else if (day == "Saturday"){
            price = 20;
        }else if (day == "Sunday"){
            price = 22.50;
        }
    }
    
    totalPrice = price * count;

    if (type === "Students" && count >=30){
        totalPrice -= totalPrice*0.15;
    }
    
    if (type === "Business" && count >= 100){
        totalPrice = (count - 10) * price;
    } 
    
    if (type === "Regular" && count >=10 && count <=20){
        totalPrice-=totalPrice*0.05;
    }

    console.log(`Total price: ${totalPrice.toFixed(2)}`);
}

solve(100,
    "Business",
    "Sunday"
    );

solve(40,
    "Regular",
    "Saturday"
    
    );