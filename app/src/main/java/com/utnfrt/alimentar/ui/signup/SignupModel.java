package com.utnfrt.alimentar.ui.signup;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.CreateUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.UsuarioCreate;
import com.utnfrt.alimentar.data.entity.user.RepositoryUser;
import io.reactivex.Observable;

public class SignupModel implements SignupContract.Model {

    private RepositoryUser repository;

    public SignupModel(RepositoryUser repository){
        this.repository = repository;
    }

    @Override
    public Observable<CreateUsuarioResponse> signupUser(String usuario, String email, String pass, String repeatPass, String fullName, String fechaNac, String peso, String altura) {
        if (!fullName.trim().matches("^[^\\\\/±!@£$%^&*_+§¡€#¢§¶•ªº«<>.?:;´`¨|=,¬~\\d]{3,50}$")) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_nombre, "", "", "")));
        } else if (fechaNac.isEmpty()) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_fecha_nacimiento, "", "", "")));
        } else if (peso.isEmpty()) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_peso, "", "", "")));
        } else if (altura.isEmpty()) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_altura, "", "", "")));
        } else if (usuario.contains(" ")) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_usuario, "", "", "")));
        } else if (!email.matches("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$")) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_email, "", "", "")));
        } else if (pass.length() < 8) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_password, "", "", "")));
        } else if (!pass.equals(repeatPass)) {
            return Observable.just(new CreateUsuarioResponse("ERROR", "", new UsuarioCreate(R.string.error_password_confirmation, "", "", "")));
        } else {
            return Observable.wrap(repository.createUser(usuario, pass, email));
        }
    }

    @Override
    public Observable<Integer> createFamiliar(int idUser, String nombre, String fechaNac, String peso, String altura) {
        return Observable.wrap(repository.createFamiliar(nombre, fechaNac, peso, altura)).map(crearFamiliarResponse -> R.string.ok);
    }
}
