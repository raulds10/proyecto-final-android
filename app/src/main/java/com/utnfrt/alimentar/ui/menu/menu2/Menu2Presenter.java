package com.utnfrt.alimentar.ui.menu.menu2;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Menu2Presenter <V extends Menu2Contract.View> extends BasePresenter<V> implements Menu2Contract.Presenter<V> {

    private Menu2Contract.Model model;
    private Disposable disposable = null;

    public Menu2Presenter(Menu2Contract.Model model){
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
                .subscribeWith(new DisposableObserver<List<HortalizaUsuarioResponse>>() {
                    @Override
                    public void onNext(List<HortalizaUsuarioResponse> hortalizas) {
                        getView().finishLoading();
                        if (hortalizas.isEmpty()) {
                            getView().emptyList(R.string.no_hay_hortalizas);
                        } else {
                            getView().showHortaliza(hortalizas);
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