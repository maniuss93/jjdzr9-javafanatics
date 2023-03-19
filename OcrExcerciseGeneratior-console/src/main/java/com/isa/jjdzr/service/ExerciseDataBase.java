package com.isa.jjdzr.service;

import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.utils.WriteAndReadFromFile;

import java.util.List;

public class ExerciseDataBase {
    private static final List<Exercise> exerciseList = WriteAndReadFromFile.readExercisesList();

    public static List<Exercise> getExerciseList(){
        return exerciseList;
    }
    public static List<Exercise> getUserExerciseList(User user){
        return WriteAndReadFromFile.readUserExerciseList(user.getUserName());
    }
    public static void saveNewExerciseToDataBase(Exercise exercise){
        exerciseList.add(exercise);
        WriteAndReadFromFile.writeExercisesList(exerciseList);
    }
    public static List<Exercise> findExerciseByCategory(String category){
        return exerciseList.stream().filter(c->c.getCategory().equals(category)).toList();
    }
}
