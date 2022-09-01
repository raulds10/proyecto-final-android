package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionesResponse;
import com.utnfrt.alimentar.data.entity.afeccion.RepositoryAfeccion;
import io.reactivex.Observable;

public class ShowAfeccionesModel implements ShowAfeccionesContract.Model {

    private RepositoryAfeccion repository;

    public ShowAfeccionesModel(RepositoryAfeccion repository){
        this.repository = repository;
    }

    @Override
    public Observable<Integer> addAfeccion(String idFamiliar, String idAfeccion) {
        return Observable.wrap(repository.addAfeccionToFamiliar(idFamiliar, idAfeccion)).map(response -> R.string.ok);
    }

    @Override
    public Observable<AfeccionesResponse> getAfecciones() {
        return Observable.wrap(repository.obtainAfecciones());
    }
}
