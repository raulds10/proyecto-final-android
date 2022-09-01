package com.utnfrt.alimentar.ui.base;

public interface MvpPresenter<V extends MvpView> {

    void setView(V view);
    V getView();
    void rxJavaUnsuscribe();
    void handleApiError(Throwable error);
}
