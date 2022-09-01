package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal.confirm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.Hortaliza;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import java.lang.reflect.Type;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmVegetalActivity extends BaseActivity implements ConfirmVegetalContract.View {

    @Inject
    ConfirmVegetalContract.Presenter presenter;
    @BindView(R.id.tv_title_confirm_vegetal)
    TextView tvTitle;
    @BindView(R.id.et_cantidad_confirm_vegetal)
    EditText etCantidad;
    @BindView(R.id.pb_loading_confirm_vegetal)
    ProgressBar pbLoading;
    @BindView(R.id.btn_create_confirm_vegetal)
    Button btnCreate;
    private Bundle bundle;
    private Hortaliza h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vegetal);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(ConfirmVegetalActivity.this);
        ((App) getApplication()).getComponent().injectConfirmVegetal(this);
        bundle = getIntent().getExtras();
        if (bundle!=null){
            final Type tyObject = new TypeToken<Hortaliza>(){}.getType();
            Gson gson = new Gson();
            h = gson.fromJson(bundle.getString("vegetal"),tyObject);
            tvTitle.setText(h.getNombre());
        }
    }

    @OnClick(R.id.btn_create_confirm_vegetal) public void createVegetal() {
        presenter.createHortaliza(h.getIdHortaliza() + "", etCantidad.getText().toString());
    }

    @Override
    public void loading() {
        pbLoading.setVisibility(View.VISIBLE);
        btnCreate.setEnabled(false);
    }

    @Override
    public void finishLoading() {
        pbLoading.setVisibility(View.INVISIBLE);
        btnCreate.setEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void createOk() {
        Intent i = getIntent();
        i.putExtra("create", "ok");
        setResult(RESULT_OK, i);
        finish();
    }
}