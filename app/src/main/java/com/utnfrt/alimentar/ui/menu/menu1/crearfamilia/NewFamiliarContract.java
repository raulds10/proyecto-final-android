package com.utnfrt.alimentar.ui.menu.menu1.crearfamilia;

import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface NewFamiliarContract {

    interface View extends MvpView {
        void familyCreated();
    }

    interface Presenter <V extends NewFamiliarContract.View> extends MvpPresenter<V> {
        void createFamiliar(String nombre, String fechaNac, String peso, String altura);
    }

    interface Model {
        Observable<Integer> createFamiliar(String nombre, String fechaNac, String peso, String altura);
    }
}
