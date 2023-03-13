function employees(input){
    // class Employee{
    //     constructor (name, personalNumber){
    //         this.name = name;
    //         this.personalNumber = personalNumber;
    //     }

    //     printInfo(){
    //         console.log(`Name: ${this.name} -- Personal Number: ${this.personalNumber}`);
    //     }
    // }

    // input.forEach(element => {
    //     let personalNumber = [...element];
    //     let employee = new Employee(element, personalNumber.length);
    //     employee.printInfo();
    // });

    input.forEach(element => {
            let personalNumber = [...element].length;
            let employee = {
                name: element,
                personalNumber: personalNumber,

                printInfo(){
                    console.log(`Name: ${this.name} -- Personal Number: ${this.personalNumber}`);
                }
            }
            employee.printInfo();
        });

}

employees([
    'Samuel Jackson',
    'Will Smith',
    'Bruce Willis',
    'Tom Holland'
    ]    
    )