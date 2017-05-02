package me.shenfan.updateapp.sample;

import android.app.Notification;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.shenfan.updateapp.UpdateService;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://27.221.81.15/dd.myapp.com/16891/63C4DA61823B87026BBC8C22BBBE212F.apk?mkey=575e443c53406290&f=8b5d&c=0&fsname=com.daimajia.gold_3.2.0_80.apk&p=.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void update(View view){
        UpdateService.Builder.create(URL).build(this);
    }

    public void updateFlag(View view){
        UpdateService.Builder.create(URL)
                .setStoreDir("update/flag")
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                .setIcoResId(R.mipmap.ic_launcher)
                .build(this);
}

    public void updateStore(View view){
        UpdateService.Builder.create(URL)
                .setStoreDir("update/store")
                .build(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
