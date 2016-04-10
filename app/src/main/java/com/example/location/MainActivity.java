package com.example.location;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.location.geofence.MyActivityGeofence;
import com.example.location.location.MyActivityLocation;
import com.example.location.recognition.MyActivityRecognition;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private Button mStartLocationButton;
    private Button mStartRecognitionButton;
    private Button mStartGeofenceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartLocationButton = (Button) findViewById(R.id.button_location);
        mStartRecognitionButton = (Button) findViewById(R.id.button_recognition);
        mStartGeofenceButton = (Button) findViewById(R.id.button_geofence);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            changeButtonState(false);

        } else {
            changeButtonState(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    changeButtonState(true);
                } else {
                    Toast.makeText(this, getString(R.string.enable_location), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void startLocation(View view) {
        Intent intent = new Intent(this, MyActivityLocation.class);
        startActivity(intent);
    }

    public void startRecognition(View view) {
        Intent intent = new Intent(this, MyActivityRecognition.class);
        startActivity(intent);
    }

    public void startGeofence(View view) {
        Intent intent = new Intent(this, MyActivityGeofence.class);
        startActivity(intent);
    }

    private void changeButtonState(boolean state) {
        mStartLocationButton.setEnabled(state);
        mStartRecognitionButton.setEnabled(state);
        mStartGeofenceButton.setEnabled(state);
    }
}