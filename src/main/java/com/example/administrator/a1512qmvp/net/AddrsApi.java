package com.example.administrator.a1512qmvp.net;



import com.example.administrator.a1512qmvp.bean.AddrsBean;

import io.reactivex.Observable;

public class AddrsApi {
    private static AddrsApi addrsApi;
    private AddrsApiService addrsApiService;

    private AddrsApi(AddrsApiService addrsApiService) {
        this.addrsApiService = addrsApiService;
    }

    public static AddrsApi getAddrsApi(AddrsApiService addrsApiService) {
        if (addrsApi == null) {
            addrsApi = new AddrsApi(addrsApiService);
        }
        return addrsApi;
    }

    public Observable<AddrsBean> getAddrs(String uid, String token) {
        return addrsApiService.getAddrs(uid, token);
    }

}
