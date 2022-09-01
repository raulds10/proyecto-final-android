package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.family.RepositoryFamily;
import com.utnfrt.alimentar.ui.menu.menu1.crearfamilia.NewFamiliarContract;
import com.utnfrt.alimentar.ui.menu.menu1.crearfamilia.NewFamiliarModel;
import com.utnfrt.alimentar.ui.menu.menu1.crearfamilia.NewFamiliarPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class NewFamiliarModule {

    @Provides
    public NewFamiliarContract.Presenter providePresenter(NewFamiliarContract.Model model){
        return new NewFamiliarPresenter(model);
    }

    @Provides
    public NewFamiliarContract.Model provideModel(RepositoryFamily repository){
        return new NewFamiliarModel(repository);
    }

}
