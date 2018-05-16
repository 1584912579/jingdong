package com.example.asus.jingdong.ui.Search;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.ui.Search.adapter.myBaseAdapter;
import com.example.asus.jingdong.utils.FlowLayout;
import com.example.asus.jingdong.utils.Mytitle;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private String mNames[] = {
            "平板","电脑","手机"
    };
    private List<String> list=new ArrayList<String>();
    private FlowLayout mFlowLayout;
    private ListView lv;
    private Mytitle mtl;
    private Button bt;
    private EditText et;
    private TextView mtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                lv.setAdapter(new myBaseAdapter(SearchActivity.this,list));
            }
        });
        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString()==null){

                }else {
                    Intent intent = new Intent(SearchActivity.this, SearchTouActivity.class);
                    intent.putExtra("keywords",et.getText().toString());
                    startActivity(intent);
                }
            }
        });
        initChildViews();
        jilu();
    }
    private void initView() {
        mFlowLayout = findViewById(R.id.flowlayout);
        lv = (ListView) findViewById(R.id.lv);
        mtl = (Mytitle) findViewById(R.id.mtt);
        bt = (Button) findViewById(R.id.bt);
        et = (EditText) findViewById(R.id.et);
        //textView
        mtv = (TextView) findViewById(R.id.textView);

    }
    private void initChildViews() {
        ViewGroup.MarginLayoutParams ip = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ip.leftMargin=5;
        ip.rightMargin = 5;
        ip.topMargin = 5;
        ip.bottomMargin = 5;
        for( int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.BLACK);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.line2));
            mFlowLayout.addView(view,ip);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(SearchActivity.this, SearchTouActivity.class);
                    intent.putExtra("keywords",mNames[finalI]);
                    startActivity(intent);
                    list.add(mNames[finalI]);
                    lv.setAdapter(new myBaseAdapter(SearchActivity.this,list));


                }
            });


        }
    }
    private void jilu() {
        mtl.setJiekou(new Mytitle.onsetHuida() {
            @Override
            public void huida(String aa) {
                Toast.makeText(SearchActivity.this, aa+"", Toast.LENGTH_SHORT).show();
                list.add(aa);
                lv.setAdapter(new myBaseAdapter(SearchActivity.this,list));
            }


        });

        lv.setAdapter(new myBaseAdapter(SearchActivity.this,list));
    }
}
