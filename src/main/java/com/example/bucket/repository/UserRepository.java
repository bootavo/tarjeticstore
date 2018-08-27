package com.example.bucket.repository;

import com.example.bucket.model.Persona;
import com.example.bucket.model.User;
import com.google.gson.JsonObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserRepository extends CrudRepository<User, Integer> {

}
