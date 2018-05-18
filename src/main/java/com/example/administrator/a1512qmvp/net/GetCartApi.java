package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.bean.GetCartsBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/18.
 */

public class GetCartApi {
    private static GetCartApi getCartApi;
    private GetCartApiService getCartApiService;

    private GetCartApi(GetCartApiService getCartApiService) {
        this.getCartApiService = getCartApiService;
    }

    public static GetCartApi getGetCartApi(GetCartApiService getCartApiService) {
        if (getCartApi == null) {
            getCartApi = new GetCartApi(getCartApiService);
        }
        return getCartApi;
    }

    public Observable<GetCartsBean> getCatagory(String uid, String token) {
        return getCartApiService.getCart(uid,token);
    }
}
