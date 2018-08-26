package com.example.bucket.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name="t_promocion")
public class Promotion {

    @Id
    int id_promocion;
    String nombre;
    String descripcion;
    String estado;
    float precio;
    int precio_fichas;

    int cantidad_productos;

    @Transient
    private List<Product> producto;

    private String imagen;

    public int getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(int id_promocion) {
        this.id_promocion = id_promocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getPrecio_fichas() {
        return precio_fichas;
    }

    public void setPrecio_fichas(int precio_fichas) {
        this.precio_fichas = precio_fichas;
    }

    public List<Product> getProducto() {
        return producto;
    }

    public void setProducto(List<Product> producto) {
        this.producto = producto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad_productos() {
        return cantidad_productos;
    }

    public void setCantidad_productos(int cantidad_productos) {
        this.cantidad_productos = cantidad_productos;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id_promocion=" + id_promocion +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", precio=" + precio +
                ", precio_fichas=" + precio_fichas +
                ", cantidad_productos=" + cantidad_productos +
                ", producto=" + producto +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}

