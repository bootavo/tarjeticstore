package com.example.bucket.controller;

import com.example.bucket.model.Rol;
import com.example.bucket.service.RolService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
