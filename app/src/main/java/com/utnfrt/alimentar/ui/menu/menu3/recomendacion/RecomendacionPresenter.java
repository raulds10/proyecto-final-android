package com.utnfrt.alimentar.ui.menu.menu3.recomendacion;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecomendacionPresenter <V extends RecomendacionContract.View> extends BasePresenter<V> implements RecomendacionContract.Presenter<V> {

    private RecomendacionContract.Model model;
    private Disposable disposable = null;

    public RecomendacionPresenter(RecomendacionContract.Model model){
        this.model = model;
    }

    @Override
    public void rxJavaUnsuscribe() {
        if (disposable!= null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void getMenuRecomendado() {
        getView().loading();
        disposable = model.getMenuRecomendado()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Integer>>() {
                    @Override
                    public void onNext(List<Integer> response) {
                        getView().finishLoading();
                        if (response.isEmpty()) {
                            getView().emptyList(R.string.no_hay_recomendaciones);
                        } else {
                            getView().showMenuRecomendado(response);
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
    public void getMenuComplete(String idMenu) {
        getView().loading();
        disposable = model.getMenuComplete(idMenu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<DetailMenuResponse>() {
                    @Override
                    public void onNext(DetailMenuResponse response) {
                        getView().finishLoading();
                        getView().showMenuComplete(response);
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