package com.utnfrt.alimentar.ui.menu.menu1;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.FamiliaresResponse;
import com.utnfrt.alimentar.data.entity.family.RepositoryFamily;
import com.utnfrt.alimentar.data.entity.user.User;
import io.reactivex.Observable;

public class Menu1Model implements Menu1Contract.Model {

    private RepositoryFamily repository;

    public Menu1Model(RepositoryFamily repository){
        this.repository = repository;
    }

    @Override
    public User getUser() {
        return repository.obtainUserLocal();
    }

    @Override
    public void logout() {
        repository.logout();
    }

    @Override
    public Observable<FamiliaresResponse> getFamiliares() {
        return Observable.wrap(repository.obtainFamiliares());
    }
}
