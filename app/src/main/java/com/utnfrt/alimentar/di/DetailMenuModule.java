package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.menu.RepositoryMenu;
import com.utnfrt.alimentar.ui.menu.menu3.detailmenu.DetailMenuContract;
import com.utnfrt.alimentar.ui.menu.menu3.detailmenu.DetailMenuModel;
import com.utnfrt.alimentar.ui.menu.menu3.detailmenu.DetailMenuPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailMenuModule {

    @Provides
    public DetailMenuContract.Presenter providePresenter(DetailMenuContract.Model model){
        return new DetailMenuPresenter(model);
    }

    @Provides
    public DetailMenuContract.Model provideModel(RepositoryMenu repository){
        return new DetailMenuModel(repository);
    }

}
