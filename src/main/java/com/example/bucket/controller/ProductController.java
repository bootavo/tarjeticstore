package com.example.bucket.controller;

import com.example.bucket.model.Product;
import com.example.bucket.service.ProductService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class ProductController {

    @Autowired private ProductService service;

    @GetMapping(path= {"/product", "/product/"})
    public @ResponseBody JsonObject getProducts() {
        return service.getProducts();
    }

    @PostMapping(path= {"/product", "/product/"})
    public @ResponseBody JsonObject registerProduct(@RequestBody Product request) {
        return service.registerProduct(request);
    }

    @GetMapping(path={"/product/{id_producto}","/product/{id_producto}"})
    public @ResponseBody JsonObject getProductById(@PathVariable(value="id_producto") int id_producto){
        return service.getProductById(id_producto);
    }

}
