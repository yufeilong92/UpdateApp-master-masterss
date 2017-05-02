package me.shenfan.updateapp.sample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showDialog();
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("sssss");
        builder.setMessage("aaaaa");
        AlertDialog show = builder.create();
        show.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

    }
}
