package com.utnfrt.alimentar.di;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.entity.user.RepositoryUser;
import com.utnfrt.alimentar.data.entity.user.UserRepository;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import com.utnfrt.alimentar.ui.main.MainContract;
import com.utnfrt.alimentar.ui.main.MainModel;
import com.utnfrt.alimentar.ui.main.MainPresenter;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public MainContract.Presenter providePresenter(MainContract.Model model){
        return new MainPresenter(model);
    }

    @Provides
    public MainContract.Model provideModel(RepositoryUser repository){
        return new MainModel(repository);
    }

    @Singleton
    @Provides
    public RepositoryUser provideRepository(PrincipalApi api, PrincipalSP pref){
        return new UserRepository(api,pref);
    }

}
