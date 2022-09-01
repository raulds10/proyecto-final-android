package com.utnfrt.alimentar.ui.menu.menu1.crearfamilia;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewFamiliarPresenter <V extends NewFamiliarContract.View> extends BasePresenter<V> implements NewFamiliarContract.Presenter<V> {

    private NewFamiliarContract.Model model;
    private Disposable disposable = null;

    public NewFamiliarPresenter(NewFamiliarContract.Model model){
        this.model = model;
    }

    @Override
    public void createFamiliar(String nombre, String fechaNac, String peso, String altura) {
        getView().loading();
        disposable = model.createFamiliar(nombre, fechaNac, peso,altura)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer message) {
                        getView().finishLoading();
                        if (message == R.string.ok) {
                            getView().familyCreated();
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