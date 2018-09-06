package org.xiaoxingqi.gmdoc.impl;


import org.xiaoxingqi.gmdoc.entity.TokenData;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiServer {

    @POST
    Observable<TokenData> post_token(@Url String url);

    @GET
    Observable<String> get_token(@Url String url);
}
