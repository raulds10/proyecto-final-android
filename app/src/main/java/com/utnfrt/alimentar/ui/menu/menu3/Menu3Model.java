package com.utnfrt.alimentar.ui.menu.menu3;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.ListMenuResponse;
import com.utnfrt.alimentar.data.entity.menu.RepositoryMenu;
import io.reactivex.Observable;

public class Menu3Model implements Menu3Contract.Model {

    private RepositoryMenu repository;

    public Menu3Model(RepositoryMenu repository){
        this.repository = repository;
    }

    @Override
    public Observable<ListMenuResponse> getMenus() {
        return Observable.wrap(repository.showMenu());
    }
}
