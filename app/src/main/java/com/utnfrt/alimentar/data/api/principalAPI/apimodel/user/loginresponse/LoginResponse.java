package com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.loginresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("usuario")
    @Expose
    private UsuarioLogin usuario;

    public LoginResponse() {}

    public LoginResponse(UsuarioLogin usuario) {
        super();
        this.usuario = usuario;
    }

    public UsuarioLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioLogin usuario) {
        this.usuario = usuario;
    }

}