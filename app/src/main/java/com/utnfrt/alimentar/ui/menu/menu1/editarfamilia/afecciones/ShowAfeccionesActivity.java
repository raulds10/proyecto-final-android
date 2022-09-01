package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.Afeccione;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionesResponse;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowAfeccionesActivity extends BaseActivity implements ShowAfeccionesContract.View{

    @Inject
    ShowAfeccionesContract.Presenter presenter;
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
    @BindView(R.id.rv_show_afecciones)
    RecyclerView rvAfecciones;
    private AfeccionesAdapter adapter;
    private GridLayoutManager layoutManager;
    private List<Afeccione> listAfecciones = new ArrayList<>();
    private Bundle bundle;
    private int selectedPos = 0;
    private String idFamiliar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_afecciones);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(ShowAfeccionesActivity.this);
        ((App) getApplication()).getComponent().injectShowAfecciones(this);

        bundle = getIntent().getExtras();
        idFamiliar = bundle.getString("idFamiliar");
        rvAfecciones.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvAfecciones.setLayoutManager(layoutManager);
        adapter = new AfeccionesAdapter(listAfecciones, getApplicationContext());
        adapter.setOnClickListener(v -> {
            selectedPos = rvAfecciones.getChildAdapterPosition(v);
            presenter.addAfeccion(idFamiliar, listAfecciones.get(selectedPos).getIdAfeccion() + "");
        });
        rvAfecciones.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getAfecciones();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        listAfecciones.clear();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_error) public void restartActivity(){
        presenter.rxJavaUnsuscribe();
        listAfecciones.clear();
        adapter.notifyDataSetChanged();
        presenter.getAfecciones();
    }

    @Override
    public void showError(int idTitle, int idMessage) {
        if (getApplicationContext()!=null){
            clError.setVisibility(View.VISIBLE);
            tvError.setText(getString(idMessage));
        }
    }

    @Override
    public void emptyList(int idMessage) {
        if (getApplicationContext()!=null){
            clListaVacia.setVisibility(View.VISIBLE);
            tvListaVacia.setText(getString(idMessage));
        }
    }

    @Override
    public void loading() {
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        clError.setVisibility(View.INVISIBLE);
        clListaVacia.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showAfeccionesList(AfeccionesResponse afeccionesResponse) {
        if (getApplicationContext()!=null){
            rvAfecciones.setVisibility(View.VISIBLE);
            listAfecciones.addAll(afeccionesResponse.getAfecciones());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addAfeccionOk() {
        Intent i = getIntent();
        Gson gson = new Gson();
        String json = gson.toJson(listAfecciones.get(selectedPos));
        i.putExtra("afeccion", json);
        setResult(RESULT_OK, i);
        finish();
    }
}