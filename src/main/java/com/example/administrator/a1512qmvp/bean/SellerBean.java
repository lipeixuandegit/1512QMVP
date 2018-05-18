package com.example.administrator.a1512qmvp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/18.
 */

public class SellerBean implements Serializable {
    private String sellerName;
    private String sellerid;
    private boolean selected;//商家是否选中

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }
}

