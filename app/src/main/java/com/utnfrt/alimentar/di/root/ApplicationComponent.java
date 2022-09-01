package com.utnfrt.alimentar.di.root;

import com.utnfrt.alimentar.data.api.authenticator.OAuthModule;
import com.utnfrt.alimentar.di.ConfirmVegetalModule;
import com.utnfrt.alimentar.di.DetailMenuModule;
import com.utnfrt.alimentar.di.EditFamiliarModule;
import com.utnfrt.alimentar.di.LoginModule;
import com.utnfrt.alimentar.di.Menu1Module;
import com.utnfrt.alimentar.di.Menu2Module;
import com.utnfrt.alimentar.di.Menu3Module;
import com.utnfrt.alimentar.di.NewFamiliarModule;
import com.utnfrt.alimentar.di.RecomendacionModule;
import com.utnfrt.alimentar.di.SelectVegetalModule;
import com.utnfrt.alimentar.di.ShowAfeccionesModule;
import com.utnfrt.alimentar.di.SignupModule;
import com.utnfrt.alimentar.ui.login.LoginActivity;
import com.utnfrt.alimentar.ui.main.MainActivity;
import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApiModule;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import com.utnfrt.alimentar.di.MainModule;
import com.utnfrt.alimentar.ui.menu.menu1.Menu1Fragment;
import com.utnfrt.alimentar.ui.menu.menu1.crearfamilia.NewFamiliarActivity;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.EditFamiliarActivity;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones.ShowAfeccionesActivity;
import com.utnfrt.alimentar.ui.menu.menu2.Menu2Fragment;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.SelectVegetalActivity;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm.ConfirmVegetalActivity;
import com.utnfrt.alimentar.ui.menu.menu3.Menu3Fragment;
import com.utnfrt.alimentar.ui.menu.menu3.detailmenu.DetailMenuActivity;
import com.utnfrt.alimentar.ui.menu.menu3.recomendacion.RecomendacionActivity;
import com.utnfrt.alimentar.ui.signup.SignupActivity;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        MainModule.class,
        LoginModule.class,
        SignupModule.class,
        Menu1Module.class,
        NewFamiliarModule.class,
        EditFamiliarModule.class,
        ShowAfeccionesModule.class,
        Menu2Module.class,
        SelectVegetalModule.class,
        ConfirmVegetalModule.class,
        Menu3Module.class,
        DetailMenuModule.class,
        RecomendacionModule.class,
        PrincipalApiModule.class,
        OAuthModule.class
})
public interface ApplicationComponent {

    PrincipalSP getSharedPrefs();
    void injectMain(MainActivity activity);
    void injectLogin(LoginActivity activity);
    void injectSignup(SignupActivity activity);
    void injectMenu1(Menu1Fragment fragment);
    void injectNewFamiliar(NewFamiliarActivity activity);
    void injectEditFamiliar(EditFamiliarActivity activity);
    void injectShowAfecciones(ShowAfeccionesActivity activity);
    void injectMenu2(Menu2Fragment fragment);
    void injectSelectVegetal(SelectVegetalActivity activity);
    void injectConfirmVegetal(ConfirmVegetalActivity activity);
    void injectMenu3(Menu3Fragment fragment);
    void injectDetailMenu(DetailMenuActivity activity);
    void injectRecomendacion(RecomendacionActivity activity);
}
