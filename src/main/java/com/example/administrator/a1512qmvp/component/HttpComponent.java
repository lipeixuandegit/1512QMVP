package com.example.administrator.a1512qmvp.component;

import com.example.administrator.a1512qmvp.module.HttpModule;
import com.example.administrator.a1512qmvp.ui.HomePage.HomePageFragment;
import com.example.administrator.a1512qmvp.ui.classfy.ClasssfyFragment;
import com.example.administrator.a1512qmvp.ui.classfy.Contract.ClassfyContract;
import com.example.administrator.a1512qmvp.ui.classfy.ListActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/11.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {

    void inject (HomePageFragment homePageFragment);

    void inject(ClasssfyFragment classsfyFragment);

    void inject (ListActivity ListActivity);
}
