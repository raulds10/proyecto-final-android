package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionesResponse;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface ShowAfeccionesContract {

    interface View extends MvpView {
        void showAfeccionesList(AfeccionesResponse afeccionesResponse);
        void addAfeccionOk();
    }

    interface Presenter <V extends ShowAfeccionesContract.View> extends MvpPresenter<V> {
        void getAfecciones();
        void addAfeccion(String idFamiliar, String idAfeccion);
    }

    interface Model {
        Observable<Integer> addAfeccion(String idFamiliar, String idAfeccion);
        Observable<AfeccionesResponse> getAfecciones();
    }
}
