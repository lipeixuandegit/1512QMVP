package com.example.administrator.a1512qmvp.ui.classfy.Contract;

import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.bean.ProductCatagoryBean;
import com.example.administrator.a1512qmvp.bean.ProductsBean;
import com.example.administrator.a1512qmvp.ui.base.BaseContract;

import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface ListContract {
    interface View extends BaseContract.BaseView {
        void onSuccess(List<ProductsBean.DataBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProducts(String pscid);
    }
}

