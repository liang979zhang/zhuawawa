package com.shenzhaus.sz.model;

/**
 * Created by Administrator on 2017/10/23.
 * Author: XuDeLong
 */

public class MessageType {
    public static final int NORMAL = 1;//普通消息
    public static final int GAME_UP = 2;
    public static final int GAME_DOWN = 3;
    public static final int GAME_LEFT = 4;
    public static final int GAME_RIGHT = 5;
    public static final int GAME_STATUS_IDLE = 6;//空闲可用
    public static final int GAME_STATUS_REPAIRE = 7;//维修
    public static final int GAME_STATUS_BUSY = 8;//繁忙使用中
    public static final int GAME_OK = 9;//抓中
    public static final int GAME_NO = 10;//未抓中
    public static final int GAME_GO = 11;//开始下抓命令
    public static final int GAME_CAMERA = 12;//摄像头切换
    public static final int GAME_CONNECT = 13;//机器连接……
    public static final int GAME_CONNECTTED = 14;//机器连接成功
    public static final int GAME_DISCONNECTTED = 15;//机器连接断开故障
    public static final int GAME_STOP_UP = 16;//指令抬起结束
}
