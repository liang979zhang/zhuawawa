package com.shenzhaus.sz.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/23.
 * Author: XuDeLong
 */

public class DanmuMessage implements Serializable{
    private int messageType;
    private String messageContent;
    private String uid;
    private String userName;
    private String remoteUid;
    private String avator;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getRemoteUid() {
        return remoteUid;
    }

    public void setRemoteUid(String remoteUid) {
        this.remoteUid = remoteUid;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
