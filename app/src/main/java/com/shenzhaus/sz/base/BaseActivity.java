package com.shenzhaus.sz.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shenzhaus.sz.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/23.
 * Author: XuDeLong
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Boolean active = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
    }

    public void toast(String mes){
        ToastUtils.showShort(mes);
    }
    public void showSnake(String msg){
        SnackbarUtils.with(getWindow().getDecorView()).setBgColor(getResources().getColor(R.color.colorPrimary)).setMessage(msg).show();
    }
    public abstract int getLayoutResource();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }
}
