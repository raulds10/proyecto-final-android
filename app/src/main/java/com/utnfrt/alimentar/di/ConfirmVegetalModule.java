package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.hortaliza.RepositoryHortaliza;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm.ConfirmVegetalContract;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm.ConfirmVegetalModel;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm.ConfirmVegetalPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ConfirmVegetalModule {

    @Provides
    public ConfirmVegetalContract.Presenter providePresenter(ConfirmVegetalContract.Model model){
        return new ConfirmVegetalPresenter(model);
    }

    @Provides
    public ConfirmVegetalContract.Model provideModel(RepositoryHortaliza repository){
        return new ConfirmVegetalModel(repository);
    }

}
