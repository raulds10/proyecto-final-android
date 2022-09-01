package com.utnfrt.alimentar.data.entity.menu;

import com.utnfrt.alimentar.data.api.principalAPI.PrincipalApi;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.ListMenuResponse;
import com.utnfrt.alimentar.data.prefs.PrincipalSP;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class MenuRepository implements RepositoryMenu {

    private PrincipalSP pref;
    private PrincipalApi api;

    public MenuRepository(PrincipalApi api, PrincipalSP pref) {
        this.pref = pref;
        this.api = api;
    }

    @Override
    public Observable<DetailMenuResponse> detailMenu(String idMenu) {
        Observable<DetailMenuResponse> user = api.postDetailMenu(pref.getToken(), idMenu);
        return user.concatMap(new Function<DetailMenuResponse, ObservableSource<? extends DetailMenuResponse>>() {
            @Override
            public ObservableSource<? extends DetailMenuResponse> apply(DetailMenuResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<ListMenuResponse> showMenu() {
        Observable<ListMenuResponse> menuList = api.getMenus(pref.getToken());
        return menuList.concatMap(new Function<ListMenuResponse, ObservableSource<? extends ListMenuResponse>>() {
            @Override
            public ObservableSource<? extends ListMenuResponse> apply(ListMenuResponse response) throws Exception {
                return Observable.just(response);
            }
        });
    }

    @Override
    public Observable<List<Integer>> obtainListMenu() {
        Observable<List<Integer>> user = api.postObtainListMenu(pref.getToken(), pref.getIdUsuario());
        return user.concatMap(new Function<List<Integer>, ObservableSource<? extends List<Integer>>>() {
            @Override
            public ObservableSource<? extends List<Integer>> apply(List<Integer> response) throws Exception {
                return Observable.just(response);
            }
        });
    }

}