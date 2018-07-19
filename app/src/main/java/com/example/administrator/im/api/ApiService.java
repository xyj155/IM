package com.example.administrator.im.api;

import com.example.administrator.im.entity.BannerGson;
import com.example.administrator.im.gson.Active;
import com.example.administrator.im.gson.BaseGson;
import com.example.administrator.im.gson.IcoGson;
import com.example.administrator.im.gson.UserGson;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/25.
 */

public interface ApiService {
//
//    public static final String Base_URL = "http://182.254.147.87/";

    /**
     * 登陆
     * 参数  username:用户名
     * password：密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/IM/public/index.php/index/User/login")
    Observable<BaseGson<UserGson>> login(@Field("username") String username,
                                         @Field("password") String password);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param tel
     * @return
     */
    @FormUrlEncoded
    @POST("/IM/public/index.php/index/User/Register")
    Observable<BaseGson<UserGson>> register(@Field("username") String username,
                                            @Field("password") String password,
                                            @Field("tel") String tel);

    /**
     * 附近的人
     * @param location
     * @return
     */
    @FormUrlEncoded
    @POST("/IM/public/index.php/index/Useractive/AroundUserActive")
    Observable<BaseGson<List<UserGson<Active>>>> queryLocationActive(@Field("location") String location);

    /**
     * l轮播图
     * @return
     */
    @GET("/IM/public/index.php/index/Banner/BannerList")
    Observable<BaseGson<List<BannerGson>>> getBannerURLlist();


    /****
     * 设置主界面图标
     * @return
     */
    @GET("/IM/public/index.php/index/Ico/setMainPageIco")
    Observable<BaseGson<IcoGson>> setMainPageIco();


    @Multipart
    @POST("/IM/public/index.php/index/User/UserHead")
    Observable<ResponseBody> setUserHead(
            @Part("userid") int userid,
            @Part MultipartBody.Part files
    );



//    /**
//     * 添加marker
//     * @return
//     */
//    @GET("/Usage/public/index.php/index/Usage/getUsageList")
//    Observable<Usage> addMarkers();
//    /**
//     * 获取文字
//     * @return
//     */
//    @GET("/Usage/public/index.php/index/Usage/getUsageByLatin")
//    Observable<SingleUsage> getUsageByLatin(@Query("latitude") double latitude, @Query("longitude") double longitude);
//    @GET("{url}")
//    Observable<ResponseBody> executeGet(
//            @Path("url") String url,
//            @QueryMap Map<String, String> maps);
//
//
//    @POST("{url}")
//    Observable<ResponseBody> executePost(
//            @Path("url") String url,
//            @QueryMap Map<String, String> maps);
//
//    @Multipart
//    @POST("{url}")
//    Observable<ResponseBody> upLoadFile(
//            @Path("url") String url,
//            @Part("image\\\\"; filename=\\"image.jpg") RequestBody avatar);

//
//    @POST("{url}")
//    Observable<ResponseBody> uploadFiles(
//            @Path("url") String url,
//            @Path("headers") Map<String, String> headers,
//            @Part("filename") String description,
//            @PartMap()  Map<String, RequestBody> maps);
//
//    @Streaming
//    @GET
//    Observable<ResponseBody> downloadFile(@Url String fileUrl);

}