package com.example.bucket.repository;

import com.example.bucket.model.UserRequest;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserExtraRepository {

    @ResponseBody JsonObject login(UserRequest userRequest);
    @ResponseBody JsonObject getUserById(int id_usuario);
    @ResponseBody JsonObject registerUser(UserRequest userRequest);
    @ResponseBody JsonObject getUsers();

}
