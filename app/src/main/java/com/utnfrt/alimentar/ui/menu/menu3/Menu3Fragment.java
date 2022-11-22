package com.utnfrt.alimentar.ui.menu.menu3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.ListMenuResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.Menu;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.MenuDetail;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseFragment;
import com.utnfrt.alimentar.ui.menu.menu3.detailmenu.DetailMenuActivity;
import com.utnfrt.alimentar.ui.menu.menu3.recomendacion.RecomendacionAdapter;
import com.utnfrt.alimentar.ui.menu.menu3.recomendacion.RecomendacionContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu3Fragment extends BaseFragment implements Menu3Contract.View, RecomendacionContract.View {

    private View view;
    @Inject
    RecomendacionContract.Presenter recomendationPresenter;
    @Inject
    Menu3Contract.Presenter presenter;
    @BindView(R.id.cl_error)
    ConstraintLayout clError;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.btn_error)
    Button btnError;
    @BindView(R.id.cl_empty_list)
    ConstraintLayout clListaVacia;
    @BindView(R.id.tv_empty_list)
    TextView tvListaVacia;
    @BindView(R.id.cl_loading)
    ConstraintLayout clLoading;

    @BindView(R.id.rv_menu3)
    RecyclerView rvMenu3;
    private Menu3Adapter adapter;
    private GridLayoutManager layoutManager;
    private List<Menu> listMenu = new ArrayList<>();
    private List<MenuDetail> listRecomendation = new ArrayList<>();

    @BindView(R.id.rv_recomendacion)
    RecyclerView rvRecomendacion;
    private RecomendacionAdapter adapterRecomendaciones;
    private List<Integer> listIdMenu = new ArrayList<>();
    private int currentPos = 0;

    public Menu3Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_tres, container, false);
        ButterKnife.bind(this, view);
        ((App) getActivity().getApplication()).getComponent().injectMenu3(this);

        rvMenu3.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 1);
        rvMenu3.setLayoutManager(layoutManager);
        adapter = new Menu3Adapter(listMenu, getContext());
        adapter.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), DetailMenuActivity.class);
            i.putExtra("idMenu", listMenu.get(rvMenu3.getChildAdapterPosition(v)).getIdMenu() + "");
            startActivity(i);
        });
        rvMenu3.setAdapter(adapter);


        rvRecomendacion.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 1);
        rvRecomendacion.setLayoutManager(layoutManager);
        adapterRecomendaciones = new RecomendacionAdapter(listRecomendation, getContext());
        adapterRecomendaciones.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), DetailMenuActivity.class);
            i.putExtra("idMenu", listRecomendation.get(rvRecomendacion.getChildAdapterPosition(v)).getIdMenu() + "");
            startActivity(i);
        });
        rvRecomendacion.setAdapter(adapterRecomendaciones);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        recomendationPresenter.setView(this);
        presenter.getMenus();
        recomendationPresenter.getMenuRecomendado();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        listMenu.clear();
        adapter.notifyDataSetChanged();

        recomendationPresenter.rxJavaUnsuscribe();
        listRecomendation.clear();
        listIdMenu.clear();
        currentPos = 0;
        adapterRecomendaciones.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_error)
    public void restartActivity() {
        presenter.rxJavaUnsuscribe();
        listMenu.clear();
        adapter.notifyDataSetChanged();
        presenter.getMenus();
        recomendationPresenter.rxJavaUnsuscribe();
        listRecomendation.clear();
        listIdMenu.clear();
        currentPos = 0;
        adapterRecomendaciones.notifyDataSetChanged();
        recomendationPresenter.getMenuRecomendado();

    }

    @Override
    public void showError(int idTitle, int idMessage) {
        if (getContext() != null) {
//            btnRecomendar.setVisibility(View.INVISIBLE);
            clError.setVisibility(View.VISIBLE);
            tvError.setText(getString(idMessage));
        }
    }

    @Override
    public void emptyList(int idMessage) {
        if (getContext() != null) {
//            btnRecomendar.setVisibility(View.VISIBLE);
            clListaVacia.setVisibility(View.VISIBLE);
            tvListaVacia.setText(getString(idMessage));
        }
    }

    @Override
    public void loading() {
//        btnRecomendar.setVisibility(View.INVISIBLE);
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
//        btnRecomendar.setVisibility(View.INVISIBLE);
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showMenuRecomendado(List<Integer> response) {
        if (getContext() != null) {
            rvRecomendacion.setVisibility(View.VISIBLE);
            listIdMenu.addAll(response);
            recomendationPresenter.getMenuComplete(listIdMenu.get(currentPos) + "");
        }
    }

    @Override
    public void showMenuComplete(DetailMenuResponse detailMenuResponse) {
        if (getContext() != null) {
            listRecomendation.add(detailMenuResponse.getMenu());
            currentPos++;
            adapterRecomendaciones.notifyDataSetChanged();
            if (currentPos < listIdMenu.size()) {
                recomendationPresenter.getMenuComplete(listIdMenu.get(currentPos) + "");
            }
        }
    }

    @Override
    public void showMenus(ListMenuResponse response) {
        if (getContext() != null) {
//            btnRecomendar.setVisibility(View.VISIBLE);
            rvMenu3.setVisibility(View.VISIBLE);
            listMenu.addAll(response.getMenus());
            adapter.notifyDataSetChanged();
        }
    }
}
