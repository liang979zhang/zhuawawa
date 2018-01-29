package com.shenzhaus.sz;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.hss01248.dialog.StyledDialog;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/10/23.
 * Author: XuDeLong
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
        StyledDialog.init(this);
        MobSDK.init(this,"21e75c561cc28","cbb65c61bf460075ad37ed570b8ca2f1");
        CrashReport.initCrashReport(getApplicationContext(), "f973cfa9b3", false);
        //JPushInterface.setDebugMode(true);
        //JPushInterface.init(this);
    }
    public static MyApplication getInstance(){
        return instance;
    }
}
