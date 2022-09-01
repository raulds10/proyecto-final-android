package com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.edituserresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditUsuarioResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("nuevosDatos")
    @Expose
    private NuevosDatosEditUser nuevosDatos;

    public EditUsuarioResponse() {}

    public EditUsuarioResponse(String status, String message, NuevosDatosEditUser nuevosDatos) {
        super();
        this.status = status;
        this.message = message;
        this.nuevosDatos = nuevosDatos;
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

    public NuevosDatosEditUser getNuevosDatos() {
        return nuevosDatos;
    }

    public void setNuevosDatos(NuevosDatosEditUser nuevosDatos) {
        this.nuevosDatos = nuevosDatos;
    }

}