package zsx.com.new_project.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zsx.com.new_project.R;
import zsx.com.new_project.bean.ProBean;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyVh> {
 Context context;
 List<ProBean.Results> list;

    public SearchAdapter(Context context) {
        this.context = context;
        this.list= new ArrayList<>();
    }
//添加数据
    public void setList(List<ProBean.Results> lists) {
        if (lists!=null){
            this.list = lists;
        }
       notifyDataSetChanged();
    }
    //追加数据
    public void addList(List<ProBean.Results> lists) {
        if (lists==null){
          list.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_item, viewGroup, false);
        MyVh myVh = new MyVh(view);
        return myVh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVh myVh, final int i) {
        ProBean.Results results = list.get(i);
         myVh.name.setText(results.commodityName);
         myVh.price.setText(results.price);


        String url = results.masterPic;
        if (url.equals("")||url==null){
            myVh.image.setImageResource(R.mipmap.ic_launcher);
        }else {
            Uri uri =  Uri.parse(url);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            myVh.image.setController(controller);
        }


        myVh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener!=null){
                    itemListener.onItemClickListener(list.get(i).commodityId);
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class MyVh extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SimpleDraweeView image;
        @BindView(R.id.name)
         TextView name;
        @BindView(R.id.price)
        TextView price;

        public MyVh(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
    }
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener{
        void onItemClickListener(String posi);


    }
}
