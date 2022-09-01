package com.utnfrt.alimentar.ui.menu.menu2;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import com.utnfrt.alimentar.ui.base.MvpPresenter;
import com.utnfrt.alimentar.ui.base.MvpView;
import java.util.List;
import io.reactivex.Observable;

public interface Menu2Contract {

    interface View extends MvpView {
        void showHortaliza(List<HortalizaUsuarioResponse> hortalizas);
    }

    interface Presenter <V extends Menu2Contract.View> extends MvpPresenter<V> {
        void getHortalizas();
    }

    interface Model {
        Observable<List<HortalizaUsuarioResponse>> getHortalizas();
    }

}
