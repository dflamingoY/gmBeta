package org.xiaoxingqi.gmdoc.tools;

import org.greenrobot.eventbus.EventBus;
import org.xiaoxingqi.gmdoc.eventbus.SocketEvent;
import org.xiaoxingqi.gmdoc.impl.IConstant;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by yzm on 2017/12/5.
 */

public class SocketUtils {

    public static WebSocket initSocket() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url(IConstant.SOCKETSPORT + ":7272/")
                .build();
        WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                EventBus.getDefault().post(new SocketEvent(text));
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                /**
                 * 断线重连
                 */
                //                EventBus.getDefault().post();
            }
        });
        webSocket.request();
        return webSocket;
    }
}
