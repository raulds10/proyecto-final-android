package com.utnfrt.alimentar.di.root;

import android.app.Application;
import android.content.Context;
import com.utnfrt.alimentar.data.api.authenticator.ApiHandler;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application app){
        this.application = app;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }

    @Provides
    @Singleton
    PrincipalSP provideSharedPrefs() {
        return new PrincipalSP(application.getSharedPreferences("PRINCIPAL_SP",Context.MODE_PRIVATE));
    }

    @Provides
    @Singleton
    ApiHandler provideApiHandler(){
        return new ApiHandler();
    }

}
