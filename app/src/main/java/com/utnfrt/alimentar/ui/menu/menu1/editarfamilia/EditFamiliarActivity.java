package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.Afeccione;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.AfeccioneFamiliar;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.Familiar;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.ui.menu.menu1.crearfamilia.AfeccionEditAdapter;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones.ShowAfeccionesActivity;
import com.utnfrt.alimentar.utils.DatePickerFragment;
import com.utnfrt.alimentar.utils.DateUtilities;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditFamiliarActivity extends BaseActivity implements EditFamiliarContract.View {

    @Inject
    EditFamiliarContract.Presenter presenter;
    @BindView(R.id.et_full_name_edit_familiar)
    EditText etFullName;
    @BindView(R.id.et_altura_edit_familiar)
    EditText etAltura;
    @BindView(R.id.et_fecha_nac_edit_familiar)
    EditText etFechaNac;
    @BindView(R.id.et_peso_edit_familiar)
    EditText etPeso;
    @BindView(R.id.pb_loading_edit_familiar)
    ProgressBar pbLoading;
    @BindView(R.id.btn_create_edit_familiar)
    Button btnSiguiente;
    @BindView(R.id.rv_afecciones_edit_familiar)
    RecyclerView rvAfecciones;
    private AfeccionEditAdapter adapter;
    private GridLayoutManager layoutManager;
    private List<AfeccioneFamiliar> listAfecciones = new ArrayList<>();
    private Bundle bundle;
    private Familiar f;
    private static final int ADD_AFECCION = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_familiar);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(EditFamiliarActivity.this);
        ((App) getApplication()).getComponent().injectEditFamiliar(this);
        bundle = getIntent().getExtras();
        if (bundle!=null){
            final Type tyObject = new TypeToken<Familiar>(){}.getType();
            Gson gson = new Gson();
            f = gson.fromJson(bundle.getString("familiar"),tyObject);
            etFullName.setText(f.getNombre());
            etFechaNac.setText(DateUtilities.obtenerFechaDeUnRequest2(f.getFechaNacimiento()));
            etPeso.setText(f.getPeso() + "");
            etAltura.setText(f.getAltura() + "");
            listAfecciones.clear();
            rvAfecciones.setHasFixedSize(true);
            layoutManager = new GridLayoutManager(getApplicationContext(), 1);
            rvAfecciones.setLayoutManager(layoutManager);
            adapter = new AfeccionEditAdapter(listAfecciones, getApplicationContext());
            rvAfecciones.setAdapter(adapter);
            if (!f.getAfecciones().isEmpty()){
                listAfecciones.addAll(f.getAfecciones());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @OnClick(R.id.tv_nueva_afeccion_edit_familiar) public void selectAfeccion(){
        Intent i = new Intent(getApplicationContext(), ShowAfeccionesActivity.class);
        i.putExtra("idFamiliar", f.getIdFamiliar() + "");
        startActivityForResult(i, ADD_AFECCION);
    }

    @OnClick(R.id.et_fecha_nac_edit_familiar) public void selectBirthDate(){
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 porque enero es cero
            final String selectedDate = DateUtilities.dosDigitos(day) + "/" + DateUtilities.dosDigitos(month+1) + "/" + year;
            etFechaNac.setText(selectedDate);
        },100,3);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @OnClick(R.id.btn_create_edit_familiar) public void editFamiliar(){
        String fechaNac = DateUtilities.obtenerFechaParaHacerRequest(etFechaNac.getText().toString());
        presenter.editFamiliar(f.getIdFamiliar() + "", etFullName.getText().toString(), fechaNac,
                etPeso.getText().toString(), etAltura.getText().toString());
    }

    @OnClick(R.id.iv_delete_edit_familiar) public void deleteFamiliar(){
        DialogInterface.OnClickListener listener = (dialog, id) -> {
            if (id == DialogInterface.BUTTON_POSITIVE) {
                presenter.deleteFamiliar(f.getIdFamiliar());
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name).setCancelable(true)
                .setMessage("Estas seguro que quieres eliminar este familiar?")
                .setPositiveButton(R.string.aceptar, listener).setNegativeButton(R.string.cancelar,listener)
                .show();
    }

    @Override
    public void editOk() {
        Toast.makeText(getApplicationContext(), "Se modificaron con exitos los datos del familiar!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void deleteOk() {
        Toast.makeText(getApplicationContext(), "Se eliminó exitosamente el familiar", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_AFECCION && resultCode == RESULT_OK) {
            final Type afeccionType = new TypeToken<Afeccione>(){}.getType();
            Gson gson = new Gson();
            Afeccione af = gson.fromJson(data.getExtras().getString("afeccion"),afeccionType);
            AfeccioneFamiliar afeccioneFamiliar = new AfeccioneFamiliar(af.getIdAfeccion(), af.getNombre());
            listAfecciones.add(afeccioneFamiliar);
            adapter.notifyDataSetChanged();
        }
    }
}