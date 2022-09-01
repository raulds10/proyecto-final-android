package com.utnfrt.alimentar.data.entity.user;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.CrearFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.CreateUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.edituserresponse.EditUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.loginresponse.LoginResponse;
import io.reactivex.Observable;

public interface RepositoryUser {
    User obtainUserLocal();
    void logout();
    Observable<LoginResponse> loginUser(String usuario, String password);
    Observable<CrearFamiliarResponse> createFamiliar(String fullName, String fachaNac, String peso, String altura);
    Observable<CreateUsuarioResponse> createUser(String usuario, String password, String email);
    Observable<EditUsuarioResponse> editUser(String usuario, String password, String email);
}
