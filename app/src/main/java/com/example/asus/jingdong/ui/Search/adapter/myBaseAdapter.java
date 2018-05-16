package com.example.asus.jingdong.ui.Search.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.jingdong.R;

import java.util.List;

/**
 * Created by asus on 2018/5/14.
 */

public class myBaseAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public myBaseAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold=null;
        if(convertView==null){
            if(hold==null){
                hold=new ViewHold();
                convertView=View.inflate(context, R.layout.item,null);
                hold.tv = convertView.findViewById(R.id.tvv1);
            }
            convertView.setTag(hold);
        }else{
            hold = (ViewHold) convertView.getTag();
        }
        hold.tv.setText(list.get(position));
        return convertView;
    }
}
class ViewHold{
    TextView tv;
}

