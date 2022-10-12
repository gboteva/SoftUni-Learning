package exams.june2022.workout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Workout {
    private String type;
    private int exerciseCount;
    List<Exercise> exercises;

    public Workout(String type, int exerciseCount) {
        this.type = type;
        this.exerciseCount = exerciseCount;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise){
        if (this.exercises.size()<exerciseCount){
            this.exercises.add(exercise);
        }
    }

    public boolean removeExercise(String name, String muscle){
        for (Exercise ex : this.exercises) {
            if (ex.getName().equals(name) && ex.getMuscle().equals(muscle)){
                this.exercises.remove(ex);
                return true;
            }
        }
        return false;
    }

    public Exercise getExercise(String name, String muscle){
        for (Exercise ex : this.exercises) {
            if (ex.getName().equals(name) && ex.getMuscle().equals(muscle)){
                return ex;
            }
        }
        return null;
    }

    public Exercise getMostBurnedCaloriesExercise(){
        return this.exercises.stream()
                .max(Comparator.comparing(Exercise::getBurnedCalories))
                .orElse(null);
    }

    public int getExerciseCount(){
        return this.exercises.size();
    }

    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Workout type: %s", this.type));
        for (Exercise exercise : exercises) {
           builder.append("\n").append(exercise);
        }
        return builder.toString().trim();
    }
}
