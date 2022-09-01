package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm;

import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.EditFamiliarContract;

import io.reactivex.Observable;

public interface ConfirmVegetalContract {

    interface View extends MvpView {
        void createOk();
    }

    interface Presenter <V extends ConfirmVegetalContract.View> extends MvpPresenter<V> {
        void createHortaliza(String idHortaliza, String cantidad);
    }

    interface Model {
        Observable<Integer> createHortaliza(String idHortaliza, String cantidad);
    }

}
