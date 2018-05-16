package com.example.asus.jingdong.ui.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.Details.ListDetailsActivity;
import com.example.asus.jingdong.ui.Search.contract.SearchContract;
import com.example.asus.jingdong.ui.Search.presenter.SearchPresenter;
import com.example.asus.jingdong.ui.SpLb.adapter.XrvListAdapter;
import com.example.asus.jingdong.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchTouActivity  extends BaseActivity<SearchPresenter> implements SearchContract.View  {

    private XRecyclerView mXrvv;
    private int pscid=1;
    private String keywords;
    private boolean isRefresh = true;
    private XrvListAdapter adapter;
    private LinearLayout sdsd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search_tou);
        initView();
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        mPresenter.getPresenter(keywords,pscid+"");

        //点击搜索
        sdsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SearchTouActivity.this, SearchActivity.class);
                startActivity(intent1);
            }
        });
    }
    private void initView() {
        mXrvv = (XRecyclerView) findViewById(R.id.xrvv);
        sdsd = (LinearLayout) findViewById(R.id.sdsd);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mXrvv.setLayoutManager(linearLayoutManager);
        //设置刷新和加载更多监听
        mXrvv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                isRefresh = true;
                mPresenter.getPresenter(keywords,pscid+"");
               // searchProductsPresentimp.getSearchProducts(keywords,pscid + "");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                isRefresh = false;
                mPresenter.getPresenter(keywords,pscid+"");
                //searchProductsPresentimp.getSearchProducts(keywords,pscid + "");
            }
        });
    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_search_tou;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getProductsSuccess(ProductsBean productsBean) {
        List<ProductsBean.DataBean> list = productsBean.getData();
        final List<ProductsBean.DataBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        //创建适配器
        if (isRefresh) {
            adapter = new XrvListAdapter(this, list);
            mXrvv.setAdapter(adapter);
            adapter.refresh(tempList);
            mXrvv.refreshComplete();//设置刷新完成
        } else {
            if (adapter != null) {
                //判断适配器是否创建过
                adapter.loadMore(tempList);
                mXrvv.loadMoreComplete();//设置加载更多完成
            }
        }
        if (adapter == null) {
            return;
        }
        adapter.setOnListItemClickListener(new XrvListAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(SearchTouActivity.this, ListDetailsActivity.class);
                intent.putExtra("pid",tempList.get(position).getPid());
                startActivity(intent);
            }


        });
    }
}
