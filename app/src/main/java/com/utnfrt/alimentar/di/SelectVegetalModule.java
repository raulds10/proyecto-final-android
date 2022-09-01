package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.hortaliza.RepositoryHortaliza;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.SelectVegetalContract;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.SelectVegetalModel;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.SelectVegetalPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class SelectVegetalModule {

    @Provides
    public SelectVegetalContract.Presenter providePresenter(SelectVegetalContract.Model model){
        return new SelectVegetalPresenter(model);
    }

    @Provides
    public SelectVegetalContract.Model provideModel(RepositoryHortaliza repository){
        return new SelectVegetalModel(repository);
    }

}
