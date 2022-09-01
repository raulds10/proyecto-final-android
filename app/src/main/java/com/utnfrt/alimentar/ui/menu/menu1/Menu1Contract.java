package com.utnfrt.alimentar.ui.menu.menu1;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.FamiliaresResponse;
import com.utnfrt.alimentar.data.entity.user.User;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface Menu1Contract {

    interface View extends MvpView {
        void showUser(User user);
        void showFamily(FamiliaresResponse familiares);
    }

    interface Presenter <V extends Menu1Contract.View> extends MvpPresenter<V> {
        void getUser();
        void getFamiliares();
        void logout();
    }

    interface Model {
        User getUser();
        void logout();
        Observable<FamiliaresResponse> getFamiliares();
    }

}
