package me.shenfan.updateapp.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initContent();


    }

    private void initContent() {
        Intent intent = new Intent(LoginActivity.this,MyService.class);
        startService(intent);
        Intent intent1 = StartActivity(MainActivity.class);
        startActivity(intent1);
        LoginActivity.this.finish();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    }


    /**
     * @param cla 跳转的类
     * @return intent
     */
    private Intent StartActivity(Class<?> cla) {
        Intent intent = new Intent(LoginActivity.this, cla);
        return intent;
    }

    private void initView() {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                break;
        }
    }
}
