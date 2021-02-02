package br.com.programadorjm.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ServiceJobIntent extends JobIntentService {
    int count;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Test", "Job Create");
        count = 0;
    }

    //metodo por onde será iniciado o serviço
    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ServiceJobIntent.class, 123, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        count = 0;
        Log.d("Test", "Job Working");
        while (count < 10){
            try {
                Thread.sleep(1000);
                Log.d("Test", "Count: " + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Test", "Job Destroy");
    }
}
