package com.utnfrt.alimentar.ui.menu.menu1;

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
import com.google.gson.Gson;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.Familiar;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.FamiliaresResponse;
import com.utnfrt.alimentar.data.entity.user.User;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseFragment;
import com.utnfrt.alimentar.ui.menu.menu1.crearfamilia.NewFamiliarActivity;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.EditFamiliarActivity;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu1Fragment extends BaseFragment implements Menu1Contract.View {

    @Inject
    Menu1Contract.Presenter presenter;
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
    @BindView(R.id.rv_mis_familiares_menu1)
    RecyclerView rvFamiliares;
    @BindView(R.id.fab_new_family_menu1)
    FloatingActionButton fabNewFamily;
    @BindView(R.id.tv_hola_usermenu1)
    TextView tvUser;
    private Menu1Adapter adapter;
    private GridLayoutManager layoutManager;
    private List<Familiar> listFamiliares = new ArrayList<>();
    private View view;

    public Menu1Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_menu_uno, container, false);
        ButterKnife.bind(this,view);
        ((App) getActivity().getApplication()).getComponent().injectMenu1(this);

        rvFamiliares.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 1);
        rvFamiliares.setLayoutManager(layoutManager);
        adapter = new Menu1Adapter(listFamiliares, getContext());
        adapter.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), EditFamiliarActivity.class);
            Gson gson = new Gson();
            String familiar = gson.toJson(listFamiliares.get(rvFamiliares.getChildAdapterPosition(v)));
            i.putExtra("familiar", familiar);
            startActivity(i);
        });
        rvFamiliares.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getUser();
        presenter.getFamiliares();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        listFamiliares.clear();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab_new_family_menu1) public void createFamiliar(){
        startActivity(new Intent(getContext(), NewFamiliarActivity.class));
    }

    @OnClick(R.id.iv_logout_menu1) public void logoutUser(){
        presenter.logout();
        logout();
    }

    @OnClick(R.id.tv_editar_mi_perfil) public void editProfile(){
        //TODO: start activity editar Perfil
    }

    @OnClick(R.id.btn_error) public void restartActivity(){
        presenter.rxJavaUnsuscribe();
        listFamiliares.clear();
        adapter.notifyDataSetChanged();
        presenter.getUser();
        presenter.getFamiliares();
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
            fabNewFamily.setVisibility(View.VISIBLE);
            clListaVacia.setVisibility(View.VISIBLE);
            tvListaVacia.setText(getString(idMessage));
        }
    }

    @Override
    public void loading() {
        fabNewFamily.setVisibility(View.INVISIBLE);
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        fabNewFamily.setVisibility(View.INVISIBLE);
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showUser(User user) {
        tvUser.setText(getString(R.string.hola) + " " + user.getNombres() + "!");
    }

    @Override
    public void showFamily(FamiliaresResponse familiares) {
        if (getContext()!=null){
            fabNewFamily.setVisibility(View.VISIBLE);
            rvFamiliares.setVisibility(View.VISIBLE);
            listFamiliares.addAll(familiares.getFamiliares().getFamiliar());
            adapter.notifyDataSetChanged();
        }
    }
}
