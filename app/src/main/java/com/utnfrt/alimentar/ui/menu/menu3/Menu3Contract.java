package com.utnfrt.alimentar.ui.menu.menu3;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.ListMenuResponse;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface Menu3Contract {

    interface View extends MvpView {
        void showMenus(ListMenuResponse response);
    }

    interface Presenter <V extends Menu3Contract.View> extends MvpPresenter<V> {
        void getMenus();
    }

    interface Model {
        Observable<ListMenuResponse> getMenus();
    }

}