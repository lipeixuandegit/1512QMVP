package com.example.administrator.a1512qmvp.ui.shopcart.Presenter;

import com.example.administrator.a1512qmvp.bean.BaseBean;
import com.example.administrator.a1512qmvp.bean.GetCartsBean;
import com.example.administrator.a1512qmvp.bean.SellerBean;
import com.example.administrator.a1512qmvp.net.DeleteCartApi;
import com.example.administrator.a1512qmvp.net.GetCartApi;
import com.example.administrator.a1512qmvp.net.UpdateCartApi;
import com.example.administrator.a1512qmvp.ui.base.BasePresenter;
import com.example.administrator.a1512qmvp.ui.shopcart.Contract.ShopcartContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/18.
 */

public class ShopcartPresenter extends BasePresenter<ShopcartContract.View> implements ShopcartContract.Presenter {
    private GetCartApi getCartApi;
    private DeleteCartApi deleteCartApi;
    private UpdateCartApi updateCartApi;
    @Inject
    public ShopcartPresenter(GetCartApi getCartApi, DeleteCartApi deleteCartApi, UpdateCartApi updateCartApi) {
        this.getCartApi = getCartApi;
        this.deleteCartApi = deleteCartApi;
        this.updateCartApi = updateCartApi;
    }

    @Override
    public void getCarts(String uid, String token) {
        getCartApi.getCatagory(uid,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GetCartsBean>() {
                    @Override
                    public void accept(GetCartsBean getCartsBean) throws Exception {
                        List<SellerBean> groupList=new ArrayList<>();
                        List<List<GetCartsBean.DataBean.ListBean>> childList=new ArrayList<>();
                        List<GetCartsBean.DataBean> data = getCartsBean.getData();
                        if (data==null){
                            return;
                        }
                        for (int i = 0; i < data.size(); i++) {
                            GetCartsBean.DataBean dataBean = data.get(i);
                            SellerBean sellerBean = new SellerBean();
                            sellerBean.setSellerName(dataBean.getSellerName());
                            sellerBean.setSellerid(dataBean.getSellerid());
                            sellerBean.setSelected(isSellerProductAllSelect(dataBean));
                            groupList.add(sellerBean);
                            List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
                            childList.add(list);

                        }
                        if (mView != null) {
                            mView.showCartList(groupList, childList);
                        }
                    }
                });

    }

    @Override
    public void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token) {
        updateCartApi.updateCarts(uid, sellerid, pid, num, selected, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    if (mView != null) {
                        mView.updateCartsSuccess(s);
                    }
                }

        });
    }

    @Override
    public void deleteCart(String uid, String pid, String token) {
        deleteCartApi.deleteCart(uid, pid, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.updateCartsSuccess(s);
                }
            }
        });
    }

    /**
     * 判断该商家下面的商品是否全选
     *
     * @return
     */
    private boolean isSellerProductAllSelect(GetCartsBean.DataBean dataBean) {
        //获取该商家下面的所有商品
        List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            GetCartsBean.DataBean.ListBean listBean = list.get(i);
            if (0 == listBean.getSelected()) {
                //如果是0的话，表示有一个商品未选中
                return false;
            }
        }
        return true;

    }
}
