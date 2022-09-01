package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.Hortaliza;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizasResponse;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm.ConfirmVegetalActivity;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectVegetalActivity extends BaseActivity implements SelectVegetalContract.View {

    @Inject
    SelectVegetalContract.Presenter presenter;
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
    @BindView(R.id.rv_select_vegetal)
    RecyclerView rvHortalizas;
    private SelectVegetalAdapter adapter;
    private GridLayoutManager layoutManager;
    private List<Hortaliza> listHortalizas = new ArrayList<>();
    private static final int ADD_VEGETAL = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vegetal);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(SelectVegetalActivity.this);
        ((App) getApplication()).getComponent().injectSelectVegetal(this);

        rvHortalizas.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvHortalizas.setLayoutManager(layoutManager);
        adapter = new SelectVegetalAdapter(listHortalizas, getApplicationContext());
        adapter.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ConfirmVegetalActivity.class);
            Gson gson = new Gson();
            String json = gson.toJson(listHortalizas.get(rvHortalizas.getChildAdapterPosition(v)));
            i.putExtra("vegetal", json);
            startActivityForResult(i, ADD_VEGETAL);
        });
        rvHortalizas.setAdapter(adapter);
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

    @OnClick(R.id.btn_error) public void restartActivity(){
        presenter.rxJavaUnsuscribe();
        listHortalizas.clear();
        adapter.notifyDataSetChanged();
        presenter.getHortalizas();
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
    public void showVegetales(HortalizasResponse response) {
        if (getApplicationContext()!=null){
            rvHortalizas.setVisibility(View.VISIBLE);
            listHortalizas.addAll(response.getHortalizas());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_VEGETAL && resultCode == RESULT_OK) {
            if (data.getExtras().getString("create") != null) {
                if (data.getExtras().getString("create").equals("ok")) {
                    Toast.makeText(getApplicationContext(), "Ingrediente agregado!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}