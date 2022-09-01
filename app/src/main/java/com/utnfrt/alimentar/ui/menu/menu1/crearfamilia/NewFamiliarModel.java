package com.utnfrt.alimentar.ui.menu.menu1.crearfamilia;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.entity.family.RepositoryFamily;
import io.reactivex.Observable;

public class NewFamiliarModel implements NewFamiliarContract.Model {

    private RepositoryFamily repository;

    public NewFamiliarModel(RepositoryFamily repository){
        this.repository = repository;
    }

    @Override
    public Observable<Integer> createFamiliar(String nombre, String fechaNac, String peso, String altura) {

        if (!nombre.trim().matches("^[^\\\\/±!@£$%^&*_+§¡€#¢§¶•ªº«<>.?:;´`¨|=,¬~\\d]{3,50}$")) {
            return Observable.just(R.string.error_nombre);
        } else if (fechaNac.isEmpty()) {
            return Observable.just(R.string.error_fecha_nacimiento);
        } else if (peso.isEmpty()) {
            return Observable.just(R.string.error_peso);
        } else if (altura.isEmpty()) {
            return Observable.just(R.string.error_altura);
        } else {
            return Observable.wrap(repository.createFamiliar(nombre, fechaNac, peso, altura)).map(crearFamiliarResponse -> R.string.ok);
        }
    }
}

