package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.entity.hortaliza.RepositoryHortaliza;
import io.reactivex.Observable;

public class ConfirmVegetalModel implements ConfirmVegetalContract.Model {

    private RepositoryHortaliza repository;

    public ConfirmVegetalModel(RepositoryHortaliza repository){
        this.repository = repository;
    }

    @Override
    public Observable<Integer> createHortaliza(String idHortaliza, String cantidad) {
        if (cantidad.isEmpty()) {
            return Observable.just(R.string.error_cantidad_horataliza);
        } else if (Integer.parseInt(cantidad) < 10 ) {
            return Observable.just(R.string.error_cantidad_horataliza);
        } else if (Integer.parseInt(cantidad) > 50000) {
            return Observable.just(R.string.error_cantidad_horataliza);
        } else {
            return Observable.wrap(repository.addhortalizaUser(idHortaliza, cantidad)).map(response -> R.string.ok);
        }
    }
}
