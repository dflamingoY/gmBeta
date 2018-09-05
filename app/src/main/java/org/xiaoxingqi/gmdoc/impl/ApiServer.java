package org.xiaoxingqi.gmdoc.impl;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiServer {

    @POST
    Call<ResponseBody> post(@Url String url);
}
