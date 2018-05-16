package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.CatagoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface CatagoryApiService {
    @GET("product/getCatagory")
    Observable<CatagoryBean> getCatagory();

}
