package com.example.asus.jingdong.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.UserBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.MainActivity;
import com.example.asus.jingdong.ui.base.BaseActivity;
import com.example.asus.jingdong.ui.login.contract.LoginContract;
import com.example.asus.jingdong.ui.login.presenter.LoginPresenter;
import com.example.asus.jingdong.ui.register.RegisterActivity;
import com.example.asus.jingdong.ui.showActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private RelativeLayout mLoginTitleRelative;
    /**
     * 请输入手机号
     */
    private EditText mEditPhone;
    /**
     * 请输入密码
     */
    private EditText mEditPwd;
    /**
     * 手机快速注册
     */
    private TextView mTextRegist;
    private ImageView mLoginByWechat;
    private ImageView mLoginByQq;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        initView();
        //登陆
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户名和密码
                String mobile = mEditPhone.getText().toString();
                String password = mEditPwd.getText().toString();
              mPresenter.login(mobile,password);
            }
        });
        //注册
        mTextRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //第三方微信登录
        mLoginByWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                //UMShareAPI.get(mContext).deleteOauth(Activity, Platform, authListener);
                //获取
                //mShareAPI.getPlatformInfo(UserinfoActivity.this, SHARE_MEDIA.SINA, umAuthListener);
            }
        });
        //第三方qq登录
        mLoginByQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UMShareAPI.get(LoginActivity.this)
//                        .getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
            }
        });
    }



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
    public void loginSuccess(UserBean userBean) {
        //保存用户信息到SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("uid", userBean.getData().getUid() + "");
        Log.i("uuuu",userBean.getData().getUid()+"");
        editor.putString("name", userBean.getData().getUsername() + "");
        editor.putString("iconUrl", userBean.getData().getIcon() + "");
        editor.putString("token", userBean.getData().getToken() + "");
        Log.i("uuuu",userBean.getData().getToken()+"");
        editor.commit();
        Intent intent = new Intent(this, showActivity.class);
        startActivity(intent);
        //关闭当前页面
        this.finish();
    }

    private void initView() {
        mLoginTitleRelative = (RelativeLayout) findViewById(R.id.login_title_relative);
        mEditPhone = (EditText) findViewById(R.id.edit_phone);
        mEditPwd = (EditText) findViewById(R.id.edit_pwd);
        mLogin = (Button) findViewById(R.id.login);
        mTextRegist = (TextView) findViewById(R.id.text_regist);
        mLoginByWechat = (ImageView) findViewById(R.id.login_by_wechat);
        mLoginByQq = (ImageView) findViewById(R.id.login_by_qq);

    }
}
