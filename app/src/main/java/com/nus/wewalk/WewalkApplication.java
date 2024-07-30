package com.nus.wewalk;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import androidx.health.connect.client.HealthConnectClient;

public class WewalkApplication extends Application {

    public static HealthConnectClient healthConnectClient;

    @Override
    public void onCreate() {
        super.onCreate();
        int availabilityStatus = HealthConnectClient.getSdkStatus(this);
        if (availabilityStatus == HealthConnectClient.SDK_UNAVAILABLE) {
            System.exit(0);
            return;
        }

        if (availabilityStatus == HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED) {
            String uriString = "market://details?id=$providerPackageName&url=healthconnect%3A%2F%2Fonboarding";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
            intent.putExtra("overlay", true);
            intent.putExtra("callerId", getPackageName());
            intent.setPackage("com.android.vending");
            return;
        }

        healthConnectClient = HealthConnectClient.getOrCreate(this);
    }
}
