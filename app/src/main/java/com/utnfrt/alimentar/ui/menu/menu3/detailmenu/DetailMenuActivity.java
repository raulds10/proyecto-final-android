package com.utnfrt.alimentar.ui.menu.menu3.detailmenu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMenuActivity extends BaseActivity implements DetailMenuContract.View {

    @Inject
    DetailMenuContract.Presenter presenter;
    @BindView(R.id.cl_error)
    ConstraintLayout clError;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.btn_error)
    Button btnError;
    @BindView(R.id.cl_loading)
    ConstraintLayout clLoading;
    @BindView(R.id.tv_name_menu_detail)
    TextView tvNombre;
    @BindView(R.id.tv_ingredientes_detail_menu)
    TextView tvIngredientes;
    @BindView(R.id.tv_preparacion_detail_menu)
    TextView tvPreparacion;
    private Bundle bundle;
    private String idMenu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(DetailMenuActivity.this);
        ((App) getApplication()).getComponent().injectDetailMenu(this);
        bundle = getIntent().getExtras();
        if (bundle!=null){
            idMenu = bundle.getString("idMenu");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getDetailMenu(idMenu);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
    }

    @OnClick(R.id.btn_error) public void restartActivity(){
        presenter.rxJavaUnsuscribe();
        presenter.getDetailMenu(idMenu);
    }

    @Override
    public void showError(int idTitle, int idMessage) {
        if (getApplicationContext()!=null){
            clError.setVisibility(View.VISIBLE);
            tvError.setText(getString(idMessage));
        }
    }

    @Override
    public void loading() {
        clError.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        clError.setVisibility(View.INVISIBLE);
        clLoading.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showDetailMenu(DetailMenuResponse response) {
        tvNombre.setText(response.getMenu().getNombre());
        if (response.getMenu().getIngredientes() != null) { tvIngredientes.setText(response.getMenu().getIngredientes()); }
        else { tvIngredientes.setText(""); }
        if (response.getMenu().getPreparacion() != null) { tvPreparacion.setText(response.getMenu().getPreparacion()); }
        else { tvPreparacion.setText(""); }
    }
}