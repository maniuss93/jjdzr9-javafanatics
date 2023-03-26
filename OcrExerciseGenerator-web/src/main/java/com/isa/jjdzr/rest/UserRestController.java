package com.isa.jjdzr.rest;

import com.isa.jjdzr.service.UserService;
import com.isa.jjdzr.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserRestController {

    private final UserService userService;

    // http://localhost:8080/api/user/new
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody User user) {
        Optional<User> userFromDB = userService.findByUserName(user.getUserName());
        if (userFromDB.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

    // http://localhost:8080/api/user/all
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return userService.findAll();
    }

    // http://localhost:8080/api/user/delete/id
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }
}
