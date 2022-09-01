package com.utnfrt.alimentar.ui.menu.menu3;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.ListMenuResponse;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Menu3Presenter <V extends Menu3Contract.View> extends BasePresenter<V> implements Menu3Contract.Presenter<V> {

    private Menu3Contract.Model model;
    private Disposable disposable = null;

    public Menu3Presenter(Menu3Contract.Model model){
        this.model = model;
    }

    @Override
    public void rxJavaUnsuscribe() {
        if (disposable!= null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void getMenus() {
        getView().loading();
        disposable = model.getMenus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ListMenuResponse>() {
                    @Override
                    public void onNext(ListMenuResponse response) {
                        getView().finishLoading();
                        if (response.getMenus().isEmpty()) {
                            getView().emptyList(R.string.no_hay_menus);
                        } else {
                            getView().showMenus(response);
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
}