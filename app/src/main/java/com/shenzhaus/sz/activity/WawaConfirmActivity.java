package com.shenzhaus.sz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.adapter.TiquRecyclerListAdapter;
import com.shenzhaus.sz.base.BaseActivity;
import com.shenzhaus.sz.common.Api;
import com.shenzhaus.sz.intf.OnRequestDataListener;
import com.shenzhaus.sz.model.DanmuMessage;

import java.util.ArrayList;

import butterknife.Bind;

public class WawaConfirmActivity extends BaseActivity {

    @Bind(R.id.shouhuoren)
    TextView shouhuoren;
    @Bind(R.id.lianxifangshi)
    TextView lianxifangshi;
    @Bind(R.id.shouhuo_location)
    TextView shouhuoLocation;
    @Bind(R.id.rl_adreess)
    RecyclerView rlAdreess;
    @Bind(R.id.lltop)
    LinearLayout lltop;
    private String mToken;
    private String delivery_name;
    private String delivery_mobile;
    private String delivery_addr;
    private ArrayList<DanmuMessage> mListDataAdre = new ArrayList<>();
    private TiquRecyclerListAdapter mAdapter;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString("token");
        initAdressDate();
        initWawaInfo();

    }

    private void initWawaInfo() {
        Intent intent = getIntent();
        if (intent != null) {
            mListDataAdre = (ArrayList<DanmuMessage>) intent.getSerializableExtra("adr");
            type = intent.getStringExtra("type");
            setListData(mListDataAdre);

        }
    }

    private void setListData(ArrayList<DanmuMessage> mListDataAdre) {

        if (type.equals("0")) {
            lltop.setVisibility(View.GONE);
        } else {
            lltop.setVisibility(View.VISIBLE);
        }
        mAdapter = new TiquRecyclerListAdapter(this, mListDataAdre);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rlAdreess.setLayoutManager(manager);
        rlAdreess.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void initAdressDate() {
        JSONObject p = new JSONObject();
        p.put("token", mToken);
        Api.getShouHuoLocation(this, p, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                JSONObject jo = data.getJSONObject("data");
                delivery_name = jo.getString("delivery_name");
                delivery_mobile = jo.getString("delivery_mobile");
                delivery_addr = jo.getString("delivery_addr");
                if (!StringUtils.isEmpty(delivery_name)) {
                    shouhuoren.setText(delivery_name);
                }
                if (!StringUtils.isEmpty(delivery_mobile)) {
                    lianxifangshi.setText(delivery_mobile);
                }
                if (!StringUtils.isEmpty(delivery_addr)) {
                    shouhuoLocation.setText(delivery_addr);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_wawa_confirm;
    }


    public void confirorder(View view) {
        tiquDuihuan(type);
    }

    public void goBack(View v) {
        finish();
    }

    private void tiquDuihuan(String type) {
        JSONObject p = new JSONObject();
        p.put("token", mToken);
        String doll_id = "";
        for (int i = 0; i < mListDataAdre.size(); i++) {
            if ("1".equals(mListDataAdre.get(i).getRemoteUid())) {
                doll_id += mListDataAdre.get(i).getId();
                doll_id += ",";
            }
        }
        if (doll_id.length() > 0) {
            doll_id = doll_id.substring(0, doll_id.length() - 1);
        }
        p.put("doll_id", doll_id);
        p.put("type", type);
        if (StringUtils.isTrimEmpty(doll_id)) {
            toast(getString(R.string.data_empty_error));
            return;
        }
        Api.applyPostOrDuiHuanWaWa(this, p, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                toast(data.getString("descrp"));
                SPUtils.getInstance().put("balance", data.getString("balance"));
                startActivity(new Intent(WawaConfirmActivity.this, TiquSucActivity.class));
                WawaConfirmActivity.this.finish();

            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
            }
        });
    }

}
