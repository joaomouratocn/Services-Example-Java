package br.com.programadorjm.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class BackgroundService extends IntentService {
    private int count;

    public BackgroundService() {//Construtor
        super("BackgroundService");
        count = 0;
    }

    //neste metodo o serviço e criado em uma nova thread, e começa a executar suas tarefas, para cada evento que deseja ser iniciado, são infilerados no atribudo intent e executado um apos o outro
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (count < 7){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count ++;
            Log.d("count","Count: " + count);
        }
        count = 0;
    }
}
