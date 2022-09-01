package com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HortalizaUsuarioResponse {

    @SerializedName("id_hortaliza")
    @Expose
    private Integer idHortaliza;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("calorias")
    @Expose
    private Integer calorias;

    public HortalizaUsuarioResponse() {}

    public HortalizaUsuarioResponse(Integer idHortaliza, String nombre, Integer calorias) {
        super();
        this.idHortaliza = idHortaliza;
        this.nombre = nombre;
        this.calorias = calorias;
    }

    public Integer getIdHortaliza() {
        return idHortaliza;
    }

    public void setIdHortaliza(Integer idHortaliza) {
        this.idHortaliza = idHortaliza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

}
