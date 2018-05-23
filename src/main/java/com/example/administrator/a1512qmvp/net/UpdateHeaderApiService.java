package com.example.administrator.a1512qmvp.net;

import com.example.administrator.a1512qmvp.bean.BaseBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2018/5/23.
 */

public interface UpdateHeaderApiService {
    @Multipart
    @POST("file/upload")
    Observable<BaseBean> updateHeader(@Part RequestBody uid,@Part MultipartBody.Part file);
}

