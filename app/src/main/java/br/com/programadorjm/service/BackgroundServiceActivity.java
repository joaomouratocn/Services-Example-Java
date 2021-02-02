package br.com.programadorjm.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class BackgroundServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_service);

        Button btnStart = findViewById(R.id.btn_start_bg_service);

        Intent intent = new Intent(getBaseContext(), BackgroundService.class);

        btnStart.setOnClickListener(v -> {
            startService(intent);
        });
    }
}