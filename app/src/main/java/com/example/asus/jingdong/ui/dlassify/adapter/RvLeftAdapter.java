package com.example.asus.jingdong.ui.dlassify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.CatagoryBean;
import com.example.asus.jingdong.inter.OnItemClickListener;

import java.util.List;

/**
 * Created by asus on 2018/5/11.
 */

public class RvLeftAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    public RvLeftAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvleft_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CatagoryBean.DataBean dataBean = list.get(position);
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv.setText(dataBean.getName());

        //设置字体颜色
        if (dataBean.getChecked()){
            holder1.tv.setTextColor(Color.RED);
            holder1.tv.setBackgroundColor(Color.GRAY);
        }else {
            holder1.tv.setTextColor(Color.BLACK);
            holder1.tv.setBackgroundColor(Color.WHITE);
        }
        holder1.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    //选中后，改变字体颜色和背景色
    public void changeCheck(int position, boolean bool) {
        //先还原一下
        for (int i = 0;i<list.size();i++){
            list.get(i).setChecked(false);
        }
        CatagoryBean.DataBean dataBean = list.get(position);
        dataBean.setChecked(bool);
        //刷新界面
        notifyDataSetChanged();
    }
}
