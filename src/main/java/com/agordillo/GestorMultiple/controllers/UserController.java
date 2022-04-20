package com.agordillo.GestorMultiple.controllers;

import java.util.List;

import com.agordillo.GestorMultiple.models.User;
import com.agordillo.GestorMultiple.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String newUser(@RequestBody User user) throws Exception{
        return this.userService.save(user);
    }

    @GetMapping("/get")
    public User getUser(@RequestBody String userId) throws Exception{
        return this.userService.get(userId);
    }
    
    @PostMapping(value = "/registerNewUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean authenticate(@RequestBody User user) {
    	return this.userService.registerNewUser(user);
    }

    @GetMapping("/getAll")
    public List<User> getAllUser() throws Exception{
        return this.userService.getAll();
    }

    @PostMapping("/update")
    public boolean updateUser(@RequestBody String user) throws Exception{
        return this.userService.update(user);
    }

    @PutMapping("/delete")
    public void deleteUser(@RequestBody String userId) throws Exception{
        this.userService.delete(userId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test endPoint OK!");
    }



}
