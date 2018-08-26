package com.example.bucket.repository;

import com.example.bucket.model.Persona;
import com.example.bucket.model.UserRequest;
import com.google.gson.JsonObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {



}
