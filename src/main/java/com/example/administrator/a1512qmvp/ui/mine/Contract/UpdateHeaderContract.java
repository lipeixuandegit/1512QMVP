package com.example.administrator.a1512qmvp.ui.mine.Contract;

import com.example.administrator.a1512qmvp.ui.base.BaseContract;

/**
 * Created by Administrator on 2018/5/23.
 */

public interface UpdateHeaderContract {
    interface View extends BaseContract.BaseView{
        void updateSuccess(String code);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void updateHeader(String uid, String filePath);
    }
}
