package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionesResponse;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ShowAfeccionesPresenter <V extends ShowAfeccionesContract.View> extends BasePresenter<V> implements ShowAfeccionesContract.Presenter<V> {

    private ShowAfeccionesContract.Model model;
    private Disposable disposable = null;

    public ShowAfeccionesPresenter(ShowAfeccionesContract.Model model){
        this.model = model;
    }

    @Override
    public void rxJavaUnsuscribe() {
        if (disposable!= null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void getAfecciones() {
        getView().loading();
        disposable = model.getAfecciones()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AfeccionesResponse>() {
                    @Override
                    public void onNext(AfeccionesResponse afeccionesResponse) {
                        getView().finishLoading();
                        if (afeccionesResponse.getAfecciones().isEmpty()) {
                            getView().emptyList(R.string.no_hay_afecciones);
                        } else {
                            getView().showAfeccionesList(afeccionesResponse);
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
    public void addAfeccion(String idFamiliar, String idAfeccion) {
        getView().loading();
        disposable = model.addAfeccion(idFamiliar, idAfeccion)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer message) {
                        getView().finishLoading();
                        if (message == R.string.ok) {
                            getView().addAfeccionOk();
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