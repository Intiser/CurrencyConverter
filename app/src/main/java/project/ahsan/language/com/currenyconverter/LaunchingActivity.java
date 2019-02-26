package project.ahsan.language.com.currenyconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;
import project.ahsan.language.com.currenyconverter.api.DataFetcher;
import project.ahsan.language.com.currenyconverter.manager.DataManager;
import project.ahsan.language.com.currenyconverter.permission.PermissionChecker;

import android.content.Intent;
import android.os.Bundle;

public class LaunchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);
        callInternetPermission();
    }

    private void callInternetPermission() {
        PermissionChecker.internetPermissionChecker(this, new PermissionCallback() {
            @Override
            public void permissionGranted() {
                fetchJsonData();
            }

            @Override
            public void permissionRefused() {

            }
        });
    }

    private void fetchJsonData() {
        DataFetcher.sharedInstance().fecthData(new DataFetcher.FetchingCallback() {
            @Override
            public void dataFetchingSuccess(String json) {
                DataManager.sharedInstance().extractData(json);
                Intent intent = new Intent(LaunchingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void dataFetchingCanceled() {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Nammu.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
