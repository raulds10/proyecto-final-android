package com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AfeccionesResponse {

    @SerializedName("afecciones")
    @Expose
    private List<Afeccione> afecciones = null;

    public AfeccionesResponse() {}
    public AfeccionesResponse(List<Afeccione> afecciones) {
        super();
        this.afecciones = afecciones;
    }

    public List<Afeccione> getAfecciones() {
        return afecciones;
    }

    public void setAfecciones(List<Afeccione> afecciones) {
        this.afecciones = afecciones;
    }

}
