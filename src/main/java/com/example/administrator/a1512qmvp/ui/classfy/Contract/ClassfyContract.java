package com.example.administrator.a1512qmvp.ui.classfy.Contract;

import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.bean.ProductCatagoryBean;
import com.example.administrator.a1512qmvp.ui.base.BaseContract;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface ClassfyContract {
    interface View extends BaseContract.BaseView{
        void getCatagorySuccess(CatagoryBean catagoryBean);
        void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getCatagory();
        void getProductCatagory(String cid);

    }
}
