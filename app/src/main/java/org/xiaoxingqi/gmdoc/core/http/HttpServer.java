package org.xiaoxingqi.gmdoc.core.http;

import android.content.Context;

import com.google.gson.GsonBuilder;

import org.xiaoxingqi.gmdoc.impl.ApiServer;
import org.xiaoxingqi.gmdoc.impl.IConstant;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpServer {

    private static HttpServer sHttpServer;
    private Context mContext;
    private final ApiServer mApiServer;

    public static HttpServer getInstance(Context context) {
        synchronized (HttpServer.class) {
            if (sHttpServer == null) {
                synchronized (HttpServer.class) {
                    sHttpServer = new HttpServer(context);
                }
            }
        }
        return sHttpServer;
    }

    private HttpServer(Context context) {
        mContext = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IConstant.SPORT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava   RxJava2CallAdapterFactory 会一直报错, 不明白????
                .client(getUnsafeOkHttpClient())
                .build();
        mApiServer = retrofit.create(ApiServer.class);
    }

    /**
     * 配置https 的请求协议安全
     *
     * @return
     */
    public OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
                    .addInterceptor(new ReciveIntercept(mContext))
                    .addInterceptor(new RequestHeadInterceptot(mContext))
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取网络服务框架的基础服务
     *
     * @return
     */
    public ApiServer getApiServer() {
        return mApiServer;
    }

}
