package com.shenzhaus.sz.activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.shenzhaus.sz.MainActivity;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.base.BaseActivity;
import com.shenzhaus.sz.common.Api;
import com.shenzhaus.sz.common.WebviewActivity;
import com.shenzhaus.sz.intf.OnRequestDataListener;
import com.hss01248.dialog.StyledDialog;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity {
    Platform mPlatForm;
    Dialog mLoadingDialog;
    MyHandler mHandler;
    JSONObject params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlatForm = ShareSDK.getPlatform(Wechat.NAME);
        mHandler = new MyHandler();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    public void loginWx(View v){
        mPlatForm.setPlatformActionListener(mPlatListener);
        mPlatForm.authorize();
        mLoadingDialog = StyledDialog.buildLoading().setActivity(this).show();
    }

    public void xieyi(View v){
        Bundle temp = new Bundle();
        temp.putString("title",getResources().getString(R.string.xieyi));
        temp.putString("jump", Api.URL_GAME_XIEYI);
        ActivityUtils.startActivity(temp,WebviewActivity.class);
    }

    private PlatformActionListener mPlatListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            //mLoadingDialog.dismiss();
            PlatformDb db = platform.getDb();
            String name = db.getUserName();
            String a = db.exportData();
            JSONObject t = JSON.parseObject(a);
            String from = "Wechat";
            String head_img = db.getUserIcon();
            String openid = db.getUserId();
            String access_token = db.getToken();
            String expires_date = db.getExpiresTime()+"";
            params = new JSONObject();
            params.put("name",name);
            params.put("from",from);
            params.put("head_img",head_img);
            params.put("openid",openid);
            params.put("unionid",t.getString("unionid"));
            params.put("access_token",access_token);
            params.put("expires_date",expires_date);
            params.put("qudao",Api.QUDAO);
            mHandler.sendEmptyMessage(1);
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            mLoadingDialog.dismiss();
        }

        @Override
        public void onCancel(Platform platform, int i) {
            mLoadingDialog.dismiss();
        }

    };

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
              Api.doLogin(LoginActivity.this, params, new OnRequestDataListener() {
                @Override
                public void requestSuccess(int code, JSONObject data) {

                    mLoadingDialog.dismiss();
                    SPUtils.getInstance().put("token",data.getString("token"));
                    JSONObject userinfo = data.getJSONObject("data");
                    SPUtils.getInstance().put("balance",userinfo.getString("balance"));
                    SPUtils.getInstance().put("id",userinfo.getString("id"));
                    SPUtils.getInstance().put("avatar",userinfo.getString("avatar"));
                    SPUtils.getInstance().put("user_nicename",userinfo.getString("user_nicename"));
                    SPUtils.getInstance().put("signaling_key",userinfo.getString("signaling_key"));
                    SPUtils.getInstance().put("bgm","1");
                    SPUtils.getInstance().put("yinxiao","1");
                    ActivityUtils.startActivity(MainActivity.class);
                    finish();
                }

                @Override
                public void requestFailure(int code, String msg) {
                    toast(msg);
                    mLoadingDialog.dismiss();
                }
            });
            }
        }
    }
}
