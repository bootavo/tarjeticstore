package com.example.bucket.repository;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PromotionExtraRepository {

    @ResponseBody JsonObject getPromotionsModified();
    @ResponseBody JsonObject getPromotionById(int id_promocion);

}
