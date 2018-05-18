package com.example.administrator.a1512qmvp.ui.classfy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.bean.ProductCatagoryBean;
import com.example.administrator.a1512qmvp.component.DaggerHttpComponent;
import com.example.administrator.a1512qmvp.inter.OnItemClickListener;
import com.example.administrator.a1512qmvp.module.HttpModule;
import com.example.administrator.a1512qmvp.ui.base.BaseFragment;
import com.example.administrator.a1512qmvp.ui.classfy.Contract.ClassfyContract;
import com.example.administrator.a1512qmvp.ui.classfy.Presenter.ClassfyPresenter;
import com.example.administrator.a1512qmvp.ui.classfy.adapter.ElvAdapter;
import com.example.administrator.a1512qmvp.ui.classfy.adapter.RvLeftAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */

public class ClasssfyFragment extends BaseFragment<ClassfyPresenter> implements ClassfyContract.View {
    private View view;
    private ImageView mIvZxing;
    private RecyclerView mRvLeft;
    private ImageView mIv;
    private ExpandableListView mElv;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_class;
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

        mIvZxing = (ImageView) view.findViewById(R.id.ivZxing);
        mRvLeft = (RecyclerView) view.findViewById(R.id.rvLeft);
        mIv = (ImageView) view.findViewById(R.id.iv);
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mIv.setBackgroundResource(R.drawable.timg);
        mPresenter.getCatagory();
    }

    @Override
    public void getCatagorySuccess(final CatagoryBean catagoryBean) {
        List<CatagoryBean.DataBean> data = catagoryBean.getData();
        mRvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvLeft.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        final RvLeftAdapter rvLeftAdapter = new RvLeftAdapter(getContext(),data);
        mRvLeft.setAdapter(rvLeftAdapter);
        int cid = data.get(0).getCid();
        mPresenter.getProductCatagory(cid+"");
        rvLeftAdapter.changeCheck(0,true);
        rvLeftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                rvLeftAdapter.changeCheck(position,true);
                mPresenter.getProductCatagory(catagoryBean.getData().get(position).getCid()+"");
            }

            @Override
            public void OnLongClick(int position) {

            }
        });
    }

    @Override
    public void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean) {
        List<String> groupList= new ArrayList<>();
        List<List<ProductCatagoryBean.DataBean.ListBean>> childList= new ArrayList<>();
        List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
        for (int i=0;i<data.size();i++){
            groupList.add(data.get(i).getName());
            List<ProductCatagoryBean.DataBean.ListBean> list = data.get(i).getList();
            childList.add(list);
        }
        ElvAdapter elvAdapter = new ElvAdapter(getContext(), groupList, childList);
        mElv.setAdapter(elvAdapter);
        for(int i=0;i<groupList.size();i++){

            mElv.expandGroup(i);
        }
        productCatagoryBean.getMsg();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.fragment_class, null);
    }


}
