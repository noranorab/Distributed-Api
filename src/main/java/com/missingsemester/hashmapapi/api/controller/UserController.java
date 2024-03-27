package com.missingsemester.hashmapapi.api.controller;


import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.service.ShardingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ShardingServiceImpl userService;

    @Autowired
    public UserController(ShardingServiceImpl userService)
    {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id)
    {
        User user = userService.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user); // Return user with status 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }

    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user){

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
