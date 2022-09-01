package com.utnfrt.alimentar.ui.menu.menu3.detailmenu;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface DetailMenuContract {

    interface View extends MvpView {
        void showDetailMenu(DetailMenuResponse response);
    }

    interface Presenter <V extends DetailMenuContract.View> extends MvpPresenter<V> {
        void getDetailMenu(String idMenu);
    }

    interface Model {
        Observable<DetailMenuResponse> getDetailMenu(String idMenu);
    }

}
