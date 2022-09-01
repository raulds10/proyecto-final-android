package com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsuarioCreate {

    @SerializedName("id_usuario")
    @Expose
    private Integer idUsuario;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;

    public UsuarioCreate() {}

    public UsuarioCreate(Integer idUsuario, String nombre, String password, String email) {
        super();
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}