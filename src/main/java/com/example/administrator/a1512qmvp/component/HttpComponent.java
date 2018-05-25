package com.example.administrator.a1512qmvp.component;

import com.example.administrator.a1512qmvp.module.HttpModule;
import com.example.administrator.a1512qmvp.ui.HomePage.HomePageFragment;
import com.example.administrator.a1512qmvp.ui.classfy.ClasssfyFragment;
import com.example.administrator.a1512qmvp.ui.classfy.Contract.ClassfyContract;
import com.example.administrator.a1512qmvp.ui.classfy.ListActivity;
import com.example.administrator.a1512qmvp.ui.classfy.ListDetailsActivity;
import com.example.administrator.a1512qmvp.ui.login.LoginActivity;
import com.example.administrator.a1512qmvp.ui.mine.MakeSureOrderActivity;
import com.example.administrator.a1512qmvp.ui.mine.MyFragment;
import com.example.administrator.a1512qmvp.ui.mine.UserInfoActivity;
import com.example.administrator.a1512qmvp.ui.shopcart.ShopCartActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/11.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {


    void inject (HomePageFragment homePageFragment);

    void inject(ClasssfyFragment classsfyFragment);

    void inject (ListActivity ListActivity);

    void inject(ListDetailsActivity listDetailsActivity);

    void inject(LoginActivity loginActivity);

    void inject(ShopCartActivity shopCartActivity);

    void inject(MakeSureOrderActivity makeSureOrderActivity);

    void inject(UserInfoActivity userInfoActivity);

    void inject(MyFragment myFragment);
}
