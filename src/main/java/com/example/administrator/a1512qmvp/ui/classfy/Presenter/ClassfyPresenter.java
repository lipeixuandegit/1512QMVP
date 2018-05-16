package com.example.administrator.a1512qmvp.ui.classfy.Presenter;

import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.bean.ProductCatagoryBean;
import com.example.administrator.a1512qmvp.net.CatagoryApi;
import com.example.administrator.a1512qmvp.net.ProductCatagoryApi;
import com.example.administrator.a1512qmvp.ui.base.BaseContract;
import com.example.administrator.a1512qmvp.ui.base.BasePresenter;
import com.example.administrator.a1512qmvp.ui.classfy.Contract.ClassfyContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/15.
 */

public class ClassfyPresenter extends BasePresenter<ClassfyContract.View> implements ClassfyContract.Presenter {
    private CatagoryApi catagoryApi;
    private ProductCatagoryApi productCatagoryApi;

    @Inject
    public ClassfyPresenter(CatagoryApi catagoryApi, ProductCatagoryApi productCatagoryApi) {
        this.catagoryApi = catagoryApi;
        this.productCatagoryApi = productCatagoryApi;
    }

    @Override
    public void getCatagory() {
        catagoryApi.getCatagory().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CatagoryBean catagoryBean) {

                        mView.getCatagorySuccess(catagoryBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getProductCatagory(String cid) {
        productCatagoryApi.getProductCatagory(cid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductCatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductCatagoryBean productCatagoryBean) {
                        mView.getProductCatagorySuccess(productCatagoryBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
