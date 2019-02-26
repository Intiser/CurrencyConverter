package project.ahsan.language.com.currenyconverter.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;
import project.ahsan.language.com.currenyconverter.application.CurrencyConverterApp;

public class PermissionChecker {

    public static void internetPermissionChecker(Activity activity, final PermissionCallback permissionCallback){
        if (isInternetPermissionGranted()) {
            permissionCallback.permissionGranted();

        } else {
            String[] internetPermission = new String[]{Manifest.permission.INTERNET};

            Nammu.askForPermission(activity, internetPermission, new PermissionCallback() {
                @Override
                public void permissionGranted() {

                    permissionCallback.permissionGranted();
                }

                @Override
                public void permissionRefused() {
                    permissionCallback.permissionRefused();
                }
            });

        }
    }

    private static boolean isInternetPermissionGranted() {
        int internetPermission = ContextCompat.checkSelfPermission(CurrencyConverterApp.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (internetPermission != PackageManager.PERMISSION_GRANTED) {
            return false;
        }


        return true;
    }
}
