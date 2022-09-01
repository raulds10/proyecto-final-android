package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.entity.hortaliza.HortalizaRepository;
import com.utnfrt.alimentar.data.entity.hortaliza.RepositoryHortaliza;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import com.utnfrt.alimentar.ui.menu.menu2.Menu2Contract;
import com.utnfrt.alimentar.ui.menu.menu2.Menu2Model;
import com.utnfrt.alimentar.ui.menu.menu2.Menu2Presenter;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class Menu2Module {

    @Provides
    public Menu2Contract.Presenter providePresenter(Menu2Contract.Model model){
        return new Menu2Presenter(model);
    }

    @Provides
    public Menu2Contract.Model provideModel(RepositoryHortaliza repository){
        return new Menu2Model(repository);
    }

    @Singleton
    @Provides
    public RepositoryHortaliza provideRepository(PrincipalApi api, PrincipalSP pref){
        return new HortalizaRepository(api,pref);
    }

}
