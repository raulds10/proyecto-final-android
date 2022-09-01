package com.utnfrt.alimentar.ui.menu.menu1;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.FamiliaresResponse;
import com.utnfrt.alimentar.data.entity.user.User;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Menu1Presenter <V extends Menu1Contract.View> extends BasePresenter<V> implements Menu1Contract.Presenter<V> {

    private Menu1Contract.Model model;
    private Disposable disposable = null;

    public Menu1Presenter(Menu1Contract.Model model){
        this.model = model;
    }

    @Override
    public void rxJavaUnsuscribe() {
        if (disposable!= null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void getUser() {
        User user = model.getUser();
        getView().showUser(user);
    }

    @Override
    public void getFamiliares() {
        getView().loading();
        disposable = model.getFamiliares()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<FamiliaresResponse>() {
                    @Override
                    public void onNext(FamiliaresResponse familiares) {
                        getView().finishLoading();
                        if (familiares.getFamiliares().getFamiliar().isEmpty()) {
                            getView().emptyList(R.string.no_hay_familiares);
                        } else {
                            getView().showFamily(familiares);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        handleApiError(e);
                    }
                    @Override
                    public void onComplete(){}
                });
    }

    @Override
    public void logout() {
        model.logout();
    }
}