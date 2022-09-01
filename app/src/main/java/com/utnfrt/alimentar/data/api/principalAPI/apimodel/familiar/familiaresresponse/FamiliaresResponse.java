package com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FamiliaresResponse {

    @SerializedName("familiares")
    @Expose
    private Familiares familiares;

    public FamiliaresResponse() {}

    public FamiliaresResponse(Familiares familiares) {
        super();
        this.familiares = familiares;
    }

    public Familiares getFamiliares() {
        return familiares;
    }

    public void setFamiliares(Familiares familiares) {
        this.familiares = familiares;
    }
}