package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.menu.RepositoryMenu;
import com.utnfrt.alimentar.ui.menu.menu3.recomendacion.RecomendacionContract;
import com.utnfrt.alimentar.ui.menu.menu3.recomendacion.RecomendacionModel;
import com.utnfrt.alimentar.ui.menu.menu3.recomendacion.RecomendacionPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class RecomendacionModule {

    @Provides
    public RecomendacionContract.Presenter providePresenter(RecomendacionContract.Model model){
        return new RecomendacionPresenter(model);
    }

    @Provides
    public RecomendacionContract.Model provideModel(RepositoryMenu repository){
        return new RecomendacionModel(repository);
    }

}
