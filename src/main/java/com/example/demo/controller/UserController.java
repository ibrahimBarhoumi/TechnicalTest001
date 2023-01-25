package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-operations")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) throws NotFoundException {
        return userService.findById(id);
    }

    @GetMapping("/")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userService.save(user);
    }
}
