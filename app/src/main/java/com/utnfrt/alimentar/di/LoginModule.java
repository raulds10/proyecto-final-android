package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.user.RepositoryUser;
import com.utnfrt.alimentar.ui.login.LoginContract;
import com.utnfrt.alimentar.ui.login.LoginModel;
import com.utnfrt.alimentar.ui.login.LoginPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginContract.Presenter providePresenter(LoginContract.Model model){
        return new LoginPresenter(model);
    }

    @Provides
    public LoginContract.Model provideModel(RepositoryUser repository){
        return new LoginModel(repository);
    }
}
