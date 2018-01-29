package com.shenzhaus.sz.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.adapter.RecordZqRecyclerListAdapter;
import com.shenzhaus.sz.base.BaseActivity;
import com.shenzhaus.sz.common.Api;
import com.shenzhaus.sz.common.GlideCircleTransform;
import com.shenzhaus.sz.intf.OnRequestDataListener;
import com.shenzhaus.sz.model.DanmuMessage;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.Bind;

public class UserCenterOtherActivity extends BaseActivity {


    @Bind(R.id.user_avator)
    ImageView mUserAvator;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.user_all_num)
    TextView mUserAllNum;

    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private ArrayList<DanmuMessage> mListData = new ArrayList();
    private RecordZqRecyclerListAdapter mAdapter = new RecordZqRecyclerListAdapter(this,mListData);

    private String mToken;
    private String mUserId;
    public void goBack(View v){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString("token");
        Bundle data = getIntent().getExtras();
        if(data == null){
            toast(getString(R.string.net_error));
            finish();
            return;
        }
        mUserId =  data.getString("userId");
        getUserInfo(mUserId);
        mRefreshLayout.autoRefresh();
        initGameList();
    }

    private void initGameList() {
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //mRecyclerView.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(10)));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getGameData(0);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getGameData(mListData.size());
            }
        });
    }

    private void getGameData(final int limit_begin) {
        JSONObject params = new JSONObject();
        params.put("token",mToken);
        params.put("userId",mUserId);
        params.put("limit_begin",limit_begin);
        params.put("limit_num",10);
        Api.getZhuaRecord(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if(limit_begin == 0){
                    mListData.clear();
                }
                if(mRefreshLayout.isRefreshing()){
                    mRefreshLayout.finishRefresh();
                }
                if(mRefreshLayout.isLoading()){
                    mRefreshLayout.finishLoadmore();
                }
                JSONArray list = data.getJSONArray("info");
                for (int i = 0; i < list.size(); i++) {
                    DanmuMessage g = new DanmuMessage();
                    JSONObject t = list.getJSONObject(i);
                    g.setUserName(t.getString("name"));
                    g.setAvator(t.getString("img"));
                    g.setUid(t.getString("play_time"));
                    g.setMessageContent(t.getString("play_result"));
                    mListData.add(g);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
                if(mRefreshLayout.isRefreshing()){
                    mRefreshLayout.finishRefresh();
                }
                if(mRefreshLayout.isLoading()){
                    mRefreshLayout.finishLoadmore();
                }
            }
        });
    }

    private void getUserInfo(String userId) {
        JSONObject p = new JSONObject();
        p.put("token",mToken);
        p.put("id",userId);
        Api.getUserInfo(this, p, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                JSONObject userinfo = data.getJSONObject("data");
                mUserAllNum.setText(getResources().getString(R.string.zq_all_num)+userinfo.getString("all_num"));
                mUserName.setText(userinfo.getString("user_nicename"));
                Glide.with(UserCenterOtherActivity.this).load(userinfo.getString("avatar"))
                        .error(R.mipmap.logo)
                        .transform(new GlideCircleTransform(UserCenterOtherActivity.this))
                        .into(mUserAvator);
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_usercenter_other;
    }

}
