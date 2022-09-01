package com.utnfrt.alimentar.ui.menu.menu2;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseFragment;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.SelectVegetalActivity;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu2Fragment extends BaseFragment implements Menu2Contract.View{

    private View view;
    @Inject
    Menu2Contract.Presenter presenter;
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
    @BindView(R.id.rv_mis_vegetales_menu2)
    RecyclerView rvVegetales;
    @BindView(R.id.fab_new_vegetal_menu2)
    FloatingActionButton fabNewVegetal;
    private Menu2Adapter adapter;
    private GridLayoutManager layoutManager;
    private List<HortalizaUsuarioResponse> listHortalizas = new ArrayList<>();

    public Menu2Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_dos, container, false);
        ButterKnife.bind(this,view);
        ((App) getActivity().getApplication()).getComponent().injectMenu2(this);

        rvVegetales.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 1);
        rvVegetales.setLayoutManager(layoutManager);
        adapter = new Menu2Adapter(listHortalizas, getContext());
        adapter.setOnClickListener(v -> {
            //TODO: seleccionar vegetal para editar o eliminar
            //rvVegetales.getChildAdapterPosition(v)
        });
        rvVegetales.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getHortalizas();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        listHortalizas.clear();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab_new_vegetal_menu2) public void createHortaliza(){
        startActivity(new Intent(getContext(), SelectVegetalActivity.class));
    }

    @OnClick(R.id.btn_error) public void restartActivity(){
        presenter.rxJavaUnsuscribe();
        listHortalizas.clear();
        adapter.notifyDataSetChanged();
        presenter.getHortalizas();
    }

    @Override
    public void showError(int idTitle, int idMessage) {
        if (getContext()!=null){
            clError.setVisibility(View.VISIBLE);
            tvError.setText(getString(idMessage));
        }
    }

    @Override
    public void emptyList(int idMessage) {
        if (getContext()!=null){
            fabNewVegetal.setVisibility(View.VISIBLE);
            clListaVacia.setVisibility(View.VISIBLE);
            tvListaVacia.setText(getString(idMessage));
        }
    }

    @Override
    public void loading() {
        fabNewVegetal.setVisibility(View.INVISIBLE);
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        fabNewVegetal.setVisibility(View.INVISIBLE);
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showHortaliza(List<HortalizaUsuarioResponse> hortalizas) {
        if (getContext()!=null){
            fabNewVegetal.setVisibility(View.VISIBLE);
            rvVegetales.setVisibility(View.VISIBLE);
            listHortalizas.addAll(hortalizas);
            adapter.notifyDataSetChanged();
        }
    }
}
