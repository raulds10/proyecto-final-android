package com.utnfrt.alimentar.ui.main;

import com.utnfrt.alimentar.ui.base.BasePresenter;

public class MainPresenter <V extends MainContract.View> extends BasePresenter<V> implements MainContract.Presenter<V> {

    private MainContract.Model model;

    public MainPresenter(MainContract.Model model){
        this.model = model;
    }

    @Override
    public void verifyLoggedInUser() {
        if (!model.obtainUser().getUserId().isEmpty()) getView().userLoggedIn();
    }
}
