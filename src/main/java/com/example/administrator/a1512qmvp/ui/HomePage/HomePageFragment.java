package com.example.administrator.a1512qmvp.ui.HomePage;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import android.widget.ImageView;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.WebViewActivity;
import com.example.administrator.a1512qmvp.bean.AdBean;
import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.component.DaggerHttpComponent;
import com.example.administrator.a1512qmvp.module.HttpModule;
import com.example.administrator.a1512qmvp.ui.HomePage.Contract.HomePageContract;
import com.example.administrator.a1512qmvp.ui.HomePage.Persenter.HomePagePresenter;
import com.example.administrator.a1512qmvp.ui.HomePage.adapter.RvClassAdapter;
import com.example.administrator.a1512qmvp.ui.HomePage.adapter.RvRecommendAdapter;
import com.example.administrator.a1512qmvp.ui.HomePage.adapter.RvSecKillAdapter;
import com.example.administrator.a1512qmvp.ui.base.BaseFragment;
import com.example.administrator.a1512qmvp.utils.GlideImageLoader;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class HomePageFragment extends BaseFragment<HomePagePresenter> implements HomePageContract.View, View.OnClickListener {
    private Banner banner;
    private RecyclerView rvClass;
    private MarqueeView marqueeView;
    private RecyclerView rvSecKill;
    private RecyclerView rvRecommend;
    private ImageView ivZxing;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
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
        marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是孙福生。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. GitHub帐号：sfsheng0322");
        info.add("4. 新浪微博：孙福生微博");
        info.add("5. 个人博客：sunfusheng.com");
        info.add("6. 微信公众号：孙福生");
        marqueeView.startWithList(info);

        banner = (Banner) view.findViewById(R.id.banner);
        rvClass = view.findViewById(R.id.rvClass);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        rvClass.setLayoutManager(gridLayoutManager);

        rvSecKill = view.findViewById(R.id.rvSecKill);
        //设置布局管理器
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        rvSecKill.setLayoutManager(gridLayoutManager1);

        //设置布局管理器
        rvRecommend = view.findViewById(R.id.rvRecommend);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvRecommend.setLayoutManager(gridLayoutManager2);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //二维码
        ivZxing = view.findViewById(R.id.ivZxing);
        setListener();

        //请求数据
        initData();

    }

    private void setListener() {
        ivZxing.setOnClickListener(this);
    }

    /**
     * 请求数据
     */
    private void initData() {
        mPresenter.getAd();
        mPresenter.getCatagory();
    }

    @Override
    public void getAdSuccess(AdBean adBean) {
        final List<AdBean.DataBean> data = adBean.getData();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            images.add(data.get(i).getIcon());
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
         banner.start();
         banner.setOnBannerListener(new OnBannerListener() {
             @Override
             public void OnBannerClick(int position) {
                 String url = data.get(position).getUrl();
                 if (!TextUtils.isEmpty(url)){
                     Intent intent = new Intent(getContext(),WebViewActivity.class);
                     intent.putExtra("detailUrl",url);
                     startActivity(intent);
                 }
             }
         });


        RvSecKillAdapter rvSecKillAdapter = new RvSecKillAdapter(getActivity(), adBean.getMiaosha().getList());
        rvSecKill.setAdapter(rvSecKillAdapter);

        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(getActivity(), adBean.getTuijian().getList());
        rvRecommend.setAdapter(rvRecommendAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        List<CatagoryBean.DataBean> data = catagoryBean.getData();
        RvClassAdapter adapter = new RvClassAdapter(getActivity(), data);
        rvClass.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivZxing:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }
}
