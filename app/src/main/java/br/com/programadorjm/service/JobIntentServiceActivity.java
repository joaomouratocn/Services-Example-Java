package br.com.programadorjm.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class JobIntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_intent_service);

        Button btnStart = findViewById(R.id.btn_start_job);

        btnStart.setOnClickListener(v -> {
            //estanciando a intent do job
            Intent intent = new Intent(getBaseContext(), ServiceJobIntent.class);

            //creando o serviço
            ServiceJobIntent.enqueueWork(getBaseContext(), intent);
        });
    }
}