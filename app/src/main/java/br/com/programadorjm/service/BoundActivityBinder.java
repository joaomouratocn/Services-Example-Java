package br.com.programadorjm.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BoundActivityBinder extends AppCompatActivity{
    BoundServiceIbinder boundServiceIBinder;
    //Variavel para verificar se ja existe uma conecxão
    Boolean isConected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_binder);

        TextView txt_messege = findViewById(R.id.txt_msg);
        Button btn_getMessege = findViewById(R.id.btn_get_messege);
        btn_getMessege.setOnClickListener(v -> {
            if (isConected){
                String text = boundServiceIBinder.getMessege();
                txt_messege.setText(text);
            }else{Toast.makeText(getApplicationContext(), "Not connected", Toast.LENGTH_SHORT).show(); }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //iniciando e se juntado ao serviço
        Intent intent = new Intent(getApplicationContext(), BoundServiceIbinder.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE); //BIND_AUTO_CREATE inicia o serviço enquanto estiver alguem vinculado
    }

    @Override
    protected void onStop() {
        super.onStop();
        //desvinculando ao serviço
        unbindService(serviceConnection);
        isConected = false;
    }

    //Estanciando conecxão com o serviço
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundServiceIbinder.GetBinder getBinder = (BoundServiceIbinder.GetBinder) service;
            boundServiceIBinder = getBinder.getService();
            isConected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConected = false;
        }
    };
}