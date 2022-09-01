package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ConfirmVegetalPresenter <V extends ConfirmVegetalContract.View> extends BasePresenter<V> implements ConfirmVegetalContract.Presenter<V> {

    private ConfirmVegetalContract.Model model;
    private Disposable disposable = null;

    public ConfirmVegetalPresenter(ConfirmVegetalContract.Model model){
        this.model = model;
    }

    @Override
    public void createHortaliza(String idHortaliza, String cantidad) {
        getView().loading();
        disposable = model.createHortaliza(idHortaliza, cantidad)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer message) {
                        getView().finishLoading();
                        if (message == R.string.ok) {
                            getView().createOk();
                        }
                        else getView().showError(R.string.error_title_bottom_sheet, message);
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