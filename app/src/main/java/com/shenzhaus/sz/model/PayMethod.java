package com.shenzhaus.sz.model;

/**
 * Created by Administrator on 2017/10/30.
 * Author: XuDeLong
 */

public class PayMethod {
    private String coin;
    private String price;
    private String if_check;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIf_check() {
        return if_check;
    }

    public void setIf_check(String if_check) {
        this.if_check = if_check;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
