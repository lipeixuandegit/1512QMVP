package com.example.administrator.a1512qmvp.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.ui.base.BaseFragment;
import com.example.administrator.a1512qmvp.ui.login.LoginActivity;
import com.example.administrator.a1512qmvp.utils.SharedPreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by Administrator on 2018/5/23.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private ImageView mMyUserIcon;
    /**
     * 登录/注册 >
     */
    private TextView mMyUserName;
    private LinearLayout mMyLinearLogin;
    private ImageView mMyXx;
    private RelativeLayout mLoginBackPic;
    private LinearLayout mMyOrderDfk;
    private LinearLayout mMyOrderDsh;
    private LinearLayout mMyOrderDpj;
    private LinearLayout mMyOrderTh;
    private LinearLayout mMyOrderAll;
    private RecyclerView mTuiJianRecycler;
    private ScrollView mFragmentMyScroll;
    private SmartRefreshLayout mSmartRefresh;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        String name = (String) SharedPreferencesUtils.getParam(getContext(), "name", "");
        String iconUrl = (String) SharedPreferencesUtils.getParam(getContext(), "iconUrl", "");
        String uid = (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
        if (!TextUtils.isEmpty(uid)) {
            //登录过
            mLoginBackPic.setBackgroundResource(R.drawable.normal_regbg);
        } else {
            //未登录
            mLoginBackPic.setBackgroundResource(R.drawable.reg_bg);
        }
        if (!TextUtils.isEmpty(iconUrl)) {
            Glide.with(getContext()).load(iconUrl).into(mMyUserIcon);
        }
        if (!TextUtils.isEmpty(name)) {
            mMyUserName.setText(name);
        }

    }

    @Override
    public void inject() {

    }

    @Override
    public void initView(View view) {

        mMyUserIcon = (ImageView) view.findViewById(R.id.my_user_icon);
        mMyUserName = (TextView) view.findViewById(R.id.my_user_name);
        mMyLinearLogin = (LinearLayout) view.findViewById(R.id.my_linear_login);
        mMyXx = (ImageView) view.findViewById(R.id.my_xx);
        mLoginBackPic = (RelativeLayout) view.findViewById(R.id.login_back_pic);
        mMyOrderDfk = (LinearLayout) view.findViewById(R.id.my_order_dfk);
        mMyOrderDsh = (LinearLayout) view.findViewById(R.id.my_order_dsh);
        mMyOrderDpj = (LinearLayout) view.findViewById(R.id.my_order_dpj);
        mMyOrderTh = (LinearLayout) view.findViewById(R.id.my_order_th);
        mMyOrderAll = (LinearLayout) view.findViewById(R.id.my_order_all);
        mTuiJianRecycler = (RecyclerView) view.findViewById(R.id.tui_jian_recycler);
        mFragmentMyScroll = (ScrollView) view.findViewById(R.id.fragment_my_scroll);
        mSmartRefresh = (SmartRefreshLayout) view.findViewById(R.id.smart_refresh);
        setListener();
    }

    private void setListener() {
        mMyLinearLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_linear_login:
                String uid = (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
                if (TextUtils.isEmpty(uid)) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), UserInfoActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
