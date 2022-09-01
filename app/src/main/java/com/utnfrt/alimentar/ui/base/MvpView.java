package com.utnfrt.alimentar.ui.base;

public interface MvpView {

    void loading();
    void finishLoading();
    void showError(int idTitle, int idMessage);
    void logout();
    void emptyList(int idMessage);

}
