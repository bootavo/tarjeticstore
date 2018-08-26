package com.example.bucket.repository;

import com.example.bucket.model.Rol;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

public interface RolRepository extends CrudRepository<Rol, Integer> {

}
