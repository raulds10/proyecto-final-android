package com.utnfrt.alimentar.data.entity.menu;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.ListMenuResponse;

import java.util.List;
import io.reactivex.Observable;

public interface RepositoryMenu {

    Observable<List<Integer>> obtainListMenu();
    Observable<DetailMenuResponse> detailMenu(String idMenu);
    Observable<ListMenuResponse> showMenu();

}
