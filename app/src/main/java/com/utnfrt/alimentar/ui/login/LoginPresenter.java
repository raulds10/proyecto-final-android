package com.utnfrt.alimentar.ui.login;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class LoginPresenter <V extends LoginContract.View> extends BasePresenter<V> implements LoginContract.Presenter<V> {

    private LoginContract.Model model;
    private Disposable disposable = null;

    public LoginPresenter(LoginContract.Model model){
        this.model = model;
    }

    @Override
    public void loginUser(String usuario, String password) {
        getView().loading();
        disposable = model.loginUser(usuario, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer message) {
                        getView().finishLoading();
                        if (message.equals(R.string.ok)) getView().userLoggedIn();
                        else getView().showError(R.string.error_title_bottom_sheet, message);
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof HttpException) {
                            switch (((HttpException) e).code()){
                                case 400:
                                    getView().finishLoading();
                                    getView().showError(R.string.error_title_bottom_sheet, R.string.error_login);
                                    break;
                                default:
                                    handleApiError(e);
                                    break;
                            }
                        }
                        else handleApiError(e);
                    }

                    @Override
                    public void onComplete(){}
                });
    }
}
