package com.example.administrator.a1512qmvp.bean.eventbus;

import com.example.administrator.a1512qmvp.bean.GetCartsBean;
import com.example.administrator.a1512qmvp.bean.SellerBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class MessageEvent {
    private List<SellerBean> gList;
    private List<List<GetCartsBean.DataBean.ListBean>> cList;

    public List<SellerBean> getgList() {
        return gList;
    }

    public void setgList(List<SellerBean> gList) {
        this.gList = gList;
    }

    public List<List<GetCartsBean.DataBean.ListBean>> getcList() {
        return cList;
    }

    public void setcList(List<List<GetCartsBean.DataBean.ListBean>> cList) {
        this.cList = cList;
    }
}

