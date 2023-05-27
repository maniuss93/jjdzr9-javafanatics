package com.isa.jjdzr.rest;

import com.isa.jjdzr.dto.UserDto;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody UserDto user) {
        Optional<User> userFromDb = userService.findByUserName(user.getUserName());
        if (userFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll() {
        return userService.findAllUsers();
    }

    // http://localhost:8080/api/user/all
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getById(@PathVariable Long id) {
        return userService.findByUserId(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity editUser(@RequestBody UserDto user) {
        UserDto updated = userService.editUser(user);

        return updated != null ?
                ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(updated) :
                ResponseEntity
                        .badRequest()
                        .build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }
}
