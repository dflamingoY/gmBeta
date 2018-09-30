package org.xiaoxingqi.gmdoc.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/9/6.
 * <p/>
 */
public class SPUtils {
    private final static String SP_NAME = "GMDOC";
    private static SharedPreferences sp;

    private static SharedPreferences getSp(Context context) {
        if (sp == null) {
            sp = context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp;
    }

    /**
     * get
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key, defValue);
    }

    /**
     * set a cache Data
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 设置一个int类型的参数
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取到int型 的数值
     *
     * @param context  上下文
     * @param key      名称
     * @param defValue 默认参数
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getInt(key, defValue);
    }

    /**
     * 存储 String类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取String类型的数据
     *
     * @param context
     * @param key
     * @param defauValue
     * @return
     */
    public static String getString(Context context, String key, String defauValue) {
        SharedPreferences sp = getSp(context);
        return sp.getString(key, defauValue);
    }


}
