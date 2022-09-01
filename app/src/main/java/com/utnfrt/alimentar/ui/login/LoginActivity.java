package com.utnfrt.alimentar.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.ui.main.MainActivity;
import com.utnfrt.alimentar.ui.menu.MenuPrincipalActivity;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

    @BindView(R.id.et_username_login)
    EditText etUsuario;

    @BindView(R.id.et_password_login)
    EditText etPassword;

    @BindView(R.id.pb_loading_login)
    ProgressBar pbLoading;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(LoginActivity.this);
        ((App) getApplication()).getComponent().injectLogin(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @OnClick(R.id.btn_login) public void loginApp(){
        presenter.loginUser(etUsuario.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void userLoggedIn() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void loading() {
        pbLoading.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
    }

    @Override
    public void finishLoading() {
        pbLoading.setVisibility(View.INVISIBLE);
        btnLogin.setEnabled(true);
    }
}
