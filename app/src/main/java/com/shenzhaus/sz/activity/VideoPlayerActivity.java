package com.shenzhaus.sz.activity;

import android.os.Bundle;
import android.view.Window;

import com.shenzhaus.sz.R;
import com.shenzhaus.sz.base.BaseActivity;
import com.shenzhaus.sz.model.DanmuMessage;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class VideoPlayerActivity extends BaseActivity{

    TXLivePlayer mLivePlayer;
    TXCloudVideoView mPlayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        initData();
    }


    private void initData() {
        Bundle data = getIntent().getExtras();
        if(data == null){
            toast(getString(R.string.net_error));
            finish();
            return;
        }
        DanmuMessage d = (DanmuMessage) data.getSerializable("item");
        initTencentPlayer(d);
    }
    private void initTencentPlayer(DanmuMessage d) {
        //mPlayerView即step1中添加的界面view
        mPlayView = (TXCloudVideoView) findViewById(R.id.player_surface);
        //创建player对象
        mLivePlayer = new TXLivePlayer(this);
        //关键player对象与界面view
        mLivePlayer.setPlayerView(mPlayView);
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        mLivePlayer.startPlay(d.getId(),TXLivePlayer.PLAY_TYPE_VOD_MP4);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        mPlayView.onDestroy();
    }


    @Override
    public int getLayoutResource() {
        return R.layout.activity_video_player;
    }
}
