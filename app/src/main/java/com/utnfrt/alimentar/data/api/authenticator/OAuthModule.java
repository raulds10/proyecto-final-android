package com.utnfrt.alimentar.data.api.authenticator;

import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class OAuthModule {

    @Singleton
    @Provides
    @Named("provideOAuth")
    public RepositoryOAuth provideOAuthRepository(PrincipalSP pref, ApiHandler api){
        return new OAuthRepository(pref,api);
    }

}
