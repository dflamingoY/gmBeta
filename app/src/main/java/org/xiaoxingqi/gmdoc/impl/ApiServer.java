package org.xiaoxingqi.gmdoc.impl;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiServer {

    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap Map<String, String> maps);
}
