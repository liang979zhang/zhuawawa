package com.shenzhaus.sz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.adapter.WeiQuRecyclerListAdapter;
import com.shenzhaus.sz.base.BaseActivity;
import com.shenzhaus.sz.common.Api;
import com.shenzhaus.sz.intf.OnRecyclerViewItemClickListener;
import com.shenzhaus.sz.intf.OnRequestDataListener;
import com.shenzhaus.sz.model.DanmuMessage;

import java.util.ArrayList;

import butterknife.Bind;

public class WeiQuListActivity extends BaseActivity implements OnRecyclerViewItemClickListener, CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.checkbox_all)
    CheckBox mCheckBoxAll;
    private String mToken;
    private ArrayList<DanmuMessage> mListData = new ArrayList();
    private ArrayList<DanmuMessage> mListDataAdre = new ArrayList();

    private WeiQuRecyclerListAdapter mAdapter ;

    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString("token");
        mRefreshLayout.autoRefresh();
        mCheckBoxAll.setOnCheckedChangeListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        mRefreshLayout.autoRefresh();
        initGameList();

        getGameData(0);

    }

    private void initGameList() {
        mAdapter = new WeiQuRecyclerListAdapter(this, mListData);
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
        mAdapter.setOnRecyclerViewItemClickListener(this);
    }

    private void getGameData(final int limit_begin) {
        JSONObject params = new JSONObject();
        params.put("token", mToken);
        params.put("limit_begin", limit_begin);
        params.put("limit_num", 10);
        Api.getNoTakenWawa(this, params, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {

                if (limit_begin == 0) {
                    mListData.clear();
                }
                if (mRefreshLayout.isRefreshing()) {
                    mListData.clear();

                    mRefreshLayout.finishRefresh();
                }
                if (mRefreshLayout.isLoading()) {
                    mRefreshLayout.finishLoadmore();
                }
                JSONArray list = data.getJSONArray("info");
                for (int i = 0; i < list.size(); i++) {
                    DanmuMessage g = new DanmuMessage();
                    JSONObject t = list.getJSONObject(i);
                    g.setUserName(t.getString("name"));
                    g.setAvator(t.getString("img"));
                    g.setUid(t.getString("play_time"));
                    g.setMessageContent(t.getString("exchange_price"));
                    g.setId(t.getString("doll_id"));
                    g.setRemoteUid("0");
                    mListData.add(g);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
                mListData.clear();
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.finishRefresh();
                }
                if (mRefreshLayout.isLoading()) {
                    mRefreshLayout.finishLoadmore();
                }
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_weiqu_list;
    }

    @Override
    public void onRecyclerViewItemClick(View view, int position) {
        String s = mListData.get(position).getRemoteUid();
        if ("1".equals(s)) {
            mListData.get(position).setRemoteUid("0");
        }
        if ("0".equals(s)) {
            mListData.get(position).setRemoteUid("1");
        }
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            for (int i = 0; i < mListData.size(); i++) {
                mListData.get(i).setRemoteUid("1");
            }
        } else {
            for (int i = 0; i < mListData.size(); i++) {
                mListData.get(i).setRemoteUid("0");
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public void tiqu(View v) {
//        tiquDuihuan("1");
        tiquDuihuanNew("1");

    }

    public void duihuan(View v) {
//        tiquDuihuan("0");

        tiquDuihuanNew("0");
    }


    private void tiquDuihuanNew(String type) {
        mListDataAdre.clear();
        for (int i = 0; i < mListData.size(); i++) {
            if ("1".equals(mListData.get(i).getRemoteUid())) {
                mListDataAdre.add(mListData.get(i));
            }
        }

        if (mListDataAdre.size() == 0) {
            toast(getString(R.string.data_empty_error));
            return;
        }

        Intent intent = new Intent(WeiQuListActivity.this, WawaConfirmActivity.class);
        intent.putExtra("adr", mListDataAdre);
        intent.putExtra("type", type);
        startActivity(intent);
    }

}
