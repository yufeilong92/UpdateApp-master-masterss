package me.shenfan.updateapp.sample;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import me.shenfan.updateapp.UpdateService;

public class updateService extends Service {
    public static final String TAG = "=====";
    private static final String URL = "http://27.221.81.15/dd.myapp.com/16891/63C4DA61823B87026BBC8C22BBBE212F.apk?mkey=575e443c53406290&f=8b5d&c=0&fsname=com.daimajia.gold_3.2.0_80.apk&p=.apk";

    public updateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        requestok();
    }

    private void requestok() {
        //
        VoUpdate.DateBean dateBean = new VoUpdate.DateBean();
        dateBean.setUpcode("0");
        dateBean.setUpdateSize("4M");
        dateBean.setUpinfo("");
        dateBean.setUpVersion("v1.21");
        if (getLoadVersion().equals("")) {
            showUpdateSuccessDialog(dateBean);
        } else if (!getLoadVersion().equals(dateBean.getUpVersion())) {
            //忽略的版本
            showUpdateSuccessDialog(dateBean);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);


    }

    public void showUpdateSuccessDialog(final VoUpdate.DateBean bean) {
        final WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams para = new WindowManager.LayoutParams();
        para.height = -2;//WRAP_CONTENT
        para.width = -1;//WRAP_CONTENT
        para.format = 1;
        para.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        para.gravity = Gravity.CENTER;
        para.type = WindowManager.LayoutParams.TYPE_TOAST;


        final View contentView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_layout, null);
        Button btn_inguor = (Button) contentView.findViewById(R.id.btn_inguor);
        Button btn_update = (Button) contentView.findViewById(R.id.btn_update);
        TextView tv_newVersion = (TextView) contentView.findViewById(R.id.tv_newVersion);
        TextView tv_nowVersion = (TextView) contentView.findViewById(R.id.tv_nowVersion);
        TextView tv_versionsize = (TextView) contentView.findViewById(R.id.tv_versionsize);
        TextView tv_updateTitle = (TextView) contentView.findViewById(R.id.tv_updateTitle);
        Button btn_fouore = (Button) contentView.findViewById(R.id.btn_fouore);

        tv_newVersion.setText(bean.getUpVersion());
        tv_nowVersion.setText(getVersionCode());
        tv_versionsize.setText(bean.getUpdateSize());
        tv_updateTitle.setText("有版本更新");
        String upcode = bean.getUpcode();
        int code = Integer.parseInt(upcode);
        switch (code) {
            case 0:
                btn_fouore.setOnClickListener(new View.OnClickListener() {//以后在数
                    @Override
                    public void onClick(View v) {
                        wm.removeView(contentView);
                    }
                });
                btn_inguor.setOnClickListener(new View.OnClickListener() {//忽略改版
                    @Override
                    public void onClick(View v) {
                        saveVersion(bean.getUpVersion());
                        wm.removeViewImmediate(contentView);
                    }
                });
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UpdateService.Builder.create(URL)
                                .setStoreDir("update/flag")
                                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                                .setIcoResId(R.mipmap.ic_launcher)
                                .build(getApplicationContext());
                        wm.removeView(contentView);
                    }
                });
                break;
            case 1:
                btn_fouore.setOnClickListener(new View.OnClickListener() {//以后在数
                    @Override
                    public void onClick(View v) {
                        wm.removeView(contentView);
                    }
                });
                btn_inguor.setVisibility(View.GONE);
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UpdateService.Builder.create(URL)
                                .setStoreDir("update/flag")
                                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                                .setIcoResId(R.mipmap.ic_launcher)
                                .build(getApplicationContext());
                        wm.removeView(contentView);
                    }
                });
                break;
            case 2:
                btn_update.setText("确认");
                btn_fouore.setVisibility(View.GONE);
                btn_inguor.setVisibility(View.GONE);
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UpdateService.Builder.create(URL)
                                .setStoreDir("update/flag")
                                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                                .setIcoResId(R.mipmap.ic_launcher)
                                .build(getApplicationContext());
                        wm.removeView(contentView);
                    }
                });
                break;
            default:
                break;
        }

        wm.addView(contentView, para);
    }

    private String getVersionCode() {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        try {
            PackageInfo info = packageManager.getPackageInfo(getApplicationContext().getPackageName(), 0);
            String versionCode = info.versionName;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void saveVersion(String version) {
        SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences("date", 0).edit();
        edit.putString("version", version);
        edit.apply();
    }

    private String getLoadVersion() {
        SharedPreferences date = getApplicationContext().getSharedPreferences("date", 0);
        String version = date.getString("version", "");
        return version;
    }
}
