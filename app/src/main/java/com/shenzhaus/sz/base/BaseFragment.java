package com.shenzhaus.sz.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shenzhaus.sz.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/23.
 * Author: XuDeLong
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    public void toast(String mes){
        ToastUtils.showShort(mes);
    }
    public void showSnake(String msg){
        SnackbarUtils.with(getView()).setBgColor(getResources().getColor(R.color.colorPrimary)).setMessage(msg).show();
    }
    public abstract int getLayoutResource();
}
