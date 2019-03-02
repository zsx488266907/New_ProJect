package zsx.com.new_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import zsx.com.new_project.R;
import zsx.com.new_project.bean.ProductBean;

public class ProductAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;
    private final int TYPE_THREE = 2;
    private final int TYPE_FOUR = 3;
    private Context context;

    private List<String> list3;
    private List<ProductBean.ProductItemBean>  list;

    private ProductBean.ProductItemBean productItemBean;

    public ProductAdapter(Context context, List<ProductBean.ProductItemBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<ProductBean.ProductItemBean> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
       /* if(getItemViewType(i)==TYPE_ONE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemzero_layout,viewGroup,false);

            ZeroViewVH zeroViewVH = new ZeroViewVH(view);
            return  zeroViewVH;
        }

       else*/  if (getItemViewType(i)==TYPE_ONE){

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemone_layout,viewGroup,false);

            OneViewVH oneViewVH = new OneViewVH(view);

            return oneViewVH;

            }else  if (getItemViewType(i)==TYPE_TWO){

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemtwo_layout,viewGroup,false);

            TwoViewVH twoViewVH = new TwoViewVH(view);

            return twoViewVH;

        }else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemthree_layout,viewGroup,false);

           ThreeViewVH threeViewVH = new ThreeViewVH(view);
           return  threeViewVH;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

       /* if (viewHolder instanceof  ZeroViewVH){
            list3 = new ArrayList<>();
            list3.add("http://172.17.8.100/images/small/banner/cj.png");
            list3.add("http://172.17.8.100/images/small/banner/hzp.png");
            list3.add("http://172.17.8.100/images/small/banner/px.png");
            list3.add("http://172.17.8.100/images/small/banner/wy.png");
            ((ZeroViewVH) viewHolder).flyBanner.setImagesUrl(list3);

        }else*/ if (viewHolder instanceof  OneViewVH){

        ((OneViewVH) viewHolder).text.setText(list.get(i).name);
        ((OneViewVH) viewHolder).recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
         ((OneViewVH) viewHolder).recyclerView.setAdapter(new ItemOneAdapter(context,list.get(i).commodityList));

    }else if (viewHolder instanceof  TwoViewVH){
         ((TwoViewVH) viewHolder).text.setText(list.get(i).name);
        ((TwoViewVH) viewHolder).recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        ((TwoViewVH) viewHolder).recyclerView.setAdapter(new ItemTwoAdapter(context,list.get(i).commodityList));
    }else  if (viewHolder instanceof  ThreeViewVH) {
        ((ThreeViewVH) viewHolder).text.setText(list.get(i).name);
        ((ThreeViewVH) viewHolder).recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        ((ThreeViewVH) viewHolder).recyclerView.setAdapter(new ItemThreeAdapter(context,list.get(i).commodityList));
    }

       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (itemListener!=null){
                   /////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               itemListener.onItemClickListener(list);

               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getItemViewType(int position) {
        productItemBean = list.get(position);
        if ("1002".equals(productItemBean.id)) {
            return TYPE_ONE;
        } else if ("1003".equals(productItemBean.id)) {
            return TYPE_TWO;
        } else {
            return TYPE_THREE;
        }
    }
    class OneViewVH extends RecyclerView.ViewHolder{
        private TextView text;
        RecyclerView recyclerView;
        public OneViewVH(@NonNull View itemView) {

            super(itemView);
            text = itemView.findViewById(R.id.text1);
            recyclerView = itemView.findViewById(R.id.rv);

        }
    }
    class TwoViewVH extends RecyclerView.ViewHolder{
        private TextView text;
        RecyclerView recyclerView;
        public TwoViewVH(@NonNull View itemView) {

            super(itemView);
            text = itemView.findViewById(R.id.text2);
            recyclerView= itemView.findViewById(R.id.rv);
        }
    }
    class ThreeViewVH extends RecyclerView.ViewHolder{
        private TextView text;
        RecyclerView recyclerView;
        public ThreeViewVH(@NonNull View itemView) {

            super(itemView);
            text = itemView.findViewById(R.id.text3);
            recyclerView= itemView.findViewById(R.id.rv);
        }
    }

   /* class ZeroViewVH extends RecyclerView.ViewHolder{
       // @BindView(R.id.fly_banner)
        FlyBanner flyBanner;
        public ZeroViewVH(@NonNull View itemView) {

            super(itemView);

           ............
        }
    }*/
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener{

        void onItemClickListener(List<ProductBean.ProductItemBean> posi);

    }

}
