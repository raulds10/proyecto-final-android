package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import com.utnfrt.alimentar.utils.HistorySaver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EditFamiliarPresenter <V extends EditFamiliarContract.View> extends BasePresenter<V> implements EditFamiliarContract.Presenter<V> {

    private EditFamiliarContract.Model model;
    private Disposable disposable = null;

    public EditFamiliarPresenter(EditFamiliarContract.Model model){
        this.model = model;
    }

    @Override
    public void editFamiliar(String idFamiliar, String nombre, String fecha_nacimiento, String peso, String altura) {
        getView().loading();
        disposable = model.editFamiliar(idFamiliar, nombre, fecha_nacimiento, peso,altura)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer message) {
                        getView().finishLoading();
                        if (message == R.string.ok) {
                            HistorySaver.guardarPesoHistorico(idFamiliar,peso);
                            getView().editOk();
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

    @Override
    public void deleteFamiliar(int idFamiliar) {
        getView().loading();
        disposable = model.deleteFamiliar(idFamiliar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer message) {
                        getView().finishLoading();
                        if (message == R.string.ok) {
                            getView().deleteOk();
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