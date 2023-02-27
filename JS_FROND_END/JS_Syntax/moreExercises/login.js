function login(arg){
    let userName = arg[0];
    let correctPass = getCorrectPassword(userName);
    
    let countWrongInput = 0;
    
    for (let i=1; i<arg.length; i++){
       
        if (arg[i] === correctPass){
            console.log(`User ${userName} logged in.`);
            break
        }else{
            countWrongInput++;
            if (countWrongInput === 4){
                console.log(`User ${userName} blocked!`);
                break;
            }
            console.log('Incorrect password. Try again.');
        }
    }


    function getCorrectPassword(userName){
        let correct ='';
        for (let i=userName.length -1; i>=0; i--){
            correct +=userName[i];
        }
        return correct;
    }
}

login(['sunny','rainy','cloudy','sunny','not sunny'])