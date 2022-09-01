package com.utnfrt.alimentar.ui.menu.menu3.recomendacion;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.data.entity.menu.RepositoryMenu;
import java.util.List;
import io.reactivex.Observable;

public class RecomendacionModel implements RecomendacionContract.Model {

    private RepositoryMenu repository;

    public RecomendacionModel(RepositoryMenu repository){
        this.repository = repository;
    }

    @Override
    public Observable<List<Integer>> getMenuRecomendado() {
        return Observable.wrap(repository.obtainListMenu());
    }

    @Override
    public Observable<DetailMenuResponse> getMenuComplete(String idMenu) {
        return Observable.wrap(repository.detailMenu(idMenu));
    }
}