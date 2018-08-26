package com.example.bucket.model;

import java.util.List;

public class PromotionRequest {

    private Promotion promocion;
    private List<Product> productos;

    public Promotion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promotion promocion) {
        this.promocion = promocion;
    }

    public List<Product> getProductos() {
        return productos;
    }

    public void setProductos(List<Product> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "PromotionRequest{" +
                "promocion=" + promocion +
                ", productos=" + productos +
                '}';
    }
}
