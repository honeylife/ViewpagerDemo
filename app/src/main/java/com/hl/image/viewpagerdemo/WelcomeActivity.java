package com.hl.image.viewpagerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, Runnable {

    private TextView welcome_tv;
    private ImageView welcome_im;
    private boolean flag = true;
    private int mTime = 4;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            welcome_tv.setText((String)msg.obj);
            mTime--;
            Log.e("TAG","---mTime--"+mTime);
            if (mTime > 0) {
                welcome_tv.setText(mTime + "秒跳转");
            } else if (mTime == 0) {
                flag = false;
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                flag = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome_tv = (TextView) findViewById(R.id.welcome_tv);
        welcome_im = (ImageView) findViewById(R.id.welcome_im);
        welcome_im.setOnClickListener(this);
        new Thread(this).start();
    }

    @Override
    public void onClick(View view) {
        if (view == welcome_im) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void run() {
        try {
            Log.e("TAG","---run--");
            while (flag) {
                Log.e("TAG","---flag--");
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
//                String str = sdf.format(new Date());
//                handler.sendMessage(handler.obtainMessage(100, str));
                handler.sendEmptyMessage(0);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
