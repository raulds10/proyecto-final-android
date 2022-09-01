package com.utnfrt.alimentar.data.entity.family;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.CrearFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.DeleteFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.FamiliaresResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.UpdateFamiliarResponse;
import com.utnfrt.alimentar.data.entity.user.User;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class FamilyRepository implements RepositoryFamily {

    private PrincipalSP pref;
    private PrincipalApi api;

    public FamilyRepository(PrincipalApi api, PrincipalSP pref) {
        this.pref = pref;
        this.api = api;
    }

    @Override
    public User obtainUserLocal() {
        User user = new User();
        user.setNombres(pref.getNombre());
        user.setPassword(pref.getPassword());
        user.setEmail(pref.getEmail());
        user.setUserId(pref.getIdUsuario());
        return user;
    }

    @Override
    public void logout() {
        pref.limpiarSP();
    }

    @Override
    public Observable<FamiliaresResponse> obtainFamiliares() {
        Observable<FamiliaresResponse> user = api.postObtainFamiliares(pref.getToken(), pref.getIdUsuario());
        return user.concatMap(new Function<FamiliaresResponse, ObservableSource<? extends FamiliaresResponse>>() {
            @Override
            public ObservableSource<? extends FamiliaresResponse> apply(FamiliaresResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<CrearFamiliarResponse> createFamiliar(String fullName, String fachaNac, String peso, String altura) {
        Observable<CrearFamiliarResponse> user = api.postCreateFamiliar(pref.getToken(),pref.getIdUsuario(), fullName, fachaNac, peso, altura);
        return user.concatMap(new Function<CrearFamiliarResponse, ObservableSource<? extends CrearFamiliarResponse>>() {
            @Override
            public ObservableSource<? extends CrearFamiliarResponse> apply(CrearFamiliarResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<UpdateFamiliarResponse> editFamiliar(String idFamiliar, String fullName, String fachaNac, String peso, String altura) {
        Observable<UpdateFamiliarResponse> user = api.postUpdateFamiliar(pref.getToken(), idFamiliar, fullName, fachaNac, peso, altura);
        return user.concatMap(new Function<UpdateFamiliarResponse, ObservableSource<? extends UpdateFamiliarResponse>>() {
            @Override
            public ObservableSource<? extends UpdateFamiliarResponse> apply(UpdateFamiliarResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<DeleteFamiliarResponse> deleteFamiliar(String idFamiliar) {
        Observable<DeleteFamiliarResponse> user = api.postDeleteFamiliar(pref.getToken(), idFamiliar);
        return user.concatMap(new Function<DeleteFamiliarResponse, ObservableSource<? extends DeleteFamiliarResponse>>() {
            @Override
            public ObservableSource<? extends DeleteFamiliarResponse> apply(DeleteFamiliarResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }
}