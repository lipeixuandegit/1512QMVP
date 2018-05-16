package com.example.administrator.a1512qmvp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.a1512qmvp.ui.HomePage.HomePageFragment;
import com.example.administrator.a1512qmvp.ui.base.BaseActivity;
import com.example.administrator.a1512qmvp.ui.classfy.ClasssfyFragment;

public class MainActivity extends BaseActivity {
    /*
    * 更新数据到GitHub
    *
    *
    * */
    private FrameLayout mFl;
    private RadioButton mRbHomepage;
    private RadioButton mRbClass;
    private RadioButton mRbFind;
    private RadioButton mRbShopCar;
    private RadioButton mRbMine;
    private RadioGroup mRg;
    private FrameLayout mFlContent;
    private int currentIndex=1;
    private FragmentManager fragmentManager;
    private HomePageFragment homePageFragment;
    private ClasssfyFragment classsfyFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
        * 111111111111111111111111111111111111
        * */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
        classsfyFragment = new ClasssfyFragment();
        homePageFragment = new HomePageFragment();
        fragmentManager.beginTransaction().replace(R.id.flContent, homePageFragment)
                .commit();
        mRbHomepage.setChecked(true);
        setLisenter();
    }

    private void setLisenter() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbHomepage:
                        if (currentIndex==1){
                            return;
                        }
                        currentIndex=1;
                        fragmentManager.beginTransaction().replace(R.id.flContent,homePageFragment).commit();
                        break;
                    case R.id.rbClass:
                        if (currentIndex==2){
                            return;
                        }
                        currentIndex=2;
                        fragmentManager.beginTransaction().replace(R.id.flContent,classsfyFragment).commit();
                        break;
                    case R.id.rbFind:
                        if (currentIndex==3){
                            return;
                        }
                        currentIndex=3;
                        //fragmentManager.beginTransaction().replace(R.id.flContent,classsfyFragment).commit();
                        break;
                    case R.id.rbShopCar:
                        if (currentIndex==4){
                            return;
                        }
                        currentIndex=4;
                        //fragmentManager.beginTransaction().replace(R.id.flContent,classsfyFragment).commit();
                        break;
                    case R.id.rbMine:
                        if (currentIndex==5){
                            return;
                        }
                        currentIndex=5;
                        //fragmentManager.beginTransaction().replace(R.id.flContent,classsfyFragment).commit();
                        break;

                }

            }
        });

    }

    private void initView() {
        mFl = (FrameLayout) findViewById(R.id.flContent);
        mRbHomepage = (RadioButton) findViewById(R.id.rbHomepage);
        mRbClass = (RadioButton) findViewById(R.id.rbClass);
        mRbFind = (RadioButton) findViewById(R.id.rbFind);
        mRbShopCar = (RadioButton) findViewById(R.id.rbShopCar);
        mRbMine = (RadioButton) findViewById(R.id.rbMine);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mFlContent = (FrameLayout) findViewById(R.id.flContent);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {

    }

    @Override
    public void initView(View view) {

    }
}
