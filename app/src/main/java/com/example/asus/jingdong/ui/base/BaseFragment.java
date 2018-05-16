package com.example.asus.jingdong.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.jingdong.inter.IBase;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by asus on 2018/5/10.
 */

public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements IBase, BaseContract.BaseView {
    @Inject
    protected T mPresenter;
    private Unbinder bind;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        if (mPresenter != null) {
            mPresenter.attchView(this);
        }
    }
    //解绑
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (bind != null) {
            bind.unbind();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //布局加载器
        View view = inflater.inflate(getContentLayout(), null);
        bind = ButterKnife.bind(getActivity(), view);
        //找id
        initView(view);
        return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
