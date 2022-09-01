package com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AfeccionFamiliarResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("familiar_afeccion")
    @Expose
    private FamiliarAfeccionAdd familiarAfeccion;

    public AfeccionFamiliarResponse() {}

    public AfeccionFamiliarResponse(String status, String message, FamiliarAfeccionAdd familiarAfeccion) {
        super();
        this.status = status;
        this.message = message;
        this.familiarAfeccion = familiarAfeccion;
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

    public FamiliarAfeccionAdd getFamiliarAfeccion() {
        return familiarAfeccion;
    }

    public void setFamiliarAfeccion(FamiliarAfeccionAdd familiarAfeccion) {
        this.familiarAfeccion = familiarAfeccion;
    }

}

class FamiliarAfeccionAdd {

    @SerializedName("id_familiar_afeccion")
    @Expose
    private Integer idFamiliarAfeccion;
    @SerializedName("id_familiar")
    @Expose
    private String idFamiliar;
    @SerializedName("id_afeccion")
    @Expose
    private String idAfeccion;

    public FamiliarAfeccionAdd() {}

    public FamiliarAfeccionAdd(Integer idFamiliarAfeccion, String idFamiliar, String idAfeccion) {
        super();
        this.idFamiliarAfeccion = idFamiliarAfeccion;
        this.idFamiliar = idFamiliar;
        this.idAfeccion = idAfeccion;
    }

    public Integer getIdFamiliarAfeccion() {
        return idFamiliarAfeccion;
    }

    public void setIdFamiliarAfeccion(Integer idFamiliarAfeccion) {
        this.idFamiliarAfeccion = idFamiliarAfeccion;
    }

    public String getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(String idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public String getIdAfeccion() {
        return idAfeccion;
    }

    public void setIdAfeccion(String idAfeccion) {
        this.idAfeccion = idAfeccion;
    }

}
