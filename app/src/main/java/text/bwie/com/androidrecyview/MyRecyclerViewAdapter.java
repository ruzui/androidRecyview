package text.bwie.com.androidrecyview;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import text.bwie.com.androidrecyview.bean.JavaBean;
import text.bwie.com.androidrecyview.listener.RecyclerViewOnItemClickListener;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    List<JavaBean> list;
    Context context;
    RecyclerViewOnItemClickListener onItemClickListener;
    public MyRecyclerViewAdapter(List<JavaBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        return  3;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jj, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getImages()).into(holder.img);
        holder.cb.setChecked(list.get(position).isCheck());
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isCheck()) {
                    list.get(position).setCheck(false);
                } else {
                    list.get(position).setCheck(true);
                }
            }
        });
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemLongClickListener(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final CheckBox cb;
        private final ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            cb = (CheckBox) itemView.findViewById(R.id.cb);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    //设置点击事件
    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}