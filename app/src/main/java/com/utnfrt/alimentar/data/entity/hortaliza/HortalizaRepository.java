package com.utnfrt.alimentar.data.entity.hortaliza;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizaAddUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizasResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class HortalizaRepository implements RepositoryHortaliza {

    private PrincipalSP pref;
    private PrincipalApi api;

    public HortalizaRepository(PrincipalApi api, PrincipalSP pref) {
        this.pref = pref;
        this.api = api;
    }

    @Override
    public Observable<HortalizasResponse> obtainHortalizas() {
        Observable<HortalizasResponse> user = api.getHortalizas(pref.getToken());
        return user.concatMap(new Function<HortalizasResponse, ObservableSource<? extends HortalizasResponse>>() {
            @Override
            public ObservableSource<? extends HortalizasResponse> apply(HortalizasResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<List<HortalizaUsuarioResponse>> obtainHortalizaUser() {
        Observable<List<HortalizaUsuarioResponse>> user = api.getHortalizasUser(pref.getToken(), pref.getIdUsuario());
        return user.concatMap(new Function<List<HortalizaUsuarioResponse>, ObservableSource<? extends List<HortalizaUsuarioResponse>>>() {
            @Override
            public ObservableSource<? extends List<HortalizaUsuarioResponse>> apply(List<HortalizaUsuarioResponse> response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<HortalizaAddUsuarioResponse> addhortalizaUser(String idHortaliza, String cantidad) {
        Observable<HortalizaAddUsuarioResponse> user = api.postAddHortalizaUser(pref.getToken(),pref.getIdUsuario(), idHortaliza, cantidad);
        return user.concatMap(new Function<HortalizaAddUsuarioResponse, ObservableSource<? extends HortalizaAddUsuarioResponse>>() {
            @Override
            public ObservableSource<? extends HortalizaAddUsuarioResponse> apply(HortalizaAddUsuarioResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }
}