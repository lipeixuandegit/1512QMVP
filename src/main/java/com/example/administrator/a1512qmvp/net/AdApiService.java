package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.AdBean;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/5/10.
 */

public interface AdApiService {
    @GET("ad/getAd")
    Observable<AdBean> getAd();
}
