package com.isa.jjdzr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteAndReadFromFiles {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> readUserList () {
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
    public static void writeUserList(List <User> usersList) {
        try {
            objectMapper.writeValue(new FileWriter
                    ("src/main/resources/user.json"), usersList);
        } catch (IOException e) {
            System.out.println("Nie można utworzyć użytkownika");
        }
    }
}
