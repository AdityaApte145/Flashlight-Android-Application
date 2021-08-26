package com.example.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Switch;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Switch toggleFlashLightOnOff;
    private CameraManager cameraManager;
    private String getCameraID;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleFlashLightOnOff = findViewById(R.id.ToggleFlashlight);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        }
        catch (CameraAccessException e) {
            e.printStackTrace();
        }


        toggleFlashLightOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (toggleFlashLightOnOff.isChecked()) {
                    try {
                        cameraManager.setTorchMode(getCameraID, true);
                        Toast.makeText(MainActivity.this, "Flashlight is turned ON", Toast.LENGTH_SHORT).show();
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        cameraManager.setTorchMode(getCameraID, false);

                        Toast.makeText(MainActivity.this, "Flashlight is turned OFF", Toast.LENGTH_SHORT).show();
                    } catch (CameraAccessException e) {

                        e.printStackTrace();
                    }
                }
            }
        });
    }
}

