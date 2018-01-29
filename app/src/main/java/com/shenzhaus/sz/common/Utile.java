package com.shenzhaus.sz.common;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.SPUtils;

/**
 * Created by Administrator on 2017/11/28.
 * Author: XuDeLong
 */

public class Utile {
    public static JSONObject getAppE(Context context){
        JSONObject config = JSON.parseObject(SPUtils.getInstance().getString("config",""));
        if(config != null){
            JSONObject app_e = config.getJSONObject("app_e");
            return  app_e;
        }
        return new JSONObject();
    }

    public static JSONObject getShengWangId(Context context){
        JSONObject config = JSON.parseObject(SPUtils.getInstance().getString("config",""));
        if(config != null){
            JSONObject app_e = config.getJSONObject("shengwang");
            return  app_e;
        }
        return new JSONObject();
    }
}
