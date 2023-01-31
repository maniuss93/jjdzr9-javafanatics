package com.isa.jjdzr;

import javax.json.Json;
import javax.json.JsonObject;

public class Exercises {
    String addingExercisesInfo = "This will be the Adding Exercises Class";

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String description;
    private String url;
    public Exercises(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }
    public JsonObject toJSON() {
        JsonObject json = Json.createObjectBuilder()
                .add("name", this.name)
                .add("description", this.description)
                .add("url", this.url).build();
        return json;
    }

}
