package org.xiaoxingqi.gmdoc.impl;


import org.xiaoxingqi.gmdoc.entity.BaseRespData;
import org.xiaoxingqi.gmdoc.entity.TokenData;
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData;
import org.xiaoxingqi.gmdoc.entity.game.GameListData;
import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData;
import org.xiaoxingqi.gmdoc.entity.game.GameScoreAllData;
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData;
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;
import org.xiaoxingqi.gmdoc.entity.login.LoginUserData;
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiServer {

    @POST
    Observable<TokenData> post_token(@Url String url);

    @GET
    Observable<String> get_token(@Url String url);

    @GET
    Observable<HomeActiveData> get_HomeActiv(@Url String url);

    @GET
    Observable<HomeGameData> get_HomeGame(@Url String url);

    @GET
    Observable<HomeUserShareData> get_HomeContrubite(@Url String url);

    @GET
    Observable<GameDetailsData> get_GameDetails(@Url String url);

    @GET
    Observable<GameListData> get_GameList(@Url String url);

    @GET
    Observable<HomeUserShareData> get_GameDynamic(@Url String url);

    @GET
    Observable<GamePlatformData> get_GamePlat(@Url String url);

    @GET
    Observable<GameListData> get_FragGameList(@Url String url);

    /**
     * 获取游戏列表的 长评短评列表
     *
     * @param map
     * @return
     */
    @POST
    Observable<GameScoreAllData> queryGameComment(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<UserInfoData> get_UserInfo(@Url String url);

    /**
     * 登录操作
     *
     * @param url
     * @param map
     * @return
     */
    @POST
    Observable<LoginUserData> post_login(@Url String url, @QueryMap Map<String, String> map);

    /**
     * 不要求返回值, 只看失败或者成功  基础post
     *
     * @param url
     * @param map
     * @return
     */
    @POST
    Observable<String> base_post(@Url String url, @QueryMap Map<String, String> map);

    /**
     * 不做json解析的get请求  拿到返回值才做解析
     *
     * @param url
     * @return
     */
    @GET
    Observable<String> base_get(@Url String url);


}
