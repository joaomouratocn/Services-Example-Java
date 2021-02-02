package br.com.programadorjm.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ForegroundService extends Service {

    //comando para realizar alguma configuração no serviço, somente chamado antes de iniciar o serviço
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //comando para iniciar o serviço
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //pegando texto putString
        String recevied = intent.getStringExtra("SERVICE_NAME");

        startForeground(1, createNotify(recevied));

        Toast.makeText(getApplicationContext(), "Service is running", Toast.LENGTH_SHORT).show();
        Log.v("test", "test");

        //execute comando do processo aqui

        //return(START_NOT_STICKY); //caso o sistema precise fechar o serviço, não é preciso voltar com ele.
        //return(START_STICKY); //asim que possivel retornar com o serviço
        return(START_REDELIVER_INTENT); //assim que possivel voltar com o serviço, recupetando valores passados pela intent
    }

    //juntar ao processo do serviço
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //comando para parar o serviço, fechar qualquer processo ou thread que estiver em execução
    @Override
    public void onDestroy() {
        super.onDestroy();
        //para serviço, passando TRUE para remover a notificação
        stopForeground(true);
        Toast.makeText(this, "Service stoped", Toast.LENGTH_SHORT).show();
    }

    private Notification createNotify(String recevied) {
        //Intent que irar abrir apartir da notificação
        Intent notifyIntent = new Intent(this, ForegroundActivity.class);

        //usada para especificar uma ação que será realizada no futuro. armazena a intent a ser bearta ao tocar na notitifação
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0);

        //Creando a estancia que será retornada
        Notification.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //em apis apartir da 28, para criar uma notificação precisa criar um canal, passado um ID, NAME e um IMPORTANCIA
            String CHANNEL_ID = "FORENGROUND_SERVICE";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "teste", NotificationManager.IMPORTANCE_DEFAULT);

            //apos criar o canal regista ele no sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

            builder = new Notification.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_android)
                    .setContentTitle("Service is running")
                    .setContentText(recevied)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);//remove a notificação automaticamente quando tocado
        }else{
            builder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.icon_android)
                    .setContentTitle("Service is running")
                    .setContentText(recevied)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);//remove a notificação automaticamente quando tocado
        }
        return builder.build();
    }
}
