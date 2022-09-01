package com.utnfrt.alimentar.data.api.authenticator;

public interface RepositoryOAuth {

    String getAccessToken();
    String refreshAccessToken();

}
