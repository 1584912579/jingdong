package com.example.asus.jingdong.inter;

import android.view.View;

/**
 * Created by asus on 2018/5/10.
 */

public interface IBase {
    int getContentLayout();

    void inject();

    void initView(View view);
}
