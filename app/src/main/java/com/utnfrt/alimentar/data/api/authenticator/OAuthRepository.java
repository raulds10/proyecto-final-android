package com.utnfrt.alimentar.data.api.authenticator;

import com.utnfrt.alimentar.data.prefs.PrincipalSP;

public class OAuthRepository implements RepositoryOAuth {

    private ApiHandler api;
    private PrincipalSP pref;

    public OAuthRepository(PrincipalSP pref, ApiHandler api) {
        this.pref = pref;
        this.api = api;
    }

    @Override
    public String getAccessToken() {
        return pref.getToken();
    }

    @Override
    public synchronized String refreshAccessToken() {
        return null;
    }
}
