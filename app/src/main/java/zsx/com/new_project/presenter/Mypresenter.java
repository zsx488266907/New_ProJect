package zsx.com.new_project.presenter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zsx.com.new_project.api.Api;
import zsx.com.new_project.bean.ProBean;
import zsx.com.new_project.bean.ProductBean;
import zsx.com.new_project.callback.RequestCallBack;
import zsx.com.new_project.contart.MyContract;
import zsx.com.new_project.model.MyModel;

public class Mypresenter extends MyContract.MyPresenter {
    private MyModel myModel;
    private MyContract.MyView myView;
    ProBean proBean;


    public Mypresenter(MyContract.MyView myView) {
        myModel = new MyModel();
        this.myView = myView;
    }



    @Override
    public void ProductList(HashMap<String, String> params) {

        myModel.ProductList(params, Api.USER_PRODUCT, new RequestCallBack() {


            @Override
            public void onfair(String msg) {

                myView.onFile(msg);
            }

            @Override
            public void onsucc(Object msg) {

            }

            @Override
            public void onsuccess(String msg) {
            ProductBean productBean = new Gson().fromJson(msg, ProductBean.class);
                List<ProductBean.ProductItemBean> list = new ArrayList<>();
                list.add(productBean.result.rxxp);
                list.add(productBean.result.mlss);
                list.add(productBean.result.pzsh);

                myView.onSuccessBean(list);
            }


        });
    }



    @Override
    public void pro(HashMap<String, String> map, String str) {
        myModel.Pro(map, str, new RequestCallBack() {
            @Override
            public void onfair(String msg) {
                myView.onFile(msg);
            }

            @Override
            public void onsucc(Object msg) {
                myView.onSucc(proBean);
            }

            @Override
            public void onsuccess(String msg) {

            }
        });
    }

    @Override
    public void detail(HashMap<String, String> map, String str) {
               myModel.detail(map, str, new RequestCallBack() {
                   @Override
                   public void onfair(String msg) {
                       myView.onFile(msg);
                   }

                   @Override
                   public void onsucc(Object msg) {
                         myView.onSucc(msg);
                   }

                   @Override
                   public void onsuccess(String msg) {

                   }
               });
    }

    @Override
    public void shoppingcart(HashMap<String, String> map, String str) {
        myModel.shoppingcart(map, str, new RequestCallBack() {
            @Override
            public void onfair(String msg) {
                myView.onFile(msg);
            }

            @Override
            public void onsucc(Object msg) {
                myView.onSucc(msg);
            }

            @Override
            public void onsuccess(String msg) {

            }
        });
    }

    @Override
    public void shopcarshow(String str) {
        myModel.shopcarshow(str, new RequestCallBack() {
            @Override
            public void onfair(String msg) {
                myView.onFile(msg);
            }

            @Override
            public void onsucc(Object msg) {
                myView.onSucc(msg);
            }

            @Override
            public void onsuccess(String msg) {

            }
        });
    }

    public void destroy(){
            if (myView!=null){
                myView = null;
            }

        }
}
