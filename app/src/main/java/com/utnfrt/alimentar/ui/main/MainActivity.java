package com.utnfrt.alimentar.ui.main;

import android.content.Intent;
import android.os.Bundle;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.di.root.App;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.ui.login.LoginActivity;
import com.utnfrt.alimentar.ui.menu.MenuPrincipalActivity;
import com.utnfrt.alimentar.ui.signup.SignupActivity;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(MainActivity.this);
        ((App) getApplication()).getComponent().injectMain(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.verifyLoggedInUser();
    }

    @OnClick(R.id.cl_signup_main) public void startSignup(){
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }
    @OnClick(R.id.cl_login_main) public void startLogin(){startActivity(new Intent(getApplicationContext(), LoginActivity.class));}

    @Override
    public void userLoggedIn() {
        Intent i = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}