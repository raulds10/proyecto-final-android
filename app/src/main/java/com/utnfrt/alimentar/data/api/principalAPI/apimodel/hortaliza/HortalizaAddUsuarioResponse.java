package com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HortalizaAddUsuarioResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("usuario_hortaliza")
    @Expose
    private UsuarioHortalizaAdd usuarioHortaliza;

    public HortalizaAddUsuarioResponse() {}

    public HortalizaAddUsuarioResponse(String status, String message, UsuarioHortalizaAdd usuarioHortaliza) {
        super();
        this.status = status;
        this.message = message;
        this.usuarioHortaliza = usuarioHortaliza;
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

    public UsuarioHortalizaAdd getUsuarioHortaliza() {
        return usuarioHortaliza;
    }

    public void setUsuarioHortaliza(UsuarioHortalizaAdd usuarioHortaliza) {
        this.usuarioHortaliza = usuarioHortaliza;
    }

}

class UsuarioHortalizaAdd {

    @SerializedName("id_usuario_hortaliza")
    @Expose
    private Integer idUsuarioHortaliza;
    @SerializedName("id_usuario")
    @Expose
    private String idUsuario;
    @SerializedName("id_hortaliza")
    @Expose
    private String idHortaliza;
    @SerializedName("cantidad")
    @Expose
    private String cantidad;

    public UsuarioHortalizaAdd() {}

    public UsuarioHortalizaAdd(Integer idUsuarioHortaliza, String idUsuario, String idHortaliza, String cantidad) {
        super();
        this.idUsuarioHortaliza = idUsuarioHortaliza;
        this.idUsuario = idUsuario;
        this.idHortaliza = idHortaliza;
        this.cantidad = cantidad;
    }

    public Integer getIdUsuarioHortaliza() {
        return idUsuarioHortaliza;
    }

    public void setIdUsuarioHortaliza(Integer idUsuarioHortaliza) {
        this.idUsuarioHortaliza = idUsuarioHortaliza;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdHortaliza() {
        return idHortaliza;
    }

    public void setIdHortaliza(String idHortaliza) {
        this.idHortaliza = idHortaliza;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}