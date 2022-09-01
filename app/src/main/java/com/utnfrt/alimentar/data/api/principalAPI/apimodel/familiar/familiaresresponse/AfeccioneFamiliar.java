package com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AfeccioneFamiliar {

    @SerializedName("id_afeccion")
    @Expose
    private Integer idAfeccion;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    public AfeccioneFamiliar() {}

    public AfeccioneFamiliar(Integer idAfeccion, String nombre) {
        super();
        this.idAfeccion = idAfeccion;
        this.nombre = nombre;
    }

    public Integer getIdAfeccion() {
        return idAfeccion;
    }

    public void setIdAfeccion(Integer idAfeccion) {
        this.idAfeccion = idAfeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}