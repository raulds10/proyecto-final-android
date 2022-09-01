package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.family.RepositoryFamily;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.EditFamiliarContract;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.EditFamiliarModel;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.EditFamiliarPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class EditFamiliarModule {

    @Provides
    public EditFamiliarContract.Presenter providePresenter(EditFamiliarContract.Model model){
        return new EditFamiliarPresenter(model);
    }

    @Provides
    public EditFamiliarContract.Model provideModel(RepositoryFamily repository){
        return new EditFamiliarModel(repository);
    }

}
