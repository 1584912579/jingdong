package com.example.asus.jingdong.ui.Details;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.AddCartBean;
import com.example.asus.jingdong.bean.DatailsBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.Details.contract.DetailsContract;
import com.example.asus.jingdong.ui.Details.presenter.DetailsPresenter;
import com.example.asus.jingdong.ui.ShopCart.ShopCartActivity;
import com.example.asus.jingdong.ui.base.BaseActivity;
import com.example.asus.jingdong.ui.login.LoginActivity;
import com.example.asus.jingdong.utils.MyApp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;

public class ListDetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsContract.View {
    private ImageView mIvShare;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVipPrice;
    private TextView mTvShopCard;
    private TextView mTvAddCard;
    private DatailsBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_details);
        initView();
        //获取pid
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        //请求数据
        mPresenter.getPresenter(pid+"");
        //分享
        mIvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb umWeb = new UMWeb(data.getDetailUrl());
                new ShareAction(ListDetailsActivity.this).withMedia(umWeb).setDisplayList(SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE
                ).setCallback(shareListener).open();
            }
        });
        //添加购物车
        mTvAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
        //购物车
        mTvShopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到购物车
                Intent intent = new Intent(ListDetailsActivity.this, ShopCartActivity.class);
                startActivity(intent);
            }
        });
    }
    //添加购物车
    private void addCard() {
        //判断用户是否登录
        //判断的逻辑是，从SharedPreferences里取uid，如果能取到说明已经登录过，否则跳转到登录页面
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "-1");
        if ("-1".equals(uid)){
            //跳转到登陆页面表示没登录
            Intent intent = new Intent(ListDetailsActivity.this, LoginActivity.class);
            startActivity(intent);

        }else {
            //添加购物车
            String token = sharedPreferences.getString("token", "");
            int pid = data.getPid();
            mPresenter.getAddCartPresenter(uid,pid+"",token);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list_details;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }
    private void initView() {
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mBanner = (Banner) findViewById(R.id.banner);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvVipPrice = (TextView) findViewById(R.id.tvVipPrice);
        mTvShopCard = (TextView) findViewById(R.id.tvShopCard);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
    }
    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }
    @Override
    public void getDatailsSuccess(DatailsBean datailsBean) {
        data = datailsBean.getData();
        setData();
    }

    @Override
    public void getAddCartSuccess(AddCartBean addCartBean) {
        Toast.makeText(ListDetailsActivity.this, "加入成功", Toast.LENGTH_SHORT).show();
    }

    private void setData() {
        //判断传过来的bean不为空
        if (data == null) {
            return;
        }
        //设值图片加载起
        mBanner.setImageLoader(new MyApp());
        String[] split = data.getImages().split("\\|");
        //设置图片集合
        mBanner.setImages(Arrays.asList(split));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
        //添加值
        mTvTitle.setText(data.getTitle());
        //给原价加横线
        SpannableString spannableString = new SpannableString("原价:" + data.getSalenum());
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvPrice.setText(spannableString);

        mTvVipPrice.setText("原价：" + data.getPrice());
        //点击放大图
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(ListDetailsActivity.this, BannerDetailsActivity.class);
                intent.putExtra("bean", data);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ListDetailsActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ListDetailsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ListDetailsActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
