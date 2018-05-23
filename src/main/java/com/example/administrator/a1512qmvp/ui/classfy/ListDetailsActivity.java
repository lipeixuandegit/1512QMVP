package com.example.administrator.a1512qmvp.ui.classfy;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a1512qmvp.MainActivity;
import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.bean.AdBean;
import com.example.administrator.a1512qmvp.bean.ProductsBean;
import com.example.administrator.a1512qmvp.component.DaggerHttpComponent;
import com.example.administrator.a1512qmvp.ui.base.BaseActivity;
import com.example.administrator.a1512qmvp.ui.classfy.Contract.AddCartContract;
import com.example.administrator.a1512qmvp.ui.classfy.Presenter.AddCartPresenter;
import com.example.administrator.a1512qmvp.ui.login.LoginActivity;
import com.example.administrator.a1512qmvp.ui.shopcart.ShopCartActivity;
import com.example.administrator.a1512qmvp.utils.GlideImageLoader;
import com.example.administrator.a1512qmvp.utils.SharedPreferencesUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;

public class ListDetailsActivity extends BaseActivity<AddCartPresenter> implements View.OnClickListener,AddCartContract.View {

    private int flag;
    private ProductsBean.DataBean bean;
    private String images;
    private AdBean.TuijianBean.ListBean listBean;
    private ImageView mIvShare;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVipPrice;
    /**
     * 购物车
     */
    private TextView mTvShopCard;
    /**
     * 加入购物车
     */
    private TextView mTvAddCard;
    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        initView();

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", -1);
        if (flag == -1) {
            return;
        }
        if (flag == ListActivity.LISTACTIVITY) {
            bean = (ProductsBean.DataBean) intent.getSerializableExtra("bean");

            images = bean.getImages();
        } else {
            listBean = (AdBean.TuijianBean.ListBean) intent.getSerializableExtra("bean");

            images = listBean.getImages();
        }
        setData();

    }

    private void setData() {
        int money=0;
        if (flag==ListActivity.LISTACTIVITY){
            money=bean.getSalenum();
        }else {
           money=listBean.getSalenum();
        }
        //给原价加横线
        SpannableString spannableString = new SpannableString("原价:" + money);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvPrice.setText(spannableString);

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(ListDetailsActivity.this, BannerDetailsActivity.class);
                intent.putExtra("imgs", images);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        mBanner.setImageLoader(new GlideImageLoader());
        String[] img=null;
        if (flag==ListActivity.LISTACTIVITY){

            img = bean.getImages().split("\\|");
            mTvTitle.setText(bean.getTitle());
            mTvVipPrice.setText("现价:"+bean.getPrice());
        }else {
            img = listBean.getImages().split("\\|");
            mTvTitle.setText(listBean.getTitle());
            mTvVipPrice.setText("现价:"+listBean.getPrice());

        }
        mBanner.setImages(Arrays.asList(img));
        mBanner.start();


    }

    private void initView() {
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvShare.setOnClickListener(this);
        mBanner = (Banner) findViewById(R.id.banner);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvVipPrice = (TextView) findViewById(R.id.tvVipPrice);
        mTvShopCard = (TextView) findViewById(R.id.tvShopCard);
        mTvShopCard.setOnClickListener(this);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
        mTvAddCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ivShare:
                UMWeb umWeb=null;
                if (flag==ListActivity.LISTACTIVITY){
                    umWeb= new UMWeb(bean.getDetailUrl());
                }else {
                    umWeb= new UMWeb(listBean.getDetailUrl());
                }
                //分享

                new ShareAction(ListDetailsActivity.this).withMedia(umWeb).setDisplayList(SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();


        break;
        case R.id.tvShopCard:
        //跳转到购物车
        Intent intent1 = new Intent(ListDetailsActivity.this, ShopCartActivity.class);
        startActivity(intent1);
        break;

        case R.id.tvAddCard:
        token = (String) SharedPreferencesUtils.getParam(ListDetailsActivity.this, "token", "");
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(ListDetailsActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            String uid = (String) SharedPreferencesUtils.getParam(ListDetailsActivity.this, "uid", "");
            int pid = 0;
            if (flag == ListActivity.LISTACTIVITY) {
                pid = bean.getPid();
            } else {
                pid = listBean.getPid();
            }
            mPresenter.addCart(uid, pid + "", "");

        }

        break;
    }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list_details;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                //.httpModule(new HttpModule())
                .build()
                .inject(this);

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void onsuccess(String str) {
        Toast.makeText(ListDetailsActivity.this, str, Toast.LENGTH_LONG).show();
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ListDetailsActivity.this,"成功   了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ListDetailsActivity.this,"失    败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ListDetailsActivity.this,"取消   了",Toast.LENGTH_LONG).show();

        }
    };
}
