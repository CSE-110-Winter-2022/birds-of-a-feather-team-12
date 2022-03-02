package com.example.team12bof;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

public class DemoService extends Service {

    public DemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(DemoService.this, "Service Started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);




    }

    @Override
    public void onDestroy() {
        Toast.makeText(DemoService.this, "Service Stopped",Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
}