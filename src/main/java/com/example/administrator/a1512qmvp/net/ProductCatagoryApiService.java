package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.ProductCatagoryBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface ProductCatagoryApiService {
    @FormUrlEncoded
    @POST("product/getProductCatagory")
    Observable<ProductCatagoryBean> getProductCatagory(@Field("cid") String cid);

}
