package com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HortalizasResponse {

    @SerializedName("hortalizas")
    @Expose
    private List<Hortaliza> hortalizas = null;

    public HortalizasResponse() {}

    public HortalizasResponse(List<Hortaliza> hortalizas) {
        super();
        this.hortalizas = hortalizas;
    }

    public List<Hortaliza> getHortalizas() {
        return hortalizas;
    }

    public void setHortalizas(List<Hortaliza> hortalizas) {
        this.hortalizas = hortalizas;
    }

}