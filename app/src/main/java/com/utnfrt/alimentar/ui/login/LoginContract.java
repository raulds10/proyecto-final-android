package com.utnfrt.alimentar.ui.login;

import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface LoginContract {

    interface View extends MvpView {
        void userLoggedIn();
    }

    interface Presenter <V extends LoginContract.View> extends MvpPresenter<V> {
        void loginUser(String usuario, String password);
    }

    interface Model {
        Observable<Integer> loginUser(String usuario, String password);
    }
}
