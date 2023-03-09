package com.isa.jjdzr.user.model;

import com.isa.jjdzr.exercise.model.Exercise;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private int userID;
    private String userName;
    private String userPassword;
    private String userEmail;
    private int userAdvancementLevel;
    private List<Exercise> userExercises;
}
