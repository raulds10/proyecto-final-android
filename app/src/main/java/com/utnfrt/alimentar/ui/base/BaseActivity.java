package com.utnfrt.alimentar.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.utnfrt.alimentar.ui.login.LoginActivity;
import com.utnfrt.alimentar.ui.main.MainActivity;
import com.utnfrt.alimentar.utils.BottomSheetError;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void loading() {}

    @Override
    public void finishLoading() {

    }

    @Override
    public void showError(int idTitle, int idMessage) {
        Bundle bundle = new Bundle();
        bundle.putInt("idMessage",idMessage);
        bundle.putInt("idTitle",idTitle);
        BottomSheetError bottomSheet = new BottomSheetError();
        bottomSheet.setArguments(bundle);
        bottomSheet.show(getSupportFragmentManager(),"failOperationBottomSheet");
    }

    @Override
    public void emptyList(int idMessage) {

    }

    @Override
    public void logout() {
        if (getApplicationContext()!=null){
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null){
            try{
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }catch (Exception e){}
        }
        return super.dispatchTouchEvent(ev);
    }
}
