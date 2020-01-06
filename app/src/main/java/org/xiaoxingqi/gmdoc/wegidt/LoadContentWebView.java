package org.xiaoxingqi.gmdoc.wegidt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class LoadContentWebView extends WebView {

    private static final String SETUP_HTML = "file:///android_asset/index.html";
    private boolean isReady = false;
    private static final String CALLBACK_SCHEME = "re-callback://";
    private static final String STATE_SCHEME = "re-state://";
    private AfterInitialLoadListener mLoadListener;

    public LoadContentWebView(Context context) {
        this(context, null);
    }

    public LoadContentWebView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public LoadContentWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new EditorWebViewClient());
        loadUrl(SETUP_HTML);
    }

    public void setNightStyle(boolean isNight) {
        exec("javascript:RE.callback(" + isNight + ")");
    }

    public void setHtml(String contents) {
        if (contents == null) {
            contents = "内容获取失败,请重新进入";
        }
        exec("javascript:RE.setHtml('" + contents + "');");
    }

    protected void exec(final String trigger) {
        if (isReady) {
            load(trigger);
        } else {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    exec(trigger);
                }
            }, 100);
        }
    }

    private void load(String trigger) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            evaluateJavascript(trigger, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    if (null != listener) {
                        listener.contentFinish(value);
                    }
                }
            });
        } else {
            loadUrl(trigger);
        }
    }

    public interface AfterInitialLoadListener {

        void onAfterInitialLoad(boolean isReady);
    }

    protected class EditorWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            isReady = url.equalsIgnoreCase(SETUP_HTML);
            if (mLoadListener != null) {
                mLoadListener.onAfterInitialLoad(isReady);
            }
            if (null != listener) {
                listener.onPageFinished(view, url);
            }

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String decode;
            try {
                decode = URLDecoder.decode(url, "UTF-8");
                //TODO 可以跳转要需要的界面
            } catch (UnsupportedEncodingException e) {
                // No handling
                return false;
            }
           /* if (TextUtils.indexOf(url, CALLBACK_SCHEME) == 0) {
                //                callback(decode);
                return true;
            } else if (TextUtils.indexOf(url, STATE_SCHEME) == 0) {
                //                stateCheck(decode);
                return true;
            }*/
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            try {
                String decodeUrl = URLDecoder.decode(request.getUrl().toString(), "UTF-8");
                //TODO 可以跳转到需要的步骤
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (listener != null) {
                listener.onReceivedError(view, request, error);
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            if (listener != null) {
                listener.onReceivedSslError(view, handler, error);
            }
        }
    }

    private onWebClientListener listener;

    public void setWebClientListener(onWebClientListener listener) {
        this.listener = listener;
    }

    public interface onWebClientListener {
        void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error);

        void onPageFinished(WebView view, String url);

        void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error);

        void contentFinish(String value);
    }

}
