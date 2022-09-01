package com.utnfrt.alimentar.ui.signup;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.CreateUsuarioResponse;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import io.reactivex.Observable;

public interface SignupContract {

    interface View extends MvpView {
        void userCreated(CreateUsuarioResponse response);
        void familyCreated();
    }

    interface Presenter <V extends SignupContract.View> extends MvpPresenter<V> {
        void signupUser(String usuario, String email, String pass, String repeatPass, String fullName, String fechaNac, String peso, String altura);
        void createFamiliar(int idUser, String nombre, String fechaNac, String peso, String altura);
    }

    interface Model {
        Observable<CreateUsuarioResponse> signupUser(String usuario, String email, String pass, String repeatPass, String fullName, String fechaNac, String peso, String altura);
        Observable<Integer> createFamiliar(int idUser, String nombre, String fechaNac, String peso, String altura);
    }

}
