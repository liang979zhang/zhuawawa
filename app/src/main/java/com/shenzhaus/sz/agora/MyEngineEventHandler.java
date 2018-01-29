package com.shenzhaus.sz.agora;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import io.agora.rtc.IRtcEngineEventHandler;

public class MyEngineEventHandler extends IRtcEngineEventHandler {
    private static final int INIT_FRAME = 1000;
    private Context mContext;
    private Handler mHandler;
    public MyEngineEventHandler(Context ctx,Handler hdl) {
        this.mContext = ctx;
        this.mHandler = hdl;
    }
    private void mLog(String msg){
        Log.i("========",msg);
    }
    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        super.onJoinChannelSuccess(channel, uid, elapsed);
        mLog("onJoinChannelSuccess");
    }

    @Override
    public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {
        super.onRejoinChannelSuccess(channel, uid, elapsed);
        mLog("onRejoinChannelSuccess");
    }

    @Override
    public void onWarning(int warn) {
        super.onWarning(warn);
        mLog("onWarning");
    }

    @Override
    public void onError(int err) {
        super.onError(err);
        mLog("onError");
    }

    @Override
    public void onApiCallExecuted(String api, int error) {
        super.onApiCallExecuted(api, error);
        mLog("onApiCallExecuted");
    }

    @Override
    public void onCameraReady() {
        super.onCameraReady();
        mLog("onCameraReady");
    }

    @Override
    public void onVideoStopped() {
        super.onVideoStopped();
        mLog("onVideoStopped");
    }

    @Override
    public void onAudioQuality(int uid, int quality, short delay, short lost) {
        super.onAudioQuality(uid, quality, delay, lost);
        mLog("onAudioQuality");
    }

    @Override
    public void onLeaveChannel(RtcStats stats) {
        super.onLeaveChannel(stats);
        mLog("onLeaveChannel");
    }

    @Override
    public void onRtcStats(RtcStats stats) {
        super.onRtcStats(stats);
        mLog("onRtcStats");
    }

    @Override
    public void onAudioVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {
        super.onAudioVolumeIndication(speakers, totalVolume);
        mLog("onAudioVolumeIndication");
    }

    @Override
    public void onNetworkQuality(int uid, int txQuality, int rxQuality) {
        super.onNetworkQuality(uid, txQuality, rxQuality);
        mLog("onNetworkQuality");
    }

    @Override
    public void onLastmileQuality(int quality) {
        super.onLastmileQuality(quality);
    }

    @Override
    public void onUserJoined(int uid, int elapsed) {
        super.onUserJoined(uid, elapsed);
        mLog("onUserJoined");
    }

    @Override
    public void onUserOffline(int uid, int reason) {
        super.onUserOffline(uid, reason);
        mLog("onUserOffline");
    }

    @Override
    public void onUserMuteAudio(int uid, boolean muted) {
        super.onUserMuteAudio(uid, muted);
    }

    @Override
    public void onUserMuteVideo(int uid, boolean muted) {
        super.onUserMuteVideo(uid, muted);
    }

    @Override
    public void onUserEnableVideo(int uid, boolean enabled) {
        super.onUserEnableVideo(uid, enabled);
        mLog("onUserEnableVideo");
    }

    @Override
    public void onRemoteVideoStats(RemoteVideoStats stats) {
        super.onRemoteVideoStats(stats);
        mLog("onRemoteVideoStats");
    }

    @Override
    public void onLocalVideoStats(LocalVideoStats stats) {
        super.onLocalVideoStats(stats);
        mLog("onLocalVideoStats");
    }

    @Override
    public void onFirstRemoteVideoFrame(int uid, int width, int height, int elapsed) {
        super.onFirstRemoteVideoFrame(uid, width, height, elapsed);
        mLog("onFirstRemoteVideoFrame");

    }

    @Override
    public void onFirstLocalVideoFrame(int width, int height, int elapsed) {
        super.onFirstLocalVideoFrame(width, height, elapsed);
        mLog("onFirstLocalVideoFrame");
    }

    @Override
    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
        super.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
        mLog("onFirstRemoteVideoDecoded");
        Message m = mHandler.obtainMessage();
        m.what = INIT_FRAME ;
        m.sendToTarget();
    }

    @Override
    public void onVideoSizeChanged(int uid, int width, int height, int rotation) {
        super.onVideoSizeChanged(uid, width, height, rotation);
        mLog("onVideoSizeChanged");
    }

    @Override
    public void onConnectionLost() {
        super.onConnectionLost();
        mLog("onConnectionLost");
    }

    @Override
    public void onConnectionInterrupted() {
        super.onConnectionInterrupted();
        mLog("onConnectionInterrupted");
    }

    @Override
    public void onRefreshRecordingServiceStatus(int status) {
        super.onRefreshRecordingServiceStatus(status);
        mLog("onRefreshRecordingServiceStatus");
    }

    @Override
    public void onStreamMessage(int uid, int streamId, byte[] data) {
        super.onStreamMessage(uid, streamId, data);
        mLog("onStreamMessage");
    }

    @Override
    public void onStreamMessageError(int uid, int streamId, int error, int missed, int cached) {
        super.onStreamMessageError(uid, streamId, error, missed, cached);
        mLog("onStreamMessageError");
    }

    @Override
    public void onMediaEngineLoadSuccess() {
        super.onMediaEngineLoadSuccess();
    }

    @Override
    public void onMediaEngineStartCallSuccess() {
        super.onMediaEngineStartCallSuccess();
    }

    @Override
    public void onAudioMixingFinished() {
        super.onAudioMixingFinished();
    }

    @Override
    public void onRequestChannelKey() {
        super.onRequestChannelKey();
    }

    @Override
    public void onAudioRouteChanged(int routing) {
        super.onAudioRouteChanged(routing);
    }

    @Override
    public void onFirstLocalAudioFrame(int elapsed) {
        super.onFirstLocalAudioFrame(elapsed);
    }

    @Override
    public void onFirstRemoteAudioFrame(int uid, int elapsed) {
        super.onFirstRemoteAudioFrame(uid, elapsed);
        mLog("onFirstRemoteAudioFrame");
    }

    @Override
    public void onActiveSpeaker(int uid) {
        super.onActiveSpeaker(uid);
    }

    @Override
    public void onAudioEffectFinished(int soundId) {
        super.onAudioEffectFinished(soundId);
    }

    @Override
    public void onClientRoleChanged(int oldRole, int newRole) {
        super.onClientRoleChanged(oldRole, newRole);
        mLog("onClientRoleChanged");
    }
}
