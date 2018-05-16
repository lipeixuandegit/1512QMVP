package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.ProductsBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/15.
 */

public class ListApi {
    private static ListApi listApi;
    private ListApiService listApiService;

    private ListApi(ListApiService listApiService) {
        this.listApiService = listApiService;
    }

    public static ListApi getListApi(ListApiService listApiService) {
        if (listApi == null) {
            listApi = new ListApi(listApiService);
        }
        return listApi;
    }

    public Observable<ProductsBean> getProduct(String pscid) {
        return listApiService.getProduct(pscid);
    }
}
