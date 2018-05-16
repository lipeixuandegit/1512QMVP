package com.example.administrator.a1512qmvp.ui.classfy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.bean.ProductsBean;
import com.example.administrator.a1512qmvp.component.DaggerHttpComponent;
import com.example.administrator.a1512qmvp.module.HttpModule;
import com.example.administrator.a1512qmvp.ui.base.BaseActivity;
import com.example.administrator.a1512qmvp.ui.classfy.Contract.ListContract;
import com.example.administrator.a1512qmvp.ui.classfy.Presenter.ListPresenter;
import com.example.administrator.a1512qmvp.ui.classfy.adapter.XrvListAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity<ListPresenter> implements ListContract.View {
    private XRecyclerView mXrv;
    private int pscid;
    private boolean isRefresh = true;
    private XrvListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取pscid
        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 0);
        initView();
        mPresenter.getProducts(pscid + "");
    }
    private void initView() {
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mXrv.setLayoutManager(linearLayoutManager);

        //设置刷新和加载更多监听
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                isRefresh = true;
                mPresenter.getProducts(pscid + "");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                isRefresh = false;
                mPresenter.getProducts(pscid + "");
            }
        });

    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void onSuccess(List<ProductsBean.DataBean> list) {
        final List<ProductsBean.DataBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        //创建适配器
        if (isRefresh) {
            adapter = new XrvListAdapter(this, list);
            mXrv.setAdapter(adapter);
            adapter.refresh(tempList);
            mXrv.refreshComplete();//设置刷新完成
        } else {
            if (adapter != null) {
                //判断适配器是否创建过
                adapter.loadMore(tempList);
                mXrv.loadMoreComplete();//设置加载更多完成
            }
        }
        if  (adapter == null) {
                return;
        }
        /*adapter.setOnListItemClickListener(new XrvListAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(ProductsBean.DataBean dataBean) {
                Intent intent = new Intent(ListActivity.this, ListDetailsActivity.class);
                intent.putExtra("bean", dataBean);
                startActivity(intent);
            }
        });*/

    }
}
