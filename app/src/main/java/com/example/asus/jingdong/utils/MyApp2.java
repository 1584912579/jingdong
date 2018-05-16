package com.example.asus.jingdong.utils;

import android.app.Application;

import com.dash.zxinglibrary.activity.ZXingLibrary;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by asus on 2018/5/10.
 */

public class MyApp2 extends Application {
    {    //改里面的值
        PlatformConfig.setWeixin("wx396ea2b17e2f8938", "e21c38fb0064a9631b05957f6bec73bd");
        PlatformConfig.setQQZone("1106788439", "qwQLs9n3pNWxqFM4");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);
        //友盟舒适化
        //53f11ecd275528ea5d6780aeaf51090d
        UMConfigure.init(this, "53f11ecd275528ea5d6780aeaf51090d"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
    }
}
