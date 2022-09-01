package com.utnfrt.alimentar.ui.main;

import com.utnfrt.alimentar.data.entity.user.User;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;

public interface MainContract {

    interface View extends MvpView{
        void userLoggedIn();
    }
    interface Presenter <V extends MainContract.View> extends MvpPresenter<V> {
        void verifyLoggedInUser();
    }

    interface Model {
        User obtainUser();
    }
}
