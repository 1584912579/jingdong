package com.example.asus.jingdong.ui.OrderList.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.bean.GetOrdersBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.ui.OrderList.adapter.OrderAdapter;
import com.example.asus.jingdong.ui.OrderList.contract.OrdersContract;
import com.example.asus.jingdong.ui.OrderList.presenter.OrdersPresenter;
import com.example.asus.jingdong.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class FragmentWaitOrder extends BaseFragment<OrdersPresenter> implements OrdersContract.View {
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RelativeLayout relative_empty_order;
    //分页
    private int page = 1;
    private OrderAdapter adapter;
    private boolean isRefresh = true;//刷新状态
    private String uid;
    private String token;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_order_all_layout;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_order);
        smartRefreshLayout = view.findViewById(R.id.smart_refresh);
        relative_empty_order = view.findViewById(R.id.relative_empty_order);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        token = sharedPreferences.getString("token", "");
        //初始化Recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //设置适配器
        adapter = new OrderAdapter(getContext(),mPresenter);
        recyclerView.setAdapter(adapter);

        //请求接口获取订单
        mPresenter.getWaitOrders(uid, page + "",token);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                isRefresh = true;
                mPresenter.getWaitOrders(uid, page + "", token);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                isRefresh = false;
                mPresenter.getWaitOrders(uid, page + "", token);
            }
        });
    }

    @Override
    public void showOrders(List<GetOrdersBean.DataBean> list) {
        if (list == null || list.size() == 0) {
            relative_empty_order.setVisibility(View.VISIBLE);
            smartRefreshLayout.setVisibility(View.GONE);
            return;
        }
        smartRefreshLayout.setVisibility(View.VISIBLE);
        relative_empty_order.setVisibility(View.GONE);
        if (isRefresh) {
            if (adapter != null) {
                adapter.refresh(list);
                smartRefreshLayout.finishRefresh();
            }
        } else {
            if (adapter != null) {
                adapter.loadMore(list);
                smartRefreshLayout.finishLoadmore();
            }
            //判断当前列表的数据，是否大于等于总条目
            //   smartRefreshLayout.setLoadmoreFinished(true);
        }
    }

    @Override
    public void updateOrderSuccess(BaseBean baseBean) {

    }
}
