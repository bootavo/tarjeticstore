package com.example.bucket.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PromotionIdentity implements Serializable {

    private int id_promocion;
    private int id_producto;

    public int getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(int id_promocion) {
        this.id_promocion = id_promocion;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionIdentity that = (PromotionIdentity) o;

        return Objects.equals(getId_promocion(), that.getId_promocion()) &&
                Objects.equals(getId_producto(), that.getId_producto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_promocion(), getId_producto());
    }
}
