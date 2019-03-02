package zsx.com.new_project.bean;

import java.util.List;

public class ProductBean {


    public String message;
    public String status;
    public Product result;

    public static class Product{


        public ProductItemBean rxxp;
        public ProductItemBean pzsh;
        public ProductItemBean mlss;



    }

    public static class ProductItemBean {

        public String id;
        public String name;
        public List<ProductItem>  commodityList;

        public static class  ProductItem{
            public int commodityId;
            public String commodityName;
            public String masterPic;
            public String price;
            public String saleNum;
        }



    }


}
