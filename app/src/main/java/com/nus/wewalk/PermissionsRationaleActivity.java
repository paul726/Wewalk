package com.nus.wewalk;

import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.health.connect.client.PermissionController;

import com.nus.wewalk.databinding.ActivityPermissionsRationaleBinding;

import java.util.HashSet;
import java.util.Set;

public class PermissionsRationaleActivity extends AppCompatActivity {

    private final Set<String> PERMISSION = new HashSet<>();
    private ActivityResultLauncher<Set<String>> requestPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPermissionsRationaleBinding binding = ActivityPermissionsRationaleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PERMISSION.add("android.permission.health.READ_STEPS");

        // Create the permissions launcher
        ActivityResultContract<Set<String>, Set<String>> requestPermissionActivityContract = PermissionController.createRequestPermissionResultContract();
        requestPermissions = registerForActivityResult(requestPermissionActivityContract, new ActivityResultCallback<Set<String>>() {
            @Override
            public void onActivityResult(Set<String> permissions) {
                if (permissions.containsAll(PERMISSION)) {
                    // Permissions successfully granted
                    finish();
                } else {
                    // Lack of required permissions
                }
            }
        });

        binding.buttonRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRationaleDialog();
            }
        });
    }

    private void showRationaleDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Health Permission Needed")
                .setMessage("We need access to your health data (e.g., step count) to provide personalized fitness advice and goals.")
                .setPositiveButton("Allow", (dialog, which) -> {
                    // Request health-related permissions
                    requestPermissions.launch(PERMISSION);
                })
                .setNegativeButton("Deny", (dialog, which) -> {
                    // Handle the permission denial
                    finish();
                })
                .create()
                .show();
    }
}
