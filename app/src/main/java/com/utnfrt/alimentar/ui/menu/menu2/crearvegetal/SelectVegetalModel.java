package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizasResponse;
import com.utnfrt.alimentar.data.entity.hortaliza.RepositoryHortaliza;
import io.reactivex.Observable;

public class SelectVegetalModel implements SelectVegetalContract.Model {

    private RepositoryHortaliza repository;

    public SelectVegetalModel(RepositoryHortaliza repository){
        this.repository = repository;
    }

    @Override
    public Observable<HortalizasResponse> getHortalizas() {
        return Observable.wrap(repository.obtainHortalizas());
    }
}