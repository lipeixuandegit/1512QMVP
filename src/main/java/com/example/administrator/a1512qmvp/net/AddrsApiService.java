package com.example.administrator.a1512qmvp.net;



import com.example.administrator.a1512qmvp.bean.AddrsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddrsApiService {
    @FormUrlEncoded
    @POST("user/getAddrs")
    Observable<AddrsBean> getAddrs(@Field("uid") String uid,
                                   @Field("token") String token);

}
