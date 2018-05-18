package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.GetCartsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/18.
 */

public interface GetCartApiService {
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<GetCartsBean> getCart(@Field("Uid") String uid, @Field("Token") String token);
}
