package com.example.macl6.mydesign.Activity;

        import android.content.Intent;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.example.macl6.mydesign.R;
        import com.example.macl6.mydesign.Utils.SharedPref;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPref.init(SplashActivity.this);
                boolean flag = SharedPref.read(SharedPref.FLAG,false);
                if (flag) {
                    Intent intent = new Intent(SplashActivity.this, FingerPrintActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);

    }
}
