package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizasResponse;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SelectVegetalPresenter <V extends SelectVegetalContract.View> extends BasePresenter<V> implements SelectVegetalContract.Presenter<V> {

    private SelectVegetalContract.Model model;
    private Disposable disposable = null;

    public SelectVegetalPresenter(SelectVegetalContract.Model model){
        this.model = model;
    }

    @Override
    public void rxJavaUnsuscribe() {
        if (disposable!= null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void getHortalizas() {
        getView().loading();
        disposable = model.getHortalizas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<HortalizasResponse>() {
                    @Override
                    public void onNext(HortalizasResponse response) {
                        getView().finishLoading();
                        if (response.getHortalizas().isEmpty()) {
                            getView().emptyList(R.string.no_hay_hortalizas_crear);
                        } else {
                            getView().showVegetales(response);
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