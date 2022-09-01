package com.utnfrt.alimentar.ui.signup;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.CreateUsuarioResponse;
import com.utnfrt.alimentar.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class SignupPresenter <V extends SignupContract.View> extends BasePresenter<V> implements SignupContract.Presenter<V> {

    private SignupContract.Model model;
    private Disposable disposable = null;

    public SignupPresenter(SignupContract.Model model){
        this.model = model;
    }

    @Override
    public void signupUser(String usuario, String email, String pass, String repeatPass, String fullName, String fechaNac, String peso, String altura) {
        getView().loading();
        disposable = model.signupUser(usuario, email, pass, repeatPass, fullName, fechaNac, peso, altura)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CreateUsuarioResponse>() {
                    @Override
                    public void onNext(CreateUsuarioResponse response) {
                        getView().finishLoading();
                        if (response.getStatus().equals("ERROR")){
                            getView().showError(R.string.error_title_bottom_sheet, response.getUsuario().getIdUsuario());
                        } else {
                            getView().userCreated(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            switch (((HttpException) e).code()){
                                case 422:
                                    getView().finishLoading();
                                    getView().showError(R.string.error_title_bottom_sheet, R.string.error_datos_mal_ingresados);
                                    break;
                                default:
                                    handleApiError(e);
                                    break;
                            }
                        }
                        else handleApiError(e);
                    }

                    @Override
                    public void onComplete(){}
                });
    }

    @Override
    public void createFamiliar(int idUser, String nombre, String fechaNac, String peso, String altura) {
        getView().loading();
        disposable = model.createFamiliar(idUser, nombre, fechaNac, peso,altura)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer message) {
                        getView().finishLoading();
                        getView().familyCreated();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().familyCreated();
                    }

                    @Override
                    public void onComplete(){}
                });
    }
}