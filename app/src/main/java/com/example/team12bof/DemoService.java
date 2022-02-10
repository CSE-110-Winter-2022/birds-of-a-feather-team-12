package com.example.team12bof;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

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



<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    }

    @Override
    public void onDestroy() {
        Toast.makeText(DemoService.this, "Service Stopped",Toast.LENGTH_SHORT).show();
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        super.onDestroy();
    }
}

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
