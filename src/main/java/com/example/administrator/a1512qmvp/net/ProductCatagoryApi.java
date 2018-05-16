package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.ProductCatagoryBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/15.
 */

public class ProductCatagoryApi {
    private static ProductCatagoryApi productCatagoryApi;
    private  ProductCatagoryApiService productCatagoryApiService;

    public ProductCatagoryApi(ProductCatagoryApiService productCatagoryApiService) {
        this.productCatagoryApiService = productCatagoryApiService;
    }
    public static ProductCatagoryApi getProductCatagoryApi(ProductCatagoryApiService productCatagoryApiService){

        if (productCatagoryApi==null){
            productCatagoryApi = new ProductCatagoryApi(productCatagoryApiService);
        }
        return productCatagoryApi;
    }
    public Observable<ProductCatagoryBean> getProductCatagory(String cid){
        return productCatagoryApiService.getProductCatagory(cid);
    }
}
