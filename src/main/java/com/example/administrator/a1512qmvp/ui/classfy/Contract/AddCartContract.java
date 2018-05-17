package com.example.administrator.a1512qmvp.ui.classfy.Contract;

import com.example.administrator.a1512qmvp.ui.base.BaseContract;

/**
 * Created by Administrator on 2018/5/17.
 */

public interface AddCartContract {
    interface View extends BaseContract.BaseView{
        void onsuccess(String str);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void addCart(String uid,String pid, String token );
    }
}
