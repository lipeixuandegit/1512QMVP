package com.example.administrator.a1512qmvp.net;



import com.example.administrator.a1512qmvp.bean.BaseBean;

import io.reactivex.Observable;

public class CreateOrderApi {
    private static CreateOrderApi createOrderApi;
    private CreateOrderApiService createOrderApiService;

    private CreateOrderApi(CreateOrderApiService createOrderApiService) {
        this.createOrderApiService = createOrderApiService;
    }

    public static CreateOrderApi getCreateOrderApi(CreateOrderApiService createOrderApiService) {
        if (createOrderApi == null) {
            createOrderApi = new CreateOrderApi(createOrderApiService);
        }
        return createOrderApi;
    }

    public Observable<BaseBean> getCatagory(String uid, String price, String token) {
        return createOrderApiService.createOrder(uid, price, token);
    }

}
