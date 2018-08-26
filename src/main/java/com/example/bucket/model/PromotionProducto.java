package com.example.bucket.model;

import javax.persistence.*;

@Entity
@Table(name="t_promocion_producto")
public class PromotionProducto {

    @EmbeddedId
    private PromotionIdentity id;

    private String estado;

    public PromotionIdentity getId() {
        return id;
    }

    public void setId(PromotionIdentity id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PromotionProducto{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                '}';
    }
}
