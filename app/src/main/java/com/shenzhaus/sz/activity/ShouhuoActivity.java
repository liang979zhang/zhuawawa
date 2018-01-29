package com.shenzhaus.sz.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.base.BaseActivity;
import com.shenzhaus.sz.common.Api;
import com.shenzhaus.sz.intf.OnRequestDataListener;

import butterknife.Bind;

public class ShouhuoActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.shouhuoren)
    TextView mShouHuoRen;
    @Bind(R.id.shouhuo_location)
    TextView mShouHuoLocation;
    @Bind(R.id.lianxifangshi)
    TextView mLianXiFangShi;
    private String mToken;
    private String delivery_name;
    private String delivery_mobile;
    private String delivery_addr;

    public void goBack(View v){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString("token");
        initDate();
    }

    private void initDate() {
        JSONObject p = new JSONObject();
        p.put("token",mToken);
        Api.getShouHuoLocation(this, p, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                JSONObject jo = data.getJSONObject("data");
                delivery_name = jo.getString("delivery_name");
                delivery_mobile = jo.getString("delivery_mobile");
                delivery_addr = jo.getString("delivery_addr");
                if(!StringUtils.isEmpty(delivery_name)){
                    mShouHuoRen.setText(delivery_name);
                }
                if(!StringUtils.isEmpty(delivery_mobile)){
                    mLianXiFangShi.setText(delivery_mobile);
                }
                if(!StringUtils.isEmpty(delivery_addr)){
                    mShouHuoLocation.setText(delivery_addr);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
            }
        });
    }
    Dialog d;
    public void edit(View v){
        d = new MaterialDialog.Builder(this)
                .widgetColor(getResources().getColor(R.color.colorPrimary))
                .customView(R.layout.window_shouhuo,false)
               .show();
        final EditText e1 = (EditText)d.findViewById(R.id.window_shouhuoren);
        final EditText e2 = (EditText)d.findViewById(R.id.window_lianxifangshi);
        final EditText e3 = (EditText)d.findViewById(R.id.window_shouhuo);
        TextView t = (TextView)d.findViewById(R.id.window_positive);
        e1.setText(delivery_name);
        e2.setText(delivery_mobile);
        e3.setText(delivery_addr);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mShr = e1.getText().toString();
                final String mLxfs = e2.getText().toString();
                final String mSh = e3.getText().toString();
                if(StringUtils.isTrimEmpty(mShr) || StringUtils.isTrimEmpty(mLxfs) || StringUtils.isTrimEmpty(mSh)){
                    toast(getResources().getString(R.string.empty_tip));
                    return;
                }
                if(!RegexUtils.isMobileSimple(mLxfs)){
                    toast(getResources().getString(R.string.mobile_tip));
                    return;
                }
                JSONObject p = new JSONObject();
                p.put("token",mToken);
                p.put("delivery_name",mShr);
                p.put("delivery_mobile",mLxfs);
                p.put("delivery_addr",mSh);
                Api.setShouHuoLocation(ShouhuoActivity.this, p, new OnRequestDataListener() {
                    @Override
                    public void requestSuccess(int code, JSONObject data) {
                        toast(data.getString("descrp"));
                        if(d != null){
                            d.dismiss();
                        }
                        delivery_name = mShr;
                        delivery_mobile = mLxfs;
                        delivery_addr = mSh;
                        if(!StringUtils.isEmpty(delivery_name)){
                            mShouHuoRen.setText(delivery_name);
                        }
                        if(!StringUtils.isEmpty(delivery_mobile)){
                            mLianXiFangShi.setText(delivery_mobile);
                        }
                        if(!StringUtils.isEmpty(delivery_addr)){
                            mShouHuoLocation.setText(delivery_addr);
                        }
                    }

                    @Override
                    public void requestFailure(int code, String msg) {
                        toast(msg);
                    }
                });
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_shouhuo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
