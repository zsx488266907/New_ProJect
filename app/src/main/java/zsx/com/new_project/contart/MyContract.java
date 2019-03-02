package zsx.com.new_project.contart;

import java.util.HashMap;
import java.util.List;

import zsx.com.new_project.bean.ProductBean;
import zsx.com.new_project.callback.RequestCallBack;

public interface MyContract {
    public abstract class MyPresenter {
        public abstract void ProductList(HashMap<String, String> params);

        //搜索
        public abstract void pro(HashMap<String, String> map, String str);

        //详情
        public abstract void detail(HashMap<String, String> map, String str);

        //加入购物车
        public abstract void shoppingcart(HashMap<String, String> map, String str);

        //显示购物车
        public abstract void shopcarshow(String str);
    }

    public interface MyModel {

        void ProductList(HashMap<String, String> params, String str, RequestCallBack callback);

        void Pro(HashMap<String, String> params, String str, RequestCallBack requestCallBack);

        void detail(HashMap<String, String> params, String str, RequestCallBack requestCallBack);

        void shoppingcart(HashMap<String, String> params, String str, RequestCallBack requestCallBack);

        void shopcarshow(String uri, RequestCallBack requestCallBack);
    }

    interface MyView {
        void onFile(String s);

        void onSuccess(String msg);

        void onSucc(Object msg);

        void onSuccessBean(List<ProductBean.ProductItemBean> list);
    }
}
