package com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Familiares {

    @SerializedName("familiar")
    @Expose
    private List<Familiar> familiar = null;

    public Familiares() {}

    public Familiares(List<Familiar> familiar) {
        super();
        this.familiar = familiar;
    }

    public List<Familiar> getFamiliar() {
        return familiar;
    }

    public void setFamiliar(List<Familiar> familiar) {
        this.familiar = familiar;
    }

}