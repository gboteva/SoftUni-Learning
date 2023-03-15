function schoolRegister(input){
    class Student {
        constructor(name, grade, score){
            this.name = name;
            this.grade = grade;
            this.score = score;
        }
    }
    let allStudentsForNextYear = [];
    
    for (const student of input) {
        let infoForStudent = student.split(', ');
        let studentName = infoForStudent[0].split(': ')[1];
        let grade = Number(infoForStudent[1].split(': ')[1]);
        let score = Number(infoForStudent[2].split(': ')[1])

        if (score >= 3){
            allStudentsForNextYear.push(new Student(studentName,grade, score));
        }
    }

    let sortedStudentsByGrade = allStudentsForNextYear.sort((a, b)=>{
        return a.grade - b.grade;
    })

    let register = {}; 
    for (const stud of sortedStudentsByGrade) {
        if (!register.hasOwnProperty(stud.grade)){
            register[stud.grade] = [];
        }
        register[stud.grade].push(stud)
    }

    for (const key in register) {
        let grade = Number(key) + 1;
        console.log(`${grade} Grade`);

        let students = [];
        register[key].forEach(student => {
            students.push(student.name);
        });
        console.log(`List of students: ${students.join(', ')}`);

        let scores = [];
        register[key].forEach(student => {
            scores.push(student.score);
        });
       
        let sum = scores.reduce((a,b)=> {return a + b;}, 0);
        let avgScore = sum / scores.length;
        console.log(`Average annual score from last year: ${avgScore.toFixed(2)}`);

        console.log();
    }
}

schoolRegister(
    [
    'Student name: George, Grade: 5, Graduated with an average score: 2.75',
    'Student name: Alex, Grade: 9, Graduated with an average score: 3.66',
    'Student name: Peter, Grade: 8, Graduated with an average score: 2.83',
    'Student name: Boby, Grade: 5, Graduated with an average score: 4.20',
    'Student name: John, Grade: 9, Graduated with an average score: 2.90',
    'Student name: Steven, Grade: 2, Graduated with an average score: 4.90',
    'Student name: Darsy, Grade: 1, Graduated with an average score: 5.15'
    ]  
        
)