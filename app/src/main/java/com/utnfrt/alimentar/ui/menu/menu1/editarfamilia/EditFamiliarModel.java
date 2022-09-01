package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.entity.family.RepositoryFamily;
import io.reactivex.Observable;

public class EditFamiliarModel implements EditFamiliarContract.Model {

    private RepositoryFamily repository;

    public EditFamiliarModel(RepositoryFamily repository){
        this.repository = repository;
    }

    @Override
    public Observable<Integer> editFamiliar(String idFamiliar, String nombre, String fecha_nacimiento, String peso, String altura) {
        if (!nombre.trim().matches("^[^\\\\/±!@£$%^&*_+§¡€#¢§¶•ªº«<>.?:;´`¨|=,¬~\\d]{3,50}$")) {
            return Observable.just(R.string.error_nombre);
        } else if (fecha_nacimiento.isEmpty()) {
            return Observable.just(R.string.error_fecha_nacimiento);
        } else if (peso.isEmpty()) {
            return Observable.just(R.string.error_peso);
        } else if (altura.isEmpty()) {
            return Observable.just(R.string.error_altura);
        } else {
            return Observable.wrap(repository.editFamiliar(idFamiliar, nombre, fecha_nacimiento, peso, altura)).map(crearFamiliarResponse -> R.string.ok);
        }
    }

    @Override
    public Observable<Integer> deleteFamiliar(int idFamiliar) {
        if (idFamiliar == 0) {
            return Observable.just(R.string.error_id_familiar);
        } else {
            return Observable.wrap(repository.deleteFamiliar(String.valueOf(idFamiliar))).map(deleteFamiliarResponse -> R.string.ok);
        }
    }
}
