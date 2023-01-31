package com.isa.jjdzr;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Person {
    private String name;
    private String  level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public Exercises getExercises() {
        return exercises;
    }

    public void setExercises(Exercises exercises) {
        this.exercises = exercises;
    }

    private int age;
    private String male;

    private Exercises exercises;

    public Person(String name, String level, String male, int age,  Exercises exercises) {
        this.name = name;
        this.level = level;
        this.male = male;
        this.age = age;
        this.exercises = exercises;

    }
    public JsonObject toJson() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        JsonObject json = Json.createObjectBuilder()
                .add("name", name)
                .add("level", level)
                .add("male", male)
                .add("age", age)
                .add("exercises", this.exercises.toJSON()).build();
        return json;
    }
}
