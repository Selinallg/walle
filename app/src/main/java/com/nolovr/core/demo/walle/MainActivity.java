package com.nolovr.core.demo.walle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meituan.android.walle.WalleChannelReader;

public class MainActivity extends AppCompatActivity {

    private TextView tv_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_read = findViewById(R.id.tv_read);
        findViewById(R.id.button_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channel = getChannel(MainActivity.this);
                String msg = getMsg(MainActivity.this);
                tv_read.setText("渠道：" + channel + "   获取额外信息  " + msg);
            }
        });
    }

    public static String getChannel(Context context) {
        if (WalleChannelReader.getChannel(context) == null) {
            return "official";//默认渠道
        }
        return WalleChannelReader.getChannel(context);
    }

    public static String getMsg(Context context) {
        if (WalleChannelReader.get(context, "msg") == null) {
            return "";//默认信息
        }
        // 或者也可以直接根据key获取
        return WalleChannelReader.get(context, "msg");
    }
}