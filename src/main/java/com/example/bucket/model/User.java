package com.example.bucket.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_usuario")
public class User {

    @Id
    private int id_usuario;
    private String usuario;
    private String clave;
    private String estado;
    private int id_rol;
    private int id_persona;
    private int pedidos_cancelados;
    private String codigo_app;
    private String tipo_registro;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getPedidos_cancelados() {
        return pedidos_cancelados;
    }

    public void setPedidos_cancelados(int pedidos_cancelados) {
        this.pedidos_cancelados = pedidos_cancelados;
    }

    public String getCodigo_app() {
        return codigo_app;
    }

    public void setCodigo_app(String codigo_app) {
        this.codigo_app = codigo_app;
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_usuario=" + id_usuario +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", estado='" + estado + '\'' +
                ", id_rol=" + id_rol +
                ", id_persona=" + id_persona +
                ", pedidos_cancelados=" + pedidos_cancelados +
                ", codigo_app='" + codigo_app + '\'' +
                ", tipo_registro='" + tipo_registro + '\'' +
                '}';
    }
}
