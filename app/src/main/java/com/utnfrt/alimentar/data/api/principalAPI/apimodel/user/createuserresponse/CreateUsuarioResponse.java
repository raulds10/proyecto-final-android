package com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUsuarioResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("usuario")
    @Expose
    private UsuarioCreate usuario;

    public CreateUsuarioResponse() {}

    public CreateUsuarioResponse(String status, String message, UsuarioCreate usuario) {
        super();
        this.status = status;
        this.message = message;
        this.usuario = usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsuarioCreate getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCreate usuario) {
        this.usuario = usuario;
    }

}
