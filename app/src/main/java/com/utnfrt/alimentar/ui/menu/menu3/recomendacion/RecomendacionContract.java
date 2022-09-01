package com.utnfrt.alimentar.ui.menu.menu3.recomendacion;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import java.util.List;
import io.reactivex.Observable;

public interface RecomendacionContract {

    interface View extends MvpView {
        void showMenuRecomendado(List<Integer> response);
        void showMenuComplete(DetailMenuResponse detailMenuResponse);
    }

    interface Presenter <V extends RecomendacionContract.View> extends MvpPresenter<V> {
        void getMenuRecomendado();
        void getMenuComplete(String idMenu);
    }

    interface Model {
        Observable<List<Integer>> getMenuRecomendado();
        Observable<DetailMenuResponse> getMenuComplete(String idMenu);
    }

}
