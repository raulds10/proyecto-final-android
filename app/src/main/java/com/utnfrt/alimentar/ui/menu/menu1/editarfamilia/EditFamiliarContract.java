package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia;

import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface EditFamiliarContract {

    interface View extends MvpView {
        void editOk();
        void deleteOk();
    }

    interface Presenter <V extends EditFamiliarContract.View> extends MvpPresenter<V> {
        void editFamiliar(String idFamiliar, String nombre, String fecha_nacimiento, String peso, String altura);
        void deleteFamiliar(int idFamiliar);
    }

    interface Model {
        Observable<Integer> editFamiliar(String idFamiliar, String nombre, String fecha_nacimiento, String peso, String altura);
        Observable<Integer> deleteFamiliar(int idFamiliar);
    }

}
