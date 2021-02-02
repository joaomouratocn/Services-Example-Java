package br.com.programadorjm.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button foreground = findViewById(R.id.btn_service_foreground);
        Button background = findViewById(R.id.btn_service_background);
        Button jobItent = findViewById(R.id.btn_service_job);
        Button linked = findViewById(R.id.btn_service_linked);

        foreground.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForegroundActivity.class);
            startActivity(intent);
        });

        background.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BackgroundServiceActivity.class);
            startActivity(intent);
        });

        jobItent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, JobIntentServiceActivity.class);
            startActivity(intent);
        });

        linked.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BoundActivityBinder.class);
            startActivity(intent);
        });
    }
}