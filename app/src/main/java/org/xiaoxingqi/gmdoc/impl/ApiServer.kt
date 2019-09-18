package org.xiaoxingqi.gmdoc.impl

import org.xiaoxingqi.gmdoc.entity.TokenData
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData
import org.xiaoxingqi.gmdoc.entity.game.GameListData
import org.xiaoxingqi.gmdoc.entity.game.GamePlatformData
import org.xiaoxingqi.gmdoc.entity.game.GameScoreAllData
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData
import org.xiaoxingqi.gmdoc.entity.login.LoginUserData
import org.xiaoxingqi.gmdoc.entity.user.UserInfoData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Url
import rx.Observable

interface ApiServer {

    @POST
    fun postToken(@Url url: String): Observable<TokenData>

    @GET
    fun getToken(@Url url: String): Observable<String>

    @GET
    fun getHomeActive(@Url url: String): Observable<HomeActiveData>

    @GET
    fun getHomeGame(@Url url: String): Observable<HomeGameData>

    @GET
    fun getHomeContribute(@Url url: String): Observable<HomeUserShareData>

    @GET
    fun getGameDetails(@Url url: String): Observable<GameDetailsData>

    @GET
    fun getGameList(@Url url: String): Observable<GameListData>

    @GET
    fun getGameDynamic(@Url url: String): Observable<HomeUserShareData>

    @GET
    fun getGamePlat(@Url url: String): Observable<GamePlatformData>

    @GET
    fun getFragGameList(@Url url: String): Observable<GameListData>

    /**
     * 获取游戏列表的 长评短评列表
     *
     * @param map
     * @return
     */
    @POST
    fun queryGameComment(@Url url: String, @QueryMap map: Map<String, String>): Observable<GameScoreAllData>

    @GET
    fun getUserInfo(@Url url: String): Observable<UserInfoData>

    /**
     * 登录操作
     *
     * @param url
     * @param map
     * @return
     */
    @POST
    fun postLogin(@Url url: String, @QueryMap map: Map<String, String>): Observable<LoginUserData>

    /**
     * 不要求返回值, 只看失败或者成功  基础post
     *
     * @param url
     * @param map
     * @return
     */
    @POST
    fun basePost(@Url url: String, @QueryMap map: Map<String, String>): Observable<String>

    /**
     * 不做json解析的get请求  拿到返回值才做解析
     *
     * @param url
     * @return
     */
    @GET
    fun baseGet(@Url url: String): Observable<String>


}