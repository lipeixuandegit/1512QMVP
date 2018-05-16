package com.example.administrator.a1512qmvp.ui.HomePage.Persenter;

import com.example.administrator.a1512qmvp.bean.AdBean;
import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.net.AdApi;
import com.example.administrator.a1512qmvp.net.CatagoryApi;
import com.example.administrator.a1512qmvp.ui.HomePage.Contract.HomePageContract;
import com.example.administrator.a1512qmvp.ui.base.BasePresenter;

import javax.inject.Inject;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/10.
 */

public class HomePagePresenter extends BasePresenter<HomePageContract.View> implements HomePageContract.Presenter {
    private AdApi adApi;
    private CatagoryApi catagoryApi;

    @Inject
    public HomePagePresenter(AdApi adApi, CatagoryApi catagoryApi) {
        this.adApi = adApi;
        this.catagoryApi = catagoryApi;
    }

    @Override
    public void getAd() {
        adApi.getAd()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AdBean adBean) {
                        if (mView!=null) {
                            mView.getAdSuccess(adBean);
                        }
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
    public void getCatagory() {
        catagoryApi.getCatagory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CatagoryBean catagoryBean) {
                        if (mView!=null) {
                            mView.getCatagorySuccess(catagoryBean);
                        }
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
