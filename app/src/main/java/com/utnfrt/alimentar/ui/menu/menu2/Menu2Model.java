package com.utnfrt.alimentar.ui.menu.menu2;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import com.utnfrt.alimentar.data.entity.hortaliza.RepositoryHortaliza;

import java.util.List;

import io.reactivex.Observable;

public class Menu2Model implements Menu2Contract.Model {

    private RepositoryHortaliza repository;

    public Menu2Model(RepositoryHortaliza repository){
        this.repository = repository;
    }

    @Override
    public Observable<List<HortalizaUsuarioResponse>> getHortalizas() {
        return repository.obtainHortalizaUser();
    }
}
