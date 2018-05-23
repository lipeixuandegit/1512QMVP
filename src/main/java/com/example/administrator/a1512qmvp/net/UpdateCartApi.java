package com.example.administrator.a1512qmvp.net;


import com.example.administrator.a1512qmvp.bean.BaseBean;

import io.reactivex.Observable;

public class UpdateCartApi {
    private static UpdateCartApi updateCartApi;
    private UpdateCartApiService updateCartApiService;

    private UpdateCartApi(UpdateCartApiService updateCartApiService) {
        this.updateCartApiService = updateCartApiService;
    }

    public static UpdateCartApi getUpdateCartApi(UpdateCartApiService updateCartApiService) {
        if (updateCartApi == null) {
            updateCartApi = new UpdateCartApi(updateCartApiService);
        }
        return updateCartApi;
    }

    public Observable<BaseBean> updateCarts(String uid, String sellerid, String pid, String num, String selected,
                                            String token) {
        return updateCartApiService.updateCarts(uid, sellerid, pid, num, selected, token);
    }
}
