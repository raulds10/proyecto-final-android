package com.utnfrt.alimentar.ui.login;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.loginresponse.LoginResponse;
import com.utnfrt.alimentar.data.entity.user.RepositoryUser;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class LoginModel implements LoginContract.Model {

    private RepositoryUser repository;

    public LoginModel(RepositoryUser repository){
        this.repository = repository;
    }

    @Override
    public Observable<Integer> loginUser(String usuario, String password) {
        if (usuario.isEmpty() || password.isEmpty()) return Observable.just(R.string.error_datos_requerdios_vacios);
        else if (usuario.length() < 5 ) return Observable.just(R.string.error_usuario);
        else if (password.length() < 8) return Observable.just(R.string.error_password);
        else {
            return Observable.wrap(repository.loginUser(usuario, password).map(new Function<LoginResponse, Integer>() {
                @Override
                public Integer apply(LoginResponse response) throws Exception {
                    return R.string.ok;
                }
            }));
        }
    }
}
