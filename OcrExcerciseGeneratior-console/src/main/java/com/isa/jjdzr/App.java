package com.isa.jjdzr;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public class App {
    public static void main(String[] args) {


        //

        Exercises ex1 = new Exercises("some exercis ", "some description", "youtube.com");
        Person person = new Person("Paweł", "hard", "G", 34, ex1);

        System.out.println(ex1.addingExercisesInfo);

        // write to file

        Path path = Path.of("OcrExcerciseGeneratior-console","src", "main", "resources", "data.txt");

        String toSave = String.valueOf(person.toJson());

        try {
            Files.writeString(path, toSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read file
        readFromPath(path);
    }

    private static void readFromPath(Path path) {
        try {
            String value = Files.readString(path);
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


