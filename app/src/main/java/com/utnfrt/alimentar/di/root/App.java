package com.utnfrt.alimentar.di.root;

import android.app.Application;
import com.utnfrt.alimentar.data.api.authenticator.OAuthModule;
import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApiModule;
import com.utnfrt.alimentar.di.ConfirmVegetalModule;
import com.utnfrt.alimentar.di.DetailMenuModule;
import com.utnfrt.alimentar.di.EditFamiliarModule;
import com.utnfrt.alimentar.di.LoginModule;
import com.utnfrt.alimentar.di.MainModule;
import com.utnfrt.alimentar.di.Menu1Module;
import com.utnfrt.alimentar.di.Menu2Module;
import com.utnfrt.alimentar.di.Menu3Module;
import com.utnfrt.alimentar.di.NewFamiliarModule;
import com.utnfrt.alimentar.di.RecomendacionModule;
import com.utnfrt.alimentar.di.SelectVegetalModule;
import com.utnfrt.alimentar.di.ShowAfeccionesModule;
import com.utnfrt.alimentar.di.SignupModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .mainModule(new MainModule())
                .loginModule(new LoginModule())
                .signupModule(new SignupModule())
                .menu1Module(new Menu1Module())
                .newFamiliarModule(new NewFamiliarModule())
                .editFamiliarModule(new EditFamiliarModule())
                .showAfeccionesModule(new ShowAfeccionesModule())
                .menu2Module(new Menu2Module())
                .selectVegetalModule(new SelectVegetalModule())
                .confirmVegetalModule(new ConfirmVegetalModule())
                .menu3Module(new Menu3Module())
                .detailMenuModule(new DetailMenuModule())
                .recomendacionModule(new RecomendacionModule())
                .principalApiModule(new PrincipalApiModule())
                .oAuthModule(new OAuthModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
