package com.shenzhaus.sz.common;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.shenzhaus.sz.MainActivity;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.activity.LoginActivity;
import com.shenzhaus.sz.base.BaseActivity;
import com.shenzhaus.sz.intf.OnRequestDataListener;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {
    private static final int SHOW_TIME_MIN = 3000;
    @Bind(R.id.lauch_screen)
    ImageView mLauchScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTask();
        getLaunchScreen();
        initData();
    }

    private void getLaunchScreen() {
        Api.getLaunchScreen(this, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, final JSONObject data) {
                    Glide.with(getApplicationContext()).load(data.getString("info")).into(mLauchScreen);
            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });
    }

    private void initTask() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SPUtils.getInstance().contains("token")){
                    ActivityUtils.startActivity(MainActivity.class);
                }else{
                    ActivityUtils.startActivity(LoginActivity.class);
                }
                finish();
            }
        },SHOW_TIME_MIN);
    }


    private void initData(){
        Api.getAppConfig(this, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                SPUtils.getInstance().put("config",data.getJSONObject("data").toJSONString());
            }
            @Override
            public void requestFailure(int code, String msg) {
            }
        });

    }


    @Override
    public int getLayoutResource() {
        return R.layout.activity_splash;
    }

}
