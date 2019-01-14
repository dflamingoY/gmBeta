package org.xiaoxingqi.gmdoc.core.http;


import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by yzm on 2017/11/17.
 * 七牛核心上传类
 */

public class QIniuUpLoadManager {
    private static Configuration config = new Configuration.Builder()
            .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
            .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
            .connectTimeout(10)           // 链接超时。默认10秒
            .useHttps(true)               // 是否使用https上传域名
            .responseTimeout(60)          // 服务器响应超时。默认60秒
            .recorder(null)           // recorder分片上传时，已上传片记录器。默认null
            //.recorder(null, null)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
            .zone(FixedZone.zone2)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
            .build();
    // 重用uploadManager。一般地，只需要创建一个uploadManager对象
    private static UploadManager uploadManager = new UploadManager(config);

    public static void loadImg(String file, String token, UpLoadListener listener) throws NullPointerException {
        String key = file.endsWith("gif") ? System.currentTimeMillis() + ".gif" : System.currentTimeMillis() + file.substring(file.lastIndexOf("."));
        uploadManager.put(new File(file), key, token, (key1, info, response) -> {
            if (info.isOK()) {
                try {
                    listener.upSuccess((String) response.get("key"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.fail();
                }
            } else {
                listener.fail();
            }
        }, null);
    }

    public static void cancelUp() {

    }
}
