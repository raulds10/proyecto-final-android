package com.utnfrt.alimentar.data.entity.user;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.CrearFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.CreateUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.edituserresponse.EditUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.loginresponse.LoginResponse;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class UserRepository implements RepositoryUser {

    private PrincipalSP pref;
    private PrincipalApi api;

    public UserRepository(PrincipalApi api, PrincipalSP pref) {
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
    public Observable<LoginResponse> loginUser(String usuario, String password) {
        Observable<LoginResponse> user = api.postLogin(pref.getToken(), usuario, password);
        return user.concatMap(new Function<LoginResponse, ObservableSource<? extends LoginResponse>>() {
            @Override
            public ObservableSource<? extends LoginResponse> apply(LoginResponse response) throws Exception {
                pref.setNombre(response.getUsuario().getNombre());
                pref.setPassword(response.getUsuario().getPassword());
                pref.setEmail(response.getUsuario().getEmail());
                pref.setIdUsuario(String.valueOf(response.getUsuario().getIdUsuario()));
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
    public Observable<CreateUsuarioResponse> createUser(String usuario, String password, String email) {
        Observable<CreateUsuarioResponse> user = api.postCreateUser(pref.getToken(), usuario, password, email);
        return user.concatMap(new Function<CreateUsuarioResponse, ObservableSource<? extends CreateUsuarioResponse>>() {
            @Override
            public ObservableSource<? extends CreateUsuarioResponse> apply(CreateUsuarioResponse response) throws Exception {
                pref.setNombre(response.getUsuario().getNombre());
                pref.setPassword(response.getUsuario().getPassword());
                pref.setEmail(response.getUsuario().getEmail());
                pref.setIdUsuario(String.valueOf(response.getUsuario().getIdUsuario()));
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<EditUsuarioResponse> editUser(String usuario, String password, String email) {
        Observable<EditUsuarioResponse> user = api.postEditUser(pref.getToken(), pref.getIdUsuario(), usuario, password, email);
        return user.concatMap(new Function<EditUsuarioResponse, ObservableSource<? extends EditUsuarioResponse>>() {
            @Override
            public ObservableSource<? extends EditUsuarioResponse> apply(EditUsuarioResponse response) throws Exception {
                pref.setNombre(response.getNuevosDatos().getNombre());
                pref.setPassword(response.getNuevosDatos().getPassword());
                pref.setEmail(response.getNuevosDatos().getEmail());
                return Observable.just(response);
            }
        });
    }
}
