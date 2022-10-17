package exams.april_2021.university;

import java.util.ArrayList;
import java.util.List;

public class University {
    public int capacity;
    public List<Student> students;

    public University(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getStudentCount(){
        return getStudents().size();
    }

    public String registerStudent(Student student) {
        StringBuilder builder = new StringBuilder();

        if (this.students.contains(student)) {
            builder.append("Student is already in the exams.april_2021.university");
        } else if (getStudents().size() >= getCapacity()) {
            builder.append("No seats in the exams.april_2021.university");
        }else if (getStudents().size() < getCapacity()) {
            this.students.add(student);
            builder.append(String.format("Added student %s %s", student.getFirstName(), student.getLastName()));
        }
        return builder.toString().trim();
    }

    public String dismissStudent(Student student){
        if (this.students.contains(student)){
            this.students.remove(student);
            return String.format("Removed student %s %s", student.getFirstName(), student.getLastName());
        }else {
            return "Student not found";
        }
    }

    public Student getStudent(String firstName, String lastName){
      return this.students.stream()
                .filter(s->s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        for (Student student : this.students) {
         String str =  String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s"
            , student.getFirstName(), student.getLastName(), student.getBestSubject());

         builder.append(str).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
