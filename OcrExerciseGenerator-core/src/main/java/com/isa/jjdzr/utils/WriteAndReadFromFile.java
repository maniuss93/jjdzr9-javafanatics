package com.isa.jjdzr.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.jjdzr.exercise.model.Exercise;
import com.isa.jjdzr.user.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteAndReadFromFile {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> readUserList() {
        List<User> usersList = null;
        try {
            usersList = objectMapper.readValue
                    (new File("src/main/resources/user.json"), new TypeReference<>() {
                    });
        } catch (IOException e) {
            System.out.println("Nie można wczytać listy użytkowników");
        }
        return usersList;
    }

    public static List<Exercise> readExercisesList() {
        List<Exercise> exerciseList = null;
        try {
            exerciseList = objectMapper.readValue
                    (new File("src/main/resources/exercises.json"), new TypeReference<>() {
                    });
        } catch (IOException e) {
            System.out.println("Nie można wczytać listy ćwiczeń");
        }
        return exerciseList;
    }

    public static List<Exercise> readUserExerciseList(String userName) {
        List<Exercise> userExerciseList = null;
        try {
            userExerciseList = objectMapper.readValue
                    (new File("src/main/resources/" + userName + ".json"), new TypeReference<>() {
                    });
        } catch (IOException ignored) {}
        return userExerciseList;
    }

    public static void writeUserExerciseList(String userName, List<Exercise> userExerciseList) {
        try {
            objectMapper.writeValue(new FileWriter
                    ("src/main/resources/" + userName + ".json"), userExerciseList);
        } catch (IOException e) {
            System.out.println("Nie można zapisać ćwiczeń");
        }
    }

    public static void writeUserList(List<User> usersList) {
        try {
            objectMapper.writeValue(new FileWriter
                    ("src/main/resources/user.json"), usersList);
        } catch (IOException e) {
            System.out.println("Nie można utworzyć użytkownika");
        }
    }

    public static void writeExercisesList(List<Exercise> exerciseList) {
        try {
            objectMapper.writeValue(new FileWriter
                    ("src/main/resources/exercises.json"), exerciseList);
        } catch (IOException e) {
            System.out.println("Nie można utworzyć ćwiczenia");
        }
    }
}
