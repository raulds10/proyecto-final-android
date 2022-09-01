package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.entity.afeccion.AfeccionRepository;
import com.utnfrt.alimentar.data.entity.afeccion.RepositoryAfeccion;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones.ShowAfeccionesContract;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones.ShowAfeccionesModel;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones.ShowAfeccionesPresenter;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ShowAfeccionesModule {

    @Provides
    public ShowAfeccionesContract.Presenter providePresenter(ShowAfeccionesContract.Model model){
        return new ShowAfeccionesPresenter(model);
    }

    @Provides
    public ShowAfeccionesContract.Model provideModel(RepositoryAfeccion repository){
        return new ShowAfeccionesModel(repository);
    }

    @Singleton
    @Provides
    public RepositoryAfeccion provideRepository(PrincipalApi api, PrincipalSP pref){
        return new AfeccionRepository(api,pref);
    }

}
