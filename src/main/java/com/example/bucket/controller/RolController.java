package com.example.bucket.controller;

import com.example.bucket.model.Rol;
import com.example.bucket.service.RolService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class RolController {

    @Autowired RolService service;

    @RequestMapping(value = {"/rol", "/rol/"}, method = RequestMethod.GET)
    public @ResponseBody JsonObject getRols() {
        return service.getRols();
    }

    @RequestMapping(value = {"/rol", "/rol/"}, method = RequestMethod.POST)
    public @ResponseBody JsonObject registerRol(@RequestBody Rol rol) {
        return service.registerRol(rol);
    }

    @GetMapping(value = {"/rol/{id_rol}","/rol/{id_rol}/"})
    public @ResponseBody JsonObject getRolById(@PathVariable(value = "id_rol") int id_rol){
        return service.getRolById(id_rol);
    }

}
