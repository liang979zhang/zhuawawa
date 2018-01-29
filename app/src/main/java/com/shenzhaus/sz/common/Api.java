package com.shenzhaus.sz.common;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.intf.OnRequestDataListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/8/19.
 * Author: XuDeLong
 */
public class Api {
    public static final String APP_VER = "1.0.5";
    public static final String HOST = "http://zxl.imyxi.com/";
    private static final String OS = "android";
    public static final String QUDAO = "kuailai-one";
    private static final String OS_VER = Build.VERSION.RELEASE;
    private static final String KEY = "HZ1lERfDhUqNuUQ42PfX5lALvKlaTQxT";
    private static final String LOGIN = HOST + "Api/SiSi/sendOauthUserInfo";
    private static final String GET_BANNER = HOST + "Api/SiSi/getBanner";
    private static final String GET_GAME = HOST + "Api/SiSi/getLive";
    private static final String GET_CHANNEL_KEY = HOST + "Api/SiSi/getChannelKey";
    public static final String URL_GAME_HELP = HOST + "/portal/appweb/help?qudao=" + QUDAO;
    public static final String URL_GAME_YAOQING = HOST + "/portal/appweb/my_code?qudao=" + QUDAO;
    public static final String URL_GAME_YAOQINGMA = HOST + "/portal/appweb/input_code?qudao=" + QUDAO;
    public static final String URL_GAME_FEEDBACK = HOST + "/portal/appweb/feedback?qudao=" + QUDAO;
    public static final String URL_GAME_ABOUT = HOST + "/portal/appweb/about_us?qudao=" + QUDAO;
    public static final String URL_GAME_XIEYI = HOST + "portal/page/index/id/2?qudao=" + QUDAO;
    public static final String URL_GAME_VIDEO = HOST + "portal/client/mobile";
    private static final String CHECK_UPDATE = HOST + "Api/SiSi/checkAndroidVer";
    private static final String ENTER_PLAYER = HOST + "Api/SiSi/enterDeviceRoom";
    private static final String GET_LATEST_DEVICE_RECORD = HOST + "Api/SiSi/getWinLogByDeviceid";
    private static final String GET_SHOUHUO_LOCATION = HOST + "Api/SiSi/get_delivery_addr";
    private static final String SET_SHOUHUO_LOCATION = HOST + "/Api/SiSi/change_userinfo";
    private static final String GET_ZHUA_RECORD = HOST + "Api/SiSi/getPlayLogByUid";
    private static final String GET_COIN_RECORD = HOST + "/Api/SiSi/getMoneylog";
    private static final String GET_USER_INFO = HOST + "Api/SiSi/getUserInfo";
    private static final String GET_PAY_METHOD = HOST + "Api/SiSi/get_recharge_package";
    private static final String BEGIN_PAY = HOST + "/Api/Pay/begin_pay";
    private static final String REQUEST_CONNECT_DEVICE = HOST + "Api/SiSi/connDeviceControl";
    private static final String GET_NOTAKEN_WAWA = HOST + "Api/SiSi/getNotTakenWawaByUid";
    private static final String GET_MESSAGE = HOST + "Api/SiSi/get_message";
    private static final String APPLY_POST_DUIHUAN_WAWA = HOST + "Api/SiSi/applyPostWawa";
    public static String GET_PAY_TYPE = HOST + "/Api/Appconfig/getPayType";
    private static final String GET_LAUNCH_SCREEN = HOST + "Api/SiSi/getLaunchScreen";
    public static final String UPLOAD_RECORD = HOST + "/Api/SiSi/userUploadPlayVideo";
    public static final String APP_CONFIG = HOST + "/Api/Appconfig/getAPPConfig";

    public static void doLogin(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(LOGIN, context, params, listener);
    }

    public static void getAppConfig(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(APP_CONFIG, context, params, listener);
    }

    public static void getPayType(final Context context, JSONObject params, final OnRequestDataListener listener) {
        //SFProgrssDialog dialog = SFProgrssDialog.show(context,"请稍后...");
        excutePost(GET_PAY_TYPE, context, params, listener);
    }

    public static void applyPostOrDuiHuanWaWa(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(APPLY_POST_DUIHUAN_WAWA, context, params, listener);
    }

    public static void getNoTakenWawa(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_NOTAKEN_WAWA, context, params, listener);
    }

    public static void getMessage(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_MESSAGE, context, params, listener);
    }

    public static void requestConnectDevice(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(REQUEST_CONNECT_DEVICE, context, params, listener);
    }

    public static void beginPay(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(BEGIN_PAY, context, params, listener);
    }

    public static void getPayMethod(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_PAY_METHOD, context, params, listener);
    }

    public static void getLaunchScreen(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_LAUNCH_SCREEN, context, params, listener);
    }

    public static void getUserInfo(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_USER_INFO, context, params, listener);
    }

    public static void getZhuaRecord(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_ZHUA_RECORD, context, params, listener);
    }

    public static void getCoinRecord(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_COIN_RECORD, context, params, listener);
    }

    public static void setShouHuoLocation(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(SET_SHOUHUO_LOCATION, context, params, listener);
    }

    public static void getShouHuoLocation(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_SHOUHUO_LOCATION, context, params, listener);
    }

    public static void getLatestDeviceRecord(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_LATEST_DEVICE_RECORD, context, params, listener);
    }


    public static void enterPlayer(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(ENTER_PLAYER, context, params, listener);
    }

    public static void getChannelKey(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_CHANNEL_KEY, context, params, listener);
    }

    public static void getBanner(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_BANNER, context, params, listener);
    }

    public static void getGameList(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(GET_GAME, context, params, listener);
    }

    public static void checkUpdate(final Context context, JSONObject params, final OnRequestDataListener listener) {
        excutePost(CHECK_UPDATE, context, params, listener);
    }

    public static void uploadFile(final Context context, RequestParams params, final OnRequestDataListener listener) {
        excutePostFile(UPLOAD_RECORD, context, params, listener);
    }

    public static JSONObject getJsonObject(Context context, int statusCode, byte[] responseBody, OnRequestDataListener listener) {
        final String net_error = context.getString(R.string.net_error);
        if (statusCode == 200) {
            String response = null;
            try {
                if (responseBody != null) {
                    response = new String(responseBody, "UTF-8");
                    LogUtils.i(response);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            JSONObject object = null;
            if (response != null) {
                object = JSON.parseObject(response);
            }
            return object;
        }
        return null;
    }

    protected static RequestParams getRequestParams(JSONObject params) {
        RequestParams requestParams = new RequestParams();
        Iterator<?> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) params.getString(key);
            requestParams.put(key, value);
        }
        return requestParams;
    }

    protected static void excutePost(String url, final Context context, JSONObject params, final OnRequestDataListener listener) {
        Log.e("tag", url);
        params.put("os", OS);
        params.put("soft_ver", APP_VER);
        params.put("os_ver", OS_VER);
        params.put("qudao", QUDAO);
        //String stamp = getTime();
        //params.put("timestamp",stamp);
        //params.put("sign",getMD5(KEY+stamp));
        final String net_error = context.getString(R.string.net_error);
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(30000);
        RequestParams requestParams = getRequestParams(params);
        client.post(context, url, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    if (responseBody != null) {
                        JSONObject data = getJsonObject(context, statusCode, responseBody, listener);
                        if (data != null) {
                            if (200 == data.getIntValue("code")) {
                                listener.requestSuccess(statusCode, data);
                            } else {
                                listener.requestFailure(-1, data.getString("descrp"));
                            }

                        } else {
                            listener.requestFailure(-1, net_error);
                        }
                    } else {
                        if (listener != null) {
                            listener.requestFailure(-1, net_error);
                        }
                    }
                } catch (Exception e) {
                    //listener.requestFailure(-1, net_error);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    if (null != context && responseBody != null) {
                        String response = "";
                        response = new String(responseBody, "UTF-8");
                        LogUtils.i("response=" + response);
                        if (listener != null) {
                            listener.requestFailure(-1, net_error);
                        }
                    } else {
                        if (listener != null) {
                            listener.requestFailure(-1, net_error);
                        }
                    }
                } catch (Exception e) {

                }

            }
        });
    }


    protected static void excutePostFile(String url, final Context context, RequestParams requestParams, final OnRequestDataListener listener) {
        requestParams.put("os", OS);
        requestParams.put("soft_ver", APP_VER);
        requestParams.put("os_ver", OS_VER);
        //String stamp = getTime();
        //params.put("timestamp",stamp);
        //params.put("sign",getMD5(KEY+stamp));
        final String net_error = context.getString(R.string.net_error);
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(300000);
        client.post(context, url, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    if (responseBody != null) {
                        JSONObject data = getJsonObject(context, statusCode, responseBody, listener);
                        if (data != null) {
                            if (200 == data.getIntValue("code")) {
                                listener.requestSuccess(statusCode, data);
                            } else {
                                listener.requestFailure(-1, data.getString("descrp"));
                            }

                        } else {
                            listener.requestFailure(-1, net_error);
                        }
                    } else {
                        if (listener != null) {
                            listener.requestFailure(-1, net_error);
                        }
                    }
                } catch (Exception e) {
                    //listener.requestFailure(-1, net_error);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    if (null != context && responseBody != null) {
                        String response = "";
                        response = new String(responseBody, "UTF-8");
                        LogUtils.i("response=" + response);
                        if (listener != null) {
                            listener.requestFailure(-1, net_error);
                        }
                    } else {
                        if (listener != null) {
                            listener.requestFailure(-1, net_error);
                        }
                    }
                } catch (Exception e) {

                }

            }
        });
    }

    private static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
        }
        byte[] b = messageDigest.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int a = b[i];
            if (a < 0)
                a += 256;
            if (a < 16)
                buf.append("0");
            buf.append(Integer.toHexString(a));

        }
        return buf.toString();  //32位
    }

    public static String getTime() {

        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳

        String str = String.valueOf(time);

        return str;

    }

}
