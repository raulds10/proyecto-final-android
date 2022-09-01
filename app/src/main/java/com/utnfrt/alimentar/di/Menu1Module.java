package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.entity.family.FamilyRepository;
import com.utnfrt.alimentar.data.entity.family.RepositoryFamily;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import com.utnfrt.alimentar.ui.menu.menu1.Menu1Contract;
import com.utnfrt.alimentar.ui.menu.menu1.Menu1Model;
import com.utnfrt.alimentar.ui.menu.menu1.Menu1Presenter;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class Menu1Module {

    @Provides
    public Menu1Contract.Presenter providePresenter(Menu1Contract.Model model){
        return new Menu1Presenter(model);
    }

    @Provides
    public Menu1Contract.Model provideModel(RepositoryFamily repository){
        return new Menu1Model(repository);
    }

    @Singleton
    @Provides
    public RepositoryFamily provideRepository(PrincipalApi api, PrincipalSP pref){
        return new FamilyRepository(api,pref);
    }

}
