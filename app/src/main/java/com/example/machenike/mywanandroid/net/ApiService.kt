package com.example.machenike.mywanandroid.net


import com.example.machenike.mywanandroid.model.home.ArticleInfoModel
import com.example.machenike.mywanandroid.model.home.ArticlePageModel
import com.example.machenike.mywanandroid.model.home.BannerModel
import com.example.machenike.mywanandroid.model.me.ArticleCollect
import com.example.machenike.mywanandroid.model.me.PersonalScore
import com.example.machenike.mywanandroid.model.navigation.NavigationModel
import com.example.machenike.mywanandroid.model.project.ProjectNamesModel
import com.example.machenike.mywanandroid.model.project.ProjectPageModel
import com.example.machenike.mywanandroid.model.square.SquarePage
import com.example.machenike.mywanandroid.model.system.SystemModel
import com.example.machenike.mywanandroid.model.weather.NowModel
import com.example.machenike.mywanandroid.model.wechart.WeChartNames
import com.example.machenike.mywanandroid.model.wechart.WeChartPage
import com.example.machenike.mywanandroid.net.response.WanResponse
import com.example.machenike.mywanandroid.net.response.login.UserModel
import retrofit2.Response
import retrofit2.http.*

/**
created time：2019/12/12 9:30
created by：动感超人
Describe ：
 */
interface ApiService {
    /**
     * 我的
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): Response<WanResponse<UserModel>>

    @GET("/user/logout/json")
    suspend fun logOut() : WanResponse<Any>

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("username") userName: String, @Field("password") passWord: String, @Field("repassword") rePassWord: String): WanResponse<UserModel>

    @GET("/lg/coin/userinfo/json")
    suspend fun getPersonalScore():Response<WanResponse<PersonalScore>>

    /**
     * 首页
     */

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticlePage(@Path("page") page:Int):Response<WanResponse<ArticlePageModel>>

    @GET("/article/top/json")
    suspend fun getTopArticles():Response<WanResponse<List<ArticleInfoModel>>>

    @GET("/banner/json")
    suspend fun getBannerData():Response<WanResponse<List<BannerModel>>>

    /**
     * 项目
     */
    //最新项目
    @GET("/article/listproject/{page}/json")
    suspend fun getNewProjectInfo(@Path("page") page:Int):Response<WanResponse<ProjectPageModel>>

    @GET("/project/tree/json")
    suspend fun getProjectNames():Response<WanResponse<List<ProjectNamesModel>>>

    @GET("/project/list/{page}/json")
    suspend fun getProjectInfo(@Path("page") page:Int,@Query("cid") id:Int):Response<WanResponse<ProjectPageModel>>

    /**
     * 公众号
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWeChartNames():Response<WanResponse<List<WeChartNames>>>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getWeChartInfo(@Path("id") id:Int,@Path("page") page:Int):Response<WanResponse<WeChartPage>>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun findWeChartInfo(@Path("id") id:Int,@Path("page") page:Int
                                ,@Query("k")keyWord:String):Response<WanResponse<WeChartPage>>

    /**
     * 广场
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getSquareInfo(@Path("page") page:Int):Response<WanResponse<SquarePage>>

    /**
     * 体系
     */
    @GET("/tree/json")
    suspend fun getSystemTag():Response<WanResponse<List<SystemModel>>>

    /**
     * 导航
     */
    @GET("/navi/json")
    suspend fun getNavigationInfo():Response<WanResponse<List<NavigationModel>>>

    /**
     * 天气
     * 和风天气API
     */
    //现在的天气--免费
    @GET("now")
    suspend fun getNowFree(@Query("location")location:String,@Query("key")key:String= FREE_KEY):Response<NowModel>

    //3-10天 天气--免费
    @GET("forecast")
    suspend fun getForecastFree(@Query("location",encoded = true)location:String, @Query("key")key:String= FREE_KEY)

    //逐小时预报--免费
    @GET("hourly")
    suspend fun getHourlyFree(@Query("location")location:String,@Query("key")key:String= FREE_KEY)

    //生活指数
    @GET("lifestyle")
    suspend fun getLifestyleFree(@Query("location")location:String,@Query("key")key:String= FREE_KEY)


    /**
     * 收藏
     */

    //收藏列表
    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page:Int):Response<WanResponse<ArticleCollect>>

    //取消我的收藏里的文章
    @POST("/lg/uncollect/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id:Int,@Query("originId")originId:Int):Response<WanResponse<String>>

    //收藏文章
    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id")id:Int)

    //取消列表中收藏的文章
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelOriginCollectArticle(@Path("id") id: Int)


}