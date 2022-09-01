package com.utnfrt.alimentar.ui.menu.menu3.detailmenu;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.data.entity.menu.RepositoryMenu;
import io.reactivex.Observable;

public class DetailMenuModel implements DetailMenuContract.Model {

    private RepositoryMenu repository;

    public DetailMenuModel(RepositoryMenu repository){
        this.repository = repository;
    }

    @Override
    public Observable<DetailMenuResponse> getDetailMenu(String idMenu) {
        return Observable.wrap(repository.detailMenu(idMenu));
    }
}
