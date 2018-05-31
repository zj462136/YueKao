package com.example.lenovo.yuekao;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lenovo on 2018/4/29.
 */

public interface ApiService {
    //http://120.27.23.105/user/reg?mobile=15522222223&password=123456
    @GET("user/reg")
    Flowable<RegistBean> getRegist(@QueryMap Map<String, String> map);

    //http://120.27.23.105/user/login?mobile=15522222223&password=123456
    @GET("user/login")
    Flowable<LoginBean> getLogin(@QueryMap Map<String, String> map);

    //https://www.zhaoapi.cn/product/getProducts?pscid=39&page=1
    @GET("product/getProducts")
    Flowable<ProductsBean> getProduct(@QueryMap Map<String, String> map);

    //https://www.zhaoapi.cn/product/getProductDetail?pid=45&source=android
    @GET("product/getProductDetail")
    Flowable<ShowsBean> getShows(@QueryMap Map<String, String> map);

    //http://120.27.23.105/product/addCart?uid=101&pid=45&source=android
    @GET("product/addCart")
    Flowable<AddBean> getAdd(@QueryMap Map<String, String> map);
    // http://120.27.23.105/product/getCarts?uid=75&source=android
    @GET("product/getCarts")
    Flowable<CartsBean> getCarts(@QueryMap Map<String, String> map);
    //http://120.27.23.105/product/deleteCart?uid=101&pid=45&source=android
    @GET("product/deleteCart")
    Flowable<DeleteBean> getDelete(@QueryMap Map<String, String> map);

    //http://120.27.23.105/product/getOrders?uid=71
    @GET("product/getOrders")
    Flowable<DinganBean> getDingdan(@QueryMap Map<String, String> map);
}
