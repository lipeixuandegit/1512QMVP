package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/18.
 */

public interface DeleteCartApiService {
    @FormUrlEncoded
    @POST("product/deleteCart")
    Observable<BaseBean> deleteCart(@Field("uid") String uid,
                                    @Field("pid") String pid,
                                    @Field("token") String token);
}
