package com.example.bucket.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_inventario")
public class Inventory {

    @Id
    private int id_inventario;
    private int id_usuario;
    private int total_fichas;
    private String estado;

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getTotal_fichas() {
        return total_fichas;
    }

    public void setTotal_fichas(int total_fichas) {
        this.total_fichas = total_fichas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id_inventario=" + id_inventario +
                ", id_usuario=" + id_usuario +
                ", total_fichas=" + total_fichas +
                ", estado='" + estado + '\'' +
                '}';
    }
}
