package com.example.bucket.repository;

import com.example.bucket.model.Product;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ProductExtraRepository {

    @ResponseBody JsonObject getProducts();
    @ResponseBody JsonObject registerProduct(Product request);
    @ResponseBody JsonObject getProductById(int id_producto);

}
