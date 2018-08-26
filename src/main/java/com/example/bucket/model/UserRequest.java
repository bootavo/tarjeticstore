package com.example.bucket.model;

import javax.persistence.*;

@Entity
public class UserRequest {

    @Id
    private int id_persona;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String dni;
    private String imagen;
    private String correo;
    private String telefono_contacto;

    private int id_usuario;
    private String usuario;
    private String clave;
    private String estado;
    private String codigo_app;
    private int pedidos_cancelados;
    private String tipo_registro;
    private int id_rol;

    private int total_fichas;

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

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

    public String getCodigo_app() {
        return codigo_app;
    }

    public void setCodigo_app(String codigo_app) {
        this.codigo_app = codigo_app;
    }

    public int getPedidos_cancelados() {
        return pedidos_cancelados;
    }

    public void setPedidos_cancelados(int pedidos_cancelados) {
        this.pedidos_cancelados = pedidos_cancelados;
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getTotal_fichas() {
        return total_fichas;
    }

    public void setTotal_fichas(int total_fichas) {
        this.total_fichas = total_fichas;
    }

    @Override
    public String
    toString() {
        return "UserRequest{" +
                "id_persona=" + id_persona +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", imagen='" + imagen + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono_contacto='" + telefono_contacto + '\'' +
                ", id_usuario=" + id_usuario +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", estado='" + estado + '\'' +
                ", codigo_app='" + codigo_app + '\'' +
                ", pedidos_cancelados=" + pedidos_cancelados +
                ", tipo_registro='" + tipo_registro + '\'' +
                ", id_rol=" + id_rol +
                ", total_fichas=" + total_fichas +
                '}';
    }
}
