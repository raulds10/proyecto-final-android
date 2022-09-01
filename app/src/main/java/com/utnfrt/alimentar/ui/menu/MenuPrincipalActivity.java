package com.utnfrt.alimentar.ui.menu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.ui.base.BaseActivity;
import com.utnfrt.alimentar.ui.menu.menu2.Menu2Fragment;
import com.utnfrt.alimentar.ui.menu.menu1.Menu1Fragment;
import com.utnfrt.alimentar.ui.menu.menu3.Menu3Fragment;
import com.utnfrt.alimentar.utils.StatusBarUtilities;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuPrincipalActivity extends BaseActivity {

    @BindView(R.id.navigation_menu_principal)
    BottomNavigationView navigationView;

    private Fragment fragment1, fragment2, fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        ButterKnife.bind(this);
        StatusBarUtilities.cambiarColor(MenuPrincipalActivity.this);
        fragment1 = new Menu1Fragment();
        fragment2 = new Menu2Fragment();;
        fragment3 = new Menu3Fragment();;

        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {

                case R.id.nav_menu_fragment_1:
                    setFragment(fragment1);
                    return true;
                case R.id.nav_menu_fragment_2:
                    setFragment(fragment2);
                    return true;
                case R.id.nav_menu_fragment_3:
                    setFragment(fragment3);
                    return true;
                default:
                    return false;
            }
        });
        navigationView.setSelectedItemId(R.id.nav_menu_fragment_1);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_menu_principal,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (navigationView.getSelectedItemId() != R.id.nav_menu_fragment_1) navigationView.setSelectedItemId(R.id.nav_menu_fragment_1);
        else moveTaskToBack(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null){
            try{
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }catch (Exception e){e.printStackTrace();}
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
}
