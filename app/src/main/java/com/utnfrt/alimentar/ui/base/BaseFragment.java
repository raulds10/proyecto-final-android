package com.utnfrt.alimentar.ui.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.utnfrt.alimentar.ui.login.LoginActivity;
import com.utnfrt.alimentar.ui.main.MainActivity;

public abstract class BaseFragment extends Fragment implements MvpView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(false);
    }

    @Override
    public void loading() {

    }

    @Override
    public void finishLoading() {

    }

    @Override
    public void showError(int idTitle, int idMessage) {

    }

    @Override
    public void emptyList(int idMessage) {

    }

    @Override
    public void logout() {
        if (getContext()!=null){
            Intent i = new Intent(getContext(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }
}
