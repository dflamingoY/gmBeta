package org.xiaoxingqi.gmdoc.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by DoctorKevin on 2017/6/3.
 */

public class PreferenceTools {
    /**
     * @param context
     * @param savingName
     * @param entity
     * @param isEcrypt   是否加密
     * @param <T>
     * @return
     */
    public static <T> boolean saveObj(Context context, String savingName, T entity) {
        try {
            SharedPreferences error = context.getSharedPreferences(savingName, 0);
            SharedPreferences.Editor editor = error.edit();
            Gson gson = new Gson();
            String objString = gson.toJson(entity);
            /*if (isEcrypt) {
                String name = (new com.addbean.autils.utils.DeviceUuidFactory(context)).getDeviceUuid().toString();
                String encryptedMsg = com.addbean.autils.utils.AESCrypt.encrypt(name, objString);
                editor.putString(entity.getClass().getName(), encryptedMsg);
            } else {
            }*/
            editor.putString(entity.getClass().getName(), objString);
            editor.commit();
            return true;
        } catch (Exception var11) {
            return false;
        }
    }


    /**
     * 获取数据
     *
     * @param context
     * @param savingName
     * @param classType
     * @param isEcrypt   是否加密
     * @param <T>
     * @return
     */
    public static <T> T getObj(Context context, String savingName, Class<T> classType) {

        T user = null;
        try {
            SharedPreferences error = context.getSharedPreferences(savingName, 0);
            String data = error.getString(classType.getName(), (String) null);
            if (!TextUtils.isEmpty(data)) {
               /* String name = (new com.addbean.autils.utils.DeviceUuidFactory(context)).getDeviceUuid().toString();
                if (isEcrypt) {
                    String gson = AESCrypt.decrypt(name, data);
                    Gson gson1 = new Gson();
                    user = gson1.fromJson(gson, classType);
                } else {
                }*/
                Gson gson2 = new Gson();
                user = gson2.fromJson(data, classType);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }
        return user;
    }

    public static void clear(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        sp.edit().clear().commit();
    }
}
