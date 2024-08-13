package com.nus.wewalk;

import static com.nus.wewalk.utilities.Utils.hideStatusBar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nus.wewalk.databinding.ActivityMainBinding;
import com.nus.wewalk.utilities.HealthConnectUtilKt;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideStatusBar(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        HealthConnectUtilKt.checkAllPermissionGrand(new Function1<Boolean, Unit>() {
            @Override
            public Unit invoke(Boolean isGranted) {
                if (!isGranted) {
                    requestPermissions(new String[]{"android.permission.health.READ_STEPS"}, 0x11);
                }
                return null;
            }
        });
    }

}