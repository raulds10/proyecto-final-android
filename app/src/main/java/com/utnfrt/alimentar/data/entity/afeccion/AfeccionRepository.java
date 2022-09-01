package com.utnfrt.alimentar.data.entity.afeccion;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionesResponse;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class AfeccionRepository implements RepositoryAfeccion {

    private PrincipalSP pref;
    private PrincipalApi api;

    public AfeccionRepository(PrincipalApi api, PrincipalSP pref) {
        this.pref = pref;
        this.api = api;
    }

    @Override
    public Observable<AfeccionesResponse> obtainAfecciones() {
        Observable<AfeccionesResponse> user = api.getAfecciones(pref.getToken());
        return user.concatMap(new Function<AfeccionesResponse, ObservableSource<? extends AfeccionesResponse>>() {
            @Override
            public ObservableSource<? extends AfeccionesResponse> apply(AfeccionesResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<AfeccionFamiliarResponse> addAfeccionToFamiliar(String idFamiliar, String idAfeccion) {
        Observable<AfeccionFamiliarResponse> user = api.postAddAfeccionFamiliar(pref.getToken(), idFamiliar, idAfeccion);
        return user.concatMap(new Function<AfeccionFamiliarResponse, ObservableSource<? extends AfeccionFamiliarResponse>>() {
            @Override
            public ObservableSource<? extends AfeccionFamiliarResponse> apply(AfeccionFamiliarResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }
}