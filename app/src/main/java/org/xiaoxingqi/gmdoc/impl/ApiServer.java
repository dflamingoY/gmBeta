package org.xiaoxingqi.gmdoc.impl;


import org.xiaoxingqi.gmdoc.entity.TokenData;
import org.xiaoxingqi.gmdoc.entity.game.GameDetailsData;
import org.xiaoxingqi.gmdoc.entity.game.GameListData;
import org.xiaoxingqi.gmdoc.entity.home.HomeActiveData;
import org.xiaoxingqi.gmdoc.entity.home.HomeGameData;
import org.xiaoxingqi.gmdoc.entity.home.HomeUserShareData;

import retrofit2.http.GET;
import retrofit2.http.POST;
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

}
