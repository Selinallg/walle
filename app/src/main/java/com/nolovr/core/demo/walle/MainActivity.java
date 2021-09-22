package com.nolovr.core.demo.walle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.meituan.android.walle.WalleChannelReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tv_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            String        packageName = getPackageName();
            PackageInfo   p = getPackageManager().getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);
            FeatureInfo[] f = p.reqFeatures;
            for (int i = 0; i < f.length; i++) {
                Log.d(TAG, "--------onCreate: "+f[0].name);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        boolean b = getPackageManager().hasSystemFeature("com.cmcc.vr.mode");

        Log.d(TAG, "onCreate: ===="+b);

        tv_read = findViewById(R.id.tv_read);
        findViewById(R.id.button_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channel = getChannel(MainActivity.this);
                String msg = getMsg(MainActivity.this);
                tv_read.setText("渠道：" + channel + "   获取额外信息  " + msg);
            }
        });


        if (true){
            return;
        }

//        String cmd = "am start -n com.nolovr.nolohome.core/.SplashActivity";
//        String cmd = "ps -A";
//        String cmd = "root";
//        String cmd = "pm disable com.nolovr.nolohome.core";
//        String cmd = "pm enable com.nolovr.nolohome.core";
//        String cmd = "am force-stop com.nolovr.nolohome.core";
//        String cmd = "am force-stop com.nolovr.nolohome.core";



//        String cmd = "app_process -Djava.class.path=/data/local/tmp/classes.dex /system/bin shellService.Main";


//        String cmd = "am kill com.nolovr.nolohome.core";
//        String cmd = "kill -9 2060";


        String cmd = "pm install -r -t /sdcard/CloudVR-Client-debug.apk";
//        String cmd = "pm install /sdcard/CloudVR-Client-debug.apk";
//        String cmd = "pm install CloudVR-Client-debug.apk";
//        String cmd = "pm uninstall com.nolovr.core.cloudvr.client";
        CommandExecution.CommandResult result = CommandExecution.execCommand(cmd, false);


        Log.d(TAG, "onCreate: result="+result.result);
        Log.d(TAG, "onCreate: result="+result.errorMsg);
        Log.d(TAG, "onCreate: result="+result.successMsg);

//        cmd = "ls";
//        CommandExecution.CommandResult result2 = CommandExecution.execCommand(cmd, false);
//
//        Log.d(TAG, "onCreate: result="+result2.result);
//        Log.d(TAG, "onCreate: result="+result2.errorMsg);
//        Log.d(TAG, "onCreate: result="+result2.successMsg);
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