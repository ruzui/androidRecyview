package text.bwie.com.androidrecyview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import text.bwie.com.androidrecyview.bean.JavaBean;
import text.bwie.com.androidrecyview.bean.bean;
import text.bwie.com.androidrecyview.listener.RecyclerViewOnItemClickListener;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener, View.OnClickListener {
    private List<JavaBean> list = new ArrayList<>();
    private RecyclerView recyclerview;
    private MyRecyclerViewAdapter adapter;
    private Button all;
    private Button opposite;
    private RecyclerView rv;
    private Button sure;
    private List<bean.DataBean> data;
    private String s;
    ArrayList<JavaBean> listSure = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        recyclerview = (RecyclerView) findViewById(R.id.rv);
        //创建布局管理器
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //设置动画
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        getdata();

        //设置适配器
        adapter = new MyRecyclerViewAdapter(list, MainActivity.this);
        recyclerview.setAdapter(adapter);
        adapter.setRecyclerViewOnItemClickListener(MainActivity.this);

    }

    private void getdata() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url("http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&amp;encode=2092d7eb33e8ea0a7a2113f2d9886c90&amp;category_id=17").build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                s = response.body().string();
                Gson gson = new Gson();
                bean bean = gson.fromJson(s, bean.class);
                data = bean.getData();
                for (text.bwie.com.androidrecyview.bean.bean.DataBean dataBean : bean.getData()) {
                    JavaBean javaBean = new JavaBean(dataBean.getGoods_name(), dataBean.getGoods_img(), false);
                    list.add(javaBean);
                }
            }
        });
    }

    private void initView() {
        all = (Button) findViewById(R.id.all);
        opposite = (Button) findViewById(R.id.opposite);
        rv = (RecyclerView) findViewById(R.id.rv);
        sure = (Button) findViewById(R.id.sure);
        all.setOnClickListener(this);
        opposite.setOnClickListener(this);
        sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all:
                for (JavaBean jb : list) {
                    jb.setCheck(true);

                }
                break;
            case R.id.opposite:
                for (JavaBean jb : list) {
                    if (jb.isCheck()) {
                        jb.setCheck(false);
                    } else {
                        jb.setCheck(true);
                    }
                }
            case R.id.sure:
                for (JavaBean jb : list) {
                    if (jb.isCheck()) {
                        listSure.add(jb);
                    }
                }
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("listSure",listSure);
                intent.putExtras(bundle);
                startActivity(intent);
                listSure.clear();
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClickListener(View view, int position) {
        Toast.makeText(MainActivity.this, "长按", Toast.LENGTH_SHORT).show();
        return false;
    }
}
