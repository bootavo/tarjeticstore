package com.example.bucket.controller;

import com.example.bucket.model.PromotionRequest;
import com.example.bucket.service.PromotionService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class PromotionController {

    @Autowired PromotionService service;

    @PostMapping(path={"promotion","promotion/"})
    public @ResponseBody JsonObject registerPromotion(@RequestBody PromotionRequest request){
        return service.registerPromotion(request);
    }

    @GetMapping(path={"promotion","promotion/"})
    public @ResponseBody JsonObject getPromotions(){
        return service.getPromotionsModified();
    }

}
