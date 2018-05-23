package com.example.asus.jingdong.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.AdBean;
import com.example.asus.jingdong.bean.CatagoryBean;

import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.inter.OnItemClickListener;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.Details.ListDetailsActivity;
import com.example.asus.jingdong.ui.Search.SearchActivity;
import com.example.asus.jingdong.ui.WebViewActivity;
import com.example.asus.jingdong.ui.base.BaseFragment;
import com.example.asus.jingdong.ui.homepage.adapter.RvClassAdapter;
import com.example.asus.jingdong.ui.homepage.adapter.RvRecommendAdapter;
import com.example.asus.jingdong.ui.homepage.adapter.RvSecKillAdapter;
import com.example.asus.jingdong.ui.homepage.contract.HomPageContract;
import com.example.asus.jingdong.ui.homepage.presenter.HomePagePresenter;
import com.example.asus.jingdong.utils.MyApp;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/5/10.
 */

public class FragmentHomePage extends BaseFragment<HomePagePresenter> implements HomPageContract.View{

    private MarqueeView marqueeView;
    private Banner banner;
    private RecyclerView rvClass;
    private RecyclerView rvSecKill;
    private RecyclerView rvRecommend;
    private LinearLayout ivZxing;
    private LinearLayout sosuo;

    //加载布局
    @Override
    public int getContentLayout() {
        return R.layout.fragmenthomepage;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }
    //找控件
    @Override
    public void initView(View view) {
        marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是孙福生。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. GitHub帐号：sfsheng0322");
        info.add("4. 新浪微博：孙福生微博");
        info.add("5. 个人博客：sunfusheng.com");
        info.add("6. 微信公众号：孙福生");
        marqueeView.startWithList(info);

        banner = (Banner) view.findViewById(R.id.banner);
        rvClass = view.findViewById(R.id.rvClass);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        rvClass.setLayoutManager(gridLayoutManager);

        rvSecKill = view.findViewById(R.id.rvSecKill);
        //设置布局管理器
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        rvSecKill.setLayoutManager(gridLayoutManager1);

        //设置布局管理器
        rvRecommend = view.findViewById(R.id.rvRecommend);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvRecommend.setLayoutManager(gridLayoutManager2);

        //设置图片加载器
        banner.setImageLoader(new MyApp());

        //二维码
        ivZxing = view.findViewById(R.id.ivZxing);
        ivZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent,1);

            }
        });
        //点击搜索
        sosuo = view.findViewById(R.id.sosuo);
        sosuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        //请求数据
        initData();

    }
    /**
     * 请求数据
     */
    private void initData() {
        mPresenter.getAd();
        mPresenter.getCatagory();
    }

    @Override
    public void getAdSuccess(final AdBean adBean) {
        final List<AdBean.DataBean> data = adBean.getData();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            images.add(data.get(i).getIcon());
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String url = data.get(position).getUrl();
                if (!TextUtils.isEmpty(url)) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("detailUrl", url);
                    startActivity(intent);
                }
            }
        });

        RvSecKillAdapter rvSecKillAdapter = new RvSecKillAdapter(getActivity(), adBean.getMiaosha().getList());
        rvSecKill.setAdapter(rvSecKillAdapter);
        rvSecKillAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转显示详情
                //获取地址
                String detailUrl = adBean.getMiaosha().getList().get(position).getDetailUrl();
                Intent intent = new Intent(getContext(), ListDetailsActivity.class);
                //intent.putExtra("detailUrl", detailUrl);
                intent.putExtra("pid",adBean.getMiaosha().getList().get(position).getPid());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

        final List<AdBean.TuijianBean.ListBean> list = adBean.getTuijian().getList();
        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(getActivity(),list );
        rvRecommend.setAdapter(rvRecommendAdapter);
        rvRecommendAdapter.setonItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ListDetailsActivity.class);
                intent.putExtra("pid",list.get(position).getPid());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        RvClassAdapter adapter = new RvClassAdapter(getActivity(), data);
        rvClass.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if(bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){
                String string = bundle.getString(CodeUtils.RESULT_STRING);
                //拿到最终结果
                //Intent intent = new Intent(getContext(),WebViewActivity.class);

            }
        }
    }

}
