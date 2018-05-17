package com.example.administrator.a1512qmvp.ui.classfy.Presenter;

import com.example.administrator.a1512qmvp.bean.BaseBean;
import com.example.administrator.a1512qmvp.net.AddCartApi;
import com.example.administrator.a1512qmvp.ui.base.BasePresenter;
import com.example.administrator.a1512qmvp.ui.classfy.Contract.AddCartContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/17.
 */

public class AddCartPresenter extends BasePresenter<AddCartContract.View> implements AddCartContract.Presenter {
    private AddCartApi addCartApi;
    @Inject
    public AddCartPresenter(AddCartApi addCartApi) {
        this.addCartApi = addCartApi;
    }

    @Override
    public void addCart(String uid, String pid, String token) {
        addCartApi.getCatagory(uid,pid,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.onsuccess(s);
                }
            }
        });

    }
}
