package com.utnfrt.alimentar.ui.menu.menu1.crearfamilia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.utils.DatePickerFragment;
import com.utnfrt.alimentar.utils.DateUtilities;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewFamiliarActivity extends BaseActivity implements NewFamiliarContract.View{

    @Inject
    NewFamiliarContract.Presenter presenter;
    @BindView(R.id.et_full_name_new_familiar)
    EditText etFullName;
    @BindView(R.id.et_altura_new_familiar)
    EditText etAltura;
    @BindView(R.id.et_fecha_nac_new_familiar)
    EditText etFechaNac;
    @BindView(R.id.et_peso_new_familiar)
    EditText etPeso;
    @BindView(R.id.pb_loading_new_familiar)
    ProgressBar pbLoading;
    @BindView(R.id.btn_create_new_familiar)
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_familiar);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(NewFamiliarActivity.this);
        ((App) getApplication()).getComponent().injectNewFamiliar(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @OnClick(R.id.et_fecha_nac_new_familiar) public void selectBirthDate(){
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 porque enero es cero
            final String selectedDate = DateUtilities.dosDigitos(day) + "/" + DateUtilities.dosDigitos(month+1) + "/" + year;
            etFechaNac.setText(selectedDate);
        },100,3);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @OnClick(R.id.btn_create_new_familiar) public void newFamiliarCreate(){
        String fechaNac = DateUtilities.obtenerFechaParaHacerRequest(etFechaNac.getText().toString());
        presenter.createFamiliar(etFullName.getText().toString(), fechaNac, etPeso.getText().toString(),
                etAltura.getText().toString());
    }

    @Override
    public void loading() {
        pbLoading.setVisibility(View.VISIBLE);
        etFechaNac.setEnabled(false);
        btnSiguiente.setEnabled(false);
    }

    @Override
    public void finishLoading() {
        pbLoading.setVisibility(View.INVISIBLE);
        etFechaNac.setEnabled(true);
        btnSiguiente.setEnabled(true);
    }

    @Override
    public void familyCreated() {
        etAltura.setText("");
        etPeso.setText("");
        etFullName.setText("");
        etFechaNac.setText("");
        Toast.makeText(getApplicationContext(), "Familiar Creado!", Toast.LENGTH_SHORT).show();
    }
}