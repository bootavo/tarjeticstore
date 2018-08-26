package com.example.bucket.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_producto")
public class Product {

    @Id
    private int id_producto;
    private int id_categoria;
    private String nombre;
    private String descripcion;
    private float precio;
    private float precio_fichas;
    private String estado;
    private int stock;
    private String imagen;

    public Product() {
    }

    public Product(int id_producto, int id_categoria, String nombre, String descripcion, float precio, float precio_fichas, String estado, int stock, String imagen) {
        this.id_producto = id_producto;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.precio_fichas = precio_fichas;
        this.estado = estado;
        this.stock = stock;
        this.imagen = imagen;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio_fichas() {
        return precio_fichas;
    }

    public void setPrecio_fichas(float precio_fichas) {
        this.precio_fichas = precio_fichas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_producto=" + id_producto +
                ", id_categoria=" + id_categoria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", precio_fichas=" + precio_fichas +
                ", estado='" + estado + '\'' +
                ", stock=" + stock +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
