package Lab.p03_StudenSystem_refactoring;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> students;

    public StudentSystem () {
        this.students = new HashMap<>();
    }

    public Map<String, Student> getStudents() {
        return this.students;
    }

    public void create (Student student){
        if (!students.containsKey(student.getName())){
            students.put(student.getName(), student);
        }
    }

    public void show (String name){
        if (students.containsKey(name)){
            Student searchedStudent = this.students.get(name);
            String commentary = getCommentary(searchedStudent);
            String output = String.format("%s is %d years old. %s.", name,searchedStudent.getAge(),commentary);
            System.out.println(output);;
        }
    }

    private String getCommentary(Student searchedStudent) {
        StringBuilder commentary = new StringBuilder();
        if (searchedStudent.getGrade()>= 5.00){
            commentary.append("Excellent student");
        }else if (searchedStudent.getGrade()>= 3.50){
            commentary.append("Average student");
        }else {
            commentary.append("Very nice person");
        }
        return commentary.toString();
    }

}
