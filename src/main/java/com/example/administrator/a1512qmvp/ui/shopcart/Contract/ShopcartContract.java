package com.example.administrator.a1512qmvp.ui.shopcart.Contract;

import com.example.administrator.a1512qmvp.bean.GetCartsBean;
import com.example.administrator.a1512qmvp.bean.SellerBean;
import com.example.administrator.a1512qmvp.ui.base.BaseContract;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */

public interface ShopcartContract {
    interface View extends BaseContract.BaseView {
        void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);

        void updateCartsSuccess(String msg);

        void deleteCartSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getCarts(String uid, String token);

        void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

        void deleteCart(String uid, String pid, String token);
    }
}