package com.isa.jjdzr;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;



public class App {
    public static void main(String[] args) throws IOException {


        //

        Exercises ex1 = new Exercises("some exercis ", "some description", "youtube.com");
        Person person = new Person("Paweł", "hard", "G", 34, ex1);

        System.out.println(ex1.addingExercisesInfo);

        // write to file

        Path path = Path.of("OcrExcerciseGeneratior-console","src", "main", "resources", "data.txt");

        String toSave = String.valueOf(person.toJson());

        try {
            Files.writeString(path, toSave);
        } catch (FileSystemException exception) {
            System.out.println("Error! Please check file with data.");;
        }
        // read file
        readFromPath(path);
    }

    private static void readFromPath(Path path) {
        try {
            String value = Files.readString(path);
            System.out.println(value);
        } catch (IOException exception) {
            System.out.println("Error! Please check file with data.");;
        }

    }
}


