package br.com.programadorjm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Chronometer;

import androidx.annotation.Nullable;

/*
Usando IBinder -> Muito usado quando o serviço serviço é no processo local
 */

public class BoundServiceIbinder extends Service {
    //mesagem que será retornada pelo metodo do serviço
    private String MSG_RETURN = "Chronometer is running";

    //cronometro que sera executado em background
    private Chronometer chronometer;

    //interface usada para se juntar ao serviço
    private IBinder binder = new GetBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        //estanciando cronometro
        chronometer = new Chronometer(this);
        chronometer.start();
    }

    //acessar o serviço
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    //Juntar-se novamente ao serviço
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    //Desligar-se do serviço
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    //Destruir o serviço
    @Override
    public void onDestroy() {
        super.onDestroy();
        chronometer.stop();
    }

    //Metodo que retorna messagem
    public String getMessege(){
        return String.valueOf(chronometer.getBase());
    }

    //classe para o cliente se vincular, retornado uma instancia do BoundServiceIBinder, sempre será retornada a mesmo instaca do service após criado
    public class GetBinder extends Binder{
        BoundServiceIbinder getService(){
            return BoundServiceIbinder.this;
        }
    }
}
