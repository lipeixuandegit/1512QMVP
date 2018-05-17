package com.example.administrator.a1512qmvp.ui.login.contract;

import com.example.administrator.a1512qmvp.bean.UserBean;
import com.example.administrator.a1512qmvp.ui.base.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void loginSuccess(UserBean userBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void login(String mobile, String password);
    }
}
