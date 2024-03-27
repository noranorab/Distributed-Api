package com.missingsemester.hashmapapi.controller;

import com.missingsemester.hashmapapi.api.controller.UserController;
import com.missingsemester.hashmapapi.api.model.User;
import com.missingsemester.hashmapapi.api.service.ShardingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private ShardingServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUser(){
        User user = new User(1, "User1");

        when(userService.getUser(1)).thenReturn(user);

        ResponseEntity<User> response = userController.getUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }
    @Test
    public void testGetUserNotFound() {

        when(userService.getUser(anyInt())).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(999);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateUser() {
        User user = new User(1, "John");
        doNothing().when(userService).createUser(user);

        ResponseEntity<Void> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(1);

        ResponseEntity<Void> response = userController.deleteUser(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
