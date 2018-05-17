package com.example.administrator.a1512qmvp.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.bean.UserBean;
import com.example.administrator.a1512qmvp.component.DaggerHttpComponent;
import com.example.administrator.a1512qmvp.module.HttpModule;
import com.example.administrator.a1512qmvp.ui.base.BaseActivity;
import com.example.administrator.a1512qmvp.ui.login.contract.LoginContract;
import com.example.administrator.a1512qmvp.ui.login.presenter.LoginPresenter;
import com.example.administrator.a1512qmvp.utils.SharedPreferencesUtils;


public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginContract.View {

    private EditText mMobile;
    private EditText mPassword;
    private Button mBtLogin;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mMobile = (EditText) findViewById(R.id.mobile);
        mPassword = (EditText) findViewById(R.id.password);
        mBtLogin = (Button) findViewById(R.id.btLogin);
        mBtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btLogin:
                //需要调用P层，去完成接口调用
                String mobile = mMobile.getText().toString();
                String password = mPassword.getText().toString();
                mPresenter.login(mobile, password);
                break;
        }
    }


    @Override
    public void loginSuccess(UserBean userBean) {
        Toast.makeText(LoginActivity.this, userBean.getMsg(), Toast.LENGTH_SHORT).show();
        SharedPreferencesUtils.setParam(LoginActivity.this,"uid",userBean.getData().getUid() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"name",userBean.getData().getUsername() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"iconUrl",userBean.getData().getIcon() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",userBean.getData().getToken() + "");
        LoginActivity.this.finish();
    }
}
