//package org.xiaoxingqi.gmdoc.impl;
//
//
//import org.xiaoxingqi.gmdoc.entity.TokenData;
//import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData;
//import org.xiaoxingqi.gmdoc.entity.game.GameListData;
//import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData;
//import org.xiaoxingqi.gmdoc.entity.game.GameScoreAllData;
//import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData;
//import org.xiaoxingqi.gmdoc.entity.home.HomeGameData;
//import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;
//import org.xiaoxingqi.gmdoc.entity.login.LoginUserData;
//import org.xiaoxingqi.gmdoc.entity.user.UserInfoData;
//
//import java.util.Map;
//
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//import retrofit2.http.QueryMap;
//import retrofit2.http.Url;
//import rx.Observable;
//
//public interface ApiServer {
//
//    @POST
//    Observable<TokenData> postToken(@Url String url);
//
//    @GET
//    Observable<String> getToken(@Url String url);
//
//    @GET
//    Observable<HomeActiveData> getHomeActive(@Url String url);
//
//    @GET
//    Observable<HomeGameData> getHomeGame(@Url String url);
//
//    @GET
//    Observable<HomeUserShareData> getHomeContribute(@Url String url);
//
//    @GET
//    Observable<GameDetailsData> getGameDetails(@Url String url);
//
//    @GET
//    Observable<GameListData> getGameList(@Url String url);
//
//    @GET
//    Observable<HomeUserShareData> getGameDynamic(@Url String url);
//
//    @GET
//    Observable<GamePlatformData> getGamePlat(@Url String url);
//
//    @GET
//    Observable<GameListData> getFragGameList(@Url String url);
//
//    /**
//     * 获取游戏列表的 长评短评列表
//     *
//     * @param map
//     * @return
//     */
//    @POST
//    Observable<GameScoreAllData> queryGameComment(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<UserInfoData> getUserInfo(@Url String url);
//
//    /**
//     * 登录操作
//     *
//     * @param url
//     * @param map
//     * @return
//     */
//    @POST
//    Observable<LoginUserData> postLogin(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 不要求返回值, 只看失败或者成功  基础post
//     *
//     * @param url
//     * @param map
//     * @return
//     */
//    @POST
//    Observable<String> basePost(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 不做json解析的get请求  拿到返回值才做解析
//     *
//     * @param url
//     * @return
//     */
//    @GET
//    Observable<String> baseGet(@Url String url);
//
//
//}
