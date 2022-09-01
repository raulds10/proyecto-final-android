package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.entity.user.RepositoryUser;
import com.utnfrt.alimentar.ui.signup.SignupContract;
import com.utnfrt.alimentar.ui.signup.SignupModel;
import com.utnfrt.alimentar.ui.signup.SignupPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class SignupModule {

    @Provides
    public SignupContract.Presenter providePresenter(SignupContract.Model model){
        return new SignupPresenter(model);
    }

    @Provides
    public SignupContract.Model provideModel(RepositoryUser repository){
        return new SignupModel(repository);
    }

}
