package com.shenzhaus.sz.common;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenzhaus.sz.R;
import com.shenzhaus.sz.base.BaseActivity;
import com.hss01248.dialog.StyledDialog;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class WebviewActivity extends BaseActivity implements PlatformActionListener {

    @Bind(R.id.layout_top_title)
    TextView mTextTopTitle;
    @Bind(R.id.layout_top_back)
    ImageView mImageTopBack;
    @Bind(R.id.webview)
    WebView mWebView;
    Dialog mLoadingDialog;
    Platform mPlatFormWeChat;
    Platform mPlatFormMoment;

    @OnClick(R.id.layout_top_back)
    public void back(View v) {
        finish();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle data = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        mPlatFormWeChat = ShareSDK.getPlatform(Wechat.NAME);
        mPlatFormMoment = ShareSDK.getPlatform(WechatMoments.NAME);
        mPlatFormWeChat.setPlatformActionListener(this);
        mPlatFormMoment.setPlatformActionListener(this);
        mTextTopTitle.setText(data.getString("title"));
        mWebView.loadUrl(data.getString("jump"));
        mWebView.setWebContentsDebuggingEnabled(true);
        mLoadingDialog = StyledDialog.buildLoading().setActivity(this).show();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.clearCache(true);
        mWebView.setWebViewClient(new myWebClient());
        mWebView.addJavascriptInterface(new AndroidtoJs(), "Share");
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_webview;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        //toast(throwable.toString());
    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 判断某一个类是否存在任务栈里面
     *
     * @return
     */
    private boolean isExsitMianActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        ComponentName cmpName = intent.resolveActivity(getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void shareToFriend(String title, String msg, String url, String imgUrl) {
            Platform.ShareParams sp = new Platform.ShareParams();
            sp.setTitle(title);
            sp.setText(msg);
            sp.setShareType(Platform.SHARE_WEBPAGE);
            sp.setImageUrl(imgUrl);
            sp.setSite(title);
            sp.setUrl(url);
            mPlatFormWeChat.share(sp);
        }

        @JavascriptInterface
        public void shareToMoment(String title, String msg, String url, String imgUrl) {
            Platform.ShareParams sp = new Platform.ShareParams();
            sp.setTitle(title);
            sp.setUrl(url);
            sp.setText(msg);
            sp.setShareType(Platform.SHARE_WEBPAGE);
            sp.setImageUrl(imgUrl);
            sp.setSite(title);
            mPlatFormMoment.share(sp);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroyDrawingCache();
        mWebView.destroy();
    }
}
