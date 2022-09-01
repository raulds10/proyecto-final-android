package com.utnfrt.alimentar.ui.menu.menu3.detailmenu;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailMenuPresenter <V extends DetailMenuContract.View> extends BasePresenter<V> implements DetailMenuContract.Presenter<V> {

    private DetailMenuContract.Model model;
    private Disposable disposable = null;

    public DetailMenuPresenter(DetailMenuContract.Model model){
        this.model = model;
    }

    @Override
    public void rxJavaUnsuscribe() {
        if (disposable!= null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void getDetailMenu(String idMenu) {
        getView().loading();
        disposable = model.getDetailMenu(idMenu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<DetailMenuResponse>() {
                    @Override
                    public void onNext(DetailMenuResponse response) {
                        getView().finishLoading();
                        getView().showDetailMenu(response);
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