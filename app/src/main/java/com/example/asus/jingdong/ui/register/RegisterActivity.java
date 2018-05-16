package com.example.asus.jingdong.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.UserBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.base.BaseActivity;
import com.example.asus.jingdong.ui.register.Presenter.RegisterPresenter;
import com.example.asus.jingdong.ui.register.contract.RegisterContract;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {
    private EditText mMobile2;
    private EditText mPassword2;
    private Button mRegister2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_register);
        initView();

        mRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobile2.getText().toString();
                String password = mPassword2.getText().toString();
                mPresenter.Register(mobile,password);
            }
        });
    }
    private void initView() {
        mMobile2 = (EditText) findViewById(R.id.mobile2);
        mPassword2 = (EditText) findViewById(R.id.password2);
        mRegister2 = (Button) findViewById(R.id.register2);

    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void RegisterSuccess(UserBean userBean) {
        this.finish();
    }
}
