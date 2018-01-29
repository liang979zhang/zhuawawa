package com.shenzhaus.sz.activity;

import android.os.Bundle;
import android.view.View;

import com.shenzhaus.sz.R;
import com.shenzhaus.sz.base.BaseActivity;

public class TiquSucActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_tiqu_suc;
    }

    public void confirm(View view) {
        TiquSucActivity.this.finish();
    }
}
