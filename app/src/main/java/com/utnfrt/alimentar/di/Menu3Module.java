package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.entity.menu.MenuRepository;
import com.utnfrt.alimentar.data.entity.menu.RepositoryMenu;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import com.utnfrt.alimentar.ui.menu.menu3.Menu3Contract;
import com.utnfrt.alimentar.ui.menu.menu3.Menu3Model;
import com.utnfrt.alimentar.ui.menu.menu3.Menu3Presenter;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class Menu3Module {

    @Provides
    public Menu3Contract.Presenter providePresenter(Menu3Contract.Model model){
        return new Menu3Presenter(model);
    }

    @Provides
    public Menu3Contract.Model provideModel(RepositoryMenu repository){
        return new Menu3Model(repository);
    }

    @Singleton
    @Provides
    public RepositoryMenu provideRepository(PrincipalApi api, PrincipalSP pref){
        return new MenuRepository(api,pref);
    }

}
