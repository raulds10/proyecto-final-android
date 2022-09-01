package com.utnfrt.alimentar.data.api.authenticator;

import androidx.annotation.Nullable;
import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;

public class ApiHandler {

    private PrincipalApi principalApi;

    @Nullable
    public PrincipalApi getPrincipalApi() {
        return principalApi;
    }

    public void setPrincipalApi(PrincipalApi principalApi) {
        this.principalApi = principalApi;
    }

}
