package com.shenzhaus.sz.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/23.
 * Author: XuDeLong
 */

public class Game implements Serializable{
    private String gameUrl;
    private String GameName;
    private String GameId;
    private String GamePrice;
    private String GameStatus;
    private String GamePlayUrl;

    public String getGamePlayUrl() {
        return GamePlayUrl;
    }

    public void setGamePlayUrl(String gamePlayUrl) {
        GamePlayUrl = gamePlayUrl;
    }

    public String getGameId() {
        return GameId;
    }

    public void setGameId(String gameId) {
        GameId = gameId;
    }

    public String getGamePrice() {
        return GamePrice;
    }

    public void setGamePrice(String gamePrice) {
        GamePrice = gamePrice;
    }

    public String getGameStatus() {
        return GameStatus;
    }

    public void setGameStatus(String gameStatus) {
        GameStatus = gameStatus;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }
}
