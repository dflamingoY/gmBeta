package org.xiaoxingqi.gmdoc.entity;

/**
 * Created by yzm on 2017/11/17.
 */

public class QINiuRespData extends BaseRespData {


    /**
     * state : 200
     * token : mU361QYU6HoN-GEUfyEXirtlPz4XXlUkG2LG7XF5:C2UyJSmHTL8Ln9Uo2q4T1oxbOKA=:eyJzY29wZSI6ImdhbWVkb2MiLCJkZWFkbGluZSI6MTUxMDkwNDg4MH0=
     * url : http://cdn.gmdoc.com/
     */

    private String token;
    private String url;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
