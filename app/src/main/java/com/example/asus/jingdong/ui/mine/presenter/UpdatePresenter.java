package com.example.asus.jingdong.ui.mine.presenter;

import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.net.UpdateHeaderApi;
import com.example.asus.jingdong.net.UpdateNickNameApi;
import com.example.asus.jingdong.ui.base.BasePresenter;
import com.example.asus.jingdong.ui.mine.contract.UpdateHeaderContract;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by asus on 2018/5/22.
 */

public class UpdatePresenter extends BasePresenter<UpdateHeaderContract.View> implements UpdateHeaderContract.Presenter  {
    private UpdateHeaderApi updateHeaderApi;
    private UpdateNickNameApi updateNickNameApi;
    @Inject
    public UpdatePresenter(UpdateHeaderApi updateHeaderApi,UpdateNickNameApi updateNickNameApi) {
        this.updateHeaderApi = updateHeaderApi;
        this.updateNickNameApi = updateNickNameApi;
    }

    @Override
    public void updateHeader(String uid, String filePath) {
        int i = filePath.lastIndexOf("/");
        String fileName = filePath.substring(i+1);
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), new File(filePath));

        MediaType textType = MediaType.parse("text/plain");
        RequestBody u = RequestBody.create(textType, uid);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", fileName, file);
        updateHeaderApi.updateHeader(u, f)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getCode();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.updateSuccess(s);
                }
            }
        });

    }

    @Override
    public void updateNickName(String uid, String nickname) {
        updateNickNameApi.getUpdateNickNameApi(uid,nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.updateNickNameSuccess(s);
                }
            }
        });

    }
}
