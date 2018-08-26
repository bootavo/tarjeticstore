package com.example.bucket.controller;

import com.example.bucket.model.UserRequest;
import com.example.bucket.service.UserService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api") //
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(path= {"/login", "/login/"})
    public @ResponseBody JsonObject login (@RequestBody UserRequest userRequest) {
        return service.login(userRequest);
    }

    @GetMapping(path= {"/user/{id_usuario}", "/user/{id_usuario}/"})
    public @ResponseBody JsonObject getUserById(@PathVariable(value="id_usuario") int id_usuario){
        return service.getUserById(id_usuario);
    }

    @GetMapping(path= {"/user", "/user/"})
    public @ResponseBody JsonObject getUsers(){
        return service.getUsers();
    }

    @PostMapping(path= {"/user", "/user/"})
    public @ResponseBody JsonObject registerUser(@RequestBody UserRequest userRequest){
        return service.registerUser(userRequest);
    }

}
