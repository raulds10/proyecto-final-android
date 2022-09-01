package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizasResponse;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface SelectVegetalContract {

    interface View extends MvpView {
        void showVegetales(HortalizasResponse response);
    }

    interface Presenter <V extends SelectVegetalContract.View> extends MvpPresenter<V> {
        void getHortalizas();
    }

    interface Model {
        Observable<HortalizasResponse> getHortalizas();
    }

}
