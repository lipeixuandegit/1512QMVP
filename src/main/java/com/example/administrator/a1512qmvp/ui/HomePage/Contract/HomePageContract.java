package com.example.administrator.a1512qmvp.ui.HomePage.Contract;

import com.example.administrator.a1512qmvp.bean.AdBean;
import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.ui.base.BaseContract;

/**
 * Created by Administrator on 2018/5/10.
 */

public interface HomePageContract {
    interface View extends BaseContract.BaseView {
        void getAdSuccess(AdBean adBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAd();

        void getCatagory();
    }

}
