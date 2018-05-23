package com.example.administrator.a1512qmvp.ui.mine.Presenter;

import com.example.administrator.a1512qmvp.bean.BaseBean;
import com.example.administrator.a1512qmvp.net.UpdateHeaderApi;
import com.example.administrator.a1512qmvp.ui.base.BasePresenter;
import com.example.administrator.a1512qmvp.ui.mine.Contract.UpdateHeaderContract;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/5/23.
 */

public class UpdatePresenter extends BasePresenter<UpdateHeaderContract.View> implements UpdateHeaderContract.Presenter {
    private UpdateHeaderApi updateHeaderApi;
    @Inject
    public UpdatePresenter(UpdateHeaderApi updateHeaderApi) {
        this.updateHeaderApi = updateHeaderApi;
    }

    @Override
    public void updateHeader(String uid, String filePath) {
        int i = filePath.lastIndexOf("/");
        String filename = filePath.substring(i + 1);
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), new File(filePath));
        MediaType textType = MediaType.parse("text/plain");

        RequestBody u = RequestBody.create(textType, uid);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", filename, file);
        updateHeaderApi.updateHeader(u,f)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getCode();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.updateSuccess(s);
                }
            }
        });
        

    }
}
