package com.utnfrt.alimentar.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.CreateUsuarioResponse;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.ui.menu.MenuPrincipalActivity;
import com.utnfrt.alimentar.utils.DatePickerFragment;
import com.utnfrt.alimentar.utils.DateUtilities;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements SignupContract.View {

    @Inject
    SignupContract.Presenter presenter;
    @BindView(R.id.et_usuario_signup)
    EditText etUsuario;
    @BindView(R.id.et_full_name_signup)
    EditText etFullName;
    @BindView(R.id.et_altura_signup)
    EditText etAltura;
    @BindView(R.id.et_email_signup)
    EditText etEmail;
    @BindView(R.id.et_fecha_nac_signup)
    EditText etFechaNac;
    @BindView(R.id.et_peso_signup)
    EditText etPeso;
    @BindView(R.id.et_pass_signup)
    EditText etPassword;
    @BindView(R.id.et_repeat_pass_signup)
    EditText etRepeatPassword;
    @BindView(R.id.pb_loading_signup)
    ProgressBar pbLoading;
    @BindView(R.id.btn_siguiente_signup_activity)
    Button btnSiguiente;
    private String fechaNac = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(SignupActivity.this);
        ((App) getApplication()).getComponent().injectSignup(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @OnClick(R.id.et_fecha_nac_signup) public void selectBirthDate(){
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 porque enero es cero
            final String selectedDate = DateUtilities.dosDigitos(day) + "/" + DateUtilities.dosDigitos(month+1) + "/" + year;
            etFechaNac.setText(selectedDate);
        },100,3);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @OnClick(R.id.btn_siguiente_signup_activity) public void signupUser(){
        if (etFechaNac.getText().toString().isEmpty()) {
            showError(R.string.error_title_bottom_sheet, R.string.error_fecha_nacimiento);
        } else {
            fechaNac = DateUtilities.obtenerFechaParaHacerRequest(etFechaNac.getText().toString());
            presenter.signupUser(etUsuario.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(),
                    etRepeatPassword.getText().toString(), etFullName.getText().toString(), fechaNac, etPeso.getText().toString(),
                    etAltura.getText().toString());
        }
    }

    @Override
    public void loading() {
        pbLoading.setVisibility(View.VISIBLE);
        btnSiguiente.setEnabled(false);
    }

    @Override
    public void finishLoading() {
        pbLoading.setVisibility(View.INVISIBLE);
        btnSiguiente.setEnabled(true);
    }

    @Override
    public void userCreated(CreateUsuarioResponse response) {
        presenter.createFamiliar(response.getUsuario().getIdUsuario(), etFullName.getText().toString(), fechaNac,
                etPeso.getText().toString(), etAltura.getText().toString());
    }

    @Override
    public void familyCreated() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
