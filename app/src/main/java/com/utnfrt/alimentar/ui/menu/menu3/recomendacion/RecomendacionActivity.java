package com.utnfrt.alimentar.ui.menu.menu3.recomendacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.MenuDetail;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones.AfeccionesAdapter;
import com.utnfrt.alimentar.ui.menu.menu3.detailmenu.DetailMenuActivity;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecomendacionActivity extends BaseActivity implements RecomendacionContract.View {

    @Inject
    RecomendacionContract.Presenter presenter;
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
    @BindView(R.id.rv_recomendacion)
    RecyclerView rvRecomendacion;
    private RecomendacionAdapter adapter;
    private GridLayoutManager layoutManager;
    private List<MenuDetail> listMenu = new ArrayList<>();
    private List<Integer> listIdMenu = new ArrayList<>();
    private int currentPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacion);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(RecomendacionActivity.this);
        ((App) getApplication()).getComponent().injectRecomendacion(this);

        rvRecomendacion.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvRecomendacion.setLayoutManager(layoutManager);
        adapter = new RecomendacionAdapter(listMenu, getApplicationContext());
        adapter.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), DetailMenuActivity.class);
            i.putExtra("idMenu", listMenu.get(rvRecomendacion.getChildAdapterPosition(v)).getIdMenu() + "");
            startActivity(i);
        });
        rvRecomendacion.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getMenuRecomendado();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        listMenu.clear();
        listIdMenu.clear();
        currentPos = 0;
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_error) public void restartActivity(){
        presenter.rxJavaUnsuscribe();
        listMenu.clear();
        listIdMenu.clear();
        currentPos = 0;
        adapter.notifyDataSetChanged();
        presenter.getMenuRecomendado();
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
    public void showMenuRecomendado(List<Integer> response) {
        if (getApplicationContext()!=null){
            rvRecomendacion.setVisibility(View.VISIBLE);
            listIdMenu.addAll(response);
            presenter.getMenuComplete(listIdMenu.get(currentPos) + "");
        }
    }

    @Override
    public void showMenuComplete(DetailMenuResponse detailMenuResponse) {
        if (getApplicationContext()!=null){
            listMenu.add(detailMenuResponse.getMenu());
            currentPos ++;
            adapter.notifyDataSetChanged();
            if (currentPos < listIdMenu.size()) {
                presenter.getMenuComplete(listIdMenu.get(currentPos) + "");
            }
        }
    }
}